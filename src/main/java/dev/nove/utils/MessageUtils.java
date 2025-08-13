package dev.nove.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageUtils {

   @SuppressWarnings("all")
   public static void sendMessage(Player p, String msg) {
      if (msg == null) {
         Bukkit.getLogger().severe("Message is null");
         return;
      }

      p.sendMessage(ColorUtils.color(msg));
   }

}
