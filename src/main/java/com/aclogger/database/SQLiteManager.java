package com.aclogger.database;

import com.aclogger.AdvancedCommandLogger;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SQLiteManager {

    private Connection connection;

    public SQLiteManager(AdvancedCommandLogger plugin) {

        try {

            File file = new File(plugin.getDataFolder(), "logs.db");

            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }

            connection = DriverManager.getConnection("jdbc:sqlite:" + file);

            connection.createStatement().executeUpdate(
                    "CREATE TABLE IF NOT EXISTS command_logs (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "player TEXT," +
                            "command TEXT," +
                            "output TEXT" +
                            ")"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(String player, String command, String output) {

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO command_logs(player, command, output) VALUES(?,?,?)"
            );

            ps.setString(1, player);
            ps.setString(2, command);
            ps.setString(3, output);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (Exception ignored) {}
    }
}