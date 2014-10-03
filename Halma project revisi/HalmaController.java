import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon; 
/*
 Controller.
*/
public class HalmaController implements ActionListener
 {
	static private HalmaController halmaController;

	Player player[] = {};
	Player currentPlayer = new Player();
	Player nextPlayer = new Player();

	private HalmaMove move;
	private HalmaBoard board;
	private HalmaView view;

	/**
	 * This method implements the singleton pattern.
	 * @return instance of HalmaController
	 */
	public static HalmaController getInstance(HalmaBoard board, HalmaView view) 
	{
		if (halmaController == null)
		halmaController = new HalmaController(board, view);
		return halmaController;
	}

	/**
	 This method implements the singleton pattern.
	 * @return instance of HalmaController
	 */
	public static HalmaController getInstance(HalmaBoard board, HalmaViewMenu menu) 
	{
		if (halmaController == null)
		halmaController = new HalmaController(board, menu);
		return halmaController;
	}

	
	public HalmaController(HalmaBoard board, HalmaView view) 
	{
		this.view = view;
		this.board = board;
	}

	public HalmaController(HalmaBoard board, HalmaViewMenu menu)
	{
		this.board = board;
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("close"))
		{
			view.displayClosingOption();
		} 
		else if (e.getActionCommand().equals("about"))
		{
			view.displayAbout();
		} 
	

		else 
		{
			HalmaButton src = (HalmaButton) e.getSource();

			if (move instanceof HalmaMove) {
				// set to-coordinates
				move.setToXPosition(src.getField().getXPosition());
				move.setToYPosition(src.getField().getYPosition());

				// if valid execute move
				if (board.isMoveValid(move, currentPlayer))
				{
					board.move(currentPlayer, move);

					if (board.isGameOver()) 
					{
						view.rewardWinner(currentPlayer);
					}
					currentPlayer = getNextPlayer(player);
					view.setMoveStatus(0);
				}
				else 
				{
					view.setMoveStatus(-1);
				}
				// reset
				move = null;
			} 
			else 
			{
				// set from-coordinates
				move = new HalmaMove();
				move.setFromXPosition(src.getField().getXPosition());
				move.setFromYPosition(src.getField().getYPosition());
				view.setMoveStatus(1);
			}
		}
	}

	protected Player getNextPlayer(Player players[]) 
	{
		for (int i = 0; i < players.length; i++) {
			if (players[i] == currentPlayer) {
				if (i + 1 == players.length) 
				{
					nextPlayer = players[0];
				} 
				else 
				{
					nextPlayer = players[i + 1];
				}
			}
		}
		return nextPlayer;
	}


	 //* Initializes players 

	protected void setupPlayers() 
	{
		int playerCount = 2;
		player = new Player[playerCount];
		for (int i = 0 ; i < playerCount; i++) 
		{
			player[i] = new Player(i);
			player[i].setName("pemain " + i+1);
		}
	currentPlayer = player[1];
	}
}