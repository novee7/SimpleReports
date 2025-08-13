package dev.nove.model;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ICommand implements CommandExecutor, TabCompleter {
   protected final JavaPlugin plugin;
   protected final boolean tabComplete;

   public ICommand(JavaPlugin manager, boolean tabComplete) {
      this.plugin = manager;
      this.tabComplete = tabComplete;
   }

   public ICommand(JavaPlugin manager) {
      this(manager, false);
   }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return commandSender instanceof Player p ? this.tab(strings) : List.of();
    }

   public boolean onCommand(@NotNull CommandSender var1, @NotNull Command var2, @NotNull String var3, @NotNull String[] var4) {
      if (var1 instanceof Player) {
         Player p = (Player)var1;
         this.command(p, var4);
      }

      return false;
   }

   public abstract void command(Player var1, String[] var2);

    public List<String> tab(String[] args) {
        return List.of();
    }

   @SuppressWarnings("all")
   public void register(String command_name) {
      this.plugin.getLogger().info("[?] Registering command: " + command_name);
      this.plugin.getCommand(command_name).setExecutor(this);
      if (this.tabComplete) {
         this.plugin.getCommand(command_name).setTabCompleter(this);
      }

   }

   protected FileConfiguration config(){
       return this.plugin.getConfig();
   }
}
