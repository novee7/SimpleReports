package dev.nove.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class GeneralUtils {
    public static void broadcast(String message, String permission, Player player) {
        if (message == null || message.isEmpty()) {
            return;
        }
        Bukkit.getOnlinePlayers().forEach(players -> {
            if (player.hasPermission(permission) && player != players) MessageUtils.sendMessage(player,message);
        });
    }
    public static String date_formatter (Temporal date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(date);
    }
}