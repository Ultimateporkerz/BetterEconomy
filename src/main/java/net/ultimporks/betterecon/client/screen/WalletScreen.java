package net.ultimporks.betterecon.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.util.menu.WalletMenu;

public class WalletScreen extends AbstractContainerScreen<WalletMenu> {
    private static final ResourceLocation WALLET_TEXTURE =
            new ResourceLocation(Reference.MOD_ID, "textures/gui/wallet.png");


    public WalletScreen(WalletMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
    }

    @Override
    protected void init() {
        this.imageHeight = 127;
        this.imageWidth = 176;
        this.inventoryLabelY = 10000;
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float v, int i, int i1) {
        int leftPos = this.leftPos;
        int topPos = this.topPos;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, WALLET_TEXTURE);

        pGuiGraphics.blit(WALLET_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
