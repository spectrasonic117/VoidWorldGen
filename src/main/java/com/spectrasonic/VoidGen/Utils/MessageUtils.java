package com.spectrasonic.VoidGen.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MessageUtils {

    public static final Component DIVIDER = Component.text("----------------------------------------",
            NamedTextColor.GRAY);
    public static final Component PREFIX = Component.text("[").append(
            Component.text("VG", NamedTextColor.LIGHT_PURPLE).append(
                    Component.text("]", NamedTextColor.GRAY).append(
                            Component.text(" » ", NamedTextColor.GOLD))));
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private MessageUtils() {
    }

    public static void sendMessage(CommandSender sender, @NotNull String message) {
        sender.sendMessage(PREFIX.append(deserialize(message)));
    }

    public static void sendStartupMessage(JavaPlugin plugin) {
        Component[] messages = {
                deserialize(
                        "<b><gradient:blue:red>██    ██  ██████  ██ ██████   ██████  ███████ ███    ██</gradient></b>"),
                deserialize("<gradient:blue:red>██    ██ ██    ██ ██ ██   ██ ██       ██      ████   ██</gradient>"),
                deserialize("<gradient:blue:red>██    ██ ██    ██ ██ ██   ██ ██   ███ █████   ██ ██  ██</gradient>"),
                deserialize("<gradient:blue:red> ██  ██  ██    ██ ██ ██   ██ ██    ██ ██      ██  ██ ██</gradient>"),
                deserialize("<gradient:blue:red>  ████    ██████  ██ ██████   ██████  ███████ ██   ████</gradient>"),
                Component.empty(),
                PREFIX.append(
                        Component.text(plugin.getPluginMeta().getName() + " Plugin Enabled!", NamedTextColor.GREEN)),
                PREFIX.append(Component.text("Version: ", NamedTextColor.AQUA)
                        .append(Component.text(plugin.getPluginMeta().getVersion(), NamedTextColor.WHITE))),
                PREFIX.append(Component.text("Developed by: ", NamedTextColor.RED)
                        .append(Component.text(plugin.getPluginMeta().getAuthors().toString(), NamedTextColor.WHITE))),
                DIVIDER
        };

        for (Component message : messages) {
            Bukkit.getConsoleSender().sendMessage(message);
        }
    }

    public static void sendShutdownMessage(JavaPlugin plugin) {
        Component[] messages = {
                DIVIDER,
                PREFIX.append(
                        Component.text(plugin.getPluginMeta().getName() + " plugin Disabled!", NamedTextColor.RED)),
                DIVIDER
        };

        for (Component message : messages) {
            Bukkit.getConsoleSender().sendMessage(message);
        }
    }

    public static Component deserialize(@Nullable String message) {
        if (message == null || message.isEmpty()) {
            return Component.empty();
        }
        return MINI_MESSAGE.deserialize(message);
    }
}
