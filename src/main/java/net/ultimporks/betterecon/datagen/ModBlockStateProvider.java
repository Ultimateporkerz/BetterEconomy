package net.ultimporks.betterecon.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.init.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Reference.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(ModBlocks.ATM.get(),
                new ModelFile.UncheckedModelFile(
                        new ResourceLocation(Reference.MOD_ID, "block/atm")));

        horizontalBlock(ModBlocks.SHOP.get(),
                new ModelFile.UncheckedModelFile(
                        new ResourceLocation(Reference.MOD_ID, "block/shop")));

        horizontalBlock(ModBlocks.VAULT.get(),
                new ModelFile.UncheckedModelFile(
                        new ResourceLocation(Reference.MOD_ID, "block/vault")
                ));
    }

    // Helper Method
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
