package net.ultimporks.betterecon.network.atm;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.ultimporks.betterecon.Reference;

public record C2SMessageDeposit(int amount) implements CustomPacketPayload {
    public static final Type<C2SMessageDeposit> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "message_deposit"));

    public static final StreamCodec<ByteBuf, C2SMessageDeposit> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            C2SMessageDeposit::amount,
            C2SMessageDeposit::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}