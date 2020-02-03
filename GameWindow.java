import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private static final int WIN_WIDTH = 507;
    private static final int WIN_HIGHT = 555;
    private static final int WIN_POSX = 450;
    private static final int WIN_POSY = 100;

    Map map;
    Settings settings;

    GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic tac toe game");
        setLocation(WIN_POSX, WIN_POSY);
        setSize(WIN_WIDTH, WIN_HIGHT);

        JButton btnStart = new JButton("START");
        JButton btnExit = new JButton("EXIT");
        JPanel panelButtons = new JPanel(new GridLayout(1,2));
        panelButtons.add(btnStart);
        panelButtons.add(btnExit);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });

        map = new Map();
        settings = new Settings(this);

        add(panelButtons, BorderLayout.SOUTH);
        add(map, BorderLayout.CENTER);
        setVisible(true);
    }

    void start(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        map.startNewGame(gameMode, fieldSizeX, fieldSizeY, winLength);
    }
}
