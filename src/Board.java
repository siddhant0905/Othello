import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.Scanner;

public class Board {
	int Boardsize = 8;
	char[][] board;
	private char player1Symbol;
	private char player2Symbol;
	public final static int PLAYER_1_WINS = 1;
	public final static int PLAYER_2_WINS = 2;
	private final static int xDir[] = {0,0,1,1,1,-1,-1,-1};
	private final static int yDir[] = {1,-1,0,1,-1,0,-1,1};
	
	public Board()
	{
		board = new char[Boardsize][Boardsize];
		for(int i=0;i < Boardsize;i++)
		{
			for(int j=0;j<Boardsize;j++)
				board[i][j]=' ';
		}
	}

	public Board(String namep1,char Symbol1,String namep2,char Symbol2) {
		Player player1 = new Player(namep1,Symbol1);
		Player player2 = new Player(namep2, Symbol2);
		player1Symbol = Symbol1;
		player2Symbol = Symbol2;
		
		board = new char[Boardsize][Boardsize];
		for(int i=0;i < Boardsize;i++)
		{
			for(int j=0;j < Boardsize;j++)
			board[i][j] = ' ';
		}
		
		board[3][3] = player1Symbol;
		board[3][4] = player2Symbol;
		board[4][3] = player2Symbol;
		board[4][4] = player1Symbol;
		
		BoardStatus();
		}
	
	
	private void BoardStatus() {
		System.out.println(" |0  1  2  3  4  5  6  7|");
		for(int i = 0;i < Boardsize; i++)
		{
			System.out.print(i);
			for(int j = 0;j < Boardsize;j++ )
			{
				System.out.print("|"+board[i][j]+"|" );
			}
			System.out.println();
			System.out.println("------------------------");
		}
		
	}
	
	public void showBoard() {
		BoardStatus();
	}
	
	public void makemove(int x,int y,char symbol)
	{
	
	}

	public boolean move(char symbol,int x,int y)
	{
		
		boolean MovePossible = false;
		for(int i = 0;i < xDir.length;i++)
		{
			int count=0;
			int xStep = xDir[i];
			int yStep = yDir[i];
			int currentX = x + xStep;
			int currentY = y + yStep;
			while(currentX > 0 && currentX < Boardsize && currentY > 0 && currentY < Boardsize)
			{
				if(board[currentX][currentY]==' ')
					break;
				else if(board[currentX][currentY]!=symbol) 
				{
				count++;
				currentX += xStep;
				currentY += yStep;
				}
				else{
					if(count > 0){
						MovePossible = true;
						int j = currentX - xStep;
						int k = currentY - yStep;
						
						while(j != x || k != y)
						{
							board[j][k] = symbol;
							j -= xStep;
							k -= yStep;
						}
						board[j][k] = symbol;
					}
					break;
				}
				
			}
			
			
		}
			return MovePossible;
	}


public void gameplay() throws Exception
{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	boolean toggle = true;
	while(!isBoardFull())
	{
		char c;
		if(toggle == true)
		{
			System.out.println("player  " + player1Symbol + "  turn");
			c = player1Symbol;
			
		}
		else
		{
			System.out.println("player  " + player2Symbol + "  turn");
			c = player2Symbol;
		}
		
		System.out.println("enter the location of element " + c + " to make move?" + "\n x y");
		String[] ch = br.readLine().split(" ");
		int x = Integer.parseInt(ch[0]);
		int y = Integer.parseInt(ch[1]);
		
		if(board[x][y]!=' ')
		{
			System.out.println("oops! space is already filled! enter a valid space");
		}
		else{
			if(move(c, x, y))
				{
					System.out.println("Move successful!!");
					showBoard();
					toggle = !toggle;
					if(isBoardFull())
						declarewinner();
				}
			else
				{
					System.out.println("invalid move!" + "\n try again");
				}
			}
	}
		
}
	
private void declarewinner() {
	int p_one_symbols = 0,p_two_symbols = 0;
	for(int i=0;i < Boardsize;i++)
	{
		for(int j=0;j < Boardsize;j++){
			if(board[i][j] == player1Symbol)
				p_one_symbols++;
			else if(board[i][j]==player2Symbol)
				p_two_symbols++;
		}
		
	}
	if(p_one_symbols > p_two_symbols){
		System.out.println(PLAYER_1_WINS);
		System.out.println("player 1 symbols::" + p_one_symbols);
		System.out.println("player 2 symbols::" + p_two_symbols);}
	else if(p_one_symbols < p_two_symbols){
		System.out.println(PLAYER_2_WINS);
		System.out.println("player 1 symbols::" + p_one_symbols);
		System.out.println("player 2 symbols::" + p_two_symbols);}
	
	else 
		System.out.println("Its a tie!!");
}


public boolean isBoardFull(){
	for(int i=0; i<Boardsize;i++ )
	{ for(int j=0;j<Boardsize;j++)
		{
			if(board[i][j]==' ')
				return false;
		}
	}
	return true;
}
}