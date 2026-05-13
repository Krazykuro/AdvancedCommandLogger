package com.aclogger.discord;

import com.aclogger.AdvancedCommandLogger;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncWebhookManager {

    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final AdvancedCommandLogger plugin;

    public AsyncWebhookManager(AdvancedCommandLogger plugin) {
        this.plugin = plugin;
    }

    public void queue(String webhook, String payload) {
        executor.submit(() -> send(webhook, payload));
    }

    private void send(String webhook, String payload) {
        try {
            HttpURLConnection connection = (HttpURLConnection) URI.create(webhook).toURL().openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = connection.getOutputStream()) {
                os.write(payload.getBytes());
            }

            connection.getResponseCode();

        } catch (Exception e) {
            plugin.getLogger().warning("Webhook failed: " + e.getMessage());
        }
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}