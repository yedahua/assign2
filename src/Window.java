import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window extends Base {

    private final int size;
    private final Rules model;
    private final JLabel label;
    private final BreakThrough breakThrough;
    private int current_row;
    private int current_col;
    private int counter;
    private final JButton[][] buttons;
    private final String WHITE="W";
    private final String BLACK="B";
    private final String NONE="";


    public Window(int size, BreakThrough breakThrough) {
        this.size = size;
        this.breakThrough = breakThrough;
        breakThrough.getWindowList().add(this);
        model = new Rules(size);
        buttons = new JButton[size][size];

        JPanel top = new JPanel();
        label = new JLabel();
        updateLabelText();

        JButton newGameButton = new JButton();
        newGameButton.setText("New game");
        newGameButton.addActionListener(e -> newGame());

        top.add(label);
        top.add(newGameButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addButton(mainPanel, i, j, size);
            }
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, final int i,final int j, final int size)
    {
        final JButton button = new JButton();
        buttons[i][j] = button;
        if(i==0 || i==1 ){
            button.setText(BLACK);
        } else if( i==size-1 || i==size-2){
            button.setText(WHITE);
        }

        button.addActionListener(e -> {
            //Checking the ith and jth positions
            System.out.println( i+ " .. "+ j);

            if(counter==1){
                model.move(current_row, current_col, i, j);
                updateLabelText();
                counter=0;
                AllType[][] modelBoard = model.getBoard();
                for(int r=0;r<modelBoard.length;r++){
                    for(int c=0;c<modelBoard.length;c++){
                        AllType currentType = modelBoard[r][c];
                        JButton currentButton = buttons[r][c];

                        if(currentType == AllType.WHITE){
                            currentButton.setText(WHITE);
                        }else if(currentType ==AllType.BLACK){
                            currentButton.setText(BLACK);
                        }else {
                            currentButton.setText(NONE);
                        }
                    }
                }
                showGameOver(model.findWinner());
            }else{
                String currentText = buttons[i][j].getText();
                if((model.getActualPlayer()==Player.WHITE && currentText == WHITE) ||
                        (model.getActualPlayer()==Player.BLACK && currentText == BLACK)){
                    counter++;
                    current_col=j;
                    current_row=i;
                }
            }
        });

        panel.add(button);
    }

    void showGameOver(Player winner) {
        if(winner != null){
            JOptionPane.showMessageDialog(this,
                    "Game is over. Winner: " + winner.name());
            newGame();
        }
    }

    private void newGame() {
        Window newWindow = new Window(size, breakThrough);
        newWindow.setVisible(true);

        this.dispose();
        breakThrough.getWindowList().remove(this);
    }

    
    private void updateLabelText() {
        label.setText("Current player: "
                + model.getActualPlayer().name());
    }

    @Override
    protected void doUponExit() {
        super.doUponExit();
        breakThrough.getWindowList().remove(this);
    }
}