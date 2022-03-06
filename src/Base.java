
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Base extends JFrame {

    public Base() {
        setTitle("Break-through");
        setSize(600, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }
        });

    }

    /**
     * Exit confirmation
     */
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?","Quitting", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }

    protected void doUponExit() {
        this.dispose();
    }
}
