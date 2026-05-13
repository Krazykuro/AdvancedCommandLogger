package com.aclogger.commands;

import com.aclogger.AdvancedCommandLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AdminCommand implements CommandExecutor {

    private final AdvancedCommandLogger plugin;

    public AdminCommand(AdvancedCommandLogger plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("/aclogger reload|testwebhook|stats");
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "reload" -> {
                plugin.getConfigManager().reload();
                sender.sendMessage("Config reloaded.");
            }

            case "testwebhook" -> {
                plugin.getWebhookManager().queue(
                        plugin.getConfigManager().getStaffWebhook(),
                        "{\"content\":\"Webhook test successful.\"}"
                );

                sender.sendMessage("Webhook test sent.");
            }

            case "stats" -> sender.sendMessage("Plugin operational.");

        }

        return true;
    }
}