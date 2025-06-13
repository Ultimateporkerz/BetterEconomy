package net.ultimporks.betterecon.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.block.ATMBlock;
import net.ultimporks.betterecon.block.ShopBlock;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Reference.MOD_ID);

    public static final DeferredBlock<Block> ATM = registerBlock("atm",
            () -> new ATMBlock(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(4.0F, 500F)
                    .destroyTime(4.0F)
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> SHOP = registerBlock("shop",
            () -> new ShopBlock(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 150F)
                    .destroyTime(3.0F)
                    .sound(SoundType.WOOD)));

    private static <T extends net.minecraft.world.level.block.Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends net.minecraft.world.level.block.Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
