package net.ultimporks.betterecon.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.currency.Balance;
import net.ultimporks.betterecon.init.ModAttachmentTypes;

import java.util.List;

public class DebitCardItem extends Item {
    public DebitCardItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (level.isClientSide) return InteractionResultHolder.sidedSuccess(stack, true);

        Balance balance = player.getData(ModAttachmentTypes.BALANCE.get());
        int intBalance = balance.getBalance();
        String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
        player.sendSystemMessage(Component.literal("Account Balance: " + currencySymbol + intBalance).withStyle(ChatFormatting.GREEN));

        return InteractionResultHolder.sidedSuccess(stack, false);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.literal("Right-Click to view account balance.").withStyle(ChatFormatting.LIGHT_PURPLE));
        if (!tooltipFlag.hasShiftDown()) {
            tooltipComponents.add(Component.literal("Hold 'Shift' for info.").withStyle(ChatFormatting.WHITE));
        } else {
            tooltipComponents.add(Component.literal("Shops will prioritize Cash over Debit Cards.").withStyle(ChatFormatting.YELLOW));
            tooltipComponents.add(Component.literal("Debit Card must be in inventory to be used").withStyle(ChatFormatting.YELLOW));
        }
    }
}
