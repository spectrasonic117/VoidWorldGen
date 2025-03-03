package com.spectrasonic.voidWorldGen.Config;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class ConfigManager {
    private static ConfigManager instance;
    private final JavaPlugin plugin;
    private FileConfiguration config;
    
    private ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }
    
    public static ConfigManager getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin);
        }
        return instance;
    }
    
    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
    }
    
    public Location getFirstSpawnLocation(World world) {
        Location location = new Location(
            world,
            config.getDouble("FirstSpawn.x"),
            config.getDouble("FirstSpawn.y"),
            config.getDouble("FirstSpawn.z")
        );
        
        // Set the direction based on the config value
        String direction = config.getString("FirstSpawn.direction", "SOUTH");
        float yaw = getYawFromDirection(direction);
        location.setYaw(yaw);
        
        return location;
    }
    
    /**
     * Converts a direction string to the corresponding yaw angle
     * SOUTH: 0, WEST: 90, NORTH: 180, EAST: 270
     * 
     * @param direction The direction string from config
     * @return The yaw angle in degrees
     */
    private float getYawFromDirection(String direction) {
        return switch (direction.toUpperCase()) {
            case "SOUTH" -> 0F;
            case "WEST" -> 90F;
            case "NORTH" -> 180F;
            case "EAST" -> 270F;
            default -> 0F; // Default to SOUTH
        };
    }
}