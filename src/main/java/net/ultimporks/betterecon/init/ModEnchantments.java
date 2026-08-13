package net.ultimporks.betterecon.init;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.enchantment.CashBackEnchantment;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Reference.MOD_ID);

    public static final RegistryObject<Enchantment> CASH_BACK =
            ENCHANTMENTS.register("cash_back",
                    () -> new CashBackEnchantment(
                            Enchantment.Rarity.RARE,
                            EnchantmentCategory.BREAKABLE
                    ));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
