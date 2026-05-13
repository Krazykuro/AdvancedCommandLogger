package com.aclogger.config;

import com.aclogger.AdvancedCommandLogger;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager {

    private final AdvancedCommandLogger plugin;
    private FileConfiguration config;

    public ConfigManager(AdvancedCommandLogger plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }

    public List<String> getStaffCommands() {
        return config.getStringList("staff-commands");
    }

    public List<String> getPlayerCommands() {
        return config.getStringList("player-commands");
    }

    public String getStaffWebhook() {
        return config.getString("webhooks.staff");
    }

    public String getPlayerWebhook() {
        return config.getString("webhooks.player");
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }
}