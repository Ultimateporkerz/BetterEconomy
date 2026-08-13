package net.ultimporks.betterecon.network;

import net.ultimporks.betterecon.client.ClientData;
import net.ultimporks.betterecon.network.atm.S2CMessageBalance;
import net.ultimporks.betterecon.network.shop.S2CMessageItemAndPrice;
import net.ultimporks.betterecon.network.shop.S2CMessageSellPrice;

public class ClientPayloadHandler {

    public static void handleCurrencySymbol(final S2CMessageCurrencySymbol data) {
        ClientData.setCurrencySymbol(data.currencySymbol);
    }

    // Shop Owner Handlers
    public static void handleBalanceMessage(final S2CMessageBalance data) {
        ClientData.setBalance(data.balance);
        ClientData.setPlayerName(data.playerName);
    }

    public static void handleSellPriceMessage(final S2CMessageSellPrice data) {
        ClientData.setSellPrice(data.sellPrice);
    }

    // Shop Customer Handlers
    public static void handleItemAndPriceMessage(final S2CMessageItemAndPrice data) {
        // Set the price for the shop
        ClientData.setSellPrice(data.price);
        // set the ItemStack for the shop
        ClientData.setItemForSale(data.itemForSale);
        // Set the amount of stock for the shop
        ClientData.setShopStock(data.stock);
        // Is the owner viewing as a customer?
        ClientData.setCustomerView(data.customerView);
        // Set the Owners Name
        ClientData.setOwnerName(data.ownerName);
    }


}
