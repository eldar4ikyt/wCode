package ru.winlocker.wcode.tools;

import org.bukkit.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class Logger {

    public static void info(String text) {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&b[" + Tools.getInstance().getName() + "/INFO] " + text));
    }

    public static void warn(String text) {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&e[" + Tools.getInstance().getName() + "/WARN] " + text));
    }

    public static void error(String text) {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&c[" + Tools.getInstance().getName() + "/ERROR] " + text));
    }

}
