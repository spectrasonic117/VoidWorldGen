package com.spectrasonic.VoidGen;

import org.bukkit.command.PluginCommand;
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
        new FirstSpawnManager(this).register();
        ReloadCommand reloadCommand = new ReloadCommand(this);
        PluginCommand command = getCommand("voidworldgen");
        if (command != null) {
            command.setExecutor(reloadCommand);
            command.setTabCompleter(reloadCommand);
        }
        MessageUtils.sendStartupMessage(this);
    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id) {
        return new VoidChunkGenerator(this);
    }
}
