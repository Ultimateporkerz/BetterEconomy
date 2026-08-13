package net.ultimporks.betterecon.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CMessageCurrencySymbol {
    public final String currencySymbol;

    public S2CMessageCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public S2CMessageCurrencySymbol(FriendlyByteBuf buf) {
        this.currencySymbol = buf.readUtf();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(currencySymbol);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
           ClientPayloadHandler.handleCurrencySymbol(this);
        });
    }
}