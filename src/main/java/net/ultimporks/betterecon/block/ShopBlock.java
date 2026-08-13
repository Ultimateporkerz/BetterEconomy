package net.ultimporks.betterecon.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.ultimporks.betterecon.block.entity.ShopBlockEntity;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.init.ModBlockEntities;
import net.ultimporks.betterecon.network.ModMessages;
import net.ultimporks.betterecon.network.S2CMessageCurrencySymbol;
import net.ultimporks.betterecon.network.shop.S2CMessageItemAndPrice;
import net.ultimporks.betterecon.network.shop.S2CMessageSellPrice;
import net.ultimporks.betterecon.util.menu.ShopCustomerMenu;
import net.ultimporks.betterecon.util.menu.ShopOwnerMenu;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;

public class ShopBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final EnumMap<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        // Create shapes for each direction
        SHAPES.put(Direction.NORTH, Block.box(-8, 0, 0, 24, 28, 16));   // Rotated for NORTH
        SHAPES.put(Direction.EAST, Block.box(0, 0, -8, 16, 28, 24));  // Rotated for EAST
        SHAPES.put(Direction.SOUTH, Block.box(-8, 0, 0, 24, 28, 16)); // Rotated for SOUTH
        SHAPES.put(Direction.WEST, Block.box(0, 0, -8, 16, 28, 24));    // Rotated for WEST
    }

    public ShopBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getHorizontalDirection();
        return this.defaultBlockState().setValue(FACING, direction);
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.getOrDefault(state.getValue(FACING), SHAPES.get(Direction.NORTH));
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer instanceof Player player && !level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ShopBlockEntity shopBlock) {
                shopBlock.setOwner(player.getUUID());
                shopBlock.setOwnerName(player.getName().getString());
            }
        }
        super.setPlacedBy(level, pos, state, placer, stack);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof ShopBlockEntity) {
                ((ShopBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player) {
        BlockEntity shopBlockEntity = level.getBlockEntity(pos);
        if (shopBlockEntity instanceof ShopBlockEntity shopBlockEntity1) {
            return shopBlockEntity1.isOwner(player.getUUID());
        }
        return super.canHarvestBlock(state, level, pos, player);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShopBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return null;
        }
        if (type == ModBlockEntities.SHOP_BLOCK_BE.get()) {
            return (level1, blockPos, blockState, t) -> {
                if (t instanceof ShopBlockEntity shopBlockEntity) {
                    shopBlockEntity.tick(level, blockPos, blockState);
                }
            };
        }
        return null;
    }

    @Override
    public InteractionResult use(BlockState pState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (level.isClientSide) return InteractionResult.sidedSuccess(true);
        BlockEntity entity = level.getBlockEntity(pos);

        if (entity instanceof ShopBlockEntity blockEntity) {
            String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
            ModMessages.sendToPlayer(new S2CMessageCurrencySymbol(currencySymbol), (ServerPlayer) player);

            boolean isOwner = blockEntity.isOwner(player.getUUID());
            boolean canOpenMenu = blockEntity.slotHasItem();

            ItemStack itemForSale = blockEntity.getItemForSale();
            int price = blockEntity.getPrice();
            int stock = blockEntity.getStock();
            String ownerName = blockEntity.getOwnerName();

            if (isOwner) {
                ModMessages.sendToPlayer(new S2CMessageItemAndPrice(itemForSale, price, stock, true, ownerName), (ServerPlayer) player);
                if (!player.isCrouching()) {
                    openShopOwnerMenu(player, blockEntity, pos);
                } else {
                    if (canOpenMenu) {
                        openShopCustomerMenu(player, blockEntity, pos);
                    } else {
                        player.sendSystemMessage(Component.literal("You must finish setting up your shop!").withStyle(ChatFormatting.YELLOW));
                    }
                }
            } else {
                if (canOpenMenu) {
                    ModMessages.sendToPlayer(new S2CMessageItemAndPrice(itemForSale, price, stock, false, ownerName), (ServerPlayer) player);
                    openShopCustomerMenu(player, blockEntity, pos);
                } else {
                    player.sendSystemMessage(Component.literal("Shop is Closed! Please try again later!").withStyle(ChatFormatting.RED));
                }
            }
        }
        return InteractionResult.sidedSuccess(false);
    }

    private void openShopOwnerMenu(Player player, ShopBlockEntity shopBlockEntity, BlockPos pos) {
        NetworkHooks.openScreen(
                (ServerPlayer) player,
                new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("Shop Owner");
                    }

                    @Override
                    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
                        return new ShopOwnerMenu(pContainerId, pPlayerInventory, shopBlockEntity, shopBlockEntity.getData());
                    }
                },
                buf -> buf.writeBlockPos(pos)
        );
    }

    private void openShopCustomerMenu(Player player, ShopBlockEntity shopBlockEntity, BlockPos pos) {
        NetworkHooks.openScreen(
                (ServerPlayer) player,
                new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("Shop Customer");
                    }

                    @Override
                    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
                        return new ShopCustomerMenu(pContainerId, pPlayerInventory, shopBlockEntity);
                    }
                },
                buf -> buf.writeBlockPos(pos)
        );
    }


}
