package net.ultimporks.betterecon.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.network.ModMessages;
import net.ultimporks.betterecon.network.atm.C2SMessageDeposit;
import net.ultimporks.betterecon.network.atm.C2SMessageWithdraw;
import net.ultimporks.betterecon.util.menu.ATMMenu;
import net.ultimporks.betterecon.client.ClientData;

public class ATMScreen extends AbstractContainerScreen<ATMMenu> {
    private static final ResourceLocation ATM_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/atm.png");

    private ATMPage currentPage = ATMPage.HOME;

    private String currencySymbol;
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
    private int fiveButtonLeft, fiveButtonTop;
    private int fiveTextLeft, fiveTextTop;
    private int fiveClickButtonLeft, fiveClickButtonTop;
    private int tenButtonLeft, tenButtonTop;
    private int tenTextLeft, tenTextTop;
    private int tenClickButtonLeft, tenClickButtonTop;
    private int twentyButtonLeft, twentyButtonTop;
    private int twentyTextLeft, twentyTextTop;
    private int twentyClickButtonLeft, twentyClickButtonTop;
    private int fiftyButtonLeft, fiftyButtonTop;
    private int fiftyTextLeft, fiftyTextTop;
    private int fiftyClickButtonLeft, fiftyClickButtonTop;
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
        this.imageWidth = 185;
        this.imageHeight = 144;
        super.init();
        this.balance = ClientData.getBalance();
        this.currencySymbol = ClientData.getCurrencySymbol();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
        // Middle of the Screen X and Y
        int xPosMiddle = (this.width - this.imageWidth) / 2;
        int yPosMiddle = (this.height - this.imageHeight) / 2;
        // Balance text
        this.balanceTextLeft = xPosMiddle + 54;
        this.balanceTextTop = yPosMiddle + 60;
        // Selected Amount Text
        this.selectedAmountTextLeft = xPosMiddle + 54;
        this.selectedAmountTextTop = yPosMiddle + 26;
        // Welcome Text
        this.welcomeTextLeft = xPosMiddle + 48;
        this.welcomeTextTop = yPosMiddle + 22;
        // Deposit button and text
        this.depositButtonLeft = xPosMiddle + 26;
        this.depositButtonTop = yPosMiddle + 108;
        this.depositTextLeft = depositButtonLeft + 3;
        this.depositTextTop = depositButtonTop + 2;
        this.depositClickButtonLeft = xPosMiddle + 6;
        this.depositClickButtonTop = yPosMiddle + 110;
        // Withdraw button and text
        this.withdrawButtonLeft = xPosMiddle + 124;
        this.withdrawButtonTop = yPosMiddle + 108;
        this.withdrawTextLeft = withdrawButtonLeft + 3;
        this.withdrawTextTop = withdrawButtonTop + 3;
        this.withdrawClickButtonLeft = xPosMiddle + 165;
        this.withdrawClickButtonTop = yPosMiddle + 110;
        // Back button and text
        this.backButtonLeft = xPosMiddle + 26;
        this.backButtonTop = yPosMiddle + 108;
        this.backTextLeft = backButtonLeft + 8;
        this.backTextTop = backButtonTop + 2;
        this.backClickButtonLeft = xPosMiddle + 6;
        this.backClickButtonTop = yPosMiddle + 110;
        // One Dollar
        this.oneButtonLeft = xPosMiddle + 26;
        this.oneButtonTop = yPosMiddle + 40;
        this.oneTextLeft = oneButtonLeft + 14;
        this.oneTextTop = oneButtonTop + 2;
        this.oneClickButtonLeft = xPosMiddle + 6;
        this.oneClickButtonTop = yPosMiddle + 40;
        // Five Dollars
        this.fiveButtonLeft = xPosMiddle + 26;
        this.fiveButtonTop = yPosMiddle + 55;
        this.fiveTextLeft = fiveButtonLeft + 14;
        this.fiveTextTop = fiveButtonTop + 2;
        this.fiveClickButtonLeft = xPosMiddle + 6;
        this.fiveClickButtonTop = yPosMiddle + 55;
        // Ten Dollars
        this.tenButtonLeft = xPosMiddle + 26;
        this.tenButtonTop = yPosMiddle + 70;
        this.tenTextLeft = tenButtonLeft + 12;
        this.tenTextTop = tenButtonTop + 2;
        this.tenClickButtonLeft = xPosMiddle + 6;
        this.tenClickButtonTop = yPosMiddle + 70;
        // Twenty Dollars
        this.twentyButtonLeft = xPosMiddle + 26;
        this.twentyButtonTop = yPosMiddle + 85;
        this.twentyTextLeft = twentyButtonLeft + 12;
        this.twentyTextTop = twentyButtonTop + 2;
        this.twentyClickButtonLeft = xPosMiddle + 6;
        this.twentyClickButtonTop = yPosMiddle + 85;
        // Fifty Dollars
        this.fiftyButtonLeft = xPosMiddle + 125;
        this.fiftyButtonTop = yPosMiddle + 40;
        this.fiftyTextLeft = fiftyButtonLeft + 12;
        this.fiftyTextTop = fiftyButtonTop + 2;
        this.fiftyClickButtonLeft = xPosMiddle + 165;
        this.fiftyClickButtonTop = yPosMiddle + 40;
        // One-Hundred Dollars
        this.oneHundredButtonLeft = xPosMiddle + 125;
        this.oneHundredButtonTop = yPosMiddle + 55;
        this.oneHundredTextLeft = oneHundredButtonLeft + 10;
        this.oneHundredTextTop = oneHundredButtonTop + 2;
        this.oneHundredClickButtonLeft = xPosMiddle + 165;
        this.oneHundredClickButtonTop = yPosMiddle + 55;
        // Max amount button
        this.maxButtonLeft = xPosMiddle + 125;
        this.maxButtonTop = yPosMiddle + 70;
        this.maxTextLeft = maxButtonLeft + 10;
        this.maxTextTop = maxButtonTop + 2;
        this.maxClickButtonLeft = xPosMiddle + 165;
        this.maxClickButtonTop = yPosMiddle + 70;
        // Confirm Button
        this.confirmButtonLeft = xPosMiddle + 125;
        this.confirmButtonTop = yPosMiddle + 108;
        this.confirmTextLeft = confirmButtonLeft + 3;
        this.confirmTextTop = confirmButtonTop + 2;
        this.confirmClickButtonLeft = xPosMiddle + 165;
        this.confirmClickButtonTop = yPosMiddle + 110;
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
            // One Dollar
            if (isMouseOver((int) mouseX, (int) mouseY, oneClickButtonLeft, oneClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 1, maxSelectable);
                return true;
            }
            // Five Dollar
            if (isMouseOver((int) mouseX, (int) mouseY, fiveClickButtonLeft, fiveClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 5, maxSelectable);
                return true;
            }
            // Ten Dollar
            if (isMouseOver((int) mouseX, (int) mouseY, tenClickButtonLeft, tenClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 10, maxSelectable);
                return true;
            }
            // Twenty Dollar
            if (isMouseOver((int) mouseX, (int) mouseY, twentyClickButtonLeft, twentyClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 20, maxSelectable);
                return true;
            }
            // Fifty Dollar
            if (isMouseOver((int) mouseX, (int) mouseY, fiftyClickButtonLeft, fiftyClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 50, maxSelectable);
                return true;
            }
            // One-Hundred dollars
            if (isMouseOver((int) mouseX, (int) mouseY, oneHundredClickButtonLeft, oneHundredClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 100, maxSelectable);
                return true;
            }
            // Max Button
            if (isMouseOver((int) mouseX, (int) mouseY, maxClickButtonLeft, maxClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = maxSelectable;
                return true;
            }
            // Confirm Button
            if (isMouseOver((int) mouseX, (int) mouseY, confirmClickButtonLeft, confirmClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                ModMessages.sendToServer(new C2SMessageDeposit(selectedAmount));
                selectedAmount = 0;
                return true;
            }
        }
        if (currentPage == ATMPage.WITHDRAW) {
            // Back Button
            if (isMouseOver((int) mouseX, (int) mouseY, backClickButtonLeft, backClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                currentPage = ATMPage.HOME;
                selectedAmount = 0;
                return true;
            }
            // One Dollar
            if (isMouseOver((int) mouseX, (int) mouseY, oneClickButtonLeft, oneClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 1, balance);
                return true;
            }
            // Five Dollar
            if (isMouseOver((int) mouseX, (int) mouseY, fiveClickButtonLeft, fiveClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 5, balance);
                return true;
            }
            // Ten Dollar
            if (isMouseOver((int) mouseX, (int) mouseY, tenClickButtonLeft, tenClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 10, balance);
                return true;
            }
            // Twenty Dollar
            if (isMouseOver((int) mouseX, (int) mouseY, twentyClickButtonLeft, twentyClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 20, balance);
                return true;
            }
            // Fifty Dollar
            if (isMouseOver((int) mouseX, (int) mouseY, fiftyClickButtonLeft, fiftyClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 50, balance);
                return true;
            }
            // One-Hundred Dollars
            if (isMouseOver((int) mouseX, (int) mouseY, oneHundredClickButtonLeft, oneHundredClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = Math.min(selectedAmount + 100, balance);
                return true;
            }
            // Max Button
            if (isMouseOver((int) mouseX, (int) mouseY, maxClickButtonLeft, maxClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                selectedAmount = balance;
                return true;
            }
            // Confirm Button
            if (isMouseOver((int) mouseX, (int) mouseY, confirmClickButtonLeft, confirmClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
                ModMessages.sendToServer(new C2SMessageWithdraw(selectedAmount));
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
        renderHomeScreenSelection(pGuiGraphics);
        float labelScale = 0.66f;
        String welcomeText = "Welcome to Enderman Bank";
        String playerNameText = ClientData.getPlayerName() + "!";

        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScale, labelScale, labelScale);

        int guiWidth = this.width;
        int centerX = guiWidth / 2;

        int welcomeTextWidth = minecraft.font.width(welcomeText);
        int xWelcome = (int)((centerX - (welcomeTextWidth * labelScale) / 2) / labelScale);
        int yWelcome = (int)(welcomeTextTop / labelScale);
        pGuiGraphics.drawString(minecraft.font, welcomeText, xWelcome, yWelcome, 0xFF776ED8);

        int playerNameWidth = minecraft.font.width(playerNameText);
        int xPlayer = (int)((centerX - (playerNameWidth * labelScale) / 2) / labelScale);
        int yPlayer = (int)((welcomeTextTop + 7) / labelScale);
        pGuiGraphics.drawString(minecraft.font, playerNameText, xPlayer, yPlayer, 0xFF776ED8);

        pGuiGraphics.pose().popPose();


    }
    private void renderHomeScreenSelection(GuiGraphics pGuiGraphics) {
        double mouseX = Minecraft.getInstance().mouseHandler.xpos() * this.width / Minecraft.getInstance().getWindow().getScreenWidth();
        double mouseY = Minecraft.getInstance().mouseHandler.ypos() * this.height / Minecraft.getInstance().getWindow().getScreenHeight();
        // Render Deposit Button
        if (!isMouseOver((int) mouseX, (int) mouseY, depositClickButtonLeft, depositClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, depositButtonLeft, depositButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, depositButtonLeft, depositButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        pGuiGraphics.pose().pushPose();
        float labelScaleDeposit = 0.75f;
        pGuiGraphics.pose().scale(labelScaleDeposit, labelScaleDeposit, labelScaleDeposit);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Deposit", (int)((depositTextLeft) / labelScaleDeposit), (int)((depositTextTop) / labelScaleDeposit), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
        // Render Withdraw Button
        float labelScaleWithdraw = 0.68f;
        if (!isMouseOver((int) mouseX, (int) mouseY, withdrawClickButtonLeft, withdrawClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, withdrawButtonLeft, withdrawButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, withdrawButtonLeft, withdrawButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScaleWithdraw, labelScaleWithdraw, labelScaleWithdraw);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Withdraw", (int)((withdrawTextLeft) / labelScaleWithdraw), (int)((withdrawTextTop) / labelScaleWithdraw), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
        // Render Clickable Deposit Button
        pGuiGraphics.blit(ATM_TEXTURE, depositClickButtonLeft, depositClickButtonTop, 185, 31, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        // Render Clickable Withdraw Button
        pGuiGraphics.blit(ATM_TEXTURE, withdrawClickButtonLeft, withdrawClickButtonTop, 185, 22, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
    }
    private void renderDepositPage(GuiGraphics pGuiGraphics) {
        renderSelectedAmount(pGuiGraphics);
        renderPriceButtons(pGuiGraphics);
        renderBackButton(pGuiGraphics);
        renderConfirmButton(pGuiGraphics);

    }
    private void renderWithdrawPage(GuiGraphics pGuiGraphics) {
        renderSelectedAmount(pGuiGraphics);
        renderPriceButtons(pGuiGraphics);
        renderBackButton(pGuiGraphics);
        renderConfirmButton(pGuiGraphics);

    }
    private void renderSelectedAmount(GuiGraphics pGuiGraphics) {
        float labelScaleBalance = 0.80f;
        String selectedText = "Selected Amount: " + currencySymbol + selectedAmount;

        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScaleBalance, labelScaleBalance, labelScaleBalance);

        int stringWidth = minecraft.font.width(selectedText);
        int guiWidth = this.width;
        int centeredX = (int) ((guiWidth - stringWidth * labelScaleBalance) / 2);

        pGuiGraphics.drawString(minecraft.font, selectedText, (int)(centeredX / labelScaleBalance), (int)(selectedAmountTextTop / labelScaleBalance), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
    }
    private void renderBalance(GuiGraphics pGuiGraphics) {
        float labelScaleBalance = 0.80f;
        String balanceText = "Account Balance: " + currencySymbol + balance;

        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScaleBalance, labelScaleBalance, labelScaleBalance);

        int stringWidth = minecraft.font.width(balanceText);
        int guiWidth = this.width;
        int centeredX = (int) ((guiWidth - stringWidth * labelScaleBalance) / 2);

        pGuiGraphics.drawString(minecraft.font, balanceText, (int)(centeredX / labelScaleBalance), (int)(balanceTextTop / labelScaleBalance), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
    }

    private void renderPriceButtons(GuiGraphics pGuiGraphics) {
        double mouseX = Minecraft.getInstance().mouseHandler.xpos() * this.width / Minecraft.getInstance().getWindow().getScreenWidth();
        double mouseY = Minecraft.getInstance().mouseHandler.ypos() * this.height / Minecraft.getInstance().getWindow().getScreenHeight();
        float labelScalePrice = 0.80f;

        // One Dollar
        if (!isMouseOver((int) mouseX, (int) mouseY, oneClickButtonLeft, oneClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, oneButtonLeft, oneButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, oneButtonLeft, oneButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        // Five Dollar
        if (!isMouseOver((int) mouseX, (int) mouseY, fiveClickButtonLeft, fiveClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, fiveButtonLeft, fiveButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, fiveButtonLeft, fiveButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        // Ten Dollar
        if (!isMouseOver((int) mouseX, (int) mouseY, tenClickButtonLeft, tenClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, tenButtonLeft, tenButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, tenButtonLeft, tenButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        // Twenty Dollar
        if (!isMouseOver((int) mouseX, (int) mouseY, twentyClickButtonLeft, twentyClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, twentyButtonLeft, twentyButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, twentyButtonLeft, twentyButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        // Fifty Dollar
        if (!isMouseOver((int) mouseX, (int) mouseY, fiftyClickButtonLeft, fiftyClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, fiftyButtonLeft, fiftyButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, fiftyButtonLeft, fiftyButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        // One-Hundred Dollar
        if (!isMouseOver((int) mouseX, (int) mouseY, oneHundredClickButtonLeft, oneHundredClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, oneHundredButtonLeft, oneHundredButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, oneHundredButtonLeft, oneHundredButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        // Max Amount Button
        if (!isMouseOver((int) mouseX, (int) mouseY, maxClickButtonLeft, maxClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, maxButtonLeft, maxButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, maxButtonLeft, maxButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        // Render Button Texts
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScalePrice, labelScalePrice, labelScalePrice);
        pGuiGraphics.drawString(Minecraft.getInstance().font, currencySymbol + "1", (int)((oneTextLeft) / labelScalePrice), (int)((oneTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.drawString(Minecraft.getInstance().font, currencySymbol + "5", (int)((fiveTextLeft) / labelScalePrice), (int)((fiveTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.drawString(Minecraft.getInstance().font, currencySymbol + "10", (int)((tenTextLeft) / labelScalePrice), (int)((tenTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.drawString(Minecraft.getInstance().font, currencySymbol + "20", (int)((twentyTextLeft) / labelScalePrice), (int)((twentyTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.drawString(Minecraft.getInstance().font, currencySymbol + "50", (int)((fiftyTextLeft) / labelScalePrice), (int)((fiftyTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.drawString(Minecraft.getInstance().font, currencySymbol + "100", (int)((oneHundredTextLeft) / labelScalePrice), (int)((oneHundredTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Max", (int)((maxTextLeft) / labelScalePrice), (int)((maxTextTop) / labelScalePrice), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
        // Render Clickable Buttons
        pGuiGraphics.blit(ATM_TEXTURE, oneClickButtonLeft, oneClickButtonTop, 185, 31, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        pGuiGraphics.blit(ATM_TEXTURE, fiveClickButtonLeft, fiveClickButtonTop, 185, 31, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        pGuiGraphics.blit(ATM_TEXTURE, tenClickButtonLeft, tenClickButtonTop, 185, 31, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        pGuiGraphics.blit(ATM_TEXTURE, twentyClickButtonLeft, twentyClickButtonTop, 185, 31, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        pGuiGraphics.blit(ATM_TEXTURE, fiftyClickButtonLeft, fiftyClickButtonTop, 185, 22, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        pGuiGraphics.blit(ATM_TEXTURE, oneHundredClickButtonLeft, oneHundredClickButtonTop, 185, 22, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
        pGuiGraphics.blit(ATM_TEXTURE, maxClickButtonLeft, maxClickButtonTop, 185, 22, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
    }
    private void renderBackButton(GuiGraphics pGuiGraphics) {
        double mouseX = Minecraft.getInstance().mouseHandler.xpos() * this.width / Minecraft.getInstance().getWindow().getScreenWidth();
        double mouseY = Minecraft.getInstance().mouseHandler.ypos() * this.height / Minecraft.getInstance().getWindow().getScreenHeight();
        float labelScaleBack = 0.75f;
        if (!isMouseOver((int) mouseX, (int) mouseY, backClickButtonLeft, backClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, backButtonLeft, backButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, backButtonLeft, backButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScaleBack, labelScaleBack, labelScaleBack);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Back", (int)((backTextLeft) / labelScaleBack), (int)((backTextTop) / labelScaleBack), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
        // Render Clickable Back Button
        pGuiGraphics.blit(ATM_TEXTURE, backClickButtonLeft, backClickButtonTop, 185, 31, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
    }
    private void renderConfirmButton(GuiGraphics pGuiGraphics) {
        double mouseX = Minecraft.getInstance().mouseHandler.xpos() * this.width / Minecraft.getInstance().getWindow().getScreenWidth();
        double mouseY = Minecraft.getInstance().mouseHandler.ypos() * this.height / Minecraft.getInstance().getWindow().getScreenHeight();
        float labelScaleConfirm = 0.75f;
        if (!isMouseOver((int) mouseX, (int) mouseY, confirmClickButtonLeft, confirmClickButtonTop, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT)) {
            pGuiGraphics.blit(ATM_TEXTURE, confirmButtonLeft, confirmButtonTop, 185, 0, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        } else {
            pGuiGraphics.blit(ATM_TEXTURE, confirmButtonLeft, confirmButtonTop, 185, 11, SCREEN_BUTTON_WIDTH, SCREEN_BUTTON_HEIGHT);
        }
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(labelScaleConfirm, labelScaleConfirm, labelScaleConfirm);
        pGuiGraphics.drawString(Minecraft.getInstance().font, "Confirm", (int)((confirmTextLeft) / labelScaleConfirm), (int)((confirmTextTop) / labelScaleConfirm), 0xFF776ED8);
        pGuiGraphics.pose().popPose();
        // Render Clickable Confirm Button
        pGuiGraphics.blit(ATM_TEXTURE, confirmClickButtonLeft, confirmClickButtonTop, 185, 22, CLICKABLE_BUTTON_WIDTH, CLICKABLE_BUTTON_HEIGHT);
    }

    // Helper Methods
    private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
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
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(pGuiGraphics, mouseX, mouseY);
    }
}
