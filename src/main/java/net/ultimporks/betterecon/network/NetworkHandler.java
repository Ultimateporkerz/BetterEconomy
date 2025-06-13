package net.ultimporks.betterecon.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.ultimporks.betterecon.network.atm.C2SMessageDeposit;
import net.ultimporks.betterecon.network.atm.C2SMessageWithdraw;
import net.ultimporks.betterecon.network.atm.S2CMessageBalance;
import net.ultimporks.betterecon.network.shop.C2SMessagePurchase;
import net.ultimporks.betterecon.network.shop.C2SMessageSaveSellPrice;
import net.ultimporks.betterecon.network.shop.S2CMessageItemAndPrice;
import net.ultimporks.betterecon.network.shop.S2CMessageSellPrice;

public class NetworkHandler {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1")
                .executesOn(HandlerThread.NETWORK);
        // Server to Client
        registrar.playToClient(
                S2CMessageBalance.TYPE,
                S2CMessageBalance.STREAM_CODEC,
                ClientPayloadHandler::handleBalanceMessage
        );

        registrar.playToClient(
                S2CMessageSellPrice.TYPE,
                S2CMessageSellPrice.STREAM_CODEC,
                ClientPayloadHandler::handleSellPriceMessage
        );

        registrar.playToClient(
                S2CMessageItemAndPrice.TYPE,
                S2CMessageItemAndPrice.STREAM_CODEC,
                ClientPayloadHandler::handleItemAndPriceMessage
        );

        registrar.playToClient(
                S2CMessageCurrencySymbol.TYPE,
                S2CMessageCurrencySymbol.STREAM_CODEC,
                ClientPayloadHandler::handleCurrencySymbol
        );

        // Client to Server
        registrar.playToServer(
                C2SMessageDeposit.TYPE,
                C2SMessageDeposit.STREAM_CODEC,
                ServerPayloadHandler::handleDepositMessage
        );

        registrar.playToServer(
                C2SMessageWithdraw.TYPE,
                C2SMessageWithdraw.STREAM_CODEC,
                ServerPayloadHandler::handleWithdrawMessage
        );

        registrar.playToServer(
                C2SMessageSaveSellPrice.TYPE,
                C2SMessageSaveSellPrice.STREAM_CODEC,
                ServerPayloadHandler::handleSaveSellPrice
        );

        registrar.playToServer(
                C2SMessagePurchase.TYPE,
                C2SMessagePurchase.STREAM_CODEC,
                ServerPayloadHandler::handlePurchaseMessage
        );
    }
}
