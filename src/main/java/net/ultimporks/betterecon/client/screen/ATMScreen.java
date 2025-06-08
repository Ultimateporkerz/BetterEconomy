package net.ultimporks.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.network.C2SMessageDeposit;
import net.ultimporks.betterecon.network.C2SMessageWithdraw;
import net.ultimporks.betterecon.util.menu.ATMMenu;
import net.ultimporks.client.ClientData;

public class ATMScreen extends AbstractContainerScreen<ATMMenu> {
    private static final ResourceLocation ATM_TEXTURE = ResourceLocation.
            fromNamespaceAndPath(Reference.MOD_ID, "textures/gui/atm.png");

    private ATMPage currentPage = ATMPage.HOME;

    private int balance;
    private int selectedAmount = 0;

    private static final int SCREEN_BUTTON_HEIGHT = 10;
    private static final int SCREEN_BUTTON_WIDTH = 33;

    private static final int CLICKABLE_BUTTON_HEIGHT = 8;
    private static final int CLICKABLE_BUTTON_WIDTH = 14;

    // UI Element Positions
    private int balanceTextLeft, balanceTextTop;
    private int selectedAmountTextLeft, selectedAmountTextTop;
    private int welcomeTextLeft, welcomeTextTop;
    private int depositButtonLeft, depositButtonTop;
    private int depositTextLeft, depositTextTop;
    private int depositClickButtonLeft, depositClickButtonTop;
    private int withdrawButtonLeft, withdrawButtonTop;
    private int withdrawTextLeft, withdrawTextTop;
    private int withdrawClickButtonLeft, withdrawClickButtonTop;
    private int backButtonLeft, backButtonTop;
    private int backTextLeft, backTextTop;
    private int backClickButtonLeft, backClickButtonTop;

    private int confirmButtonLeft, confirmButtonTop;
    private int confirmTextLeft, confirmTextTop;
    private int confirmClickButtonLeft, confirmClickButtonTop;

    private int oneButtonLeft, oneButtonTop;
    private int oneTextLeft, oneTextTop;
    private int oneClickButtonLeft, oneClickButtonTop;

    private int tenButtonLeft, tenButtonTop;
    private int tenTextLeft, tenTextTop;
    private int tenClickButtonLeft, tenClickButtonTop;

    private int oneHundredButtonLeft, oneHundredButtonTop;
    private int oneHundredTextLeft, oneHundredTextTop;
    private int oneHundredClickButtonLeft, oneHundredClickButtonTop;

    private int maxButtonLeft, maxButtonTop;
    private int maxTextLeft, maxTextTop;
    private int maxClickButtonLeft, maxClickButtonTop;


    public ATMScreen(ATMMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
    }

    @Override
    protected void init() {
        super.init();
        this.balance = ClientData.getBalance();
        this.inventoryLabelY = 10000;
        this.titleLabelY = -10;
        // Middle of the Screen X and Y
        int xPosMiddle = (this.width - this.imageWidth) / 2;
        int yPosMiddle = (this.height - this.imageHeight) / 2;
        // Balance text
        this.balanceTextLeft = xPosMiddle + 50;
        this.balanceTextTop = yPosMiddle + 12;
        // Selected Amount Text
        this.selectedAmountTextLeft = xPosMiddle + 50;
        this.selectedAmountTextTop = yPosMiddle + 12;
        // Welcome Text
        this.welcomeTextLeft = xPosMiddle + 36;
        this.welcomeTextTop = yPosMiddle + 24;
        // Deposit button and text
        this.depositButtonLeft = xPosMiddle + 30;
        this.depositButtonTop = yPosMiddle + 61;
        this.depositTextLeft = depositButtonLeft + 4;
        this.depositTextTop = depositButtonTop + 2;
        this.depositClickButtonLeft = xPosMiddle + 11;
        this.depositClickButtonTop = yPosMiddle + 62;
        // Withdraw button and text
        this.withdrawButtonLeft = xPosMiddle + 114;
        this.withdrawButtonTop = yPosMiddle + 61;
        this.withdrawTextLeft = withdrawButtonLeft + 2;
        this.withdrawTextTop = withdrawButtonTop + 3;
        this.withdrawClickButtonLeft = xPosMiddle + 151;
        this.withdrawClickButtonTop = yPosMiddle + 62;
        // Back button and text
        this.backButtonLeft = xPosMiddle + 30;
        this.backButtonTop = yPosMiddle + 61;
        this.backTextLeft = backButtonLeft + 6;
        this.backTextTop = backButtonTop + 3;
        this.backClickButtonLeft = xPosMiddle + 11;
        this.backClickButtonTop = yPosMiddle + 62;
        // Price Buttons
        this.oneButtonLeft = xPosMiddle + 30;
        this.oneButtonTop = yPosMiddle + 27;
        this.oneTextLeft = oneButtonLeft + 14;
        this.oneTextTop = oneButtonTop + 2;
        this.oneClickButtonLeft = xPosMiddle + 11;
        this.oneClickButtonTop = yPosMiddle + 28;

        this.tenButtonLeft = xPosMiddle + 30;
        this.tenButtonTop = yPosMiddle + 44;
        this.tenTextLeft = tenButtonLeft + 12;
        this.tenTextTop = tenButtonTop + 2;
        this.tenClickButtonLeft = xPosMiddle + 11;
        this.tenClickButtonTop = yPosMiddle + 44;

        this.oneHundredButtonLeft = xPosMiddle + 114;
        this.oneHundredButtonTop = yPosMiddle + 27;
        this.oneHundredTextLeft = oneHundredButtonLeft + 10;
        this.oneHundredTextTop = oneHundredButtonTop + 2;
        this.oneHundredClickButtonLeft = xPosMiddle + 151;
        this.oneHundredClickButtonTop = yPosMiddle + 28;

        this.maxButtonLeft = xPosMiddle + 114;
        this.maxButtonTop = yPosMiddle + 44;
        this.maxTextLeft = maxButtonLeft + 10;
        this.maxTextTop = maxButtonTop + 2;
        this.maxClickButtonLeft = xPosMiddle + 151;
        this.maxClickButtonTop = yPosMiddle + 45;

        // Confirm Button
        this.confirmButtonLeft = xPosMiddle + 114;
        this.confirmButtonTop = yPosMiddle + 61;
        this.confirmTextLeft = confirmButtonLeft + 3;
        this.confirmTextTop = confirmButtonTop + 2;
        this.confirmClickButtonLeft = xPosMiddle + 151;
        this.confirmClickButtonTop = yPosMiddle + 62;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int leftPos = this.leftPos;
        int topPos = this.topPos;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, ATM_TEXTURE);

        pGuiGraphics.blit(ATM_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        switch (currentPage) {
            case HOME -> renderHomePage(pGuiGraphics);
            case DEPOSIT -> renderDepositPage(pGuiGraphics);
            case WITHDRAW -> renderWithdrawPage(pGuiGraphics);
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(pGuiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(pGuiGraphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (currentPage == ATMPage.HOME) {
            // Deposit Button
            if (isMouseOver((int) mouseX, (int) mouseY, depositClickButtonLeft, depositClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                currentPage = ATMPage.DEPOSIT;
                return true;
            }

            // Withdraw Button
            if (isMouseOver((int) mouseX, (int) mouseY, withdrawClickButtonLeft, withdrawClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                currentPage = ATMPage.WITHDRAW;
                return true;
            }
        }

        if (currentPage == ATMPage.DEPOSIT) {
            int maxSelectable = menu.getDepositTotal();

            // Back Button
            if (isMouseOver((int) mouseX, (int) mouseY, backClickButtonLeft, backClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                currentPage = ATMPage.HOME;
                selectedAmount = 0;
                return true;
            }

            if (isMouseOver((int) mouseX, (int) mouseY, oneClickButtonLeft, oneClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 1, maxSelectable);
                return true;
            }
            if (isMouseOver((int) mouseX, (int) mouseY, tenClickButtonLeft, tenClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 10, maxSelectable);
                return true;
            }
            if (isMouseOver((int) mouseX, (int) mouseY, oneHundredClickButtonLeft, oneHundredClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 100, maxSelectable);
                return true;
            }
            if (isMouseOver((int) mouseX, (int) mouseY, maxClickButtonLeft, maxClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = maxSelectable;
                return true;
            }

            if (isMouseOver((int) mouseX, (int) mouseY, confirmClickButtonLeft, confirmClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                // Take money from inventory to deposit it
                PacketDistributor.sendToServer(new C2SMessageDeposit(selectedAmount));
                selectedAmount = 0;
                return true;
            }
        } else if (currentPage == ATMPage.WITHDRAW) {
            // Back Button
            if (isMouseOver((int) mouseX, (int) mouseY, backClickButtonLeft, backClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                currentPage = ATMPage.HOME;
                selectedAmount = 0;
                return true;
            }

            if (isMouseOver((int) mouseX, (int) mouseY, oneClickButtonLeft, oneClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 1, balance);
                return true;
            }
            if (isMouseOver((int) mouseX, (int) mouseY, tenClickButtonLeft, tenClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 10, balance);
                return true;
            }
            if (isMouseOver((int) mouseX, (int) mouseY, oneHundredClickButtonLeft, oneHundredClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 100, balance);
                return true;
            }
            if (isMouseOver((int) mouseX, (int) mouseY, maxClickButtonLeft, maxClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = balance;
                return true;
            }

            if (isMouseOver((int) mouseX, (int) mouseY, confirmClickButtonLeft, confirmClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                // Give money to player from ATM
                PacketDistributor.sendToServer(new C2SMessageWithdraw(selectedAmount));
                selectedAmount = 0;
                return true;
            }


        }

        return super.mouseClicked(mouseX, mouseY, button);
    }


    // Pages
    private enum ATMPage {
        HOME,
        DEPOSIT,
        WITHDRAW,
    }
    private void renderHomePage(GuiGraphics pGuiGraphics) {
        renderBalance(pGuiGraphics);
        renderOptions(pGuiGraphics);
        float LabelScaleWelcome = 0.75f;
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(LabelScaleWelcome, LabelScaleWelcome, LabelScaleWelcome);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Welcome to 'Enderman Bank'", (int)((welcomeTextLeft) / LabelScaleWelcome), (int)((welcomeTextTop) / LabelScaleWelcome), 0xFF776ED8);
        pGuiGraphics.drawString(Minecraft.getInstance().font, ClientData.getPlayerName() + "!", (int)((welcomeTextLeft + 48) / LabelScaleWelcome), (int)((welcomeTextTop + 10) / LabelScaleWelcome), 0xFF776ED8);

        pGuiGraphics.pose().popPose();
    }
    private void renderDepositPage(GuiGraphics pGuiGraphics) {
        renderSelectedAmount(pGuiGraphics);
        renderPriceButtons(pGuiGraphics);
        renderBackButtons(pGuiGraphics);
        renderConfirmButton(pGuiGraphics);

    }
    private void renderWithdrawPage(GuiGraphics pGuiGraphics) {
        renderSelectedAmount(pGuiGraphics);
        renderPriceButtons(pGuiGraphics);
        renderBackButtons(pGuiGraphics);
        renderConfirmButton(pGuiGraphics);

    }

    private void renderSelectedAmount(GuiGraphics pGuiGraphics) {
        float labelScaleBalance = 0.80f;
        // Render Balance
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScaleBalance, labelScaleBalance, labelScaleBalance);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Selected Amount: " + selectedAmount, (int)((balanceTextLeft) / labelScaleBalance), (int)((balanceTextTop) / labelScaleBalance), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
    }

    private void renderBalance(GuiGraphics pGuiGraphics) {
        float labelScaleBalance = 0.80f;
        // Render Balance
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScaleBalance, labelScaleBalance, labelScaleBalance);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Account Balance: " + balance, (int)((balanceTextLeft) / labelScaleBalance), (int)((balanceTextTop) / labelScaleBalance), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
    }
    private void renderOptions(GuiGraphics pGuiGraphics) {
        double mouseX = Minecraft.getInstance().mouseHandler.xpos() * this.width / Minecraft.getInstance().getWindow().getScreenWidth();
        double mouseY = Minecraft.getInstance().mouseHandler.ypos() * this.height / Minecraft.getInstance().getWindow().getScreenHeight();
        // Render Deposit Button
        if (!isMouseOver((int) mouseX, (int) mouseY, depositClickButtonLeft, depositClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, depositButtonLeft, depositButtonTop, 176, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, depositButtonLeft, depositButtonTop, 176, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        pGuiGraphics.pose().pushPose();
        float labelScaleDeposit = 0.75f;
        pGuiGraphics.pose().scale(labelScaleDeposit, labelScaleDeposit, labelScaleDeposit);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Deposit", (int)((depositTextLeft) / labelScaleDeposit), (int)((depositTextTop) / labelScaleDeposit), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
        // Render Withdraw Button
        float labelScaleWithdraw = 0.70f;
        if (!isMouseOver((int) mouseX, (int) mouseY, withdrawClickButtonLeft, withdrawClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, withdrawButtonLeft, withdrawButtonTop, 176, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, withdrawButtonLeft, withdrawButtonTop, 176, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScaleWithdraw, labelScaleWithdraw, labelScaleWithdraw);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Withdraw", (int)((withdrawTextLeft) / labelScaleWithdraw), (int)((withdrawTextTop) / labelScaleWithdraw), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
        // Render Clickable Deposit Button
        pGuiGraphics.blit(ATM_TEXTURE, depositClickButtonLeft, depositClickButtonTop, 176, 31, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        // Render Clickable Withdraw Button
        pGuiGraphics.blit(ATM_TEXTURE, withdrawClickButtonLeft, withdrawClickButtonTop, 176, 22, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
    }
    private void renderPriceButtons(GuiGraphics pGuiGraphics) {
        double mouseX = Minecraft.getInstance().mouseHandler.xpos() * this.width / Minecraft.getInstance().getWindow().getScreenWidth();
        double mouseY = Minecraft.getInstance().mouseHandler.ypos() * this.height / Minecraft.getInstance().getWindow().getScreenHeight();
        float labelScalePrice = 0.80f;

        // Render Screen Button
        if (!isMouseOver((int) mouseX, (int) mouseY, oneClickButtonLeft, oneClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, oneButtonLeft, oneButtonTop, 176, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, oneButtonLeft, oneButtonTop, 176, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        if (!isMouseOver((int) mouseX, (int) mouseY, tenClickButtonLeft, tenClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, tenButtonLeft, tenButtonTop, 176, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, tenButtonLeft, tenButtonTop, 176, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        if (!isMouseOver((int) mouseX, (int) mouseY, oneHundredClickButtonLeft, oneHundredClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, oneHundredButtonLeft, oneHundredButtonTop, 176, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, oneHundredButtonLeft, oneHundredButtonTop, 176, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        if (!isMouseOver((int) mouseX, (int) mouseY, maxClickButtonLeft, maxClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, maxButtonLeft, maxButtonTop, 176, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, maxButtonLeft, maxButtonTop, 176, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        // Render Button Texts
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScalePrice, labelScalePrice, labelScalePrice);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "1", (int)((oneTextLeft) / labelScalePrice), (int)((oneTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "10", (int)((tenTextLeft) / labelScalePrice), (int)((tenTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "100", (int)((oneHundredTextLeft) / labelScalePrice), (int)((oneHundredTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Max", (int)((maxTextLeft) / labelScalePrice), (int)((maxTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
        // Render Clickable Buttons
        pGuiGraphics.blit(ATM_TEXTURE, oneClickButtonLeft, oneClickButtonTop, 176, 31, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        pGuiGraphics.blit(ATM_TEXTURE, tenClickButtonLeft, tenClickButtonTop, 176, 31, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        pGuiGraphics.blit(ATM_TEXTURE, oneHundredClickButtonLeft, oneHundredClickButtonTop, 176, 22, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        pGuiGraphics.blit(ATM_TEXTURE, maxClickButtonLeft, maxClickButtonTop, 176, 22, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
    }
    private void renderBackButtons(GuiGraphics pGuiGraphics) {
        double mouseX = Minecraft.getInstance().mouseHandler.xpos() * this.width / Minecraft.getInstance().getWindow().getScreenWidth();
        double mouseY = Minecraft.getInstance().mouseHandler.ypos() * this.height / Minecraft.getInstance().getWindow().getScreenHeight();
        float labelScaleBack = 0.75f;
        if (!isMouseOver((int) mouseX, (int) mouseY, backClickButtonLeft, backClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, backButtonLeft, backButtonTop, 176, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, backButtonLeft, backButtonTop, 176, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScaleBack, labelScaleBack, labelScaleBack);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Back", (int)((backTextLeft) / labelScaleBack), (int)((backTextTop) / labelScaleBack), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
        // Render Clickable Back Button
        pGuiGraphics.blit(ATM_TEXTURE, backClickButtonLeft, backClickButtonTop, 176, 31, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
    }
    private void renderConfirmButton(GuiGraphics pGuiGraphics) {
        double mouseX = Minecraft.getInstance().mouseHandler.xpos() * this.width / Minecraft.getInstance().getWindow().getScreenWidth();
        double mouseY = Minecraft.getInstance().mouseHandler.ypos() * this.height / Minecraft.getInstance().getWindow().getScreenHeight();
        float labelScaleConfirm = 0.75f;
        if (!isMouseOver((int) mouseX, (int) mouseY, confirmClickButtonLeft, confirmClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, confirmButtonLeft, confirmButtonTop, 176, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, confirmButtonLeft, confirmButtonTop, 176, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScaleConfirm, labelScaleConfirm, labelScaleConfirm);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Confirm", (int)((confirmTextLeft) / labelScaleConfirm), (int)((confirmTextTop) / labelScaleConfirm), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
        // Render Clickable Confirm Button
        pGuiGraphics.blit(ATM_TEXTURE, confirmClickButtonLeft, confirmClickButtonTop, 176, 22, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
    }


    // Helper Methods
    private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
