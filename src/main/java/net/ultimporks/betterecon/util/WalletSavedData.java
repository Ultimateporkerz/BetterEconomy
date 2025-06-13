package net.ultimporks.betterecon.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class WalletSavedData {
    private final List<ItemStack> items; // must be immutable

    public WalletSavedData(List<ItemStack> items) {
        // Defensive copy & immutability
        this.items = Collections.unmodifiableList(new ArrayList<>(items));
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public SimpleContainer toContainer() {
        SimpleContainer container = new SimpleContainer(items.size());
        for (int i = 0; i < items.size(); i++) {
            container.setItem(i, items.get(i));
        }
        return container;
    }

    public static final Codec<WalletSavedData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.list(ItemStack.CODEC).fieldOf("items").forGetter(WalletSavedData::getItems)
            ).apply(instance, WalletSavedData::new)
    );

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WalletSavedData other)) return false;
        return items.equals(other.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}

