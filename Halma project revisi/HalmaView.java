import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
  view class to visualize the GUI
 */
public class HalmaView implements Observer 
{
	private HalmaBoard board;
	public JFrame frame = new JFrame("Halma");
	private JLabel labelInfo;
	private HalmaButton fieldGUI[][];
	private int moveStatus = 0;
	
	private ImageIcon imgLogo = new ImageIcon(getClass().getResource("background.png"));
	private ImageIcon imgEmpty = new ImageIcon(getClass().getResource("grey.png"));
	private ImageIcon imgGreen = new ImageIcon(getClass().getResource("green.png"));
	private ImageIcon imgRed = new ImageIcon(getClass().getResource("red.png"));
	
	private Container rootPane = null;
	
	public HalmaView(HalmaBoard board) {
		labelInfo = new JLabel();
		frame = new JFrame("Halma");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		rootPane = frame.getContentPane();
		rootPane.setLayout(new BorderLayout());
		JLabel header = new JLabel();
		header.setIcon(imgLogo);
		rootPane.add(BorderLayout.NORTH, header);
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(HalmaSettings.BGCOLOR);
		
		GridLayout gridLayout = new GridLayout(board.getHeight(), board.getWidth());
		mainPanel.setLayout(gridLayout);
		fieldGUI = new HalmaButton[board.getHeight()][board.getWidth()];
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				fieldGUI[i][j] = new HalmaButton(board.field[i][j]);
				switch (board.field[i][j].getFieldType()) {

				case HalmaSettings.NO_FIELD:
					fieldGUI[i][j].setBorderPainted(false);
					break;

				case HalmaSettings.EMPTY_FIELD:
					fieldGUI[i][j].setBorderPainted(false);
					fieldGUI[i][j].setIcon(imgEmpty);
					fieldGUI[i][j].setOpaque(false);
					break;

				default:
					// set colors
					fieldGUI[i][j].setBorderPainted(false);
					fieldGUI[i][j].setOpaque(false);

					switch (board.field[i][j].getFieldType())
					{
					case 1:
						fieldGUI[i][j].setIcon(imgGreen);
						break;
					case 2:
						fieldGUI[i][j].setIcon(imgRed);
						break;
					}
				}

				if (board.field[i][j].getFieldType() > -1)
				{
					fieldGUI[i][j].addActionListener(HalmaController.getInstance(board, this));
				}

				mainPanel.add(fieldGUI[i][j]);
			}
		}
		HalmaViewMenu halmaMenu;
		halmaMenu = new HalmaViewMenu(frame, HalmaSettings.menu.defaultMenu, board);
		halmaMenu.getNull();
		
		rootPane.add(BorderLayout.CENTER, mainPanel);

		JLabel labelInfo = new JLabel();
		labelInfo.setText("");
		rootPane.add(BorderLayout.SOUTH, labelInfo);

		// frame.setResizable(false);
		frame.setBounds(0, 0, 600, 850);
		frame.setVisible(true);
	}

	public void displayGetPlayerName() {
		String s = null;

		if ((s != null) && (s.length() > 0)) 
		{
			HalmaController.getInstance(board, this).player[1].setName(s);
		}
	
	}

	public void displayAbout() {
		JOptionPane.showMessageDialog(frame,"Halma diciptakan oleh \n Albert Rahardjo, Kelvin Kurnia ", "Halma", 1);
	}
	
	public void displayClosingOption() {
		final JOptionPane optionPane = new JOptionPane("Benar-benar berhenti permainan?", JOptionPane.QUESTION_MESSAGE,	JOptionPane.YES_NO_OPTION);

		final JDialog dialog = new JDialog(frame, "Keluar permainan", true);
		dialog.setContentPane(optionPane);
		optionPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();
				if (dialog.isVisible() && (e.getSource() == optionPane)
						&& (prop.equals(JOptionPane.VALUE_PROPERTY))) {
					dialog.setVisible(false);
				}
			}
		});
		dialog.pack();
		dialog.setVisible(true);

		int value = ((Integer) optionPane.getValue()).intValue();
		if (value == JOptionPane.YES_OPTION) 
		{
			System.exit(0);
		} 
		else if (value == JOptionPane.NO_OPTION) 
		{
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		}
	}

	public void update(Observable o, Object arg) 
{ // update the board
		board = (HalmaBoard) arg;

		for (int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++) 
			{
				// now update images of buttons
				switch (board.field[i][j].getFieldType()) 
				{

				case HalmaSettings.EMPTY_FIELD:
					fieldGUI[i][j].setIcon(imgEmpty);
					break;

				default:
					switch (board.field[i][j].getFieldType())
					{
					case 1:
						fieldGUI[i][j].setIcon(imgGreen);
						break;
					case 2:
						fieldGUI[i][j].setIcon(imgRed);
						break;
					}
				}
			}
		}
	}

	public void rewardWinner(Player currentPlayer) 
	{
		JOptionPane.showMessageDialog(frame,"pemain " + currentPlayer.getName() + " menang !!! ", "Selamat!", 1);
	}
	
	public int getMoveStatus() {
		return moveStatus;
	}

	public void setMoveStatus(int moveStatus) {
		this.moveStatus = moveStatus;
	}

}