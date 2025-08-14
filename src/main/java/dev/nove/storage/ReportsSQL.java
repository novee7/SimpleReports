package dev.nove.storage;

import dev.nove.Initializer;
import dev.nove.model.IFile;
import dev.nove.model.IManager;
import dev.nove.model.Report;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;
import java.time.Instant;
import java.util.*;

public class ReportsSQL extends IManager implements IFile {

    private static final String PATH;
    private static final String FILE = "reports";

    public ReportsSQL(JavaPlugin manager) {
        super(manager);
    }

    static {
        PATH = Initializer.CORE.getConfig().getString("settings.storage.file.sqlite");
    }

    public void initialize() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + FILE + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "reporter TEXT NOT NULL," +
                    "reported TEXT NOT NULL," +
                    "type TEXT NOT NULL," +
                    "timestamp TEXT NOT NULL," +
                    "reason TEXT" +
                    ");");

            Bukkit.getLogger().info(config().getString("settings.storage.logs.created"));

        } catch (SQLException e) {
            Bukkit.getLogger().severe(Objects.requireNonNull(config().getString("settings.storage.logs.error-create"))
                    .replace("%error%", e.getMessage()));
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + PATH);
    }

    public void addReport(Report report) {
        String sql = "INSERT INTO " + FILE + " (reporter, reported, type, timestamp, reason) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, report.reporter().toString());
            pstmt.setString(2, report.reported().toString());
            pstmt.setString(3, report.type().name());
            pstmt.setString(4, report.timestamp().toString());
            pstmt.setString(5, report.reason());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            Bukkit.getLogger().severe(Objects.requireNonNull(config().getString("settings.storage.logs.error-saving"))
                    .replace("%error%", e.getMessage()));
        }
    }

    public List<Report> getReportsOfPlayer(UUID reporter) {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM " + FILE + " WHERE reporter = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reporter.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UUID reported = UUID.fromString(rs.getString("reported"));
                Report.Type type = Report.Type.valueOf(rs.getString("type"));
                Instant timestamp = Instant.parse(rs.getString("timestamp"));
                String reason = rs.getString("reason");

                reports.add(new Report(reporter, reported, type, reason, timestamp));
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
        String sql = "SELECT * FROM " + FILE;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UUID reporter = UUID.fromString(rs.getString("reporter"));
                UUID reported = UUID.fromString(rs.getString("reported"));
                Report.Type type = Report.Type.valueOf(rs.getString("type"));
                Instant timestamp = Instant.parse(rs.getString("timestamp"));
                String reason = rs.getString("reason");

                allReports.add(new Report(reporter, reported, type, reason, timestamp));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allReports.stream()
                .max(Comparator.comparing(Report::timestamp))
                .orElse(null);
    }
}
