package net.ultimporks.betterecon.network.atm;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.ultimporks.betterecon.network.ClientPayloadHandler;

import java.util.function.Supplier;

public class S2CMessageBalance {
    public final int balance;
    public final String playerName;

    public S2CMessageBalance(int balance, String playerName) {
        this.balance = balance;
        this.playerName = playerName;
    }

    public S2CMessageBalance(FriendlyByteBuf buf) {
        this.balance = buf.readInt();
        this.playerName = buf.readUtf();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(balance);
        buf.writeUtf(playerName);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ClientPayloadHandler.handleBalanceMessage(this);
        });
    }
}