package org.ks.frogger.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  public Map<Integer, List<Highscore>> loadHighscore() throws IOException {
    Map<Integer, List<Highscore>> highscoreList = new HashMap<>();

    if (getHighscoreFile().
            exists()) {
      for (LineIterator it = FileUtils.lineIterator(getHighscoreFile()); it.
              hasNext();) {
        String[] line = StringUtils.split(it.nextLine(), SEPERATOR);
        Integer key = Integer.parseInt(line[0]);
        if (highscoreList.containsKey(key)) {
          highscoreList.get(key).
                  add(new Highscore(line[1], Long.parseLong(line[2])));
        } else {
          List<Highscore> scores = new ArrayList<>();
          scores.add(new Highscore(line[1], Long.parseLong(line[2])));
          highscoreList.put(key, scores);
        }
      }
    }

    return highscoreList;
  }

  public void saveHighscores(Map<Integer, List<Highscore>> highscoreList) throws IOException {
    StringBuilder content = new StringBuilder();

    for (Map.Entry<Integer, List<Highscore>> entry : highscoreList.entrySet()) {
      for (Highscore highscore : entry.getValue()) {
        content.append(entry.getKey()).
                append(SEPERATOR).
                append(highscore.getName()).
                append(SEPERATOR).
                append(String.valueOf(highscore.getHighscore())).
                append(LINEBREAK);
      }
    }

    FileUtils.writeStringToFile(getHighscoreFile(), content.toString());
  }

  private File getHighscoreFile() {
    String file = USERHOME + "/" + GAMEDIRECTORY + "/" + HIGHSCOREFILE;
    file = FilenameUtils.separatorsToSystem(file);

    return FileUtils.getFile(file);
  }
}
