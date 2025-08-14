package dev.nove.commands;

import dev.nove.model.ICommand;
import dev.nove.model.managers.ReportManager;
import dev.nove.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class LastReportCommand extends ICommand {

    private final ReportManager manager;

    public LastReportCommand(JavaPlugin plugin, ReportManager manager) {
        super(plugin, true);
        this.manager = manager;
    }

    @Override
    public void command(Player player, String[] args) {
        if (args.length == 0) {
            this.manager.lastReport(player);
            return;
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null || !target.isOnline()) {
                MessageUtils.sendMessage(player, config().getString("exempts.player"));
                return;
            }
            this.manager.lastReport(player, target);
            return;
        }
        MessageUtils.sendMessage(player, config().getString("exempts.argument"));
    }

    @Override
    public List<String> tab(String[] args) {
        if (!config().getBoolean("settings.tabcomplete.enabled")) return List.of();

        if (args.length == 1) {
            return Bukkit.getOnlinePlayers()
                    .stream()
                    .map(Player::getName)
                    .filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase()))
                    .toList();
        }else return List.of();
    }
}
