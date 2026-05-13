package com.aclogger.utils;

import java.util.List;

public class CommandMatcher {

    public static boolean matches(String input, List<String> configured) {

        String normalized = input.toLowerCase();

        for (String cmd : configured) {
            if (normalized.startsWith(cmd.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
}