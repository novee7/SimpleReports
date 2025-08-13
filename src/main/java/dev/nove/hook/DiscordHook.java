package dev.nove.hook;

import dev.nove.Initializer;
import org.bukkit.entity.Player;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DiscordHook {

    public void sendReport(Player reporter, Player target, String type, String reason) {
        String payload = buildEmbedPayload(reporter.getName(), target.getName(), type, reason);
        post(payload);
    }

    // ---------- internals ----------
    private String buildEmbedPayload(String reporter, String target, String type, String reason) {
        return "{"
                + "\"embeds\":[{"
                + "\"title\":\"" + escape("REPORT") + "\","
                + "\"color\":16711680,"
                + "\"fields\":["
                + "{\"name\":\"Reporter\",\"value\":\"" + escape(reporter) + "\",\"inline\":true},"
                + "{\"name\":\"Target\",\"value\":\"" + escape(target) + "\",\"inline\":true},"
                + "{\"name\":\"Type\",\"value\":\"" + escape(type) + "\",\"inline\":true},"
                + "{\"name\":\"Reason\",\"value\":\"" + escape(reason) + "\"}"
                + "]"
                + "}]"
                + "}";
    }

    @SuppressWarnings("all")
    private void post(String jsonPayload) {
        try {
            URL url = new URL(Initializer.CORE.getConfig().getString("settings.webhook"));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = con.getOutputStream()) {
                os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
            }

            int code = con.getResponseCode();
            InputStream is = (code >= 400) ? con.getErrorStream() : con.getInputStream();
            if (is != null) while (is.read() != -1) { /* drain */ }
            if (is != null) is.close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
