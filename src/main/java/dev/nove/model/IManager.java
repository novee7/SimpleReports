package dev.nove.model;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class IManager extends IEvent {

    public IManager(JavaPlugin manager) {
        super(manager);
    }

    protected FileConfiguration config() {
        return super.config();
    }

}
