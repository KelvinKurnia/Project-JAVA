import java.lang.*;

public class Halma 
{
	public static void main(String[] args) 
	{
		HalmaBoard board = new HalmaBoard(HalmaSettings.BOARD_STARLIKE);
		board.createInitialField();	
		HalmaView view = new HalmaView(board);
		board.addObserver(view);
		HalmaController controller = HalmaController.getInstance(board, view);
		controller.setupPlayers();
	}
}