package net.ultimporks.betterecon;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.ultimporks.betterecon.block.entity.renderer.ShopBlockEntityRenderer;
import net.ultimporks.betterecon.client.screen.ShopCustomerScreen;
import net.ultimporks.betterecon.client.screen.ShopOwnerScreen;
import net.ultimporks.betterecon.client.screen.WalletScreen;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.init.*;
import net.ultimporks.betterecon.network.NetworkHandler;
import net.ultimporks.betterecon.client.screen.ATMScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class BetterEconomy {
    private static final Logger LOGGER = LogManager.getLogger();

    public BetterEconomy(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, ModConfigs.COMMON_SPEC);

        ModAttachmentTypes.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.registerItems(modEventBus);
        ModCreativeTabs.registerCreativeTab(modEventBus);
        ModDataComponents.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        modEventBus.addListener(NetworkHandler::register);
    }

    public static void LOGGING(String message) {
        if (ModConfigs.COMMON.enableDebugging.get()) {
            LOGGER.info("BetterEconomy LOGGER {}", message);
        }
    }

    @EventBusSubscriber(modid = Reference.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.ATM_MENU.get(), ATMScreen::new);
            event.register(ModMenuTypes.WALLET_MENU.get(), WalletScreen::new);
            event.register(ModMenuTypes.SHOP_OWNER_MENU.get(), ShopOwnerScreen::new);
            event.register(ModMenuTypes.SHOP_CUSTOMER_MENU.get(), ShopCustomerScreen::new);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.SHOP_BLOCK_BE.get(), ShopBlockEntityRenderer::new);
        }

    }



}
