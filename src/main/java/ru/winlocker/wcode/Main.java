package ru.winlocker.wcode;

import org.bukkit.plugin.java.*;
import ru.winlocker.wcode.data.*;
import ru.winlocker.wcode.tools.*;
import ru.winlocker.wcode.commands.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class Main extends JavaPlugin {

    @Override
    public void onEnable() { // винлокер кста пидорас ещё тот, депенды не все указывает, я при деобфе ахуеваю когда не деобфает говно. а тут сурсы.
        Tools.setInstance(this);
        Codes.loadCodes();
        DataPlayers.loadData();
        DataCodes.loadData();
        getCommand("code").setExecutor(new ExecuteCode());
        getCommand("codereload").setExecutor(new ExecuteCodeReload());
        Logger.info("Плагин успешно включён, создатель плагина WinLocker - vk.com/winlocker02");
    }

    @Override
    public void onDisable() {
        DataPlayers.saveData();
        DataCodes.saveData();
    }

}
