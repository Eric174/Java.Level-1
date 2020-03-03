import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 270;
    private GameWindow gameWindow;

    private JRadioButton jrbHumVsAi;
    private JRadioButton jrbHumVsHum;
    private JSlider jsFieldSize;
    private JSlider jsWinLength;
    private static final String FIELD_SIZE_PREFIX = "Field size is: ";
    private static final String WIN_LENGTH_PREFIX = "Winning length is: ";
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final int MIN_WIN_LENGTH = 3;

    Settings(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Rectangle bounds = gameWindow.getBounds();
        int posX = (int)bounds.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int)bounds.getCenterY() - WINDOW_HEIGHT / 2;
        setLocation(posX, posY);
        setResizable(false);
        setTitle("NEW GAME");
        setLayout(new GridLayout(10,1));
        addGameMode();
        addField();
        JButton btnOK = new JButton("START GAME");
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        add(btnOK);
    }

    private void addGameMode() {
        add(new JLabel("Choose game mode"));
        jrbHumVsAi = new JRadioButton("Human vs AI", true);
        jrbHumVsHum = new JRadioButton("Human vs Human");
        ButtonGroup mode = new ButtonGroup();
        mode.add(jrbHumVsAi);
        mode.add(jrbHumVsHum);
        add(jrbHumVsAi);
        add(jrbHumVsHum);

    }

    private void addField() {
        JLabel jlFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        JLabel jlWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH);

        jsFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        jsWinLength = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_WIN_LENGTH);

        jsFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = jsFieldSize.getValue();
                jlFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
                jsWinLength.setMaximum(currentValue);
            }
        });

        jsWinLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                jlWinLength.setText(WIN_LENGTH_PREFIX + jsWinLength.getValue());
            }
        });

        add(new JLabel("Choose field size"));
        add(jlFieldSize);
        add(jsFieldSize);
        add(new JLabel("Choose winning length"));
        add(jlWinLength);
        add(jsWinLength);
    }

    private void startGame() {
        int gameMode;
        if (jrbHumVsAi.isSelected()) {
            gameMode = Map.GAME_MODE_HVA;
        } else if (jrbHumVsHum.isSelected()){
            gameMode = Map.GAME_MODE_HVH;
        } else {
            throw new RuntimeException("Unexpected mode");
        }

        int fieldSize = jsFieldSize.getValue();
        int winLength = jsWinLength.getValue();

        gameWindow.start(gameMode, fieldSize, fieldSize, winLength);
        setVisible(false);
    }
}
