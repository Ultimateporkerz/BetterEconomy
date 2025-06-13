package net.ultimporks.betterecon.util.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.ultimporks.betterecon.init.ModDataComponents;
import net.ultimporks.betterecon.init.ModMenuTypes;
import net.ultimporks.betterecon.item.WalletItem;
import net.ultimporks.betterecon.util.WalletSavedData;

import java.util.ArrayList;
import java.util.List;

public class WalletMenu extends AbstractContainerMenu {
    private final ItemStack walletStack;
    private final WalletSavedData walletComponent;
    private final SimpleContainer walletInventory;


    public WalletMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory, playerInventory.player.getItemInHand(
                InteractionHand.values()[buf.readInt()]
        ));
    }

    public WalletMenu(int containerId, Inventory playerInventory, ItemStack walletItem) {
        super(ModMenuTypes.WALLET_MENU.get(), containerId);
        this.walletStack = walletItem;

        this.walletComponent = walletItem.getComponents().get(ModDataComponents.WALLET_INVENTORY.get());

        if (walletComponent != null) {
            List<ItemStack> items = walletComponent.getItems();

            // Always create a SimpleContainer of size 6
            this.walletInventory = new SimpleContainer(6);

            // Copy saved items into walletInventory, but do not exceed size 6
            for (int i = 0; i < Math.min(items.size(), 6); i++) {
                this.walletInventory.setItem(i, items.get(i));
            }
        } else {
            this.walletInventory = new SimpleContainer(6);
        }

        // Adds 6 slots to the Wallet GUI
        for (int i = 0; i < 6; i++) {
            this.addSlot(new Slot(walletInventory, i, 35 + i * 18, 19) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return (!(stack.getItem() instanceof WalletItem));
                }
            });
        }
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);

        List<ItemStack> savedItems = new ArrayList<>();
        for (int i = 0; i < walletInventory.getContainerSize(); i++) {
            ItemStack stack = walletInventory.getItem(i);
            // Filter out AIR or empty stacks
            if (!stack.isEmpty() && stack.getCount() > 0 && stack.getItem() != net.minecraft.world.item.Items.AIR) {
                savedItems.add(stack.copy());
            }
        }

        walletStack.set(ModDataComponents.WALLET_INVENTORY.get(), new WalletSavedData(savedItems));
    }


    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 45 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 103));
        }
    }

    // QuickMove helpers
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 6;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
}
