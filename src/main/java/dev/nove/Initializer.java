package dev.nove;

import dev.nove.commands.ReportCommand;

import dev.nove.hook.DiscordHook;
import dev.nove.model.managers.ReportManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Initializer extends JavaPlugin {

    public static JavaPlugin CORE;

    @Override
    public void onEnable() {
        CORE = this;

        DiscordHook hook = new DiscordHook();
        ReportManager manager = new ReportManager(this, hook);

        new ReportCommand(this,manager).register("report");
        new ReportCommand.ReportReloadCommand(this).register("reportreload");

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        CORE = null;
    }
}
