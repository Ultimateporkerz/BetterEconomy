package net.ultimporks.betterecon.network.atm;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.ultimporks.betterecon.network.ServerPayloadHandler;

import java.util.function.Supplier;

public class C2SMessageDeposit {
    public final int amount;

    public C2SMessageDeposit(int amount) {
        this.amount = amount;
    }

    public C2SMessageDeposit(FriendlyByteBuf buf) {
        this.amount = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(amount);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPayloadHandler.handleDepositMessage(this, context);
        });
    }
}