package net.ultimporks.betterecon.network.shop;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.ultimporks.betterecon.network.ClientPayloadHandler;

import java.util.function.Supplier;

public class S2CMessageItemAndPrice {
    public final ItemStack itemForSale;
    public final int price;
    public final int stock;
    public final boolean customerView;
    public final String ownerName;

    public S2CMessageItemAndPrice(ItemStack itemForSale, int price, int stock, boolean customerView, String ownerName) {
        this.itemForSale = itemForSale;
        this.price = price;
        this.stock = stock;
        this.customerView = customerView;
        this.ownerName = ownerName;
    }

    public S2CMessageItemAndPrice(FriendlyByteBuf buf) {
        this.itemForSale = buf.readItem();
        this.price = buf.readInt();
        this.stock = buf.readInt();
        this.customerView = buf.readBoolean();
        this.ownerName = buf.readUtf();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeItem(itemForSale);
        buf.writeInt(price);
        buf.writeInt(stock);
        buf.writeBoolean(customerView);
        buf.writeUtf(ownerName);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ClientPayloadHandler.handleItemAndPriceMessage(this);
        });
    }
}