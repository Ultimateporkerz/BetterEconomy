package net.ultimporks.betterecon.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.block.ATMBlock;
import net.ultimporks.betterecon.block.ShopBlock;
import net.ultimporks.betterecon.block.VaultBlock;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

    public static final RegistryObject<Block> ATM = registerBlock("atm",
            () -> new ATMBlock(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 15F)
                    .destroyTime(4.0F)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> SHOP = registerBlock("shop",
            () -> new ShopBlock(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 8F)
                    .destroyTime(3.0F)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> VAULT = registerBlock("vault",
            () -> new VaultBlock(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(4.0F, 8F)
                    .destroyTime(4.0F)
                    .sound(SoundType.METAL)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
