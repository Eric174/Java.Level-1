import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Map extends JPanel {

    public static final int GAME_MODE_HVH = 0;
    public static final int GAME_MODE_HVA = 1;


    private static final int DOT_EMPTY = 0;
    private static final int DOT_HUMAN = 1;
    private static final int DOT_AI = 2;
    private static final int DOT_PADDING = 5;


    private int stateGameOver;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;

    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья!";

    private static final Random RANDOM = new Random();

    private int[][] field;

    private int fieldSizeX;
    private int fieldSizeY;
    private int gameMode;
    private int winLength;

    private int cellWidth;
    private int cellHeight;
    private boolean isGameOver;
    private boolean isInitialized;

    Map() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
        isInitialized = false;
    }

    private void setGameOver(int gameOverState) {
        isGameOver = true;
        stateGameOver = gameOverState;
        repaint();
    }

    private void update(MouseEvent e) {
        if (isGameOver) return;
        if (!isInitialized) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        if (!isCellValid(cellX, cellY) || !isCellEmpty(cellX, cellY)) {
            return;
        }
        field[cellY][cellX] = DOT_HUMAN;
        if (checkWin(DOT_HUMAN)) {
            setGameOver(STATE_WIN_HUMAN);
            return;
        }
        if (isDraw()) {
            setGameOver(STATE_DRAW);
            return;
        }
        aiTurn();
        repaint();
        if (checkWin(DOT_AI)) {
            setGameOver(STATE_WIN_AI);
            return;
        }
        if (isDraw()) {
            setGameOver(STATE_DRAW);
            return;
        }
    }

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        /*
        System.out.printf("Game mode: %d\nfieldSize: %d\nwinLength: %d",
                gameMode, fieldSizeX, winLength);
        */
        this.fieldSizeY = fieldSizeY;
        this.fieldSizeX = fieldSizeX;
        this.winLength = winLength;
        field = new int[fieldSizeY][fieldSizeX];
        isGameOver = false;
        isInitialized = true;
        repaint();
    }

    private void render(Graphics g) {
        if(!isInitialized) return;
        int width = getWidth();
        int height = getHeight();
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;
        g.setColor(Color.BLACK);;
        for (int i = 1; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }
        for (int i = 1; i < fieldSizeX; i++) {
            int  x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
               if (isCellEmpty(x, y)) continue;
               if (field[y][x] == DOT_HUMAN) {
                   g.setColor(Color.BLUE);
                   g.drawLine(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING, (x + 1) * cellWidth - DOT_PADDING, (y + 1) * cellHeight - DOT_PADDING);
                   g.drawLine((x + 1) * cellWidth - DOT_PADDING, y * cellHeight + DOT_PADDING, x * cellWidth + DOT_PADDING, (y + 1) * cellHeight - DOT_PADDING);
               } else if (field[y][x] == DOT_AI) {
                   g.setColor(Color.RED);
                   g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING, cellWidth - 2 * DOT_PADDING, cellHeight - 2 * DOT_PADDING);
               } else {
                   throw new RuntimeException(
                           String.format("Can't recognize cell field[%d][%d]: %d",y, x, field[y][x]));
               }
            }
        }
        if (isGameOver) {
            showMessageGameOver(g);
        }
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,200,getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (stateGameOver) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;
            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI, 20, getHeight() /2 );
                break;
            case STATE_WIN_HUMAN:
                        g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
                        break;
            default:
                throw new RuntimeException("Unexcpected gameOver state: " + stateGameOver);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void aiTurn() {
        if (turnHumanWinCell()) return;
        if (turnAIWinCell()) return;
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));
        field[y][x] = DOT_AI;
    }

    private boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private boolean isDraw() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean turnAIWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isCellEmpty(j, i)) {
                    field[i][j] = DOT_AI;
                    if (checkWin(DOT_AI)) return true;
                    field[i][j] = DOT_EMPTY;
                }
            }
        }
        return false;
    }

    private boolean turnHumanWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isCellEmpty(j, i)) {
                    field[i][j] = DOT_HUMAN;
                    if (checkWin(DOT_HUMAN)) {
                        field[i][j] = DOT_AI;
                        return true;
                    }
                    field[i][j] = DOT_EMPTY;
                }
            }
        }
        return false;
    }

    private boolean checkWin(int c) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (checkLine(i, j, 1, 0, winLength, c)) return true;
                if (checkLine(i, j, 1, 1, winLength, c)) return true;
                if (checkLine(i, j, 0, 1, winLength, c)) return true;
                if (checkLine(i, j, 1, -1, winLength, c)) return true;
            }
        }
        return false;
    }

    private boolean checkLine(int x, int y, int vx, int vy, int len, int c) {
        final int far_x = x + (len - 1) * vx;
        final int far_y = y + (len - 1) * vy;
        if (!isCellValid(far_x, far_y)) return false;
        for (int i = 0; i < len; i++) {
            if (field[y + i * vy][x + i * vx] != c) return  false;
        }
        return true;
    }

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
