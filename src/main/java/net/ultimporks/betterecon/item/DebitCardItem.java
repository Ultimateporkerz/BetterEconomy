package net.ultimporks.betterecon.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.currency.BalanceCapability;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class DebitCardItem extends Item {
    public DebitCardItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (level.isClientSide) return InteractionResultHolder.sidedSuccess(stack, true);

        player.getCapability(BalanceCapability.BALANCE_CAPABILITY).ifPresent(balance -> {
            int intBalance = balance.getBalance();
            String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
            player.displayClientMessage(Component.literal("Account Balance: " + currencySymbol + intBalance).withStyle(ChatFormatting.GREEN), true);
        });
        return InteractionResultHolder.sidedSuccess(stack, false);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, pLevel, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("item.betterecon.debit_card.balance.info").withStyle(ChatFormatting.LIGHT_PURPLE));

        // Check if Shift is being held down
        boolean isShiftHeld = false;
        if (pLevel != null && pLevel.isClientSide) {
            long windowHandle = Minecraft.getInstance().getWindow().getWindow();
            isShiftHeld = GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS ||
                    GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
        }

        if (!isShiftHeld) {
            tooltipComponents.add(Component.translatable("item.betterecon.debit_card.info"));
        } else {
            tooltipComponents.add(Component.translatable("item.betterecon.debit_card.info.message").withStyle(ChatFormatting.YELLOW));
        }
    }
}
