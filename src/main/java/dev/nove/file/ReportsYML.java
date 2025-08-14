package dev.nove.file;

import dev.nove.Initializer;
import dev.nove.model.IManager;
import dev.nove.model.managers.Report;
import dev.nove.model.managers.ReportType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

public class ReportsYML extends IManager {

    private static final File FILE;
    private static YamlConfiguration CONFIG;

    public ReportsYML(JavaPlugin manager) {
        super(manager);
    }

    public void initialize() {
        if (!FILE.exists()) {
            try {
                if (FILE.createNewFile()) {
                    Bukkit.getLogger().info(config().getString("settings.storage.logs.created"));
                }
            } catch (IOException var1) {
                Bukkit.getLogger().severe(Objects.requireNonNull(config().getString("settings.storage.logs.error-create"))
                        .replace("%error%", var1.getMessage()));
            }
        }
        reloadConfig();
    }

    public void reloadConfig() {
        CONFIG = YamlConfiguration.loadConfiguration(FILE);
    }

    public void saveConfig() {
        try {
            CONFIG.save(FILE);
        } catch (IOException var1) {
            Bukkit.getLogger().severe(Objects.requireNonNull(config().getString("settings.storage.logs.error-saving"))
                    .replace("%error%", var1.getMessage()));
        }
    }

    public static FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(FILE);
    }

    public void addReport(Report report) {
        String path = report.reporter() + "." + report.timestamp().toEpochMilli();
        CONFIG.set(path + ".reported", report.reported().toString());
        CONFIG.set(path + ".type", report.type().name());
        CONFIG.set(path + ".timestamp", report.timestamp().toString());
        CONFIG.set(path + ".reason", report.reason());
        saveConfig();
    }

    public List<Report> getReportsOfPlayer(UUID reporter) {
        List<Report> reports = new ArrayList<>();
        if (CONFIG.contains(reporter.toString())) {
            for (String key : Objects.requireNonNull(CONFIG.getConfigurationSection(reporter.toString())).getKeys(false)) {
                UUID reported = UUID.fromString(Objects.requireNonNull(CONFIG.getString(reporter + "." + key + ".reported")));
                ReportType type = ReportType.valueOf(CONFIG.getString(reporter + "." + key + ".type"));
                Instant timestamp = Instant.parse(Objects.requireNonNull(CONFIG.getString(reporter + "." + key + ".timestamp")));
                String reason = CONFIG.getString(reporter + "." + key + ".reason");
                reports.add(new Report(reporter, reported, type, reason, timestamp));
            }
        }
        return reports;
    }

    public Report getLastReportOfPlayer(UUID reporter) {
        return getReportsOfPlayer(reporter).stream()
                .max(Comparator.comparing(Report::timestamp))
                .orElse(null);
    }

    public Report getLastReport() {
        List<Report> allReports = new ArrayList<>();
        for (String reporterKey : CONFIG.getKeys(false)) {
            UUID reporter = UUID.fromString(reporterKey);
            allReports.addAll(getReportsOfPlayer(reporter));
        }
        return allReports.stream()
                .max(Comparator.comparing(Report::timestamp))
                .orElse(null);
    }

    static {
        FILE = new File(Objects.requireNonNull(Initializer.CORE.getConfig().getString("settings.storage.file")));
        CONFIG = YamlConfiguration.loadConfiguration(FILE);
    }
}
