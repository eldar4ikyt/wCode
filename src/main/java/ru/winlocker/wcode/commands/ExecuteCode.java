package ru.winlocker.wcode.commands;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import ru.winlocker.wcode.*;
import ru.winlocker.wcode.data.*;
import ru.winlocker.wcode.tools.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class ExecuteCode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!Utils.has(sender, "wcode.code")) {
            return true;
        }
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player)sender;
        if (args.length == 0) {
            Utils.sendMessage(player, Utils.getMessage("code"));
            return true;
        }
        Code code = Codes.getCode(args[0]);
        if(code == null) {
            Utils.sendMessage(player, Utils.getMessage("code-notfound"));
            return true;
        }
        if (!player.hasPermission("wcode.bypasslimit")) {
            if (code.getLimitPlayers() != 0) {
                if (DataCodes.getCode(code.getCode()) >= code.getLimitPlayers()) {
                    Utils.sendMessage(sender, code.getMessageLimitPlayers());
                    return true;
                }

                DataCodes.addUse(code.getCode());
            }

            if (code.getLimit() != 0) {
                if (DataPlayers.getPlayer(player, code.getCode()) >= code.getLimit()) {
                    Utils.sendMessage(sender, code.getMessageLimit());
                    return true;
                }

                DataPlayers.addUse(player, code.getCode());
            }
        }

        if (code.getCommands() != null) {
            Tools.execute(Utils.replaceList(code.getCommands(), "%player%", player.getName()));
        }

        Utils.sendMessage(player, code.getMessage());
        return true;

    }

}
