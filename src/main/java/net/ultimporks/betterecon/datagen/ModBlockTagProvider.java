package net.ultimporks.betterecon.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.init.ModBlocks;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Pickaxe
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ATM.get())
                .add(ModBlocks.VAULT.get());
        // Axe
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.SHOP.get());

    }
}
