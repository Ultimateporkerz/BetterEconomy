package net.ultimporks.client;

public class ClientData {
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

}
