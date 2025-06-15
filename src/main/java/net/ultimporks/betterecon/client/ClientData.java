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
    private static boolean customerView = false;
    private static String ownerName = "NULL";
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

    public static void setCustomerView(boolean customerView1) {
        customerView = customerView1;
    }
    public static boolean getCustomerView() {
        return customerView;
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

    public static void setOwnerName(String ownersName) {
        ownerName = ownersName;
    }
    public static String getOwnerName() {
        return ownerName;
    }


}
