import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class View extends JFrame {

	protected Image img, img2;
	protected ImageIcon cardIC, midIC, sysIC;

	JButton playB = new JButton("Play");
	JButton hitB = new JButton("Hit");
	JButton standB = new JButton("Stand");
	JButton playAIB = new JButton("AI Play");
	JButton playAI100B = new JButton("AI Play 100");
	JButton openFile = new JButton("Open STM");
	JButton saveFile = new JButton("Save STM");
	JButton printSTM = new JButton("Print STM");
	JButton makeSTM = new JButton("Make STM");

	protected Font fontR = new Font("Helvetica", Font.BOLD, 30);
	protected Color khaki = new Color(220, 230, 100);
	protected Color TCUColors = new Color(77, 25, 121);

	JPanel topPanel = new JPanel(new BorderLayout());
	JPanel bottomPanel = new JPanel(new BorderLayout());
	
	JPanel sysCards = new JPanel(new GridLayout(1, 6));
	JPanel oppCards = new JPanel(new GridLayout(1, 6));
	JPanel midPanel = new JPanel(new BorderLayout());
	JPanel cardPanel = new JPanel(new GridLayout(2,1));
	JPanel butPanel = new JPanel(new GridLayout(9, 1));
	
	
	JPanel yourSum = new JPanel(new GridLayout(2, 1));
	JLabel winOrLose = new JLabel();

	JLabel yLab = new JLabel("Your Sum:");
	JLabel sumLab = new JLabel("");
	
	JLabel player = new JLabel("<html><h2>PLAYER</h2></html>");
	JLabel oppCard1 = new JLabel("");
	JLabel oppCard2 = new JLabel("");
	JLabel oppCard3 = new JLabel("");
	JLabel oppCard4 = new JLabel("");
	JLabel oppCard5 = new JLabel("");
	
	JLabel dealer = new JLabel("<html><h2>DEALER</h2></html>");
	JLabel sysCard1 = new JLabel("");
	JLabel sysCard2 = new JLabel("");
	JLabel sysCard3 = new JLabel("");
	JLabel sysCard4 = new JLabel("");
	JLabel sysCard5 = new JLabel("");
	
	JLabel fileLabel = new JLabel("");
	
	public View() {
		
		setLayout(new BorderLayout());
		drawDisplay();
		setBounds(200, 200, 600, 400);
		setVisible(true);

	}
	public void drawDisplay() {

		img = Toolkit.getDefaultToolkit().getImage("images/heraldo.gif");
		img2 = Toolkit.getDefaultToolkit().getImage("images/bjt.jpg");
		sysIC = new ImageIcon(img);
		midIC = new ImageIcon(img2);

		JLabel wrJLab = new JLabel(midIC);
		JLabel wrJLab2 = new JLabel("Welcome! Let's Play!");
		wrJLab.setFont(fontR);
		wrJLab2.setForeground(Color.white);
		topPanel.add(wrJLab2, BorderLayout.WEST);
		
		
		midPanel.add(cardPanel,BorderLayout.CENTER);
		midPanel.add(butPanel, BorderLayout.EAST);
			
		sysCard1.setBorder(BorderFactory.createLineBorder(Color.white));
		sysCard2.setBorder(BorderFactory.createLineBorder(Color.white));
		sysCard3.setBorder(BorderFactory.createLineBorder(Color.white));
		sysCard4.setBorder(BorderFactory.createLineBorder(Color.white));
		sysCard5.setBorder(BorderFactory.createLineBorder(Color.white));
		
		sysCard1.setHorizontalAlignment(SwingConstants.CENTER);
		sysCard2.setHorizontalAlignment(SwingConstants.CENTER);
		sysCard3.setHorizontalAlignment(SwingConstants.CENTER);
		sysCard4.setHorizontalAlignment(SwingConstants.CENTER);
		sysCard5.setHorizontalAlignment(SwingConstants.CENTER);
		
		sysCard1.setForeground(Color.WHITE);
		sysCard2.setForeground(Color.WHITE);
		sysCard3.setForeground(Color.WHITE);
		sysCard4.setForeground(Color.WHITE);
		sysCard5.setForeground(Color.WHITE);
		
		dealer.setForeground(Color.white);
		sysCards.setBackground(TCUColors);
		
		sysCards.add(dealer);
		sysCards.add(sysCard1);
		sysCards.add(sysCard2);
		sysCards.add(sysCard3);
		sysCards.add(sysCard4);
		sysCards.add(sysCard5);
		
		oppCard1.setBorder(BorderFactory.createLineBorder(TCUColors));
		oppCard2.setBorder(BorderFactory.createLineBorder(TCUColors));
		oppCard3.setBorder(BorderFactory.createLineBorder(TCUColors));
		oppCard4.setBorder(BorderFactory.createLineBorder(TCUColors));
		oppCard5.setBorder(BorderFactory.createLineBorder(TCUColors));
		
		oppCard1.setHorizontalAlignment(SwingConstants.CENTER);
		oppCard2.setHorizontalAlignment(SwingConstants.CENTER);
		oppCard3.setHorizontalAlignment(SwingConstants.CENTER);
		oppCard4.setHorizontalAlignment(SwingConstants.CENTER);
		oppCard5.setHorizontalAlignment(SwingConstants.CENTER);

		oppCard1.setForeground(TCUColors);
		oppCard2.setForeground(TCUColors);
		oppCard3.setForeground(TCUColors);
		oppCard4.setForeground(TCUColors);
		oppCard5.setForeground(TCUColors);
		
		oppCards.setBackground(Color.white);
		
		player.setForeground(Color.black);
		oppCards.add(player);
		oppCards.add(oppCard1);
		oppCards.add(oppCard2);
		oppCards.add(oppCard3);
		oppCards.add(oppCard4);
		oppCards.add(oppCard5);
		
		cardPanel.add(sysCards);
		cardPanel.add(oppCards);
		
		butPanel.add(playB);
		butPanel.add(hitB);
		butPanel.add(standB);
		butPanel.add(playAIB);
		butPanel.add(playAI100B);
		butPanel.add(makeSTM);
		butPanel.add(openFile);
		butPanel.add(saveFile);
		butPanel.add(printSTM);
		
		midPanel.setBackground(TCUColors);
		topPanel.setBackground(Color.BLACK);
		bottomPanel.setBackground(Color.WHITE);

		yourSum.add(yLab);
		yourSum.add(sumLab);
		
		bottomPanel.add(yourSum, BorderLayout.WEST);
		bottomPanel.add(winOrLose, BorderLayout.EAST);
		bottomPanel.add(fileLabel, BorderLayout.CENTER);
				
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
		add(midPanel, BorderLayout.CENTER);
		
		standB.setEnabled(false);
		hitB.setEnabled(false);
		
	}

	public static void main(String[] args) {
		new View();

	}
}
