package net.ultimporks.betterecon.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.block.entity.ShopBlockEntity;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MOD_ID);

    public static final Supplier<BlockEntityType<ShopBlockEntity>> SHOP_BLOCK_BE =
            BLOCK_ENTITIES.register("shop_block_be", () ->
                    BlockEntityType.Builder.of(ShopBlockEntity::new,
                            ModBlocks.SHOP.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
