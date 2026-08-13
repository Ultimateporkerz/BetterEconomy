package net.ultimporks.betterecon.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import net.ultimporks.betterecon.BetterEconomy;
import net.ultimporks.betterecon.init.ModBlockEntities;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ShopBlockEntity extends BlockEntity {
    public final ItemStackHandler stockHandler = new ItemStackHandler(27);
    public final ItemStackHandler registerHandler = new ItemStackHandler(6);
    public final ItemStackHandler itemForSale = new ItemStackHandler(1);
    private ItemStack itemsForSale = ItemStack.EMPTY;
    private int price = 0;

    private int tickCounter = 0;
    private ContainerData data;

    private UUID ownerUUID;
    private String ownerName;

    public ShopBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SHOP_BLOCK_BE.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return ShopBlockEntity.this.price;
            }

            @Override
            public void set(int pIndex, int pValue) {
                ShopBlockEntity.this.price = pValue;
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    public ContainerData getData() {
        return data;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inventory", stockHandler.serializeNBT());
        tag.put("ItemForSale", itemForSale.serializeNBT());
        tag.put("registerInventory", registerHandler.serializeNBT());
        tag.putInt("Price", price);
        tag.putInt("TickCounter", tickCounter);

        if (ownerUUID != null) {
            tag.putUUID("Owner", ownerUUID);
        }

        if (ownerName != null) {
            tag.putString("OwnerName", ownerName);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        stockHandler.deserializeNBT(tag.getCompound("inventory"));
        itemForSale.deserializeNBT(tag.getCompound("ItemForSale"));
        registerHandler.deserializeNBT(tag.getCompound("registerInventory"));
        price = tag.getInt("Price");
        tickCounter = tag.getInt("TickCounter");

        if (tag.hasUUID("Owner")) {
            ownerUUID = tag.getUUID("Owner");
        } else {
            ownerUUID = null;
        }

        if (tag.contains("OwnerName", CompoundTag.TAG_STRING)) {
            ownerName = tag.getString("OwnerName");
        } else {
            ownerName = null;
        }
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        if (slotHasItem() && !getItemForSale().equals(getItemInSlot())) {
            setItemForSale(getItemInSlot());
        }
    }


    public boolean slotHasItem() {
        return !this.itemForSale.getStackInSlot(0).isEmpty();
    }

    private ItemStack getItemInSlot() {
        if (slotHasItem()) {
            return this.itemForSale.getStackInSlot(0);
        }
        return ItemStack.EMPTY;
    }
    public ItemStack getItemForSale() {
        return itemsForSale;
    }
    public void setItemForSale(ItemStack itemForSale) {
        this.itemsForSale = itemForSale;
        this.setChanged();
        BetterEconomy.LOGGING("(ShopBlockEntity) - Item " + itemForSale.getItem() + " is for sale for " + getPrice());
    }

    public int getStock() {
        int total = 0;
        for (int i = 0; i < stockHandler.getSlots(); i++) {
            total += stockHandler.getStackInSlot(i).getCount();
        }
        return total;
    }



    public boolean transferCashToShop(int amountEarned, Map<Item, Integer> denominations) {
        // SIMULATION PHASE
        int simulatedRemaining = amountEarned;
        ItemStackHandler copyHandler = new ItemStackHandler(registerHandler.getSlots());
        for (int i = 0; i < registerHandler.getSlots(); i++) {
            copyHandler.setStackInSlot(i, registerHandler.getStackInSlot(i).copy());
        }

        List<Map.Entry<Item, Integer>> sortedDenoms = denominations.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .toList();

        for (Map.Entry<Item, Integer> entry : sortedDenoms) {
            int value = entry.getValue();
            Item item = entry.getKey();

            int count = simulatedRemaining / value;
            if (count <= 0) continue;

            ItemStack billStack = new ItemStack(item, count);

            // Fill existing partial stacks first
            for (int slot = 0; slot < copyHandler.getSlots() && billStack.getCount() > 0; slot++) {
                ItemStack existing = copyHandler.getStackInSlot(slot);
                if (!existing.isEmpty() && ItemStack.isSameItemSameTags(existing, billStack)) {
                    billStack = copyHandler.insertItem(slot, billStack, true);
                }
            }

            // Then try any remaining slots
            for (int slot = 0; slot < copyHandler.getSlots() && billStack.getCount() > 0; slot++) {
                billStack = copyHandler.insertItem(slot, billStack, true);
            }

            int inserted = count - billStack.getCount();
            simulatedRemaining -= inserted * value;

            if (inserted == 0) return false; // No room for this denomination
        }

        if (simulatedRemaining > 0) return false;

        // INSERTION PHASE
        int remaining = amountEarned;
        for (Map.Entry<Item, Integer> entry : sortedDenoms) {
            int value = entry.getValue();
            Item item = entry.getKey();

            int count = remaining / value;
            if (count <= 0) continue;

            ItemStack billStack = new ItemStack(item, count);

            // Fill existing stacks first
            for (int slot = 0; slot < registerHandler.getSlots() && billStack.getCount() > 0; slot++) {
                ItemStack existing = registerHandler.getStackInSlot(slot);
                if (!existing.isEmpty() && ItemStack.isSameItemSameTags(existing, billStack)) {
                    billStack = registerHandler.insertItem(slot, billStack, false);
                }
            }

            // Insert into empty/valid slots
            for (int slot = 0; slot < registerHandler.getSlots() && billStack.getCount() > 0; slot++) {
                billStack = registerHandler.insertItem(slot, billStack, false);
            }

            int inserted = count - billStack.getCount();
            remaining -= inserted * value;
        }

        return true;
    }

    // Price
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
        this.setChanged();
        BetterEconomy.LOGGING("(ShopBlockEntity) - Price has been set to: " + price);
    }

    // Ownership
    public void setOwner(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
        this.setChanged();
        BetterEconomy.LOGGING("Owner of Shop set to " + ownerUUID);
    }
    public boolean isOwner(UUID currentPlayerUUID) {
        return currentPlayerUUID.equals(ownerUUID);
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        this.setChanged();
        BetterEconomy.LOGGING("Shop Owners Name: " + ownerName);
    }
    public String getOwnerName() {
        return ownerName;
    }


    public ItemStack getRenderStack() {
        if (!itemForSale.getStackInSlot(0).isEmpty()) {
            return itemForSale.getStackInSlot(0);
        } else {
            return ItemStack.EMPTY;
        }
    }

    // Take items from the stock when a player purchases
    public void removePurchasedItems(int amountToRemove) {
        ItemStackHandler handler = this.stockHandler;
        int slotCount = handler.getSlots();
        int remaining = amountToRemove;

        for (int i = 0; i < slotCount; i++) {
            ItemStack stack = handler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                int stackCount = stack.getCount();
                int toRemove = Math.min(stackCount, remaining);

                if (toRemove > 0) {
                    stack.shrink(toRemove);
                    handler.setStackInSlot(i, stack);
                    remaining -= toRemove;

                    if (remaining <= 0) {
                        setChanged();
                        return;
                    }
                }
            }
        }
    }

    public void drops() {
        int totalSlots = stockHandler.getSlots() + itemForSale.getSlots() + registerHandler.getSlots();
        SimpleContainer inventory = new SimpleContainer(totalSlots);

        for (int i = 0; i < stockHandler.getSlots(); i++) {
            inventory.setItem(i, stockHandler.getStackInSlot(i));
        }

        for (int i = 0; i < itemForSale.getSlots(); i++) {
            inventory.setItem(stockHandler.getSlots() + i, itemForSale.getStackInSlot(i));
        }

        for (int i = 0; i < registerHandler.getSlots(); i++) {
            inventory.setItem(stockHandler.getSlots() + itemForSale.getSlots() + i, registerHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
}
