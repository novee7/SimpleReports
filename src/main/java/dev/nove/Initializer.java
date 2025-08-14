package dev.nove;

import dev.nove.commands.LastReportCommand;
import dev.nove.commands.ReportCommand;

import dev.nove.storage.ReportsSQL;
import dev.nove.storage.ReportsYML;
import dev.nove.hook.DiscordHook;
import dev.nove.model.IFile;
import dev.nove.model.managers.ReportManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Initializer extends JavaPlugin {

    public static JavaPlugin CORE;
    public static IFile STORAGE;

    @Override
    public void onEnable() {
        CORE = this;

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        switch (getConfig().getString("settings.storage.TYPE","yaml1").toLowerCase()) {
            case "sqlite":
                STORAGE = new ReportsSQL(this);
                break;
            case "yaml":
            default:
                STORAGE = new ReportsYML(this);
                break;
        }

        DiscordHook hook = new DiscordHook();
        ReportManager manager = new ReportManager(this, STORAGE, hook);

        new ReportCommand(this,manager).register("report");
        new ReportCommand.Reload(this).register("reportreload");
        new LastReportCommand(this, manager).register("lastreport");

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        if (getConfig().getBoolean("settings.storage.enabled",true)) STORAGE.initialize();

    }

    @Override
    public void onDisable() {
        CORE = null;
    }
}
