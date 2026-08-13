package net.ultimporks.betterecon.currency;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.ultimporks.betterecon.BetterEconomy;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.item.DebitCardItem;

import java.util.List;
import java.util.Map;

public class Currency {
    private final String name;
    private final String symbol;

    public Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return ModConfigs.COMMON.currencyName.get();
    }

    public String getSymbol() {
        return ModConfigs.COMMON.currencySymbol.get();
    }

    public static void giveCurrencyItems(Player player, Item currencyItem, int count) {
        if (count <= 0) return;

        ItemStack exampleStack = new ItemStack(currencyItem);
        int maxStack = currencyItem.getMaxStackSize(exampleStack);

        while (count > 0) {
            int toGive = Math.min(count, maxStack);
            ItemStack stack = new ItemStack(currencyItem, toGive);

            boolean added = player.getInventory().add(stack);
            if (!added) {
                player.drop(stack, false);
            }

            count -= toGive;
        }
    }

    public static void giveCurrency(Player player, int amount, Map<Item, Integer> denominations) {
        if (amount <= 0) return;

        // Sort denominations descending by value
        List<Map.Entry<Item, Integer>> sorted = denominations.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .toList();

        for (Map.Entry<Item, Integer> entry : sorted) {
            Item currencyItem = entry.getKey();
            int value = entry.getValue();

            int count = amount / value;
            if (count > 0) {
                giveCurrencyItems(player, currencyItem, count);
                amount -= count * value;
            }

            if (amount == 0) break;
        }
    }

    public static int removeCurrency(Player player, Map<Item, Integer> denominations, int amountToRemove) {
        int totalRemoved = 0;
        int remaining = amountToRemove;

        // Sort denominations by descending value
        List<Map.Entry<Item, Integer>> sorted = denominations.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .toList();

        for (Map.Entry<Item, Integer> entry : sorted) {
            Item currencyItem = entry.getKey();
            int valuePerItem = entry.getValue();

            for (int i = 0; i < player.getInventory().items.size(); i++) {
                if (remaining <= 0) break;

                ItemStack stack = player.getInventory().items.get(i);
                if (!stack.isEmpty() && stack.getItem() == currencyItem) {
                    int stackCount = stack.getCount();
                    int stackValue = stackCount * valuePerItem;
                    int needed = remaining;

                    if (stackValue <= needed) {
                        player.getInventory().setItem(i, ItemStack.EMPTY);
                        totalRemoved += stackValue;
                        remaining -= stackValue;
                    } else {
                        int itemsToRemove = Math.min(stackCount, (int) Math.ceil((double) needed / valuePerItem));
                        stack.shrink(itemsToRemove);
                        int removedValue = itemsToRemove * valuePerItem;
                        totalRemoved += removedValue;
                        remaining -= removedValue;
                    }
                }
            }
        }

        return totalRemoved;
    }

    public static void makeChange(Player player, Map<Item, Integer> denominations, int changeAmount) {
        if (changeAmount <= 0) return;

        // Sort denominations in descending order by value
        List<Map.Entry<Item, Integer>> sorted = denominations.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .toList();

        for (Map.Entry<Item, Integer> entry : sorted) {
            Item currencyItem = entry.getKey();
            int value = entry.getValue();

            int count = changeAmount / value;
            if (count > 0) {
                Currency.giveCurrencyItems(player, currencyItem, count);
                changeAmount -= count * value;
            }

            if (changeAmount == 0) break;
        }

        if (changeAmount > 0) {
            // This shouldn't happen if denominations are complete, but warn just in case.
            player.sendSystemMessage(Component.literal("Unable to return full change " + changeAmount));
        }
    }

    public static int getCashAmount(Player player, Map<Item, Integer> denominations) {
        int total = 0;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.isEmpty()) continue;

            Item item = stack.getItem();
            Integer value = denominations.get(item);

            if (value != null) {
                total += stack.getCount() * value;
            }
        }

        return total;
    }

    public static boolean isDebitCardInInventory(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof DebitCardItem) {
                BetterEconomy.LOGGING("Debit Card was found in " + player.getName().getString() + "'s inventory!");
                return true;
            }
        }
        return false;
    }

    public static ItemStack getDebitCard(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof DebitCardItem) return stack;
        }
        return ItemStack.EMPTY;
    }

}
