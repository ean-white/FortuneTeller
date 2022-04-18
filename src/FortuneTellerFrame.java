import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;


public class FortuneTellerFrame extends JFrame
{
    JPanel top, middle, bottom, main;
    JLabel topLbl, bottomLbl;
    JButton actionBtn, quitBtn;
    JTextArea textArea;
    JScrollPane scroller;
    JScrollBar verticle;
    ImageIcon icon;
    ArrayList<String> fortunes = new ArrayList<>();
    ArrayList<Integer> repeatChecker = new ArrayList<>();

    public int index;

    public FortuneTellerFrame()
    {
        super("Fortune Teller");
        main = new JPanel();
        createTopPanel();
        createMiddlePanel();
        createBottomPanel();
        loadFortunes();

        main.setLayout(new BorderLayout());
        main.add(top, BorderLayout.NORTH);
        main.add(middle, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(3 * (screenSize.width / 4), 3 * (screenSize.height / 4));
        setLocationRelativeTo(null);


        add(main);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadFortunes()
    {
        fortunes.add("Strong reasons make strong actions.");
        fortunes.add("Pick battles big enough to matter, small enough to win.");
        fortunes.add("Carve your name on your heart and not on marble.");
        fortunes.add("Bide your time, for success is near.");
        fortunes.add("Adventure can be real happiness.");
        fortunes.add("A smile is your personal welcome mat.");
        fortunes.add("A new perspective will come with the new year.");
        fortunes.add("A lifetime of happiness lies ahead of you.");
        fortunes.add("A faithful friend is a strong defense.");
        fortunes.add("A friend is a present you give yourself.");
        fortunes.add("A feather in the hand is better than a bird in the air.");
        fortunes.add("A dubious friend may be an enemy in camouflage.");
    }

    private void createTopPanel()
    {
        top = new JPanel();
        icon = new ImageIcon("fortunehands.png");
        topLbl = new JLabel("Fortune Teller", icon, SwingConstants.CENTER);
        topLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        top.add(topLbl);
    }

    private void createMiddlePanel()
    {
        middle = new JPanel();
        textArea = new JTextArea(10, 50);
        scroller = new JScrollPane(textArea);

        scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setPreferredSize(new Dimension(700, 400));

        middle.setFont(new Font("Bradley Hand ITC", Font.ITALIC, 20));

        middle.add(scroller);
    }

    private void createBottomPanel()
    {
        bottom = new JPanel();
        bottomLbl = new JLabel();

        actionBtn = new JButton("Read My Fortune");
        actionBtn.addActionListener((ActionEvent ae) ->
        {
            mixFortunes();
        });

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            System.exit(0);
        });

        bottom.setFont(new Font("Times New Roman", Font.ITALIC, 12));
        bottom.add(actionBtn);
        bottom.add(quitBtn);
    }

    public void mixFortunes() throws ArrayIndexOutOfBoundsException
    {
        Random random = new Random();
        int previousNum;

        if (repeatChecker.size() > 1)
        {
            previousNum = repeatChecker.size() - 1;
        } else
        {
            previousNum = 0;
        }

        while (true)
        {
            index = random.nextInt(fortunes.size());
            repeatChecker.add(index);
            if (index != repeatChecker.get(previousNum)) break;
        }

        textArea.append(fortunes.get(index) + "\n");
    }

}
