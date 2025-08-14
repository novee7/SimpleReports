package dev.nove.model.managers;

import dev.nove.file.ReportsYML;
import dev.nove.hook.DiscordHook;
import dev.nove.model.IManager;
import dev.nove.utils.GeneralUtils;
import dev.nove.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.Objects;

public class ReportManager extends IManager {

    private final ReportsYML file;
    private final DiscordHook webhook;

    public ReportManager(JavaPlugin plugin, ReportsYML file, DiscordHook webhook) {
        super(plugin);
        this.webhook = webhook;
        this.file = file;
    }

    public void report(Player player, Player target, ReportType type, String reason) {
        for (String message : config().getStringList("messages.broadcast")) {
            GeneralUtils.broadcast(message.replace("%player%", player.getName())
                    .replace("%target%", target.getName())
                    .replace("%type%", type.name().toLowerCase())
                    .replace("%reason%", reason), "reports.admin", player);
        }
        for (String message : config().getStringList("messages.report")) {
            MessageUtils.sendMessage(player, message.replace("%player%", player.getName()));

        }
        this.file.addReport(new Report(player.getUniqueId(), target.getUniqueId(), type, reason, Instant.now()));
        this.webhook.sendReport(player, target, type.name(), reason);
    }

    public void lastReport(Player player) {
        Report report = this.file.getLastReport();

        if (report == null) {
            MessageUtils.sendMessage(player, config().getString("exempts.no-reports"));
            return;
        }

        for (String message : config().getStringList("messages.last-report.server")) {
            MessageUtils.sendMessage(player, message
                    .replace("%target%", Objects.requireNonNull(Bukkit.getOfflinePlayer(report.reported()).getName()))
                    .replace("%type%", report.type().name())
                    .replace("%reason%", report.reason())
                    .replace("%time%", GeneralUtils.date_formatter(report.timestamp())));
        }
    }

    public void lastReport(Player player, Player target) {
        Report report = this.file.getLastReportOfPlayer(target.getUniqueId());
        if (report == null) {
            MessageUtils.sendMessage(player, config().getString("exempts.no-reports"));
            return;
        }

        for (String message : config().getStringList("messages.last-report.target")) {
            MessageUtils.sendMessage(player, message
                    .replace("%player%", player.getName())
                    .replace("%target%", Objects.requireNonNull(Bukkit.getOfflinePlayer(report.reported()).getName()))
                    .replace("%type%", report.type().name())
                    .replace("%reason%", report.reason())
                    .replace("%time%", GeneralUtils.date_formatter(report.timestamp())));
        }
    }
}
