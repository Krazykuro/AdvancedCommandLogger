package com.aclogger.utils;

public class OutputParser {

    public static String parse(String command) {

        if (command.startsWith("/give")) {
            return "Items granted successfully.";
        }

        if (command.startsWith("/gamemode")) {
            return "Gamemode updated.";
        }

        if (command.startsWith("/fly")) {
            return "Flight toggled.";
        }

        if (command.startsWith("/tp")) {
            return "Teleport successful.";
        }

        return "Command executed.";
    }
}