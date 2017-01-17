import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Othello {

	public static void main(String[] args) throws Exception {
		
		Board startgame = new Board("siddhant",'S',"comp",'C');
		/*startgame.possibleMove('S', 3, 3);
		startgame.showBoard();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter the location of element to make moves?" + "\n Symbol x y");
		String[] ch = br.readLine().split(" ");
		int x = Integer.parseInt(ch[1]);
		int y = Integer.parseInt(ch[2]);
		if(startgame.move(c, x, y))
		{
			System.out.println("Move successful!!");
			startgame.showBoard();
		}
		else
		{
			System.out.println("invalid move!" + "\n try again");
		}
		*/
		startgame.gameplay();
		
	}

}
