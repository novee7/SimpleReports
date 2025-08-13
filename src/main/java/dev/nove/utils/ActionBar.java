package dev.nove.utils;

import dev.nove.Initializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class ActionBar {
   public static void sendActionBar(Player p, String msg) {
      sendActionBar(p, msg, 1);
   }

   public static void sendActionBar(Player p, String msg, int ticks) {
      CompletableFuture.runAsync(() -> {
         for(int i = 0; i < ticks; ++i) {
            try {
               Thread.sleep(50L);
            } catch (InterruptedException var5) {
               Thread.currentThread().interrupt();
               break;
            }

            if (!p.isOnline()) {
               break;
            }

            Bukkit.getScheduler().runTask(Initializer.CORE, () -> {
               p.sendActionBar(ColorUtils.color(msg));
            });
         }

      });
   }
}
