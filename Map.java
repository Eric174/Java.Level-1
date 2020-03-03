import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {

    public static final int GAME_MODE_HVH = 0;
    public static final int GAME_MODE_HVA = 1;

    private int fieldSizeX;
    private int fieldSizeY;
    private int gameMode;
    private int winLength;

    Map() {
        setBackground(Color.WHITE);
        this.fieldSizeX = 1;
        this.fieldSizeY = 1;
    }

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        System.out.printf("Game mode: %d\nfieldSize: %d\nwinLength: %d",
                gameMode, fieldSizeX, winLength);

        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.gameMode = gameMode;
        this.winLength = winLength;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int cellWidth = getWidth() / fieldSizeX;
        int cellHeight = getHeight() / fieldSizeY;

        for (int i = 1; i < fieldSizeX; i++) {
            g.drawLine(i * cellWidth, 0, i * cellWidth, getHeight());

        }
        for (int i = 1; i < fieldSizeY; i++) {
            g.drawLine(0, i * cellHeight, getWidth(), i * cellHeight);
        }
    }
}
