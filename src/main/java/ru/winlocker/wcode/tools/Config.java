package ru.winlocker.wcode.tools;

import java.io.*;
import org.bukkit.plugin.java.*;
import org.bukkit.configuration.file.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class Config {

    private static JavaPlugin instance = JavaPlugin.getProvidingPlugin(Config.class);

    public static FileConfiguration getFile(String fileName) {
        File file = new File(instance.getDataFolder(), fileName);

        if(!file.exists()) {
            instance.saveResource(fileName, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration save(FileConfiguration config, String fileName) {
        try {
            config.save(new File(instance.getDataFolder(), fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}