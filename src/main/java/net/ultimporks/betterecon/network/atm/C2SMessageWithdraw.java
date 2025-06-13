package net.ultimporks.betterecon.network.atm;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.ultimporks.betterecon.Reference;

public record C2SMessageWithdraw(int amount) implements CustomPacketPayload {
    public static final Type<C2SMessageWithdraw> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "message_withdraw"));

    public static final StreamCodec<ByteBuf, C2SMessageWithdraw> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            C2SMessageWithdraw::amount,
            C2SMessageWithdraw::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
