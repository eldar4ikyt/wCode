package ru.winlocker.wcode.data;

import java.io.*;
import java.util.*;
import org.bukkit.entity.*;
import ru.winlocker.wcode.tools.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class DataPlayers {

    private static Properties data = new Properties();
    private static File file = new File(Tools.getInstance().getDataFolder(), "data.properties");
    private static HashMap<String, Integer> players = new HashMap<>();

    public static void loadData() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            data.load(fileInputStream);
            data.forEach((data, player) -> {
                players.put(data.toString(), Integer.parseInt(player.toString()));
            });
            file.delete();
            data.clear();
            fileInputStream.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void saveData() {
        try {
            for (Map.Entry<String, Integer> entry : players.entrySet()) {
                data.setProperty(entry.getKey(), Integer.toString(entry.getValue()));
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            data.store(fileOutputStream, null);
            fileOutputStream.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getPlayer(String player, String code) {
        String codePlayer = player + "." + code;
        return players.get(codePlayer) == null ? 0 : players.get(codePlayer);
    }

    public static int getPlayer(Player player, String code) {
        return getPlayer(player.getName(), code);
    }

    public static void addUse(Player player, String code) {
        String id = player.getName() + "." + code;
        players.put(id, getPlayer(player, code) + 1);
    }

}
