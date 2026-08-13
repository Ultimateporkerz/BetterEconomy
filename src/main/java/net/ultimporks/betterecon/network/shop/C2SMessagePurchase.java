package net.ultimporks.betterecon.network.shop;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.ultimporks.betterecon.network.ServerPayloadHandler;

import java.util.function.Supplier;

public class C2SMessagePurchase {
    public final ItemStack purchasedItem;
    public final int price;
    public final int amountPurchased;
    public final int amountForSale;
    public final BlockPos shopBlockPos;

    public C2SMessagePurchase(ItemStack purchasedItem, int price, int amountPurchased, int amountForSale, BlockPos shopBlockPos) {
        this.purchasedItem = purchasedItem;
        this.price = price;
        this.amountPurchased = amountPurchased;
        this.amountForSale = amountForSale;
        this.shopBlockPos = shopBlockPos;
    }

    public C2SMessagePurchase(FriendlyByteBuf buf) {
        this.purchasedItem = buf.readItem();
        this.price = buf.readInt();
        this.amountPurchased = buf.readInt();
        this.amountForSale = buf.readInt();
        this.shopBlockPos = buf.readBlockPos();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeItem(purchasedItem);
        buf.writeInt(price);
        buf.writeInt(amountPurchased);
        buf.writeInt(amountForSale);
        buf.writeBlockPos(shopBlockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPayloadHandler.handlePurchaseMessage(this, context);
        });
    }
}