package net.ultimporks.betterecon.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
import net.neoforged.neoforge.network.PacketDistributor;
import net.ultimporks.betterecon.block.entity.ShopBlockEntity;
import net.ultimporks.betterecon.init.ModBlockEntities;
import net.ultimporks.betterecon.network.shop.S2CMessageSellPrice;
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
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
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
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
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
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) return InteractionResult.sidedSuccess(true);
        BlockEntity entity = level.getBlockEntity(pos);

        if (entity instanceof ShopBlockEntity blockEntity) {
            player.openMenu(new SimpleMenuProvider(blockEntity, Component.literal("Shop")), pos);
            PacketDistributor.sendToPlayer((ServerPlayer) player, new S2CMessageSellPrice(blockEntity.getPrice()));
        } else {
            throw new IllegalStateException("Container Provider is missing!");
        }
        return InteractionResult.sidedSuccess(false);
    }
}
