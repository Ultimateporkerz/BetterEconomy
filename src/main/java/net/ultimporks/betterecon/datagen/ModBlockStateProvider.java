package net.ultimporks.betterecon.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.init.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Reference.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(ModBlocks.ATM.get(),
                new ModelFile.UncheckedModelFile(ResourceLocation.
                        fromNamespaceAndPath(Reference.MOD_ID, "block/atm")));

        horizontalBlock(ModBlocks.SHOP.get(),
                new ModelFile.UncheckedModelFile(ResourceLocation.
                        fromNamespaceAndPath(Reference.MOD_ID, "block/shop")));



    }

    // Helper Method
    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
