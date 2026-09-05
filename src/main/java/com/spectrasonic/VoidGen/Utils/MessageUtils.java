package com.spectrasonic.VoidGen.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessageUtils {

    public static final String DIVIDER = "----------------------------------------";
    public static final String PREFIX = "&7[&dVG&7] &6»&r ";

    private MessageUtils() {
        // Private constructor to prevent instantiation
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(colorize(PREFIX + message));
    }

    public static void sendMessage(CommandSender sender, String message, Object... args) {
        sender.sendMessage(colorize(PREFIX + String.format(message, args)));
    }

    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(colorize(PREFIX + message));
    }

    public static void sendStartupMessage(JavaPlugin plugin) {
        String[] messages = {

                "&b██    ██  ██████  ██ ██████   ██████  ███████ ███    ██&r",
                "&b██    ██ ██    ██ ██ ██   ██ ██       ██      ████   ██&r",
                "&b██    ██ ██    ██ ██ ██   ██ ██   ███ █████   ██ ██  ██&r",
                "&b ██  ██  ██    ██ ██ ██   ██ ██    ██ ██      ██  ██ ██&r",
                "&b  ████    ██████  ██ ██████   ██████  ███████ ██   ████&r",
                "",
                PREFIX + "&f" + plugin.getPluginMeta().getName() + "&a Plugin Enabled!",
                PREFIX + "&d" + "Version: &b" + plugin.getPluginMeta().getVersion(),
                PREFIX + "&f" + "Developed by: &c" + plugin.getPluginMeta().getAuthors(),
                DIVIDER
        };

        for (String message : messages) {
            Bukkit.getConsoleSender().sendMessage(colorize(message));
        }
    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(colorize(message));
    }

    public static void sendShutdownMessage(JavaPlugin plugin) {
        String[] messages = {
                DIVIDER,
                PREFIX + "&c" + plugin.getPluginMeta().getName() + " plugin Disabled!",
                DIVIDER
        };

        for (String message : messages) {
            Bukkit.getConsoleSender().sendMessage(colorize(message));
        }
    }

    private static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}