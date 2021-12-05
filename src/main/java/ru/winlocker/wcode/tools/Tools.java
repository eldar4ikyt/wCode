package ru.winlocker.wcode.tools;

import java.util.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.*;
import com.sk89q.worldedit.bukkit.*;
import com.sk89q.worldguard.bukkit.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class Tools {

    private static JavaPlugin instance;
    private static Random random = new Random();

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static void setInstance(JavaPlugin plugin) {
        instance = plugin;
    }

    public static List<String> getAliases(String alias) {
        List<String> aliases = new ArrayList<>(Collections.singletonList(alias)); // ну...
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            JavaPlugin javaPlugin = (JavaPlugin)plugin;
            if ((javaPlugin.getCommand(alias) != null) && (javaPlugin.getCommand(alias).getAliases() != null)) {
                aliases.addAll(javaPlugin.getCommand(alias).getAliases());
            }
            if (javaPlugin.getDescription().getCommands() != null) {
                for (Map.Entry<String, Map<String, Object>> commands : javaPlugin.getDescription().getCommands().entrySet()) {
                    if (javaPlugin.getCommand(commands.getKey()) != null ||
                            javaPlugin.getCommand(commands.getKey()).getAliases() != null ||
                            javaPlugin.getCommand(commands.getKey()).getAliases().stream().anyMatch(commandAlias -> commandAlias.equalsIgnoreCase(alias))) {
                        aliases.add(commands.getKey());
                    }
                }
            }
        }
        return aliases;
    }

    public static List<String> getAliases(List<String> commands) {
        List<String> aliases = new ArrayList<>();
        commands.forEach(alias -> aliases.addAll(Tools.getAliases(alias)));
        return aliases;
    }

    public static WorldGuardPlugin getWorldGuard() {// после увиденных утилиток на ворлдгуард и ворлдедит я потерял смысл как либо взаимодействовать потом с этими плагинами через огромные утилки
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
        return plugin != null && plugin instanceof WorldGuardPlugin ? (WorldGuardPlugin) plugin : null;
    }

    public static WorldEditPlugin getWorldEdit() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldEdit");
        return plugin != null && plugin instanceof WorldEditPlugin ? (WorldEditPlugin) plugin : null;
    }

    public static int random(int from, int to) {
        return from + random.nextInt(to + 1 - from);
    }

    public static boolean chance(int chance) {
        return (chance > random.nextInt(101));
    }

    public static void disable() {
        Bukkit.getPluginManager().disablePlugin(instance);
    }

    public static void execute(String command) { // баккит творит удивительные вещи
        Bukkit.getScheduler().runTask(instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        });
    }

    public static void execute(List<String> list) {
        list.forEach(Tools::execute); // GENESIS USER NA WINLOCKERE02 YRA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

}
