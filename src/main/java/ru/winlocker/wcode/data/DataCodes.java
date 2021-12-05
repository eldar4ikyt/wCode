package ru.winlocker.wcode.data;

import java.io.*;
import java.util.*;
import ru.winlocker.wcode.tools.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class DataCodes {

    private static Properties data = new Properties();
    private static File file = new File(Tools.getInstance().getDataFolder(), "datacodes.properties");
    private static HashMap<String, Integer> codes = new HashMap<>();

    public static void loadData() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            data.load(fileInputStream);
            data.forEach((code, limit) -> {
                codes.put(code.toString(), Integer.parseInt(limit.toString()));
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
            for (Map.Entry<String, Integer> entry : codes.entrySet()) {
                data.setProperty(entry.getKey(), Integer.toString(entry.getValue()));
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            data.store(fileOutputStream, null);
            fileOutputStream.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getCode(String code) {
        return codes.get(code) == null ? 0 : codes.get(code);
    }

    public static void addUse(String code) {
        codes.put(code, getCode(code) + 1);
    }

}
