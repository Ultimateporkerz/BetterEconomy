package net.ultimporks.betterecon.network;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.ultimporks.betterecon.BetterEconomy;
import net.ultimporks.betterecon.block.entity.ShopBlockEntity;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.currency.Balance;
import net.ultimporks.betterecon.currency.BalanceCapability;
import net.ultimporks.betterecon.currency.Currency;
import net.ultimporks.betterecon.init.ModEnchantments;
import net.ultimporks.betterecon.init.ModItems;
import net.ultimporks.betterecon.network.atm.C2SMessageDeposit;
import net.ultimporks.betterecon.network.atm.C2SMessageWithdraw;
import net.ultimporks.betterecon.network.shop.C2SMessagePurchase;
import net.ultimporks.betterecon.network.shop.C2SMessageSaveSellPrice;

import java.util.Map;
import java.util.function.Supplier;

public class ServerPayloadHandler {

    // ATM Methods
    public static void handleDepositMessage(final C2SMessageDeposit data, Supplier<NetworkEvent.Context> context) {
        Player player = context.get().getSender();
        if (player == null) return;
        String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
        player.getCapability(BalanceCapability.BALANCE_CAPABILITY).ifPresent(balance -> {
            int amountToDeposit = data.amount;
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
    public static void handleWithdrawMessage(final C2SMessageWithdraw data, Supplier<NetworkEvent.Context> context) {

        Player player = context.get().getSender();
        if (player == null) return;
        String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
        player.getCapability(BalanceCapability.BALANCE_CAPABILITY).ifPresent(balance -> {
            int amountToRemove = data.amount;

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
            player.sendSystemMessage(Component.literal("Withdrew " + currencySymbol + data.amount + " from your account!").withStyle(ChatFormatting.GREEN));
        });
    }
    public static void handleSaveSellPrice(final C2SMessageSaveSellPrice data, Supplier<NetworkEvent.Context> context) {
        Player player = context.get().getSender();
        if (player == null) return;
        String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
        BlockPos shopBlockPos = new BlockPos(data.xPos, data.yPos, data.zPos);
        BetterEconomy.LOGGING(shopBlockPos.toShortString());

        BlockEntity shopBlockEntity = player.level().getBlockEntity(shopBlockPos);

        if (shopBlockEntity instanceof ShopBlockEntity shopBlock) {
            shopBlock.setPrice(data.sellingPrice);
               player.closeContainer();
               player.sendSystemMessage(Component.literal("Selling price has been set to " + currencySymbol + data.sellingPrice + "!").withStyle(ChatFormatting.GREEN));
        }
    }

    // Player Shop methods
    public static void handlePurchaseMessage(final C2SMessagePurchase data, Supplier<NetworkEvent.Context> context) {
        Player player = context.get().getSender();
        if (player == null) return;
        Level level = player.level();
        BlockPos shopBlockPos = data.shopBlockPos;
        ItemStack itemForSale = data.purchasedItem;
        int price = data.price;
        int quantity = data.amountPurchased;
        int totalQuantity = quantity * data.amountForSale;

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

            // Make sure the shop inventory can supply the requested item (fixes duplication bug)
            int availableStock = 0;
            for (int i = 0; i < shopBlock.stockHandler.getSlots(); i++) {
                ItemStack stack = shopBlock.stockHandler.getStackInSlot(i);
                if (ItemStack.isSameItem(stack, itemForSale)) {
                    availableStock += stack.getCount();
                }
            }

            if (availableStock < totalQuantity) {
                player.displayClientMessage(Component.literal("Shop does not have the requested items in stock!"), false);
                player.closeContainer();
                return;
            }

            // Handle Cash Purchase if the player has enough Cash
            int cash = Currency.getCashAmount(player, denominations);

            if (cash >= price) {
                handleCashPurchase(player, price, totalQuantity, itemForSale, shopBlock, denominations);
                player.closeContainer();
                BetterEconomy.LOGGING("Player purchased items with Cash");
                return;
            }

            // Handle Debit Card purchase if the player failed the cash check
            player.getCapability(BalanceCapability.BALANCE_CAPABILITY).ifPresent(bankAccount -> {
                int balance = bankAccount.getBalance();

                if (Currency.isDebitCardInInventory(player) && balance >= price) {
                    handleDebitCardPurchase(player, price, totalQuantity, itemForSale, shopBlock, bankAccount, denominations);
                    player.closeContainer();
                    BetterEconomy.LOGGING("Player purchased items with their Debit Card");
                    return;
                }

                String currencyName = ModConfigs.COMMON.currencyName.get();
                player.sendSystemMessage(Component.literal("You do not have enough " + currencyName + " to buy " + itemForSale.getDisplayName().getString()));
                player.closeContainer();
            });
        }
    }


    // Class Helpers
    private static void handleCashPurchase(Player player, int price, int totalItemsSold, ItemStack itemForSale, BlockEntity shopBlockEntity, Map<Item, Integer> denominations) {
        if (shopBlockEntity instanceof ShopBlockEntity shopBlock) {
            // Register is Full
            if (!shopBlock.transferCashToShop(price, denominations)) {
                String shopOwnerName = shopBlock.getOwnerName();
                player.sendSystemMessage(Component.literal(shopOwnerName + " needs to empty the register! Shop cannot hold anymore cash - transaction failed").withStyle(ChatFormatting.RED));
                player.closeContainer();
                return;
            }

            shopBlock.removePurchasedItems(totalItemsSold);

            // Pay
            int removed = Currency.removeCurrency(player, denominations, price);
            // Give change if needed
            int change = removed - price;
            if (change > 0) {
                Currency.makeChange(player, denominations, change);
            }

            ItemStack soldItem = itemForSale.copy();
            soldItem.setCount(totalItemsSold);

            boolean added = player.getInventory().add(soldItem);
            if (!added) {
                player.drop(soldItem, false);
            }
            String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
            player.sendSystemMessage(Component.literal("Purchased " + itemForSale.getDisplayName().getString() + " for " + currencySymbol + price).withStyle(ChatFormatting.GREEN));
            player.closeContainer();
        } else {
            BetterEconomy.LOGGING("(ServerPayloadHandler) - INVALID SHOP! (WRONG BLOCK POS SENT IN PACKET??)");
            player.closeContainer();
        }
    }
    private static void handleDebitCardPurchase(Player player, int price, int totalItemsSold, ItemStack itemForSale, BlockEntity shopBlockEntity, Balance balance, Map<Item, Integer> denominations) {
        if (shopBlockEntity instanceof ShopBlockEntity shopBlock) {
            // Register is Full
            if (!shopBlock.transferCashToShop(price, denominations)) {
                String shopOwnerName = shopBlock.getOwnerName();
                player.sendSystemMessage(Component.literal(shopOwnerName + " needs to empty the register! Shop cannot hold anymore cash - transaction failed").withStyle(ChatFormatting.RED));
                player.closeContainer();
                return;
            }

            shopBlock.removePurchasedItems(totalItemsSold);

            // Pay for Item
            balance.subtractBalance(price);

            ItemStack debitCard = Currency.getDebitCard(player);
            int enchantLevel = debitCard.getEnchantmentLevel(ModEnchantments.CASH_BACK.get());
            if (enchantLevel > 0 && debitCard.isEnchanted()) {
                handleCashBack(price, balance, player);
            }

            ItemStack soldItem = itemForSale.copy();
            soldItem.setCount(totalItemsSold);

            boolean added = player.getInventory().add(soldItem);
            if (!added) {
                player.drop(soldItem, false);
            }
            String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
            int updatedBalance = balance.getBalance();
            player.sendSystemMessage(Component.literal("Purchased " + itemForSale.getDisplayName().getString() + " for " + currencySymbol + price + " Account Balance is now " + currencySymbol + updatedBalance).withStyle(ChatFormatting.GREEN));
            player.closeContainer();
        } else {
            BetterEconomy.LOGGING("(ServerPayloadHandler) - INVALID SHOP! (WRONG BLOCK POS SENT IN PACKET??)");
            player.closeContainer();
        }
    }
    private static void handleCashBack(int price, Balance balance, Player player) {
        double cashbackPercent = 0.012; // 1.2%
        int cashBack = (int) Math.floor(price * cashbackPercent);
        cashBack = Math.max(cashBack, 1);
        cashBack = Math.min(cashBack, 100);

        balance.addBalance(cashBack);
        player.sendSystemMessage(Component.literal("Cashback received: " + ModConfigs.COMMON.currencySymbol.get() + cashBack).withStyle(ChatFormatting.BOLD, ChatFormatting.GOLD));
    }
}