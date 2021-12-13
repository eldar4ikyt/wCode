package ru.winlocker.wcode.commands;

import org.bukkit.command.*;
import ru.winlocker.wcode.tools.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class ExecuteCodeReload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!Utils.has(sender, "wcode.reload")) {
            // Message no permission is empty??
            return true;
        }
        Utils.reloadConfig();
        Utils.sendMessage(sender, Utils.getMessage("codereload"));
        return true;
    }

}
