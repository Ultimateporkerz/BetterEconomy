package net.ultimporks.betterecon.interfaces;

public interface IBalance {
    int getBalance();
    void setBalance(int amount);
    void addBalance(int amount);
    void subtractBalance(int amount);
}
