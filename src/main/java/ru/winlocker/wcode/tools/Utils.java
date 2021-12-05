package ru.winlocker.wcode.tools;

import java.util.*;
import org.bukkit.*;
import org.bukkit.block.*;
import java.util.stream.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import me.clip.placeholderapi.*;
import org.bukkit.configuration.file.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class Utils {

    private static FileConfiguration config;

    public static FileConfiguration getConfig() {
        return config != null ? config : (config = Config.getFile("config.yml"));
    }

    public static void reloadConfig() {
        config = Config.getFile("config.yml");
    }

    public static String getMessage(String path) {
        return getConfig().getString("messages." + path);
    }

    public static String getString(String path) {
        return getConfig().getString(path);
    }

    public static List<String> getStringList(String path) {
        return getConfig().getStringList(path);
    }

    public static int getInt(String path) {
        return getConfig().getInt(path);
    }

    public static double getDouble(String path) {
        return getConfig().getDouble(path);
    }

    public static boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> color(List<String> text) {
        return text.stream().map(x -> color(x)).collect(Collectors.toList()); // Utils::color ??????????????????????????????????????????? ну да, я же сурсы винлокера видел (ориг), поэтому не будем переделывать)
    }

    public static boolean has(CommandSender player, String permission) { // прост винлокер берёт одни и те же утилитки и ему насрать используется тот или иной код. на оптимизацию похуй, а мне не похуй. сказали без костылей - доплатите и получите. сказали на высшем сорте и без костылей - распишите и получите, высший сорт.
        if(!player.hasPermission(permission)) {
            sendMessage(player, getConfig().getString("messages.no-permission"));
            return false;
        }
        return true;
    }

    public static String replacePlaceholders(Player player, String text) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null && PlaceholderAPI.containsPlaceholders(text)) {
            return PlaceholderAPI.setPlaceholders(player, text);
        }
        return text;
    }

    public static List<String> replacePlaceholders(Player player, List<String> text) {
        List<String> placeholders = new ArrayList<>();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            text.forEach(s -> { // я бы фильтранул и добавил, а не так делал, ебанат на винлокере
                if (PlaceholderAPI.containsPlaceholders(s)) {
                    s = PlaceholderAPI.setPlaceholders(player, s);
                }
                placeholders.add(s);
            });
            return placeholders;
        }
        return text; // бомж не имеющий плейсхолдер апи с нужным плейсом))
    }

    public static List<String> replaceList(List<String> replaceList, String replace, String to) {
        List<String> list = new ArrayList<>();
        replaceList.forEach(value -> list.add(value.replace(replace, to)));
        return list;
    }

    public static boolean equals(String message, String text) {
        return message.equalsIgnoreCase(text) || message.toLowerCase().startsWith(text.toLowerCase() + " ");
    }

    public static boolean equals(String message, List<String> text) {
        return text.stream().anyMatch(value -> message.equalsIgnoreCase(value) || message.toLowerCase().startsWith(value.toLowerCase() + " "));
    }

    public static boolean equalsCommand(String message, List<String> text) {
        return text.stream().anyMatch(command -> message.equalsIgnoreCase("/" + command) || message.toLowerCase().startsWith("/" + command.toLowerCase() + " "));
    }

    public static List<Block> nearBlocks(Location loc, int radius) { // где-то я уже это видел
        World world = loc.getWorld();
        List<Block> blocks = new ArrayList<>();
        for (int x = loc.getBlockX() - radius; x <= loc.getBlockX() + radius; ++x) {
            for (int y = loc.getBlockY() - radius; y <= loc.getBlockY() + radius; y++) {
                for (int z = loc.getBlockZ() - radius; z <= loc.getBlockZ() + radius; ++z) {
                    if (world.getBlockAt(x, y, z).getType() != Material.AIR) {
                        blocks.add(world.getBlockAt(x, y, z));
                    }
                }
            }
        }
        blocks.removeIf(blockLocation -> blockLocation.getLocation().equals(loc));
        return blocks;
    }

    public static List<Player> nearPlayers(Player player, int radius) { // я такую утилку уже видел где-то
        List<Player> list = new ArrayList<>();
        for (Player online : Bukkit.getOnlinePlayers()) { // АУ ФОРЕАЧ СУЧКА НА ВИНЛОКЕРЕ!
            if(player.getWorld().equals(online.getWorld())) {
                if(player.getLocation().distance(online.getLocation()) <= Math.pow((radius), 1.0D)) {
                    if(player != online) { // equals быстрее воркает, ещё пендосятки высерали
                        list.add(online);
                    }
                }
            }
        }
        return list;
    }

    public static void sendMessage(CommandSender player, String text) {

        if(text.isEmpty()) return;

        for(String line : text.split(";")) {
            line = line.replace("%player%", player.getName());

            if(line.startsWith("title:")) {
                if(player instanceof Player)
                    Title.sendTitle((Player) player, line.split("title:")[1]);
            }
            else if(line.startsWith("actionbar:")) {
                if(player instanceof Player)
                    ActionBar.sendActionBar((Player) player, line.split("actionbar:")[1]);
            }
            else {
                player.sendMessage(color(getMessage("prefix") + line));
            }
        }
    }

}
