package breakout.Display;

import breakout.Player;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


public class ScoreBoard extends Rectangle {

  private static final double SCOREBOARD_WIDTH = 200;
  private static final double SCOREBOARD_HEIGHT = 400;
  private static final double SCOREBOARD_XPOS = 400;
  private static final double SCOREBOARD_YPOS = 0;
  private static final int DISPLAY_LABEL_XPOS = 430;
  private static final int DISPLAY_LABEL_YPOS_DISPLACEMENT = 70;
  private static final String SCOREBOARD_BACKGROUND = "data/galaxy.jpg";
  private static final String[] DISPLAY_LABELS = new String[]{"Level:", "Lives:",
      "Score:", "High Score:"};

  private List<StatusDisplay> statusDisplayOrderedList;

  public ScoreBoard() {
    super(SCOREBOARD_XPOS, SCOREBOARD_YPOS, SCOREBOARD_WIDTH, SCOREBOARD_HEIGHT);
    try {
      InputStream stream = new FileInputStream(SCOREBOARD_BACKGROUND);
      Image image = new Image(stream);
      this.setFill(new ImagePattern(image));
    } catch (Exception e) {
      this.setFill(Color.PURPLE);
    }
    this.statusDisplayOrderedList = new ArrayList<>();
    statusDisplayOrderedList.add(new LevelDisplay());
    statusDisplayOrderedList.add(new LivesDisplay());
    statusDisplayOrderedList.add(new ScoreDisplay());
    statusDisplayOrderedList.add(new HighScoreDisplay());
  }

  public void updateScoreBoard(Group root, Player myPlayer) {
    for (StatusDisplay display : statusDisplayOrderedList) {
      display.update(root, myPlayer);
    }
  }

  public void addDisplaysToRoot(Group root) {
    int i = 1;
    for (StatusDisplay display : statusDisplayOrderedList) {
      Text displayLabel = display
          .newDisplay(DISPLAY_LABEL_XPOS, DISPLAY_LABEL_YPOS_DISPLACEMENT * i,
              DISPLAY_LABELS[i - 1]);
      displayLabel.setFill(Color.WHITE);
      root.getChildren().add(displayLabel);
      root.getChildren().add(display);
      i++;
    }
  }
}
