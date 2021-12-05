package ru.winlocker.wcode;

import java.util.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: WinLocker02
 */

public class Code {

    private String code;
    private int limit;
    private int limitPlayers;
    private List<String> commands = new ArrayList<>();
    private String message;
    private String messageLimit;
    private String messageLimitPlayers;

    public Code(String code) {
        this.code = code;
    }

    public int getLimitPlayers() {
        return this.limitPlayers;
    }

    public void setLimitPlayers(int limitPlayers) {
        this.limitPlayers = limitPlayers;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<String> getCommands() {
        return this.commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageLimit() {
        return this.messageLimit;
    }

    public void setMessageLimit(String messageLimit) {
        this.messageLimit = messageLimit;
    }

    public String getMessageLimitPlayers() {
        return this.messageLimitPlayers;
    }

    public void setMessageLimitPlayers(String messageLimitPlayers) {
        this.messageLimitPlayers = messageLimitPlayers;
    }

    public String getCode() {
        return this.code;
    }

}
