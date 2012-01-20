package org.ks.frogger;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Kevin Sapper 2011
 */
public enum ActionCommand {

    NEWGAME("newGame"), STOPGAME("stopGame"), EXIT("exit"), TIMEUP("timeUp"),
    TIMEUPDATE("timeUpdate"), SHOWHIGHSCORE("showHighscore"),
    SHOWOPENING("showOpening"), SHOWSTAGES("showStages");

    private String command;

    private ActionCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static ActionCommand getActionCommand(String command) {
        ActionCommand result = null;
        for (ActionCommand actionCommand : ActionCommand.values()) {
            if (StringUtils.equals(actionCommand.getCommand(), command)) {
                result = actionCommand;
            }
        }
        return result;
    }
}
