package net.ultimporks.betterecon.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.util.menu.ATMMenu;
import net.ultimporks.betterecon.util.menu.ShopCustomerMenu;
import net.ultimporks.betterecon.util.menu.ShopOwnerMenu;
import net.ultimporks.betterecon.util.menu.WalletMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Reference.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<ATMMenu>> ATM_MENU =
            registerMenuType("atm_menu", ATMMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<WalletMenu>> WALLET_MENU =
            registerMenuType("wallet_menu", WalletMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<ShopOwnerMenu>> SHOP_OWNER_MENU =
            registerMenuType("shop_owner_menu", ShopOwnerMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<ShopCustomerMenu>> SHOP_CUSTOMER_MENU =
            registerMenuType("shop_customer_menu", ShopCustomerMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
