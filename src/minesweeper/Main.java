package minesweeper;

public class Main {
	public static int basic = 0;
	public static int advanced = 0;
	public static int guessed = 0;

	public static void main(String[] args){
		start();
		solve();
		
		System.out.println("Used basic techniques: "+basic+" times");
		System.out.println("Used advanced techniques: "+advanced+" times");
		System.out.println("Gussed: "+guessed+" times");
		Board.printBoard();
	}
	
	private static void start(){
		Window.setMinesweeperSizeAndPosition();
		Window.setMinesweeperToForeground();
		Bot.initiate();
		Mouse.clickFirstSquare();
		Board.fillBoard();
		Lists.fillArrayLists();
		Maps.fillSquaresWithNumbersMap();
	}

	private static void solve(){
		int roundsWithoutClicking = 0;

		while (Window.gameNotOver()){
			if (Basic.doBasicSolving()){
				roundsWithoutClicking = 0;
			}
			else {
				if (roundsWithoutClicking == 1){
					Board.updateBoardImage();
					Lists.updateNonClickedSquares();
					Lists.updateSquaresWithNumbers();
				}
				else if (roundsWithoutClicking == 2){
					if (Advanced.doAdvancedSolving()){
						roundsWithoutClicking = 0;
					}
				}
				else if (roundsWithoutClicking == 3){
					if (Mouse.clickRandomSurroundingNonClicked()){
						roundsWithoutClicking = 0;
					}
				}
				else if (roundsWithoutClicking == 4){
					if (Mouse.clickRandomNonClicked()){
						roundsWithoutClicking = 0;
					}
				}
			}
			roundsWithoutClicking++;	
		}
	}
}