
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class HalmaViewMenu 
{
	private JMenuBar menuBar;
	private JMenu menu;
	
	public void getNull() 
	{
	}
	
	public HalmaViewMenu(JFrame frame, HalmaSettings.menu choice, HalmaBoard board) 
	{
		menuBar = new JMenuBar();
		
		switch(choice) 
		{
		case defaultMenu:
			menu = new JMenu("Menu");

			JMenuItem close = new JMenuItem("Keluar", KeyEvent.VK_T);
			ButtonGroup group = new ButtonGroup();
			JRadioButtonMenuItem manVsMan = new JRadioButtonMenuItem("Manusia vs Manusia", new ImageIcon("img/icon.png"));
			
			manVsMan.setSelected(true);
			
			group.add(manVsMan);

			menu.add(manVsMan);

			menu.add(close);
			
			menuBar.add(menu);
			
			menu = new JMenu("About");
			JMenuItem about = new JMenuItem("About");
			menu.add(about);
			
			menuBar.add(menu);
			
			about.setActionCommand("about");
			about.addActionListener(HalmaController.getInstance(board, this));
			
			close.setActionCommand("close");
			close.addActionListener(HalmaController.getInstance(board, this));

			break;
		}
		
		frame.setJMenuBar(menuBar);
	}
	

}