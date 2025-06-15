package net.ultimporks.betterecon.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.init.ModItems;
import net.ultimporks.betterecon.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiersGenerator extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registires) {
        super(output, registires, Reference.MOD_ID);
    }

    @Override
    protected void start() {

        // Woodland Mansion ****

        // One
        add("respawn_token_woodland_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_woodland_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_woodland_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_woodland_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_woodland_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_woodland_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // End City Treasure ****

        // One
        add("respawn_token_end_city_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_end_city_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_end_city_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_end_city_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_end_city_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_end_city_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Abandoned Mineshaft ****

        // One
        add("respawn_token_abandoned_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_abandoned_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_abandoned_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_abandoned_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_abandoned_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_abandoned_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Ancient City ****

        // One
        add("respawn_token_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Bastion Bridge ****

        // One
        add("respawn_token_bastion_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_bastion_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_bastion_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_bastion_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_bastion_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_bastion_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Bastion Hoglin Stable ****

        // One
        add("respawn_token_bastion_hoglin_stable", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_hoglin_stable")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_bastion_hoglin_stable", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_hoglin_stable")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_bastion_hoglin_stable", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_hoglin_stable")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_bastion_hoglin_stable", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_hoglin_stable")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_bastion_hoglin_stable", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_hoglin_stable")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_bastion_hoglin_stable", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_hoglin_stable")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Bastion Other ****

        // One
        add("respawn_token_bastion_other", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_bastion_other", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_bastion_other", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_bastion_other", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_bastion_other", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_bastion_other", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Bastion Treasure ****

        // One
        add("respawn_token_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Buried Treasure ****

        // One
        add("respawn_token_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Igloo Chest ****

        // One
        add("respawn_token_igloo_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_igloo_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_igloo_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_igloo_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_igloo_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_igloo_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Desert Pyramid ****

        // One
        add("respawn_token_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Jungle Temple ****

        // One
        add("respawn_token_jungle_temple", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_jungle_temple", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_jungle_temple", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_jungle_temple", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_jungle_temple", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_jungle_temple", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Nether Bridge ****

        // One
        add("respawn_token_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Pillager Outpost ****

        // One
        add("respawn_token_pillager_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_pillager_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_pillager_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_pillager_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_pillager_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_pillager_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Ruined Portal ****

        // One
        add("respawn_token_ruined_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_ruined_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_ruined_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_ruined_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_ruined_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_ruined_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Shipwreck Treasure ****

        // One
        add("respawn_token_shipwreck_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_shipwreck_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_shipwreck_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_shipwreck_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_shipwreck_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_shipwreck_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Shipwreck Supply ****

        // One
        add("respawn_token_shipwreck_supply", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_supply")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_shipwreck_supply", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_supply")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_shipwreck_supply", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_supply")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_shipwreck_supply", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_supply")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_shipwreck_supply", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_supply")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_shipwreck_supply", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/shipwreck_supply")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Spawn bonus chest ****

        // One
        add("respawn_token_spawn_bonus_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/spawn_bonus_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_spawn_bonus_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/spawn_bonus_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_spawn_bonus_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/spawn_bonus_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_spawn_bonus_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/spawn_bonus_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_spawn_bonus_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/spawn_bonus_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_spawn_bonus_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/spawn_bonus_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Simple Dungeon ****

        // One
        add("respawn_token_simple_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_simple_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_simple_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_simple_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_simple_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_simple_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Stronghold Corridor ****

        // One
        add("respawn_token_stronghold_corridor", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_stronghold_corridor", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_stronghold_corridor", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_stronghold_corridor", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_stronghold_corridor", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_stronghold_corridor", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Stronghold Crossing ****

        // One
        add("respawn_token_stronghold_crossing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_stronghold_crossing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_stronghold_crossing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_stronghold_crossing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_stronghold_crossing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_stronghold_crossing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Stronghold Library ****

        // One
        add("respawn_token_stronghold_library", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_library")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_stronghold_library", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_library")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_stronghold_library", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_library")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_stronghold_library", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_library")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_stronghold_library", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_library")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_stronghold_library", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/stronghold_library")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Underwater Ruin Big ****

        // One
        add("respawn_token_underwater_ruin_big", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_underwater_ruin_big", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_underwater_ruin_big", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_underwater_ruin_big", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_underwater_ruin_big", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_underwater_ruin_big", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Underwater Ruin Small ****

        // One
        add("respawn_token_underwater_ruin_small", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_underwater_ruin_small", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_underwater_ruin_small", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_underwater_ruin_small", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_underwater_ruin_small", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_underwater_ruin_small", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));

        // Village Tool Smith ****

        // One
        add("respawn_token_village_toolsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/village/village_toolsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ONE_DOLLAR_BILL.get()));
        // Five
        add("token_part_village_toolsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/village/village_toolsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.FIVE_DOLLAR_BILL.get()));
        // Ten
        add("token_part_village_toolsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/village/village_toolsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.TEN_DOLLAR_BILL.get()));
        // Twenty
        add("token_part_village_toolsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/village/village_toolsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.TWENTY_DOLLAR_BILL.get()));
        // Fifty
        add("token_part_village_toolsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/village/village_toolsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.FIFTY_DOLLAR_BILL.get()));
        // One Hundred
        add("token_part_village_toolsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/village/village_toolsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.ONE_HUNDRED_DOLLAR_BILL.get()));
    }
}
