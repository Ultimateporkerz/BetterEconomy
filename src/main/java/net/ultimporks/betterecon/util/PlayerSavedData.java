package net.ultimporks.betterecon.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerSavedData extends SavedData {
    private final Set<UUID> knownPlayers = new HashSet<>();
    public static final String DATA_NAME = "betterecon_player_tracker";

    private PlayerSavedData() {}

    public PlayerSavedData(CompoundTag tag) {
        ListTag list = tag.getList("knownPlayers", 8);
        for (int i = 0; i < list.size(); i++) {
            knownPlayers.add(UUID.fromString(list.getString(i)));
        }
    }

    public static PlayerSavedData get(MinecraftServer server) {
        return server.overworld()
                .getDataStorage()
                .computeIfAbsent(
                        PlayerSavedData::new,
                        PlayerSavedData::new,
                        DATA_NAME
                );
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (UUID uuid : knownPlayers) {
            list.add(StringTag.valueOf(uuid.toString()));
        }
        tag.put("knownPlayers", list);
        return tag;
    }

    public void resetKnownPlayers() {
        knownPlayers.clear();
        setDirty();
    }

    public boolean isPlayerKnown(UUID uuid) {
        return knownPlayers.contains(uuid);
    }

    public void addPlayer(UUID uuid) {
        if (knownPlayers.add(uuid)) {
            setDirty();
        }
    }
}
