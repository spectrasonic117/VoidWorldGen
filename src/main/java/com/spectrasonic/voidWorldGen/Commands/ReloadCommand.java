package com.spectrasonic.voidWorldGen.Commands;

import com.spectrasonic.voidWorldGen.Config.ConfigManager;
import com.spectrasonic.voidWorldGen.Utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Location;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {
    private final JavaPlugin plugin;
    private final ConfigManager configManager;
    
    public ReloadCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        this.configManager = ConfigManager.getInstance(plugin);
    }
    
    @Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
    if (!sender.hasPermission("voidworldgen.reload")) {
        MessageUtils.sendMessage(sender, "&cYou don't have permission to use this command!");
        return true;
    }
    
    if (args.length == 0 || !args[0].equalsIgnoreCase("reload")) {
        MessageUtils.sendMessage(sender, "&cUsage: /voidworldgen reload");
        return true;
    }
    
    // Reload the configuration
    configManager.loadConfig();
    
    // Notify the server that spawn locations should be updated
    plugin.getServer().getWorlds().forEach(world -> {
        Location spawnLoc = configManager.getFirstSpawnLocation(world);
        world.setSpawnLocation(
            spawnLoc.getBlockX(),
            spawnLoc.getBlockY(),
            spawnLoc.getBlockZ(),
            spawnLoc.getYaw()
        );
    });
    
    MessageUtils.sendMessage(sender, "&aConfiguration and spawn locations reloaded successfully!");
    return true;
}
    
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            if ("reload".startsWith(args[0].toLowerCase())) {
                completions.add("reload");
            }
            return completions;
        }
        return new ArrayList<>();
    }
}