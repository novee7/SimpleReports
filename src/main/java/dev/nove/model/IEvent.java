package dev.nove.model;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class IEvent implements Listener {
   protected final JavaPlugin plugin;

   public IEvent(JavaPlugin plugin) {
      this.plugin = plugin;
   }

   protected FileConfiguration config() {
      return this.plugin.getConfig();
   }

   public void register() {
      Logger var10000 = Bukkit.getLogger();
      String var10001 = this.getClass().getSimpleName();
      var10000.info("[?] Registering events: " + var10001.replace("Event", ""));
      Bukkit.getPluginManager().registerEvents(this, this.plugin);
   }
}
