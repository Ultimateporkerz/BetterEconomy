package net.ultimporks.betterecon.network.shop;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.ultimporks.betterecon.Reference;

public record C2SMessagePurchase(ItemStack purchasedItem, int price, int amountPurchased, BlockPos shopBlockPos) implements CustomPacketPayload {
    public static final Type<C2SMessagePurchase> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "message_purchase"));

    public static final StreamCodec<RegistryFriendlyByteBuf, C2SMessagePurchase> STREAM_CODEC =
            StreamCodec.composite(
                    ItemStack.STREAM_CODEC, C2SMessagePurchase::purchasedItem,
                    ByteBufCodecs.VAR_INT, C2SMessagePurchase::price,
                    ByteBufCodecs.VAR_INT, C2SMessagePurchase::amountPurchased,
                    BlockPos.STREAM_CODEC, C2SMessagePurchase::shopBlockPos,
                    C2SMessagePurchase::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
