package com.spectrasonic.voidWorldGen;

import com.spectrasonic.voidWorldGen.Gen.VoidChunkGenerator;
import com.spectrasonic.voidWorldGen.Utils.MessageUtils;
import com.spectrasonic.voidWorldGen.Commands.ReloadCommand;
import com.spectrasonic.voidWorldGen.FirstSpawn.FirstSpawnManager;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new FirstSpawnManager(this);
        ReloadCommand reloadCommand = new ReloadCommand(this);
        getCommand("voidworldgen").setExecutor(reloadCommand);
        getCommand("voidworldgen").setTabCompleter(reloadCommand);
        MessageUtils.sendStartupMessage(this);
    }

    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id) {
        return new VoidChunkGenerator(this);
    }
}
