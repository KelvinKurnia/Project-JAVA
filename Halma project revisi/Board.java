public interface Board 
{
	public void createInitialField();
	public void move(Player currentPlayer, Move move);
	public boolean isMoveValid(Move move, Player player);
}