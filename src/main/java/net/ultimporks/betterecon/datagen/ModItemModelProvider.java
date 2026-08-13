package net.ultimporks.betterecon.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.init.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ONE_DOLLAR_BILL.get());
        basicItem(ModItems.FIVE_DOLLAR_BILL.get());
        basicItem(ModItems.TEN_DOLLAR_BILL.get());
        basicItem(ModItems.TWENTY_DOLLAR_BILL.get());
        basicItem(ModItems.FIFTY_DOLLAR_BILL.get());
        basicItem(ModItems.ONE_HUNDRED_DOLLAR_BILL.get());
        basicItem(ModItems.WALLET.get());
        basicItem(ModItems.DEBIT_CARD.get());
    }
}
