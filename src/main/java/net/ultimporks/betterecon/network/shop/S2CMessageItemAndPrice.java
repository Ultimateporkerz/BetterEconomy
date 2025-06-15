package net.ultimporks.betterecon.network.shop;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.ultimporks.betterecon.Reference;

public record S2CMessageItemAndPrice(ItemStack itemForSale, int price, int stock, boolean customerView, String ownerName) implements CustomPacketPayload {
    public static final Type<S2CMessageItemAndPrice> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "message_item_and_price"));

    public static final StreamCodec<RegistryFriendlyByteBuf, S2CMessageItemAndPrice> STREAM_CODEC =
            StreamCodec.composite(
                    ItemStack.STREAM_CODEC, S2CMessageItemAndPrice::itemForSale,
                    ByteBufCodecs.VAR_INT, S2CMessageItemAndPrice::price,
                    ByteBufCodecs.VAR_INT, S2CMessageItemAndPrice::stock,
                    ByteBufCodecs.BOOL, S2CMessageItemAndPrice::customerView,
                    ByteBufCodecs.STRING_UTF8, S2CMessageItemAndPrice::ownerName,
                    S2CMessageItemAndPrice::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
