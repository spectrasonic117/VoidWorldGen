package com.spectrasonic.VoidGen;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import com.spectrasonic.VoidGen.Commands.ReloadCommand;
import com.spectrasonic.VoidGen.FirstSpawn.FirstSpawnManager;
import com.spectrasonic.VoidGen.Gen.VoidChunkGenerator;
import com.spectrasonic.VoidGen.Utils.MessageUtils;

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
