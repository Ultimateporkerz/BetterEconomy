package net.ultimporks.betterecon.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.init.ModBlocks;
import net.ultimporks.betterecon.init.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        // 100 to 2 50
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FIFTY_DOLLAR_BILL.get(), 2)
                .requires(ModItems.ONE_HUNDRED_DOLLAR_BILL.get())
                .unlockedBy(getHasName(ModItems.ONE_HUNDRED_DOLLAR_BILL.get()), has(ModItems.ONE_HUNDRED_DOLLAR_BILL.get()))
                .save(pWriter);
        // 50 to 5 10
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TEN_DOLLAR_BILL.get(), 5)
                .requires(ModItems.FIFTY_DOLLAR_BILL.get())
                .unlockedBy(getHasName(ModItems.FIFTY_DOLLAR_BILL.get()), has(ModItems.FIFTY_DOLLAR_BILL.get()))
                .save(pWriter, new ResourceLocation(Reference.MOD_ID, "exchange_fifty_to_ten"));
        // 20 to 2 10
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TEN_DOLLAR_BILL.get(), 2)
                .requires(ModItems.TWENTY_DOLLAR_BILL.get())
                .unlockedBy(getHasName(ModItems.TWENTY_DOLLAR_BILL.get()), has(ModItems.TWENTY_DOLLAR_BILL.get()))
                .save(pWriter, new ResourceLocation(Reference.MOD_ID, "exchange_twenty_to_ten"));
        // 10 to 2 5
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FIVE_DOLLAR_BILL.get(), 2)
                .requires(ModItems.TEN_DOLLAR_BILL.get())
                .unlockedBy(getHasName(ModItems.TEN_DOLLAR_BILL.get()), has(ModItems.TEN_DOLLAR_BILL.get()))
                .save(pWriter);
        // 5 to 5 1
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ONE_DOLLAR_BILL.get(), 5)
                .requires(ModItems.FIVE_DOLLAR_BILL.get())
                .unlockedBy(getHasName(ModItems.FIVE_DOLLAR_BILL.get()), has(ModItems.FIVE_DOLLAR_BILL.get()))
                .save(pWriter);
        // Wallet
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WALLET.get())
                .pattern("   ")
                .pattern("LLL")
                .pattern("LLL")
                .define('L', Items.LEATHER)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);
        // Debit Card
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DEBIT_CARD.get())
                .pattern("IGI")
                .pattern("PAP")
                .pattern("GPG")
                .define('I', Items.IRON_NUGGET)
                .define('G', Items.GOLD_NUGGET)
                .define('P', Items.PAPER)
                .define('A', Items.AMETHYST_SHARD)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(pWriter);
        // ATM
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ATM.get())
                .pattern("MEM")
                .pattern("MCM")
                .pattern("MMM")
                .define('C', Items.CHEST)
                .define('M', Items.IRON_BLOCK)
                .define('E', Items.EMERALD)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                .save(pWriter);
        // Shop
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SHOP.get())
                .pattern("SSS")
                .pattern("WCW")
                .pattern("WWW")
                .define('C', Items.CHEST)
                .define('W', ItemTags.PLANKS)
                .define('S', ItemTags.SLABS)
                .unlockedBy(getHasName(Items.CHEST), has(Items.CHEST))
                .save(pWriter);

        // Vault
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VAULT.get())
                .pattern("ICI")
                .pattern("IAI")
                .pattern("III")
                .define('A', ModBlocks.ATM.get())
                .define('C', Items.CHEST)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(ModBlocks.ATM.get()), has(ModBlocks.ATM.get()))
                .save(pWriter);
    }
}
