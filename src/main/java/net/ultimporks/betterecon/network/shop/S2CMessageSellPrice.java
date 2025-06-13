package net.ultimporks.betterecon.network.shop;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.ultimporks.betterecon.Reference;

public record S2CMessageSellPrice(int sellPrice) implements CustomPacketPayload {
    public static final Type<S2CMessageSellPrice> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "message_sell_price"));

    public static final StreamCodec<ByteBuf, S2CMessageSellPrice> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            S2CMessageSellPrice::sellPrice,
            S2CMessageSellPrice::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
