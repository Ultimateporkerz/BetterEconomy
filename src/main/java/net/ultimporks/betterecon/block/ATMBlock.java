package net.ultimporks.betterecon.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.network.PacketDistributor;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.currency.Balance;
import net.ultimporks.betterecon.init.ModAttachmentTypes;
import net.ultimporks.betterecon.network.S2CMessageCurrencySymbol;
import net.ultimporks.betterecon.network.atm.S2CMessageBalance;
import net.ultimporks.betterecon.util.menu.ATMMenu;
import org.jetbrains.annotations.Nullable;

public class ATMBlock extends Block {
    public static final MapCodec<ATMBlock> CODEC = simpleCodec(ATMBlock::new);
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 19, 16);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public MapCodec<? extends ATMBlock> codec() {
        return CODEC;
    }

    public ATMBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(level, pos));
            // Update Client with current balance
            Balance balance = player.getData(ModAttachmentTypes.BALANCE.get());
            String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
            PacketDistributor.sendToPlayer((ServerPlayer) player, new S2CMessageCurrencySymbol(currencySymbol));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new S2CMessageBalance(balance.getBalance(), player.getName().getString()));
            return InteractionResult.CONSUME;
        }
    }

    @Override
    protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (windowId, playerInventory, player) -> new ATMMenu(windowId, playerInventory, pos),
                Component.translatable("container.banking")
        );
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
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getHorizontalDirection();
        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
