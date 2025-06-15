package net.ultimporks.betterecon.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.init.ModBlocks;
import net.ultimporks.betterecon.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // 100 to 2 50
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FIFTY_DOLLAR_BILL, 2)
                .requires(ModItems.ONE_HUNDRED_DOLLAR_BILL)
                .unlockedBy(getHasName(ModItems.ONE_HUNDRED_DOLLAR_BILL), has(ModItems.ONE_HUNDRED_DOLLAR_BILL))
                .save(recipeOutput);
        // 50 to 5 10
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TEN_DOLLAR_BILL, 5)
                .requires(ModItems.FIFTY_DOLLAR_BILL)
                .unlockedBy(getHasName(ModItems.FIFTY_DOLLAR_BILL), has(ModItems.FIFTY_DOLLAR_BILL))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "exchange_fifty_to_ten"));
        // 20 to 2 10
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TEN_DOLLAR_BILL, 2)
                .requires(ModItems.TWENTY_DOLLAR_BILL)
                .unlockedBy(getHasName(ModItems.TWENTY_DOLLAR_BILL), has(ModItems.TWENTY_DOLLAR_BILL))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "exchange_twenty_to_ten"));
        // 10 to 2 5
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FIVE_DOLLAR_BILL, 2)
                .requires(ModItems.TEN_DOLLAR_BILL)
                .unlockedBy(getHasName(ModItems.TEN_DOLLAR_BILL), has(ModItems.TEN_DOLLAR_BILL))
                .save(recipeOutput);
        // 5 to 5 1
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ONE_DOLLAR_BILL, 5)
                .requires(ModItems.FIVE_DOLLAR_BILL)
                .unlockedBy(getHasName(ModItems.FIVE_DOLLAR_BILL), has(ModItems.FIVE_DOLLAR_BILL))
                .save(recipeOutput);
        // Wallet
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WALLET.get())
                .pattern("   ")
                .pattern("LLL")
                .pattern("LLL")
                .define('L', Items.LEATHER)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(recipeOutput);
        // ATM
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ATM.get())
                .pattern("MEM")
                .pattern("MCM")
                .pattern("MMM")
                .define('C', Items.CHEST)
                .define('M', Items.IRON_BLOCK)
                .define('E', Items.EMERALD)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                .save(recipeOutput);
        // Shop
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SHOP.get())
                .pattern("SSS")
                .pattern("WCW")
                .pattern("WWW")
                .define('C', Items.CHEST)
                .define('W', ItemTags.PLANKS)
                .define('S', ItemTags.SLABS)
                .unlockedBy(getHasName(Items.CHEST), has(Items.CHEST))
                .save(recipeOutput);
    }
}
