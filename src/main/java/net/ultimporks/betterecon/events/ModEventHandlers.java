package net.ultimporks.betterecon.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ultimporks.betterecon.BetterEconomy;
import net.ultimporks.betterecon.currency.BalanceCapability;
import net.ultimporks.betterecon.init.ModEnchantments;
import net.ultimporks.betterecon.init.ModItems;
import net.ultimporks.betterecon.init.ModVillagers;
import net.ultimporks.betterecon.util.PlayerSavedData;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.configs.ModConfigs;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModEventHandlers {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity().level().isClientSide) return;
        Player player = event.getEntity();
        UUID playerUUID = player.getUUID();
        // Check SavedPlayerData to see if they have Joined before
        PlayerSavedData data = PlayerSavedData.get(player.getServer());

        // If the player is NOT known, give them the starting money set in config.
        if (!data.isPlayerKnown(playerUUID)) {
            data.addPlayer(playerUUID);
            int startingBalance = ModConfigs.COMMON.startingAmount.get();
            String currencySymbol = ModConfigs.COMMON.currencySymbol.get();

            player.getCapability(BalanceCapability.BALANCE_CAPABILITY).ifPresent(balance -> {
                balance.setBalance(startingBalance);
                BetterEconomy.LOGGING("Giving " + player.getName().getString() + " their government assistance of " + currencySymbol + startingBalance);
                if (ModConfigs.COMMON.sendGaveCurrencyMessage.get()) {
                    player.sendSystemMessage(Component.literal(currencySymbol + startingBalance + " has been added to your 'Enderman Bank' bank account!").withStyle(ChatFormatting.GREEN));
                }
            });
        } else {
            BetterEconomy.LOGGING("Player is known, skipping Government Assistance");
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (!player.getCapability(BalanceCapability.BALANCE_CAPABILITY).isPresent()) {
                event.addCapability(
                        new ResourceLocation(Reference.MOD_ID, "balance"),
                        new BalanceCapability()
                );
                BetterEconomy.LOGGING("did not have a Balance, adding.");
            } else {
                BetterEconomy.LOGGING("already has a Balance, skipping.");
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();

        event.getOriginal().getCapability(BalanceCapability.BALANCE_CAPABILITY).ifPresent(oldCap -> {
            event.getEntity().getCapability(BalanceCapability.BALANCE_CAPABILITY).ifPresent(newCap -> {
                newCap.deserializeNBT(oldCap.serializeNBT());
            });
        });

        event.getOriginal().invalidateCaps();
    }

    @SubscribeEvent
    public static void onServerLoad(ServerStartingEvent event) {
        if (ModConfigs.COMMON.resetGiveCurrency.get()) {
            PlayerSavedData data = PlayerSavedData.get(event.getServer());
            data.resetKnownPlayers();

            ModConfigs.COMMON.resetGiveCurrency.set(false);
            System.out.println("BetterEconomy - Player Currency has been reset!");
        }
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == ModVillagers.BANKER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // ----------------------
            // Level 1 – Novice
            // ----------------------
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.ONE_DOLLAR_BILL.get(), 2),
                    6, 2, 0.05F)); // bad rate, low uses

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.ONE_DOLLAR_BILL.get(), 5),
                    new ItemStack(Items.EMERALD, 1),
                    6, 2, 0.05F));

            // ----------------------
            // Level 2 – Apprentice
            // ----------------------
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.ONE_DOLLAR_BILL.get(), 5),
                    8, 3, 0.05F));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.ONE_DOLLAR_BILL.get(), 6),
                    new ItemStack(Items.EMERALD, 1),
                    8, 3, 0.05F));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.FIVE_DOLLAR_BILL.get(), 1),
                    8, 3, 0.05F));

            // ----------------------
            // Level 3 – Journeyman
            // ----------------------
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.FIVE_DOLLAR_BILL.get(), 1),
                    6, 5, 0.05F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.ONE_DOLLAR_BILL.get(), 6),
                    new ItemStack(Items.EMERALD, 1),
                    6, 5, 0.05F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ModItems.FIFTY_DOLLAR_BILL.get(), 1),
                    6, 5, 0.05F));

            // ----------------------
            // Level 4 – Expert
            // ----------------------
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.ONE_HUNDRED_DOLLAR_BILL.get(), 1),
                    4, 10, 0.05F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.ONE_HUNDRED_DOLLAR_BILL.get(), 1),
                    new ItemStack(Items.EMERALD, 12),
                    4, 10, 0.05F));

            // Cashback Enchanted Book for Debit Card
            trades.get(4).add((pTrader, pRandom) -> {
                ItemStack enchantedBook = EnchantedBookItem.createForEnchantment(
                        new EnchantmentInstance(ModEnchantments.CASH_BACK.get(), 1));
                return new MerchantOffer(
                        new ItemStack(ModItems.TWENTY_DOLLAR_BILL.get(), 4),
                        new ItemStack(Items.BOOK),
                        enchantedBook,
                        4, 10, 0.05F);
            });

            // ----------------------
            // Level 5 – Master
            // ----------------------
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.ONE_HUNDRED_DOLLAR_BILL.get(), 2),
                    3, 15, 0.05F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.ONE_HUNDRED_DOLLAR_BILL.get(), 1),
                    new ItemStack(Items.EMERALD, 14),
                    3, 15, 0.05F));
        }
    }
}
