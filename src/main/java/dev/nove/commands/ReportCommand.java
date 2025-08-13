package dev.nove.commands;

import dev.nove.model.ICommand;
import dev.nove.model.managers.ReportManager;
import dev.nove.model.managers.ReportType;
import dev.nove.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReportCommand extends ICommand {

    private final ReportManager manager;

    public ReportCommand(JavaPlugin plugin, ReportManager manager) {
        super(plugin, true);
        this.manager = manager;
    }

    @Override
    public void command(Player player, String[] args) {
        if (args.length < 3 && args.length != 1) {
            MessageUtils.sendMessage(player, config().getString("messages.usage"));
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            MessageUtils.sendMessage(player, config().getString("exempts.player"));
            return;
        }
        String reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
        if (reason.isEmpty()) {
            MessageUtils.sendMessage(player, config().getString("exempts.reason"));
            return;
        }
        String type = args[1];
        switch (type) {
            case "cheating" -> this.manager.report(player,target, ReportType.CHEATING, reason);

            case "exploiting" -> this.manager.report(player,target, ReportType.EXPLOITING, reason);

            case "harassment" -> this.manager.report(player,target, ReportType.HARASSMENT, reason);

            case "other" -> this.manager.report(player,target, ReportType.OTHER, reason);

            default -> MessageUtils.sendMessage(player, config().getString("exempts.type"));
        }
    }
    @Override
    public List<String> tab(String[] args) {
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers()
                    .stream()
                    .map(Player::getName)
                    .filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase()))
                    .toList();
        } else if (args.length == 2) {
            return Stream.of("cheating", "exploiting", "harassment", "other")
                    .filter(s -> s.toLowerCase().startsWith(args[1].toLowerCase()))
                    .toList();
        }
        if (args[1].equalsIgnoreCase("cheating")) {
            return List.of("Macros", "CW", "AutoTotem", "AimAssist", "TriggerBot", "KillAura", "Reach");
        }
        return List.of();
    }

    public static class ReportReloadCommand extends ICommand {

        public ReportReloadCommand(JavaPlugin plugin) {
            super(plugin, false);
        }

        @Override
        public void command(Player player, String[] args) {
            MessageUtils.sendMessage(player, config().getString("messages.reload"));
            this.plugin.reloadConfig();
        }
    }
}
