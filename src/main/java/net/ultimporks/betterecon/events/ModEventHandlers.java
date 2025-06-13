package net.ultimporks.betterecon.events;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.ultimporks.betterecon.BetterEconomy;
import net.ultimporks.betterecon.util.PlayerSavedData;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.currency.Balance;
import net.ultimporks.betterecon.init.ModAttachmentTypes;

import java.util.UUID;

@EventBusSubscriber(modid = Reference.MOD_ID)
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

            Balance balance = player.getData(ModAttachmentTypes.BALANCE.get());
            balance.setBalance(startingBalance);
            BetterEconomy.LOGGING("Giving " + player.getName().getString() + " their government assistance of " + currencySymbol + startingBalance);
            player.sendSystemMessage(Component.literal(currencySymbol + startingBalance + " has been added to your 'Enderman Bank' bank account!").withStyle(ChatFormatting.GREEN));
        } else {
            BetterEconomy.LOGGING("Player is known, skipping Government Assistance");
        }
    }

}
