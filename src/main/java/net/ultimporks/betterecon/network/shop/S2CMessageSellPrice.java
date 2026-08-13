package net.ultimporks.betterecon.network.shop;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.ultimporks.betterecon.network.ClientPayloadHandler;

import java.util.function.Supplier;

public class S2CMessageSellPrice {
    public final int sellPrice;

    public S2CMessageSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public S2CMessageSellPrice(FriendlyByteBuf buf) {
        this.sellPrice = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(sellPrice);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ClientPayloadHandler.handleSellPriceMessage(this);
        });
    }
}