package net.ultimporks.betterecon.client;

import net.minecraft.world.item.ItemStack;

public class ClientData {
    // ATM Helpers
   private static int currentBalance = 0;
   private static String clientPlayerName = "null";

    public static void setBalance(int balance) {
        currentBalance = balance;
    }

    public static int getBalance() {
        return currentBalance;
    }

    public static void setPlayerName(String playerName) {
        clientPlayerName = playerName;
    }

    public static String getPlayerName() {
        return clientPlayerName;
    }

    // Player Shop Helpers
    private static int sellPrice = 0;
    // Itemstack used for Customers
    private static ItemStack itemForSale = ItemStack.EMPTY;
    private static int shopStock = 0;
    private static String currencySymbol = "INVALID";

    public static void setSellPrice(int price) {
        sellPrice = price;
    }

    public static int getSellPrice() {
        return sellPrice;
    }

    public static void setItemForSale(ItemStack itemForSale1) {
        itemForSale = itemForSale1;
    }

    public static ItemStack getItemForSale() {
        return itemForSale;
    }

    public static void setShopStock(int amount) {
        shopStock = amount;
    }

    public static int getShopStock() {
        return shopStock;
    }

    public static void setCurrencySymbol(String currencySymbol1) {
        currencySymbol = currencySymbol1;

    }

    public static String getCurrencySymbol() {
        return currencySymbol;
    }

}
