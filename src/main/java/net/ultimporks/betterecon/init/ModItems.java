package net.ultimporks.betterecon.init;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.item.CurrencyItem;
import net.ultimporks.betterecon.item.DebitCardItem;
import net.ultimporks.betterecon.item.WalletItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Reference.MOD_ID);

    public static final DeferredItem<Item> ONE_DOLLAR_BILL = ITEMS.register("one_dollar_bill",
            () -> new CurrencyItem(new Item.Properties()
                    .stacksTo(64), 1));

    public static final DeferredItem<Item> FIVE_DOLLAR_BILL = ITEMS.register("five_dollar_bill",
            () -> new CurrencyItem(new Item.Properties()
                    .stacksTo(64), 5));

    public static final DeferredItem<Item> TEN_DOLLAR_BILL = ITEMS.register("ten_dollar_bill",
            () -> new CurrencyItem(new Item.Properties()
                    .stacksTo(64), 10));

    public static final DeferredItem<Item> TWENTY_DOLLAR_BILL = ITEMS.register("twenty_dollar_bill",
            () -> new CurrencyItem(new Item.Properties()
                    .stacksTo(64), 20));

    public static final DeferredItem<Item> FIFTY_DOLLAR_BILL = ITEMS.register("fifty_dollar_bill",
            () -> new CurrencyItem(new Item.Properties()
                    .stacksTo(64), 50));

    public static final DeferredItem<Item> ONE_HUNDRED_DOLLAR_BILL = ITEMS.register("one_hundred_dollar_bill",
            () -> new CurrencyItem(new Item.Properties()
                    .stacksTo(64), 100));


    public static final DeferredItem<Item> WALLET = ITEMS.register("wallet",
            () -> new WalletItem(new Item.Properties()
                    .stacksTo(1)));

    public static final DeferredItem<Item> DEBIT_CARD = ITEMS.register("debit_card",
            () -> new DebitCardItem(new Item.Properties()
                    .stacksTo(1)));



    public static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
