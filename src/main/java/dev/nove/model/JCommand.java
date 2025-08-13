package dev.nove.model;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class JCommand {
   protected final JavaPlugin plugin;
   protected final boolean tabComplete;

   public JCommand(JavaPlugin manager, boolean tabComplete) {
      this.plugin = manager;
      this.tabComplete = tabComplete;
   }

   public JCommand(JavaPlugin manager) {
      this(manager, false);
   }

   public abstract void command(Player var1, String[] var2);

   public abstract List<String> tab(Player var1, String[] var2);

   public void register() {
      this.register(this.getClass().getSimpleName().toLowerCase().replace("command", ""));
   }

   public void register(String command_name) {
      this.plugin.getLogger().info("[?] Registering command: " + command_name);
      Bukkit.getServer().getCommandMap().register(command_name, new BukkitCommand(command_name) {
         public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] strings) {
            if (sender instanceof Player p) {
                JCommand.this.command(p, strings);
            }

            return true;
         }

         @NotNull
         public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
            List<String> list = new ArrayList<>();
            if (sender instanceof Player p) {
                list.addAll(JCommand.this.tab(p, args));
            }

            return list;
         }
      });
   }
}
