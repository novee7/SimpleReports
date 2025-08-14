package dev.nove;

import dev.nove.commands.LastReportCommand;
import dev.nove.commands.ReportCommand;

import dev.nove.file.ReportsYML;
import dev.nove.hook.DiscordHook;
import dev.nove.model.managers.ReportManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Initializer extends JavaPlugin {

    public static JavaPlugin CORE;

    @Override
    public void onEnable() {
        CORE = this;

        DiscordHook hook = new DiscordHook();
        ReportsYML file = new ReportsYML(this);
        ReportManager manager = new ReportManager(this, file, hook);

        new ReportCommand(this,manager).register("report");
        new ReportCommand.Reload(this).register("reportreload");
        new LastReportCommand(this, manager).register("lastreport");

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        if (getConfig().getBoolean("settings.storage.enabled",true)) file.initialize();

    }

    @Override
    public void onDisable() {
        CORE = null;
    }
}
