package com.spectrasonic.voidWorldGen;

import com.spectrasonic.voidWorldGen.Utils.MessageUtils;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        MessageUtils.sendStartupMessage(this);
    }

    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id) {
        return new VoidChunkGenerator();
    }
}
