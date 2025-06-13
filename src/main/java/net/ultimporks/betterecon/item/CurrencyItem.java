package net.ultimporks.betterecon.item;

import net.minecraft.world.item.Item;

public class CurrencyItem extends Item {
    private final int value;

    public CurrencyItem(Properties properties, int value) {
        super(properties);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
