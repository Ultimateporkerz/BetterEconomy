package net.ultimporks.betterecon.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.client.ClientData;
import net.ultimporks.betterecon.network.shop.C2SMessagePurchase;
import net.ultimporks.betterecon.util.menu.ShopCustomerMenu;

public class ShopCustomerScreen extends AbstractContainerScreen<ShopCustomerMenu> {
    private static final ResourceLocation CUSTOMER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/gui/shop_customer.png");

    private ItemStack itemForSale;
    private BlockPos shopBlockPos;

    private int sellingPrice;
    private int totalPrice;

    private boolean customerView;
    private String ownerName;

    private int totalStock;
    private int quantity = 0;

    private String currencySymbol;

    private static final int ONE_BUTTON_HEIGHT = 11;
    private static final int ONE_BUTTON_WIDTH = 10;

    private static final int TEN_BUTTON_HEIGHT = 11;
    private static final int TEN_BUTTON_WIDTH = 15;

    private static final int FIFTY_BUTTON_HEIGHT = 11;
    private static final int FIFTY_BUTTON_WIDTH = 18;

    private static final int ONEHUNDRED_BUTTON_HEIGHT = 11;
    private static final int ONEHUNDRED_BUTTON_WIDTH = 20;

    private static final int CONFIRM_BUTTON_HEIGHT = 12;
    private static final int CONFIRM_BUTTON_WIDTH = 46;

    // UI Element Positions
    private int sellingPriceTextLeft, sellingPriceTextTop,
            costTextLeft, costTextTop,
            itemForSaleLeft, itemForSaleTop,
            amountInCartLeft, amountInCartTop,
            shopStockLeft, shopStockTop,
            oneButtonLeft, oneButtonTop,
            negativeOneButtonTop, negativeOneButtonLeft,
            tenButtonLeft, tenButtonTop,
            negativeTenButtonLeft, negativeTenButtonTop,
            fiftyButtonLeft, fiftyButtonTop,
            negativeFiftyButtonLeft, negativeFiftyButtonTop,
            oneHundredButtonLeft, oneHundredButtonTop,
            negativeOneHundredButtonLeft, negativeOneHundredButtonTop,
            confirmButtonLeft, confirmButtonTop,
            ownerNameLeft, ownerNameTop;



    public ShopCustomerScreen(ShopCustomerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.shopBlockPos = menu.getShopBlockPos();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
        this.itemForSale = ClientData.getItemForSale();
        this.sellingPrice = ClientData.getSellPrice();
        this.totalStock = ClientData.getShopStock();
        this.currencySymbol = ClientData.getCurrencySymbol();
        this.customerView = ClientData.getCustomerView();
        this.ownerName = ClientData.getOwnerName();
        // Middle of the Screen X and Y
        int xPosMiddle = (this.width - this.imageWidth) / 2;
        int yPosMiddle = (this.height - this.imageHeight) / 2;
        // Total stock
        this.shopStockLeft = xPosMiddle + 10;
        this.shopStockTop = yPosMiddle + 22;
        // Price Text
        this.sellingPriceTextLeft = xPosMiddle + 10;
        this.sellingPriceTextTop = yPosMiddle + 10;
        // Item For Sale
        this.itemForSaleLeft = xPosMiddle + 80;
        this.itemForSaleTop = yPosMiddle + 15;
        // One Buttons
        this.oneButtonLeft = xPosMiddle + 88;
        this.oneButtonTop = yPosMiddle + 64;
        this.negativeOneButtonLeft = xPosMiddle + 77;
        this.negativeOneButtonTop = yPosMiddle + 64;
        // Ten Buttons
        this.tenButtonLeft = xPosMiddle + 99;
        this.tenButtonTop = yPosMiddle + 64;
        this.negativeTenButtonLeft = xPosMiddle + 61;
        this.negativeTenButtonTop = yPosMiddle + 64;
        // Fifty Buttons
        this.fiftyButtonLeft = xPosMiddle + 115;
        this.fiftyButtonTop = yPosMiddle + 64;
        this.negativeFiftyButtonLeft = xPosMiddle + 43;
        this.negativeFiftyButtonTop = yPosMiddle + 64;
        // OneHundred Buttons
        this.oneHundredButtonLeft = xPosMiddle + 133;
        this.oneHundredButtonTop = yPosMiddle + 64;
        this.negativeOneHundredButtonLeft = xPosMiddle + 22;
        this.negativeOneHundredButtonTop = yPosMiddle + 64;
        // Confirm Button
        this.confirmButtonLeft = xPosMiddle + 123;
        this.confirmButtonTop = yPosMiddle + 46;
        // Cost of Transaction
        this.costTextLeft = confirmButtonLeft + 20;
        this.costTextTop = confirmButtonTop - 10;
        // Amount in Cart
        this.amountInCartLeft = costTextLeft;
        this.amountInCartTop = costTextTop - 10;
        // Owner Name
        this.ownerNameLeft = xPosMiddle;
        this.ownerNameTop = yPosMiddle - 8;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(pGuiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(pGuiGraphics, mouseX, mouseY);
        renderPrices(pGuiGraphics);
        renderItemStackForSale(pGuiGraphics);
        renderOneButton(pGuiGraphics, mouseX, mouseY);
        renderTenButton(pGuiGraphics, mouseX, mouseY);
        renderFiftyButton(pGuiGraphics, mouseX, mouseY);
        renderOneHundredButton(pGuiGraphics, mouseX, mouseY);
        renderQty(pGuiGraphics);
        renderCheckoutButton(pGuiGraphics, mouseX, mouseY);
        renderOwnerName(pGuiGraphics);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderTexture(0, CUSTOMER_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        pGuiGraphics.blit(CUSTOMER_TEXTURE, this.leftPos, this.topPos, 0, 0, imageWidth, imageHeight);

    }

    private void renderItemStackForSale(GuiGraphics pGuiGraphics) {
        ItemStack displayStack = itemForSale.copy();
        displayStack.setCount(itemForSale.getCount());

        pGuiGraphics.renderItem(displayStack, itemForSaleLeft, itemForSaleTop);
        pGuiGraphics.renderItemDecorations(minecraft.font, displayStack, itemForSaleLeft, itemForSaleTop);
    }

    private void renderPrices(GuiGraphics pGuiGraphics) {
        String priceString = "Price: "+ currencySymbol + sellingPrice;
        pGuiGraphics.drawString(minecraft.font, priceString, sellingPriceTextLeft, sellingPriceTextTop, 0xFF80FF80, false);


        String costString = "Total: " + currencySymbol + totalPrice;

        int stringWidth = minecraft.font.width(costString);
        int rightEdge = leftPos + imageWidth - 10;
        int adjustedX = costTextLeft;

        if (costTextLeft + stringWidth > rightEdge) {
            adjustedX = rightEdge - stringWidth;
        }

        pGuiGraphics.drawString(minecraft.font, costString, adjustedX, costTextTop, 0xFF80FF80, false);
    }
    private void renderQty(GuiGraphics pGuiGraphics) {
        String amountAvailable = "Stock: " + totalStock;
        pGuiGraphics.drawString(minecraft.font, amountAvailable, shopStockLeft, shopStockTop, 0xFFFFFF00
                , false);

        String amountSelected = "In Cart: " + quantity;

        int stringWidth = minecraft.font.width(amountSelected);
        int rightEdge = leftPos + imageWidth - 10;
        int adjustedX = amountInCartLeft;

        if (amountInCartLeft + stringWidth > rightEdge) {
            adjustedX = rightEdge - stringWidth;
        }

        pGuiGraphics.drawString(minecraft.font, amountSelected, adjustedX, amountInCartTop, 0xFFFFFFFF, false);
    }

    private void renderOwnerName(GuiGraphics guiGraphics) {
        String nameString = ownerName + "'s Shop";
        Component nameComponent = Component.literal(ownerName + "'s Shop")
                .withStyle(style -> style.withBold(true));
        float labelScale = 0.80f;

        int textWidth = Minecraft.getInstance().font.width(nameString);

        float x = ownerNameLeft + (imageWidth / 2.0f) - (textWidth * labelScale / 2.0f);
        float y = ownerNameTop;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(labelScale, labelScale, labelScale);

        guiGraphics.drawString(Minecraft.getInstance().font, nameComponent, (int)(x / labelScale), (int)(y / labelScale), 0xFFFFFF
        );
        guiGraphics.pose().popPose();
    }



    private void renderOneButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        boolean oneHovered = isMouseOver(mouseX, mouseY, oneButtonLeft, oneButtonTop, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT);

        int xPos = 66;
        int yPos = oneHovered ? 177 : 166;

        guiGraphics.blit(CUSTOMER_TEXTURE, oneButtonLeft, oneButtonTop, xPos, yPos, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT);

        boolean negativeOneHovered = isMouseOver(mouseX, mouseY, negativeOneButtonLeft, negativeOneButtonTop, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT);

        int nXPos = 55;
        int nYPos = negativeOneHovered ? 177 : 166;

        guiGraphics.blit(CUSTOMER_TEXTURE, negativeOneButtonLeft, negativeOneButtonTop, nXPos, nYPos, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT);
    }
    private void renderTenButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        boolean tenHovered = isMouseOver(mouseX, mouseY, tenButtonLeft, tenButtonTop, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT);

        int xPos = 77;
        int yPos = tenHovered ? 177 : 166;

        guiGraphics.blit(CUSTOMER_TEXTURE, tenButtonLeft, tenButtonTop, xPos, yPos, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT);

        boolean negativeTenHovered = isMouseOver(mouseX, mouseY, negativeTenButtonLeft, negativeTenButtonTop, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT);

        int nXPos = 39;
        int nYPos = negativeTenHovered ? 177 : 166;

        guiGraphics.blit(CUSTOMER_TEXTURE, negativeTenButtonLeft, negativeTenButtonTop, nXPos, nYPos, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT);
    }
    private void renderFiftyButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        boolean fiftyHovered = isMouseOver(mouseX, mouseY, fiftyButtonLeft, fiftyButtonTop, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT);

        int xPos = 93;
        int yPos = fiftyHovered ? 177 : 166;

        guiGraphics.blit(CUSTOMER_TEXTURE, fiftyButtonLeft, fiftyButtonTop, xPos, yPos, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT);

        boolean negativeFiftyHovered = isMouseOver(mouseX, mouseY, negativeFiftyButtonLeft, negativeFiftyButtonTop, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT);

        int nXPos = 21;
        int nYPos = negativeFiftyHovered ? 177 : 166;

        guiGraphics.blit(CUSTOMER_TEXTURE, negativeFiftyButtonLeft, negativeFiftyButtonTop, nXPos, nYPos, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT);
    }
    private void renderOneHundredButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        boolean oneHundredHovered = isMouseOver(mouseX, mouseY, oneHundredButtonLeft, oneHundredButtonTop, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT);

        int xPos = 111;
        int yPos = oneHundredHovered ? 177 : 166;

        guiGraphics.blit(CUSTOMER_TEXTURE, oneHundredButtonLeft, oneHundredButtonTop, xPos, yPos, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT);

        boolean negativeOneHundredHovered = isMouseOver(mouseX, mouseY, negativeOneHundredButtonLeft, negativeOneHundredButtonTop, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT);

        int nXPos = 0;
        int nYPos = negativeOneHundredHovered ? 177 : 166;

        guiGraphics.blit(CUSTOMER_TEXTURE, negativeOneHundredButtonLeft, negativeOneHundredButtonTop, nXPos, nYPos, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT);
    }
    private void renderCheckoutButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        boolean checkoutHovered = isMouseOver(mouseX, mouseY, confirmButtonLeft, confirmButtonTop, CONFIRM_BUTTON_WIDTH, CONFIRM_BUTTON_HEIGHT);

        int xPos = 0;
        int yPos = checkoutHovered ? 202 : 189;

        guiGraphics.blit(CUSTOMER_TEXTURE, confirmButtonLeft, confirmButtonTop, xPos, yPos, CONFIRM_BUTTON_WIDTH, CONFIRM_BUTTON_HEIGHT);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        // One
        if (isMouseOver((int) mouseX, (int) mouseY, oneButtonLeft, oneButtonTop, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT)) {
            adjustQuantity(1);
            return true;
        }
        if (isMouseOver((int) mouseX, (int) mouseY, negativeOneButtonLeft, negativeOneButtonTop, ONE_BUTTON_WIDTH, ONE_BUTTON_HEIGHT)) {
            adjustQuantity(-1);
            return true;
        }

        // Ten
        if (isMouseOver((int) mouseX, (int) mouseY, tenButtonLeft, tenButtonTop, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT)) {
            adjustQuantity(10);
            return true;
        }
        if (isMouseOver((int) mouseX, (int) mouseY, negativeTenButtonLeft, negativeTenButtonTop, TEN_BUTTON_WIDTH, TEN_BUTTON_HEIGHT)) {
            adjustQuantity(-10);
            return true;
        }

        // Fifty
        if (isMouseOver((int) mouseX, (int) mouseY, fiftyButtonLeft, fiftyButtonTop, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT)) {
            adjustQuantity(50);
            return true;
        }
        if (isMouseOver((int) mouseX, (int) mouseY, negativeFiftyButtonLeft, negativeFiftyButtonTop, FIFTY_BUTTON_WIDTH, FIFTY_BUTTON_HEIGHT)) {
            adjustQuantity(-50);
            return true;
        }

        // One Hundred
        if (isMouseOver((int) mouseX, (int) mouseY, oneHundredButtonLeft, oneHundredButtonTop, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT)) {
            adjustQuantity(100);
            return true;
        }
        if (isMouseOver((int) mouseX, (int) mouseY, negativeOneHundredButtonLeft, negativeOneHundredButtonTop, ONEHUNDRED_BUTTON_WIDTH, ONEHUNDRED_BUTTON_HEIGHT)) {
            adjustQuantity(-100);
            return true;
        }

        // Confirm
        if (isMouseOver((int) mouseX, (int) mouseY, confirmButtonLeft, confirmButtonTop, CONFIRM_BUTTON_WIDTH, CONFIRM_BUTTON_HEIGHT)) {
            if (quantity != 0) {
                if (!customerView) {
                    PacketDistributor.sendToServer(new C2SMessagePurchase(itemForSale, totalPrice, quantity, shopBlockPos));
                    return true;
                } else {
                    minecraft.player.sendSystemMessage(Component.literal("You cannot buy from your own Shop!").withStyle(ChatFormatting.RED));
                    minecraft.player.closeContainer();
                    return false;
                }
            }
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void adjustQuantity(int amount) {
        quantity += amount;

        if (quantity < 0) {
            quantity = 0;
        }

        if (quantity > totalStock) {
            quantity = totalStock;
        }

        totalPrice = quantity * sellingPrice;
    }

    private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
