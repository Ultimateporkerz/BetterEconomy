package net.ultimporks.betterecon.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.ultimporks.betterecon.Reference;

public record C2SMessageSaveSellPrice(int sellingPrice, int xPos, int yPos, int zPos) implements CustomPacketPayload {
    public static final Type<C2SMessageSaveSellPrice> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "message_save_sell_price"));

    public static final StreamCodec<ByteBuf, C2SMessageSaveSellPrice> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            C2SMessageSaveSellPrice::sellingPrice,
            ByteBufCodecs.VAR_INT,
            C2SMessageSaveSellPrice::xPos,
            ByteBufCodecs.VAR_INT,
            C2SMessageSaveSellPrice::yPos,
            ByteBufCodecs.VAR_INT,
            C2SMessageSaveSellPrice::zPos,
            C2SMessageSaveSellPrice::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
