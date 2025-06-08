package net.ultimporks.betterecon.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.ultimporks.betterecon.Reference;

public record S2CMessageBalance(int balance, String playerName) implements CustomPacketPayload {
    public static final Type<S2CMessageBalance> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "message_balance"));

    public static final StreamCodec<ByteBuf, S2CMessageBalance> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            S2CMessageBalance::balance,
            ByteBufCodecs.STRING_UTF8,
            S2CMessageBalance::playerName,
            S2CMessageBalance::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
