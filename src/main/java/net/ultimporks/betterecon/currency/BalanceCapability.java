package net.ultimporks.betterecon.currency;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BalanceCapability implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<Balance> BALANCE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private final Balance balance = new Balance();
    private final LazyOptional<Balance> optional = LazyOptional.of(() -> balance);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == BALANCE_CAPABILITY) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return balance.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        balance.deserializeNBT(nbt);
    }

    public void invalidate() {
        optional.invalidate();
    }


}
