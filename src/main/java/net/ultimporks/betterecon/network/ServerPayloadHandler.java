package net.ultimporks.betterecon.network;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.ultimporks.betterecon.BetterEconomy;
import net.ultimporks.betterecon.block.entity.ShopBlockEntity;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.currency.Balance;
import net.ultimporks.betterecon.currency.Currency;
import net.ultimporks.betterecon.init.ModAttachmentTypes;
import net.ultimporks.betterecon.init.ModItems;
import net.ultimporks.betterecon.network.atm.C2SMessageDeposit;
import net.ultimporks.betterecon.network.atm.C2SMessageWithdraw;
import net.ultimporks.betterecon.network.shop.C2SMessagePurchase;
import net.ultimporks.betterecon.network.shop.C2SMessageSaveSellPrice;

import java.util.Map;

public class ServerPayloadHandler {

    // ATM Methods
    public static void handleDepositMessage(final C2SMessageDeposit data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
            Balance balance = player.getData(ModAttachmentTypes.BALANCE.get());
            int amountToDeposit = data.amount();
            if (amountToDeposit <= 0) return;

            Map<Item, Integer> dollarDenominations = Map.of(
                    ModItems.ONE_HUNDRED_DOLLAR_BILL.get(), 100,
                    ModItems.FIFTY_DOLLAR_BILL.get(), 50,
                    ModItems.TWENTY_DOLLAR_BILL.get(), 20,
                    ModItems.TEN_DOLLAR_BILL.get(), 10,
                    ModItems.FIVE_DOLLAR_BILL.get(), 5,
                    ModItems.ONE_DOLLAR_BILL.get(), 1
            );

            int removed = Currency.removeCurrency(player, dollarDenominations, amountToDeposit);

            balance.addBalance(amountToDeposit);
            player.closeContainer();

            // Handle Change
            if (removed >= amountToDeposit) {
                int change = removed - amountToDeposit;
                if (change > 0) {
                    Currency.makeChange(player, dollarDenominations, change);
                    player.sendSystemMessage(Component.literal("Deposited " + currencySymbol + amountToDeposit + " and gave you " + currencySymbol + change + " back!").withStyle(ChatFormatting.GREEN));
                    return;
                }
            }
            player.sendSystemMessage(Component.literal("Deposited " + currencySymbol + amountToDeposit + "!").withStyle(ChatFormatting.GREEN));
        });
    }

    public static void handleWithdrawMessage(final C2SMessageWithdraw data, final IPayloadContext context) {
        int amountToRemove = data.amount();
        Player player = context.player();
        String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
        Balance balance = player.getData(ModAttachmentTypes.BALANCE.get());

        if (amountToRemove <= 0 || balance.getBalance() < amountToRemove) {
            player.sendSystemMessage(Component.literal("You do not have " + currencySymbol + amountToRemove + " in your account!")
                    .withStyle(ChatFormatting.RED));
            return;
        }

        Map<Item, Integer> denominations = Map.of(
                ModItems.ONE_HUNDRED_DOLLAR_BILL.get(), 100,
                ModItems.FIFTY_DOLLAR_BILL.get(), 50,
                ModItems.TWENTY_DOLLAR_BILL.get(), 20,
                ModItems.TEN_DOLLAR_BILL.get(), 10,
                ModItems.FIVE_DOLLAR_BILL.get(), 5,
                ModItems.ONE_DOLLAR_BILL.get(), 1
        );

        balance.subtractBalance(amountToRemove);
        Currency.giveCurrency(player, amountToRemove, denominations);

        player.closeContainer();
        player.sendSystemMessage(Component.literal("Withdrew " + currencySymbol + data.amount() + " from your account!").withStyle(ChatFormatting.GREEN));
    }


    public static void handleSaveSellPrice(final C2SMessageSaveSellPrice data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
            BlockPos shopBlockPos = new BlockPos(data.xPos(), data.yPos(), data.zPos());
            BetterEconomy.LOGGING(shopBlockPos.toShortString());

            BlockEntity shopBlockEntity = player.level().getBlockEntity(shopBlockPos);

            if (shopBlockEntity instanceof ShopBlockEntity shopBlock) {
                shopBlock.setPrice(data.sellingPrice());
                player.closeContainer();
                player.sendSystemMessage(Component.literal("Selling price has been set to " + currencySymbol + data.sellingPrice() + "!").withStyle(ChatFormatting.GREEN));
            }
        });
    }

    // Player Shop methods
    public static void handlePurchaseMessage(final C2SMessagePurchase data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
            Player player = context.player();
            Level level = player.level();
            BlockPos shopBlockPos = data.shopBlockPos();
            ItemStack itemForSale = data.purchasedItem();
            int price = data.price();
            int quantity = data.amountPurchased();

            Map<Item, Integer> denominations = Map.of(
                    ModItems.ONE_HUNDRED_DOLLAR_BILL.get(), 100,
                    ModItems.FIFTY_DOLLAR_BILL.get(), 50,
                    ModItems.TWENTY_DOLLAR_BILL.get(), 20,
                    ModItems.TEN_DOLLAR_BILL.get(), 10,
                    ModItems.FIVE_DOLLAR_BILL.get(), 5,
                    ModItems.ONE_DOLLAR_BILL.get(), 1
            );

            if (level.getBlockEntity(shopBlockPos) instanceof ShopBlockEntity shopBlock) {
                BetterEconomy.LOGGING("Purchasing " + itemForSale.getDisplayName().getString() + " (" + quantity + ") price: " + price);

                // Make sure player can even afford it
                int cash = Currency.getCashAmount(player, denominations);

                if (cash < price) {
                    player.sendSystemMessage(Component.literal("Not enough cash to purchase " + itemForSale.getDisplayName().getString()).withStyle(ChatFormatting.RED));
                    return;
                }

                if (!shopBlock.transferCashToShop(price, denominations)) {
                    String shopOwnerName = shopBlock.getOwnerName();
                    player.sendSystemMessage(Component.literal(shopOwnerName + " needs to empty the register! Shop cannot hold anymore cash - transaction failed").withStyle(ChatFormatting.RED));
                    return;
                }

                shopBlock.removePurchasedItems(quantity);

                // Pay
                int removed = Currency.removeCurrency(player, denominations, price);
                // Give change if needed
                int change = removed - price;
                if (change > 0) {
                    Currency.makeChange(player, denominations, change);
                }

                ItemStack soldItem = itemForSale.copy();
                soldItem.setCount(quantity);

                boolean added = player.getInventory().add(soldItem);
                if (!added) {
                    player.drop(soldItem, false);
                }
                player.sendSystemMessage(Component.literal("Purchased " + itemForSale.getDisplayName().getString() + " for " + currencySymbol + price).withStyle(ChatFormatting.GREEN));
            } else {
                BetterEconomy.LOGGING("(ServerPayloadHandler) - INVALID SHOP! (WRONG BLOCK POS SENT IN PACKET??)");
            }
            player.closeContainer();
        });
    }
}
