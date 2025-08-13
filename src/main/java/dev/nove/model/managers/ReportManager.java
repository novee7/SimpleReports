package dev.nove.model.managers;

import dev.nove.hook.DiscordHook;
import dev.nove.model.IManager;
import dev.nove.utils.GeneralUtils;
import dev.nove.utils.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ReportManager extends IManager {

    private final DiscordHook webhook;

    public ReportManager(JavaPlugin manager, DiscordHook webhook) {
        super(manager);
        this.webhook = webhook;
    }

    public void report(Player player, Player target, ReportType type, String reason) {
        for (String message : config().getStringList("messages.broadcast")) {
            GeneralUtils.broadcast(message.replace("%player%", player.getName())
                    .replace("%target%", target.getName())
                    .replace("%type%", type.name().toLowerCase())
                    .replace("%reason%", reason), "reports.admin");

        }
        for (String message : config().getStringList("messages.report")) {
            MessageUtils.sendMessage(player, message.replace("%player%", player.getName()));

        }
        webhook.sendReport(player, target, type.name(), reason);
    }
}
