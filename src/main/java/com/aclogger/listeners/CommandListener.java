package com.aclogger.listeners;

import com.aclogger.AdvancedCommandLogger;
import com.aclogger.discord.EmbedBuilderUtil;
import com.aclogger.utils.CommandMatcher;
import com.aclogger.utils.OutputParser;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    private final AdvancedCommandLogger plugin;

    public CommandListener(AdvancedCommandLogger plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("aclogger.bypass")) return;

        String command = event.getMessage().toLowerCase();

        boolean staff = CommandMatcher.matches(command, plugin.getConfigManager().getStaffCommands());
        boolean playerCmd = CommandMatcher.matches(command, plugin.getConfigManager().getPlayerCommands());

        if (!staff && !playerCmd) return;

        String output = OutputParser.parse(command);

        String embed = EmbedBuilderUtil.build(
                player,
                command,
                output,
                staff
        );

        String webhook = staff
                ? plugin.getConfigManager().getStaffWebhook()
                : plugin.getConfigManager().getPlayerWebhook();

        plugin.getWebhookManager().queue(webhook, embed);

        plugin.getSQLiteManager().insert(
                player.getName(),
                command,
                output
        );
    }
}