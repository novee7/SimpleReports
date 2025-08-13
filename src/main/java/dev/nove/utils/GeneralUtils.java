package dev.nove.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GeneralUtils {
    public static void broadcast(String message) {
        if (message == null || message.isEmpty()) {
            return;
        }
        Bukkit.getOnlinePlayers().forEach(player -> MessageUtils.sendMessage(player,message));
    }
    public static void broadcast(String message, Player player) {
        if (message == null || message.isEmpty()) {
            return;
        }
        Bukkit.getOnlinePlayers().forEach(players -> {
            if (player != players) MessageUtils.sendMessage(players,message);
        });
    }
    public static void broadcast(String message, String permission) {
        if (message == null || message.isEmpty()) {
            return;
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.hasPermission(permission)) MessageUtils.sendMessage(player,message);
        });
    }
}
