package net.ultimporks.betterecon.currency;


import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.ultimporks.betterecon.interfaces.IBalance;

public class Balance implements IBalance, INBTSerializable<CompoundTag> {
    private int balance = 0;

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void setBalance(int balance) {
        this.balance = Math.max(0, balance);
    }

    @Override
    public void addBalance(int amount) {
        this.balance += amount;
    }

    @Override
    public void subtractBalance(int amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    // NBT serialization methods
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("balance", balance);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.balance = nbt.getInt("balance");
    }

}
