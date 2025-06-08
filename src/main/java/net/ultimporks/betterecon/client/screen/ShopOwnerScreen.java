package net.ultimporks.betterecon.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.ultimporks.betterecon.BetterEconomy;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.client.ClientData;
import net.ultimporks.betterecon.util.menu.ShopMenu;

import java.util.UUID;

public class ShopScreen extends AbstractContainerScreen<ShopMenu> {
    private static final ResourceLocation OWNER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/gui/shop_owner.png");

    private static final ResourceLocation CUSTOMER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/gui/shop_customer.png");

    public ShopScreen(ShopMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    private UUID playerUUID;
    private UUID ownerUUID;

    @Override
    protected void init() {
        super.init();
        this.playerUUID = ClientData.getCurrentPlayerUUID();
        this.ownerUUID = ClientData.getOwnerPlayerUUID();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float v, int i, int i1) {
        int leftPos = this.leftPos;
        int topPos = this.topPos;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        if (ownerUUID == null) {
            BetterEconomy.LOGGING("(ShopScreen) - Owner is NULL");
            return;
        }

        if (ownerUUID.equals(playerUUID)) {
            // Owner of the Block, Render owner GUI
            RenderSystem.setShaderTexture(0, OWNER_TEXTURE);
            pGuiGraphics.blit(OWNER_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
            renderShopInventory(pGuiGraphics);
            renderInfoHover(pGuiGraphics);
        } else {
            // Customer of the Block, Render customer GUI
            RenderSystem.setShaderTexture(0, CUSTOMER_TEXTURE);
            pGuiGraphics.blit(CUSTOMER_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        }
    }

    // Owner Screen GUI
    private void renderShopInventory(GuiGraphics guiGraphics) {

    }
    private void renderInfoHover(GuiGraphics guiGraphics) {

    }


    // Customer Screen GUI

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
