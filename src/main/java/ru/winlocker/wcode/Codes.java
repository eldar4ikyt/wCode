package ru.winlocker.wcode;

import java.util.*;
import ru.winlocker.wcode.tools.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class Codes {

    private static List<Code> codes = new ArrayList<>();

    public static void loadCodes() {
        for(String codeName : Utils.getConfig().getConfigurationSection("codes").getKeys(false)) { // я бы фореач юзнул))
            Code code = new Code(codeName);
            code.setLimit(Utils.getInt("codes." + codeName + ".limit"));
            code.setLimitPlayers(Utils.getInt("codes." + codeName + ".limit-players"));
            code.setCommands(Utils.getStringList("codes." + codeName + ".commands"));
            code.setMessage(Utils.getString("codes." + codeName + ".message"));
            code.setMessageLimit(Utils.getString("codes." + codeName + ".message-limit"));
            code.setMessageLimitPlayers(Utils.getString("codes." + codeName + ".message-limit-players"));
            Logger.info("Промокод " + codeName + " успешно загружен!");
            codes.add(code);
        }

    }

    public static Code getCode(String code) {
        return codes.stream().filter(codeFilter -> codeFilter.getCode().equals(code)).findAny().orElse(null);
    }

}
