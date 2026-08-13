package net.ultimporks.betterecon.network.shop;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.ultimporks.betterecon.network.ServerPayloadHandler;

import java.util.function.Supplier;

public class C2SMessageSaveSellPrice {
    public final int sellingPrice;
    public final int xPos;
    public final int yPos;
    public final int zPos;

    public C2SMessageSaveSellPrice(int sellingPrice, int xPos, int yPos, int zPos) {
        this.sellingPrice = sellingPrice;
        this.xPos = xPos;
        this.zPos = zPos;
        this.yPos = yPos;
    }

    public C2SMessageSaveSellPrice(FriendlyByteBuf buf) {
        this.sellingPrice = buf.readInt();
        this.xPos = buf.readInt();
        this.zPos = buf.readInt();
        this.yPos = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(sellingPrice);
        buf.writeInt(xPos);
        buf.writeInt(zPos);
        buf.writeInt(yPos);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPayloadHandler.handleSaveSellPrice(this, context);
        });
    }
}