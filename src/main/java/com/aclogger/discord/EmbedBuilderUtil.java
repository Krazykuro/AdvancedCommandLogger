package com.aclogger.discord;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.entity.Player;

public class EmbedBuilderUtil {

    public static String build(Player player, String command, String output, boolean staff) {

        JsonObject root = new JsonObject();
        JsonArray embeds = new JsonArray();

        JsonObject embed = new JsonObject();

        embed.addProperty("title", staff ? "STAFF COMMAND EXECUTED" : "PLAYER COMMAND USED");
        embed.addProperty("description",
                "**Player:** " + player.getName() +
                "\n**UUID:** " + player.getUniqueId() +
                "\n**Command:** `" + command + "`" +
                "\n**Output:** " + output +
                "\n**World:** " + player.getWorld().getName() +
                "\n**Ping:** " + player.getPing() +
                "\n**Creative:** " + (player.getGameMode() == org.bukkit.GameMode.CREATIVE));

        embed.addProperty("color", staff ? 16711680 : 255);

        embeds.add(embed);
        root.add("embeds", embeds);

        return root.toString();
    }
}