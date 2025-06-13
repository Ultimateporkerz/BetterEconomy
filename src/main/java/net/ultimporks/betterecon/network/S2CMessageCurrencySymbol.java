package net.ultimporks.betterecon.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.ultimporks.betterecon.Reference;

public record S2CMessageCurrencySymbol(String currencySymbol) implements CustomPacketPayload {
    public static final Type<S2CMessageCurrencySymbol> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "message_currency_symbol"));

    public static final StreamCodec<RegistryFriendlyByteBuf, S2CMessageCurrencySymbol> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, S2CMessageCurrencySymbol::currencySymbol,
                    S2CMessageCurrencySymbol::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
