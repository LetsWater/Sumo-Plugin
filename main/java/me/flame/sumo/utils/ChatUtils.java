package me.flame.sumo.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class ChatUtils {

    public static String format(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> format(List<String> list) {
        return list.stream().map(ChatUtils::format).collect(Collectors.toList());
    }
}
