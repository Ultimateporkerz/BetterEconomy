package net.ultimporks.betterecon.block.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.network.PacketDistributor;
import net.ultimporks.betterecon.BetterEconomy;
import net.ultimporks.betterecon.configs.ModConfigs;
import net.ultimporks.betterecon.init.ModBlockEntities;
import net.ultimporks.betterecon.init.ModItems;
import net.ultimporks.betterecon.network.S2CMessageCurrencySymbol;
import net.ultimporks.betterecon.network.shop.S2CMessageItemAndPrice;
import net.ultimporks.betterecon.util.menu.ShopCustomerMenu;
import net.ultimporks.betterecon.util.menu.ShopOwnerMenu;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ShopBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler stockHandler = new ItemStackHandler(27);
    public final ItemStackHandler registerHandler = new ItemStackHandler(6);
    public final ItemStackHandler itemForSale = new ItemStackHandler(1);
    private ItemStack itemsForSale = ItemStack.EMPTY;
    private int price = 0;

    protected final ContainerData data;

    private UUID ownerUUID;

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

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", stockHandler.serializeNBT(registries));
        tag.put("ItemForSale", itemForSale.serializeNBT(registries));
        tag.put("registerInventory", registerHandler.serializeNBT(registries));
        tag.putInt("Price", price);

        if (ownerUUID != null) {
            tag.putUUID("Owner", ownerUUID);
        }
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        stockHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        itemForSale.deserializeNBT(registries, tag.getCompound("ItemForSale"));
        registerHandler.deserializeNBT(registries, tag.getCompound("registerInventory"));
        price = tag.getInt("Price");

        if (tag.hasUUID("Owner")) {
            ownerUUID = tag.getUUID("Owner");
        } else {
            ownerUUID = null;
        }
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (slotHasItem() && !getItemForSale().equals(getItemInSlot())) {
            setItemForSale(getItemInSlot());
        }
    }

    private boolean slotHasItem() {
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
    public boolean hasEnoughStock(int amountRequested) {
        int totalAvailable = 0;

        for (int i = 0; i < stockHandler.getSlots(); i++) {
            totalAvailable += stockHandler.getStackInSlot(i).getCount();
            if (totalAvailable >= amountRequested) {
                return true;
            }
        }
        return false;
    }

    public void transferCashToShop(int amountEarned) {
        int remaining = amountEarned;

        int[] denominations = {100, 50, 20, 10, 5, 1};
        Item[] bills = {
                ModItems.ONE_HUNDRED_DOLLAR_BILL.get(),
                ModItems.FIFTY_DOLLAR_BILL.get(),
                ModItems.TWENTY_DOLLAR_BILL.get(),
                ModItems.TEN_DOLLAR_BILL.get(),
                ModItems.FIVE_DOLLAR_BILL.get(),
                ModItems.ONE_DOLLAR_BILL.get()
        };

        for (int denomIndex = 0; denomIndex < denominations.length && remaining > 0; denomIndex++) {
            int billValue = denominations[denomIndex];
            int count = remaining / billValue;

            if (count <= 0) continue;

            ItemStack stack = new ItemStack(bills[denomIndex], count);

            // Insert into registerHandler first
            ItemStack leftover = registerHandler.insertItem(denomIndex, stack, false);
            int insertedCount = count - leftover.getCount();
            remaining -= insertedCount * billValue;

            // Try stockHandler if there is leftover
            if (!leftover.isEmpty() && remaining > 0) {
                ItemStack leftoverFromStock = stockHandler.insertItem(denomIndex, leftover, false);
                int insertedToStock = leftover.getCount() - leftoverFromStock.getCount();
                remaining -= insertedToStock * billValue;
            }
        }

        if (remaining > 0) {
            // Could not store full amount - log or handle this
            BetterEconomy.LOGGING("Shop inventory full! Could not store $" + remaining);
        }
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
        int totalSlots = stockHandler.getSlots() + itemForSale.getSlots();
        SimpleContainer inventory = new SimpleContainer(totalSlots);

        for (int i = 0; i < stockHandler.getSlots(); i++) {
            inventory.setItem(i, stockHandler.getStackInSlot(i));
        }

        for (int i = 0; i < itemForSale.getSlots(); i++) {
            inventory.setItem(stockHandler.getSlots() + i, itemForSale.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Shop");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        String currencySymbol = ModConfigs.COMMON.currencySymbol.get();
        PacketDistributor.sendToPlayer((ServerPlayer) player, new S2CMessageCurrencySymbol(currencySymbol));

        boolean isOwner = player.getUUID().equals(this.ownerUUID);
        boolean canOpenMenu = slotHasItem() || hasEnoughStock(1);

        if (isOwner) {
            if (!player.isCrouching()) {
                return new ShopOwnerMenu(i, inventory, this, this.data);
            } else {
                if (canOpenMenu) {
                    PacketDistributor.sendToPlayer((ServerPlayer) player, new S2CMessageItemAndPrice(getItemForSale(), getPrice(), getStock(), true));
                    return new ShopCustomerMenu(i, inventory, this);
                } else {
                    player.sendSystemMessage(Component.literal("You must finish setting up your shop!").withStyle(ChatFormatting.YELLOW));
                }
            }
        } else {
            if (canOpenMenu) {
                PacketDistributor.sendToPlayer((ServerPlayer) player, new S2CMessageItemAndPrice(getItemForSale(), getPrice(), getStock(), false));
                return new ShopCustomerMenu(i, inventory, this);
            } else {
                player.sendSystemMessage(Component.literal("Shop is Closed! Please try again later!").withStyle(ChatFormatting.RED));
            }
        }
        return null;
    }
}
