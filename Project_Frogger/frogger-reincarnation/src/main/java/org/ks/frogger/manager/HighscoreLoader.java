package org.ks.frogger.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Kevin Sapper 2011
 */
public class HighscoreLoader {

  private static final String GAMEDIRECTORY = ".frogger-reincarnation";

  private static final String HIGHSCOREFILE = "highscore.csv";

  private static final String USERHOME = System.getProperty("user.home");

  private static final String SEPERATOR = ";";

  private static final String LINEBREAK = "\n";

  public List<Highscore> loadHighscore() throws IOException {
    List<Highscore> highscore = new ArrayList<>();

    if (getHighscoreFile().exists()) {
      for (LineIterator it = FileUtils.lineIterator(getHighscoreFile()); it.
              hasNext();) {
        String[] line = StringUtils.split(it.nextLine(), SEPERATOR);
        highscore.add(new Highscore(line[0], Long.parseLong(line[1])));
      }
    }

    return highscore;
  }

  public void saveHighscores(List<Highscore> highscoreList) throws IOException {
    StringBuilder content = new StringBuilder();

    for (Highscore highscore : highscoreList) {
      content.append(highscore.getName()).append(SEPERATOR).append(String.
              valueOf(highscore.getHighscore())).append(LINEBREAK);
    }

    FileUtils.writeStringToFile(getHighscoreFile(), content.toString());
  }

  private File getHighscoreFile() {
    String file = USERHOME + "/" + GAMEDIRECTORY + "/" + HIGHSCOREFILE;
    file = FilenameUtils.separatorsToSystem(file);

    return FileUtils.getFile(file);
  }
}
