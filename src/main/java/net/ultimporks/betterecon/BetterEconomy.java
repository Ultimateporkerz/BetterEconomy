package net.ultimporks.betterecon;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.ultimporks.betterecon.block.entity.renderer.ShopBlockEntityRenderer;
import net.ultimporks.betterecon.client.screen.ShopCustomerScreen;
import net.ultimporks.betterecon.client.screen.ShopOwnerScreen;
import net.ultimporks.betterecon.client.screen.WalletScreen;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.init.*;
import net.ultimporks.betterecon.loot.ModLootModifiers;
import net.ultimporks.betterecon.network.ModMessages;
import net.ultimporks.betterecon.client.screen.ATMScreen;
import net.ultimporks.betterecon.util.PlayerSavedData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class BetterEconomy {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ModContainer modContainer;

    public BetterEconomy(FMLJavaModLoadingContext context) {
        this.modContainer = context.getContainer();
        IEventBus modEventBus = context.getModEventBus();

        this.registerConfigs();

        ModMenuTypes.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.registerItems(modEventBus);
        ModCreativeTabs.registerCreativeTab(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModEnchantments.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
    }

    private void registerConfigs() {
        ModConfig commonConfig = new ModConfig(
                ModConfig.Type.COMMON,
                ModConfigs.COMMON_SPEC,
                this.modContainer
        );
        this.modContainer.addConfig(commonConfig);
    }

    public static void LOGGING(String message) {
        if (ModConfigs.COMMON.enableDebugging.get()) {
            LOGGER.info("BetterEconomy LOGGER {}", message);
        }
    }

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void registerScreens(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.ATM_MENU.get(), ATMScreen::new);
            MenuScreens.register(ModMenuTypes.WALLET_MENU.get(), WalletScreen::new);
            MenuScreens.register(ModMenuTypes.SHOP_OWNER_MENU.get(), ShopOwnerScreen::new);
            MenuScreens.register(ModMenuTypes.SHOP_CUSTOMER_MENU.get(), ShopCustomerScreen::new);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.SHOP_BLOCK_BE.get(), ShopBlockEntityRenderer::new);
        }

    }



}
