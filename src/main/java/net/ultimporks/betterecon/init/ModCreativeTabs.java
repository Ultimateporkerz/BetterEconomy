package net.ultimporks.betterecon.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.ultimporks.betterecon.Reference;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

    public static final Supplier<CreativeModeTab> BETTER_ECONOMY_TAB = CREATIVE_MODE_TABS.register("better_economy",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ONE_DOLLAR_BILL.get()))
                    .title(Component.translatable("creativetab.betterecon_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ATM.get());
                        output.accept(ModBlocks.SHOP.get());
                        output.accept(ModItems.WALLET.get());
                        output.accept(ModItems.DEBIT_CARD.get());
                        output.accept(ModItems.ONE_DOLLAR_BILL.get());
                        output.accept(ModItems.FIVE_DOLLAR_BILL.get());
                        output.accept(ModItems.TEN_DOLLAR_BILL.get());
                        output.accept(ModItems.TWENTY_DOLLAR_BILL.get());
                        output.accept(ModItems.FIFTY_DOLLAR_BILL.get());
                        output.accept(ModItems.ONE_HUNDRED_DOLLAR_BILL.get());
                    }) .build());

    public static void registerCreativeTab(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
