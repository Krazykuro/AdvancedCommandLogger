package com.aclogger;

import com.aclogger.commands.AdminCommand;
import com.aclogger.config.ConfigManager;
import com.aclogger.database.SQLiteManager;
import com.aclogger.discord.AsyncWebhookManager;
import com.aclogger.listeners.CommandListener;
import org.bukkit.plugin.java.JavaPlugin;

public class AdvancedCommandLogger extends JavaPlugin {

    private static AdvancedCommandLogger instance;
    private ConfigManager configManager;
    private AsyncWebhookManager webhookManager;
    private SQLiteManager sqliteManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        this.configManager = new ConfigManager(this);
        this.webhookManager = new AsyncWebhookManager(this);
        this.sqliteManager = new SQLiteManager(this);

        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        getCommand("aclogger").setExecutor(new AdminCommand(this));

        getLogger().info("AdvancedCommandLogger enabled.");
    }

    @Override
    public void onDisable() {
        webhookManager.shutdown();
        sqliteManager.close();
    }

    public static AdvancedCommandLogger getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public AsyncWebhookManager getWebhookManager() {
        return webhookManager;
    }

    public SQLiteManager getSQLiteManager() {
        return sqliteManager;
    }
}