package net.ultimporks.betterecon.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.init.ModItems;
import net.ultimporks.betterecon.loot.AddItemModifier;

public class ModGlobalLootModifiersGenerator extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersGenerator(PackOutput output) {
        super(output, Reference.MOD_ID);
    }

    @Override
    protected void start() {

        // Woodland Mansion - Bills
        addBillLoot("woodland_mansion_one", "chests/woodland_mansion", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("woodland_mansion_five", "chests/woodland_mansion", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("woodland_mansion_ten", "chests/woodland_mansion", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("woodland_mansion_twenty", "chests/woodland_mansion", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("woodland_mansion_fifty", "chests/woodland_mansion", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("woodland_mansion_hundred", "chests/woodland_mansion", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // End City Treasure - Bills
        addBillLoot("end_city_treasure_one", "chests/end_city_treasure", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("end_city_treasure_five", "chests/end_city_treasure", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("end_city_treasure_ten", "chests/end_city_treasure", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("end_city_treasure_twenty", "chests/end_city_treasure", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("end_city_treasure_fifty", "chests/end_city_treasure", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("end_city_treasure_hundred", "chests/end_city_treasure", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Abandoned Mineshaft - Bills
        addBillLoot("abandoned_mineshaft_one", "chests/abandoned_mineshaft", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("abandoned_mineshaft_five", "chests/abandoned_mineshaft", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("abandoned_mineshaft_ten", "chests/abandoned_mineshaft", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("abandoned_mineshaft_twenty", "chests/abandoned_mineshaft", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("abandoned_mineshaft_fifty", "chests/abandoned_mineshaft", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("abandoned_mineshaft_hundred", "chests/abandoned_mineshaft", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Ancient City - Bills
        addBillLoot("ancient_city_one", "chests/ancient_city", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("ancient_city_five", "chests/ancient_city", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("ancient_city_ten", "chests/ancient_city", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("ancient_city_twenty", "chests/ancient_city", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("ancient_city_fifty", "chests/ancient_city", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("ancient_city_hundred", "chests/ancient_city", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Bastion Bridge - Bills
        addBillLoot("bastion_bridge_one", "chests/bastion_bridge", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("bastion_bridge_five", "chests/bastion_bridge", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("bastion_bridge_ten", "chests/bastion_bridge", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("bastion_bridge_twenty", "chests/bastion_bridge", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("bastion_bridge_fifty", "chests/bastion_bridge", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("bastion_bridge_hundred", "chests/bastion_bridge", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Bastion Hoglin Stable - Bills
        addBillLoot("bastion_hoglin_stable_one", "chests/bastion_hoglin_stable", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("bastion_hoglin_stable_five", "chests/bastion_hoglin_stable", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("bastion_hoglin_stable_ten", "chests/bastion_hoglin_stable", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("bastion_hoglin_stable_twenty", "chests/bastion_hoglin_stable", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("bastion_hoglin_stable_fifty", "chests/bastion_hoglin_stable", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("bastion_hoglin_stable_hundred", "chests/bastion_hoglin_stable", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Bastion Other - Bills
        addBillLoot("bastion_other_one", "chests/bastion_other", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("bastion_other_five", "chests/bastion_other", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("bastion_other_ten", "chests/bastion_other", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("bastion_other_twenty", "chests/bastion_other", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("bastion_other_fifty", "chests/bastion_other", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("bastion_other_hundred", "chests/bastion_other", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Bastion Treasure - Bills
        addBillLoot("bastion_treasure_one", "chests/bastion_treasure", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("bastion_treasure_five", "chests/bastion_treasure", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("bastion_treasure_ten", "chests/bastion_treasure", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("bastion_treasure_twenty", "chests/bastion_treasure", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("bastion_treasure_fifty", "chests/bastion_treasure", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("bastion_treasure_hundred", "chests/bastion_treasure", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Buried Treasure - Bills
        addBillLoot("buried_treasure_one", "chests/buried_treasure", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("buried_treasure_five", "chests/buried_treasure", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("buried_treasure_ten", "chests/buried_treasure", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("buried_treasure_twenty", "chests/buried_treasure", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("buried_treasure_fifty", "chests/buried_treasure", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("buried_treasure_hundred", "chests/buried_treasure", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Igloo Chest - Bills
        addBillLoot("igloo_chest_one", "chests/igloo_chest", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("igloo_chest_five", "chests/igloo_chest", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("igloo_chest_ten", "chests/igloo_chest", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("igloo_chest_twenty", "chests/igloo_chest", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("igloo_chest_fifty", "chests/igloo_chest", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("igloo_chest_hundred", "chests/igloo_chest", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Desert Pyramid - Bills
        addBillLoot("desert_pyramid_one", "chests/desert_pyramid", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("desert_pyramid_five", "chests/desert_pyramid", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("desert_pyramid_ten", "chests/desert_pyramid", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("desert_pyramid_twenty", "chests/desert_pyramid", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("desert_pyramid_fifty", "chests/desert_pyramid", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("desert_pyramid_hundred", "chests/desert_pyramid", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Jungle Temple - Bills
        addBillLoot("jungle_temple_one", "chests/jungle_temple", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("jungle_temple_five", "chests/jungle_temple", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("jungle_temple_ten", "chests/jungle_temple", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("jungle_temple_twenty", "chests/jungle_temple", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("jungle_temple_fifty", "chests/jungle_temple", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("jungle_temple_hundred", "chests/jungle_temple", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Nether Bridge - Bills
        addBillLoot("nether_bridge_one", "chests/nether_bridge", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("nether_bridge_five", "chests/nether_bridge", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("nether_bridge_ten", "chests/nether_bridge", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("nether_bridge_twenty", "chests/nether_bridge", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("nether_bridge_fifty", "chests/nether_bridge", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("nether_bridge_hundred", "chests/nether_bridge", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Pillager Outpost - Bills
        addBillLoot("pillager_outpost_one", "chests/pillager_outpost", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("pillager_outpost_five", "chests/pillager_outpost", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("pillager_outpost_ten", "chests/pillager_outpost", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("pillager_outpost_twenty", "chests/pillager_outpost", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("pillager_outpost_fifty", "chests/pillager_outpost", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("pillager_outpost_hundred", "chests/pillager_outpost", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Ruined Portal - Bills
        addBillLoot("ruined_portal_one", "chests/ruined_portal", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("ruined_portal_five", "chests/ruined_portal", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("ruined_portal_ten", "chests/ruined_portal", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("ruined_portal_twenty", "chests/ruined_portal", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("ruined_portal_fifty", "chests/ruined_portal", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("ruined_portal_hundred", "chests/ruined_portal", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Shipwreck Treasure - Bills
        addBillLoot("shipwreck_treasure_one", "chests/shipwreck_treasure", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("shipwreck_treasure_five", "chests/shipwreck_treasure", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("shipwreck_treasure_ten", "chests/shipwreck_treasure", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("shipwreck_treasure_twenty", "chests/shipwreck_treasure", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("shipwreck_treasure_fifty", "chests/shipwreck_treasure", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("shipwreck_treasure_hundred", "chests/shipwreck_treasure", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Shipwreck Supply - Bills
        addBillLoot("shipwreck_supply_one", "chests/shipwreck_supply", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("shipwreck_supply_five", "chests/shipwreck_supply", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("shipwreck_supply_ten", "chests/shipwreck_supply", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("shipwreck_supply_twenty", "chests/shipwreck_supply", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("shipwreck_supply_fifty", "chests/shipwreck_supply", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("shipwreck_supply_hundred", "chests/shipwreck_supply", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Spawn Bonus Chest - Bills
        addBillLoot("spawn_bonus_chest_one", "chests/spawn_bonus_chest", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("spawn_bonus_chest_five", "chests/spawn_bonus_chest", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("spawn_bonus_chest_ten", "chests/spawn_bonus_chest", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("spawn_bonus_chest_twenty", "chests/spawn_bonus_chest", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("spawn_bonus_chest_fifty", "chests/spawn_bonus_chest", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("spawn_bonus_chest_hundred", "chests/spawn_bonus_chest", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Simple Dungeon - Bills
        addBillLoot("simple_dungeon_one", "chests/simple_dungeon", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("simple_dungeon_five", "chests/simple_dungeon", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("simple_dungeon_ten", "chests/simple_dungeon", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("simple_dungeon_twenty", "chests/simple_dungeon", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("simple_dungeon_fifty", "chests/simple_dungeon", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("simple_dungeon_hundred", "chests/simple_dungeon", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Stronghold Corridor - Bills
        addBillLoot("stronghold_corridor_one", "chests/stronghold_corridor", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("stronghold_corridor_five", "chests/stronghold_corridor", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("stronghold_corridor_ten", "chests/stronghold_corridor", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("stronghold_corridor_twenty", "chests/stronghold_corridor", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("stronghold_corridor_fifty", "chests/stronghold_corridor", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("stronghold_corridor_hundred", "chests/stronghold_corridor", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Stronghold Crossing - Bills
        addBillLoot("stronghold_crossing_one", "chests/stronghold_crossing", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("stronghold_crossing_five", "chests/stronghold_crossing", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("stronghold_crossing_ten", "chests/stronghold_crossing", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("stronghold_crossing_twenty", "chests/stronghold_crossing", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("stronghold_crossing_fifty", "chests/stronghold_crossing", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("stronghold_crossing_hundred", "chests/stronghold_crossing", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Stronghold Library - Bills
        addBillLoot("stronghold_library_one", "chests/stronghold_library", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("stronghold_library_five", "chests/stronghold_library", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("stronghold_library_ten", "chests/stronghold_library", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("stronghold_library_twenty", "chests/stronghold_library", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("stronghold_library_fifty", "chests/stronghold_library", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("stronghold_library_hundred", "chests/stronghold_library", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Underwater Ruin Big - Bills
        addBillLoot("underwater_ruin_big_one", "chests/underwater_ruin_big", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("underwater_ruin_big_five", "chests/underwater_ruin_big", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("underwater_ruin_big_ten", "chests/underwater_ruin_big", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("underwater_ruin_big_twenty", "chests/underwater_ruin_big", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("underwater_ruin_big_fifty", "chests/underwater_ruin_big", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("underwater_ruin_big_hundred", "chests/underwater_ruin_big", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Underwater Ruin Small - Bills
        addBillLoot("underwater_ruin_small_one", "chests/underwater_ruin_small", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("underwater_ruin_small_five", "chests/underwater_ruin_small", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("underwater_ruin_small_ten", "chests/underwater_ruin_small", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("underwater_ruin_small_twenty", "chests/underwater_ruin_small", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("underwater_ruin_small_fifty", "chests/underwater_ruin_small", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("underwater_ruin_small_hundred", "chests/underwater_ruin_small", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());

        // Village Toolsmith - Bills
        addBillLoot("village_toolsmith_one", "chests/village/village_toolsmith", 0.15f, ModItems.ONE_DOLLAR_BILL.get());
        addBillLoot("village_toolsmith_five", "chests/village/village_toolsmith", 0.10f, ModItems.FIVE_DOLLAR_BILL.get());
        addBillLoot("village_toolsmith_ten", "chests/village/village_toolsmith", 0.07f, ModItems.TEN_DOLLAR_BILL.get());
        addBillLoot("village_toolsmith_twenty", "chests/village/village_toolsmith", 0.05f, ModItems.TWENTY_DOLLAR_BILL.get());
        addBillLoot("village_toolsmith_fifty", "chests/village/village_toolsmith", 0.03f, ModItems.FIFTY_DOLLAR_BILL.get());
        addBillLoot("village_toolsmith_hundred", "chests/village/village_toolsmith", 0.01f, ModItems.ONE_HUNDRED_DOLLAR_BILL.get());
    }

    private void addBillLoot(String name, String lootTablePath, float chance, net.minecraft.world.item.Item item) {
        add(name, new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", lootTablePath)).build(),
                LootItemRandomChanceCondition.randomChance(chance).build()
        }, item));
    }
}
