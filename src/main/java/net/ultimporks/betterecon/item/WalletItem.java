package net.ultimporks.betterecon.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.ultimporks.betterecon.util.menu.WalletMenu;
import org.jetbrains.annotations.Nullable;

public class WalletItem extends Item {

    public WalletItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (level.isClientSide) return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), true);

        ItemStack stack = player.getItemInHand(usedHand);
        NetworkHooks.openScreen(
                (ServerPlayer) player,
                new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("Wallet");
                    }

                    @Override
                    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
                        return new WalletMenu(pContainerId, pPlayerInventory, stack);
                    }
                },buf -> buf.writeInt(usedHand.ordinal()));
        return InteractionResultHolder.sidedSuccess(stack, false);
    }
}
