package net.ultimporks.betterecon.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.network.atm.C2SMessageDeposit;
import net.ultimporks.betterecon.network.atm.C2SMessageWithdraw;
import net.ultimporks.betterecon.network.atm.S2CMessageBalance;
import net.ultimporks.betterecon.network.shop.C2SMessagePurchase;
import net.ultimporks.betterecon.network.shop.C2SMessageSaveSellPrice;
import net.ultimporks.betterecon.network.shop.S2CMessageItemAndPrice;
import net.ultimporks.betterecon.network.shop.S2CMessageSellPrice;

public class ModMessages {
    public static SimpleChannel INSTANCE;
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Reference.MOD_ID, "betterecon_network"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        // Server to Client
        net.messageBuilder(S2CMessageBalance.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(S2CMessageBalance::encode)
                .decoder(S2CMessageBalance::new)
                .consumerMainThread(S2CMessageBalance::handle)
                .add();

        net.messageBuilder(S2CMessageSellPrice.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(S2CMessageSellPrice::encode)
                .decoder(S2CMessageSellPrice::new)
                .consumerMainThread(S2CMessageSellPrice::handle)
                .add();

        net.messageBuilder(S2CMessageItemAndPrice.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(S2CMessageItemAndPrice::encode)
                .decoder(S2CMessageItemAndPrice::new)
                .consumerMainThread(S2CMessageItemAndPrice::handle)
                .add();

        net.messageBuilder(S2CMessageCurrencySymbol.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(S2CMessageCurrencySymbol::encode)
                .decoder(S2CMessageCurrencySymbol::new)
                .consumerMainThread(S2CMessageCurrencySymbol::handle)
                .add();

        // Client to Server
        net.messageBuilder(C2SMessageDeposit.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(C2SMessageDeposit::encode)
                .decoder(C2SMessageDeposit::new)
                .consumerMainThread(C2SMessageDeposit::handle)
                .add();

        net.messageBuilder(C2SMessageWithdraw.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(C2SMessageWithdraw::encode)
                .decoder(C2SMessageWithdraw::new)
                .consumerMainThread(C2SMessageWithdraw::handle)
                .add();

        net.messageBuilder(C2SMessageSaveSellPrice.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(C2SMessageSaveSellPrice::encode)
                .decoder(C2SMessageSaveSellPrice::new)
                .consumerMainThread(C2SMessageSaveSellPrice::handle)
                .add();

        net.messageBuilder(C2SMessagePurchase.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(C2SMessagePurchase::encode)
                .decoder(C2SMessagePurchase::new)
                .consumerMainThread(C2SMessagePurchase::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
    public static <MSG> void sendToAll(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
