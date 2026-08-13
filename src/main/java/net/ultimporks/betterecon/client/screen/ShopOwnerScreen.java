package net.ultimporks.betterecon.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.ultimporks.betterecon.BetterEconomy;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.client.ClientData;
import net.ultimporks.betterecon.network.ModMessages;
import net.ultimporks.betterecon.network.shop.C2SMessageSaveSellPrice;
import net.ultimporks.betterecon.util.menu.ShopOwnerMenu;

public class ShopOwnerScreen extends AbstractContainerScreen<ShopOwnerMenu> {
    private static final ResourceLocation OWNER_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/shop_owner.png");

    private String currencySymbol;
    private int sellingPrice;
    private BlockPos shopBlockPos;

    private static final int INFO_HOVER_HEIGHT = 24;
    private static final int INFO_HOVER_WIDTH = 24;

    private static final int CONFIRM_BUTTON_HEIGHT = 11;
    private static final int CONFIRM_BUTTON_WIDTH = 36;

    private static final int ONE_BUTTON_HEIGHT = 11;
    private static final int ONE_BUTTON_WIDTH = 10;

    private static final int TEN_BUTTON_HEIGHT = 11;
    private static final int TEN_BUTTON_WIDTH = 15;

    private static final int FIFTY_BUTTON_HEIGHT = 11;
    private static final int FIFTY_BUTTON_WIDTH = 18;

    private static final int ONEHUNDRED_BUTTON_HEIGHT = 11;
    private static final int ONEHUNDRED_BUTTON_WIDTH = 20;


    // UI Element Positions
    private int infoTextLeft, infoTextTop;
    private int priceSelectionLeft, priceSelectionTop;
    private int oneButtonLeft, oneButtonTop, negativeOneButtonLeft, negativeOneButtonTop,
                tenButtonLeft, tenButtonTop, negativeTenButtonLeft, negativeTenButtonTop,
                fiftyButtonLeft, fiftyButtonTop, negativeFiftyButtonLeft, negativeFiftyButtonTop,
                oneHundredButtonLeft, oneHundredButtonTop, negativeOneHundredButtonLeft, negativeOneHundredButtonTop,
                confirmButtonLeft, confirmButtonTop;

    public ShopOwnerScreen(ShopOwnerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        this.imageHeight = 192;
        this.imageWidth = 176;
        super.init();
        this.sellingPrice = ClientData.getSellPrice();
        this.currencySymbol = ClientData.getCurrencySymbol();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
        this.shopBlockPos = menu.getBlockPos();
        // Middle of the Screen X and Y
        int xPosMiddle = (this.width - this.imageWidth) / 2;
        int yPosMiddle = (this.height - this.imageHeight) / 2;
        // Info Hover
        this.infoTextLeft = xPosMiddle + 8;
        this.infoTextTop = yPosMiddle + 8;
        // Price Buttons
        this.oneButtonLeft = xPosMiddle + 99;
        this.oneButtonTop = yPosMiddle + 8;
        this.negativeOneButtonLeft = xPosMiddle + 88;
        this.negativeOneButtonTop = yPosMiddle + 8;

        this.tenButtonLeft = xPosMiddle + 110;
        this.tenButtonTop = yPosMiddle + 8;
        this.negativeTenButtonLeft = xPosMiddle + 72;
        this.negativeTenButtonTop = yPosMiddle + 8;

        this.fiftyButtonLeft = xPosMiddle + 126;
        this.fiftyButtonTop = yPosMiddle + 8;
        this.negativeFiftyButtonLeft = xPosMiddle + 54;
        this.negativeFiftyButtonTop = yPosMiddle + 8;

        this.oneHundredButtonLeft = xPosMiddle + 144;
        this.oneHundredButtonTop = yPosMiddle + 8;
        this.negativeOneHundredButtonLeft = xPosMiddle + 33;
        this.negativeOneHundredButtonTop = yPosMiddle + 8;
        // Confirm Button
        this.confirmButtonLeft = xPosMiddle + 124;
        this.confirmButtonTop = yPosMiddle + 34;
        // Price Selection
        this.priceSelectionLeft = xPosMiddle + 33;
        this.priceSelectionTop = yPosMiddle + 20;


    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(pGuiGraphics, mouseX, mouseY);
        renderInfoHover(pGuiGraphics, mouseX, mouseY);
        renderConfirmButton(pGuiGraphics, mouseX, mouseY);
        renderSelectedPrice(pGuiGraphics);
        renderOneButton(pGuiGraphics, mouseX, mouseY);
        renderTenButton(pGuiGraphics, mouseX, mouseY);
        renderFiftyButton(pGuiGraphics, mouseX, mouseY);
        renderOneHundredButton(pGuiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderTexture(0, OWNER_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        pGuiGraphics.blit(OWNER_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
// One
        if (isMouseOver((int) mouseX, (int) mouseY, oneButtonLeft, oneButtonTop, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT)) {
            handlePriceButtonClick(1);
            return true;
        }
        if (isMouseOver((int) mouseX, (int) mouseY, negativeOneButtonLeft, negativeOneButtonTop, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT)) {
            handlePriceButtonClick(-1);
            return true;
        }

        // Ten
        if (isMouseOver((int) mouseX, (int) mouseY, tenButtonLeft, tenButtonTop, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT)) {
            handlePriceButtonClick(10);
            return true;
        }
        if (isMouseOver((int) mouseX, (int) mouseY, negativeTenButtonLeft, negativeTenButtonTop, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT)) {
            handlePriceButtonClick(-10);
            return true;
        }

        // Fifty
        if (isMouseOver((int) mouseX, (int) mouseY, fiftyButtonLeft, fiftyButtonTop, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT)) {
            handlePriceButtonClick(50);
            return true;
        }
        if (isMouseOver((int) mouseX, (int) mouseY, negativeFiftyButtonLeft, negativeFiftyButtonTop, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT)) {
            handlePriceButtonClick(-50);
            return true;
        }

        // One Hundred
        if (isMouseOver((int) mouseX, (int) mouseY, oneHundredButtonLeft, oneHundredButtonTop, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT)) {
            handlePriceButtonClick(100);
            return true;
        }
        if (isMouseOver((int) mouseX, (int) mouseY, negativeOneHundredButtonLeft, negativeOneHundredButtonTop, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT)) {
            handlePriceButtonClick(-100);
            return true;
        }

        // Confirm
        if (isMouseOver((int) mouseX, (int) mouseY, confirmButtonLeft, confirmButtonTop, CONFIRM_BUTTON_WIDTH, CONFIRM_BUTTON_HEIGHT)) {
            handleConfirmClick();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void handlePriceButtonClick(int modifier) {
        sellingPrice += modifier;
        if (sellingPrice < 0) sellingPrice = 0;
    }

    private void handleConfirmClick() {
        int xPos = shopBlockPos.getX();
        int yPos = shopBlockPos.getY();
        int zPos = shopBlockPos.getZ();
        BetterEconomy.LOGGING("Clicked confirm" + shopBlockPos);
        ModMessages.sendToServer(new C2SMessageSaveSellPrice(sellingPrice, xPos, yPos, zPos));
    }

    private void renderInfoHover(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (isMouseOver(mouseX, mouseY, infoTextLeft, infoTextTop, INFO_HOVER_WIDTH, INFO_HOVER_HEIGHT)) {
            int paddingLeft = 15;  // 15 pixels from the left edge of the screen

            String infoText = Component.translatable("shop.info").getString();
            String[] lines = infoText.split("\n");

            int x = paddingLeft;
            int y = infoTextTop - 30;  // vertical position as you want (relative to GUI or screen top)
            int lineHeight = minecraft.font.lineHeight;

            for (String line : lines) {
                guiGraphics.drawString(minecraft.font, line, x, y, 0xFF776ED8);
                y += lineHeight;
            }

        }
    }
    private void renderConfirmButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        boolean hovered = isMouseOver(mouseX, mouseY, confirmButtonLeft, confirmButtonTop, CONFIRM_BUTTON_WIDTH, CONFIRM_BUTTON_HEIGHT);
        int u = 0;
        int v = hovered ? 226 : 215;

        guiGraphics.blit(OWNER_TEXTURE, confirmButtonLeft, confirmButtonTop, u, v, CONFIRM_BUTTON_WIDTH, CONFIRM_BUTTON_HEIGHT);
    }
    private void renderSelectedPrice(GuiGraphics guiGraphics) {
        String label = "Price: " + currencySymbol + sellingPrice;
        guiGraphics.drawString(minecraft.font, label, priceSelectionLeft, priceSelectionTop,0xFF80FF80,true);

    }

    private void renderOneButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        boolean oneHovered = isMouseOver(mouseX, mouseY, oneButtonLeft, oneButtonTop, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT);

        int xPos = 66;
        int yPos = oneHovered ? 204 : 193;

        guiGraphics.blit(OWNER_TEXTURE, oneButtonLeft, oneButtonTop, xPos, yPos, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT);

        boolean negativeOneHovered = isMouseOver(mouseX, mouseY, negativeOneButtonLeft, negativeOneButtonTop, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT);

        int nXPos = 55;
        int nYPos = negativeOneHovered ? 204 : 193;

        guiGraphics.blit(OWNER_TEXTURE, negativeOneButtonLeft, negativeOneButtonTop, nXPos, nYPos, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT);
    }
    private void renderTenButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        boolean tenHovered = isMouseOver(mouseX, mouseY, tenButtonLeft, tenButtonTop, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT);

        int xPos = 77;
        int yPos = tenHovered ? 204 : 193;

        guiGraphics.blit(OWNER_TEXTURE, tenButtonLeft, tenButtonTop, xPos, yPos, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT);

        boolean negativeTenHovered = isMouseOver(mouseX, mouseY, negativeTenButtonLeft, negativeTenButtonTop, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT);

        int nXPos = 39;
        int nYPos = negativeTenHovered ? 204 : 193;

        guiGraphics.blit(OWNER_TEXTURE, negativeTenButtonLeft, negativeTenButtonTop, nXPos, nYPos, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT);
    }
    private void renderFiftyButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        boolean fiftyHovered = isMouseOver(mouseX, mouseY, fiftyButtonLeft, fiftyButtonTop, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT);

        int xPos = 93;
        int yPos = fiftyHovered ? 204 : 193;

        guiGraphics.blit(OWNER_TEXTURE, fiftyButtonLeft, fiftyButtonTop, xPos, yPos, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT);

        boolean negativeFiftyHovered = isMouseOver(mouseX, mouseY, negativeFiftyButtonLeft, negativeFiftyButtonTop, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT);

        int nXPos = 21;
        int nYPos = negativeFiftyHovered ? 204 : 193;

        guiGraphics.blit(OWNER_TEXTURE, negativeFiftyButtonLeft, negativeFiftyButtonTop, nXPos, nYPos, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT);
    }
    private void renderOneHundredButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        boolean oneHundredHovered = isMouseOver(mouseX, mouseY, oneHundredButtonLeft, oneHundredButtonTop, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT);

        int xPos = 111;
        int yPos = oneHundredHovered ? 204 : 193;

        guiGraphics.blit(OWNER_TEXTURE, oneHundredButtonLeft, oneHundredButtonTop, xPos, yPos, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT);

        boolean negativeOneHundredHovered = isMouseOver(mouseX, mouseY, negativeOneHundredButtonLeft, negativeOneHundredButtonTop, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT);

        int nXPos = 0;
        int nYPos = negativeOneHundredHovered ? 204 : 193;

        guiGraphics.blit(OWNER_TEXTURE, negativeOneHundredButtonLeft, negativeOneHundredButtonTop, nXPos, nYPos, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT);
    }


    private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
