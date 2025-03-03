package com.spectrasonic.voidWorldGen.FirstSpawn;

import com.spectrasonic.voidWorldGen.Config.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FirstSpawnManager implements Listener {
    private final JavaPlugin plugin;
    private final ConfigManager configManager;
    
    public FirstSpawnManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.configManager = ConfigManager.getInstance(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onPlayerFirstJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            Location spawnLocation = configManager.getFirstSpawnLocation(player.getWorld());
            player.teleport(spawnLocation);
        }
    }
}