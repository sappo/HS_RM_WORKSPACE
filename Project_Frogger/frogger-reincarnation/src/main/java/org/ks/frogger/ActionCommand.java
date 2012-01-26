package org.ks.frogger;

import org.apache.commons.lang.StringUtils;

/**
 * Administration of used action commands for JComponents
 *
 * @author Kevin Sapper 2011
 */
public enum ActionCommand {

  NEWGAME("newGame"), STOPGAME("stopGame"), EXIT("exit"), TIMEUP("timeUp"),
  TIMEUPDATE("timeUpdate"), SHOWHIGHSCORE("showHighscore"),
  SHOWOPENING("showOpening"), SHOWSTAGES("showStages"), SHOWABOUT("showAbout");

  private String command;

  private ActionCommand(String command) {
    this.command = command;
  }

  /**
   * Get the String for a action command.
   * @return the String of the action command
   */
  public String getCommand() {
    return command;
  }

  /**
   * Get the action command by it's String
   * @param command the command string
   * @return the action command to which the command string matches, else null
   */
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
