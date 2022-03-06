
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JFrame;

public class BreakThrough extends Base {
    private JFrame frame;


    private List<Window> gameWindows = new ArrayList<>();

    public BreakThrough() {

        JButton small = new JButton();
        small.setText("6 x 6");

        small.addActionListener(getActionListener(6));

        JButton medium = new JButton();
        medium.setText("8 x 8");

        medium.addActionListener(getActionListener(8));

        JButton large = new JButton();
        large.setText("10 x 10");

        large.addActionListener(getActionListener(10));

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        getContentPane().add(small);
        getContentPane().add(medium);
        getContentPane().add(large);
    }

    private ActionListener getActionListener(final int size) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new Window(size, BreakThrough.this);
                window.setVisible(true);
                gameWindows.add(window);
            }
        };
    }

    public List<Window> getWindowList() {
        return gameWindows;
    }

    @Override
    protected void doUponExit() {
        System.exit(0);
    }
}
