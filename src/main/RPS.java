package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RPS {
	private long ai_wins=0;
	private long player_wins=0;
	private long draws=0;
	//The ai matches the last 5 moves played attempting to find patterns and predict the next move
	private String recent_moves="_____";
	private Map<String,Long> past_moves = new HashMap<>();

	public RPS() {
		this.fillMoveArrays();
	}

	private void fillMoveArrays()
	{
		for (int m1=0;m1<3;m1++)
		{
			char c1 = tertToChar(m1);
			this.past_moves.put(""+c1,(long) 0);
			for (int m2=0;m2<3;m2++)
			{
				char c2 = tertToChar(m2);
				this.past_moves.put(""+c1+c2,(long) 0);
				for (int m3=0;m3<3;m3++)
				{
					char c3 = tertToChar(m3);
					this.past_moves.put(""+c1+c2+c3,(long) 0);
					for (int m4=0;m4<3;m4++)
					{
						char c4 = tertToChar(m4);
						this.past_moves.put(""+c1+c2+c3+c4,(long) 0);
						for (int m5=0;m5<3;m5++)
						{
							char c5 = tertToChar(m5);
							this.past_moves.put(""+c1+c2+c3+c4+c5,(long) 0);

						}
					}
				}
			}
		}
	}

	private static char tertToChar(int x)
	{
		switch (x)
		{
		case 0:
			return 'R';
		case 1:
			return 'P';
		case 2:
			return 'S';
		default:
			return '?';
		}
	}

	private void play(char player_move)
	{
		char p_move = Character.toUpperCase(player_move);
		if (p_move!='R' && p_move!='P' && p_move!='S')
		{
			System.out.print(" ");
			return;
		}
		char ai_move = this.getMove();
		System.out.print(ai_move);
		this.evaluate(ai_move,p_move);
		this.recent_moves = this.recent_moves.substring(1,5) + p_move;
		int start_index=0;
		if (this.recent_moves.charAt(0)=='_') start_index = this.recent_moves.lastIndexOf("_")+1;
		for (int i=start_index;i<5;i++)
		{
			this.past_moves.replace(this.recent_moves.substring(i, 5),this.past_moves.get(this.recent_moves.substring(i, 5))+1);
			//totals[4-i]++;
		}
	}

	public void play(String moves)
	{
		for(int i=0;i<moves.length();i++)
		{
			play(moves.charAt(i));
		}
		System.out.println("");
		this.displayScore();
	}

	private void displayScore() {
		// TODO Auto-generated method stub
		System.out.println("Player wins: "+this.player_wins+"\t\t\tAI wins: "+this.ai_wins);
	}

	private char getMove()
	{
		Random rand = new Random();
		double[] weights = new double[] {1.0/3.0,1.0/3.0,1.0/3.0};
		for (int i=1;i<=5;i++)
		{
			weights=consider(i,weights);
		}
		double pick = -1;
		while (pick<0)
		{
			pick = rand.nextDouble();
			if (pick<weights[0]) return 'R';
			if (pick<weights[0]+weights[1]) return 'P';
			if (pick<weights[0]+weights[1]+weights[2]) return 'S';
			pick=-1;
		}


		return 'R';
	}


	private double[] consider(int i, double[] weights) {
		// TODO Auto-generated method stub
		String previous = "";
		if (i>1)
		{
			previous = this.recent_moves.substring(6-i,5);
			if (previous.charAt(0)=='_') return weights;
		}

		long rs = this.past_moves.get((previous+"R"));
		long ps = this.past_moves.get((previous+"P"));
		long ss = this.past_moves.get((previous+"S"));
		long total = rs+ps+ss;

		weights= new double[] {
								(10.0*weights[0]+ss)/(total+10.0),
								(10.0*weights[1]+rs)/(total+10.0),
								(10.0*weights[2]+ps)/(total+10.0)
								};
		//if (i==5) System.out.println("["+weights[0]+','+weights[1]+','+weights[2]+']');
		return weights;
	}

	private void evaluate(char ai_move,char player_move)
	{
		if (ai_move=='R')
		{
			if (player_move=='S')
			{
				this.ai_win();
			}
			if (player_move=='P')
			{
				this.player_win();
			}
			if (player_move=='R')
			{
				this.draw();
			}
		}
		if (ai_move=='P')
		{
			if (player_move=='R')
			{
				this.ai_win();
			}
			if (player_move=='S')
			{
				this.player_win();
			}
			if (player_move=='P')
			{
				this.draw();
			}
		}
		if (ai_move=='S')
		{
			if (player_move=='P')
			{
				this.ai_win();
			}
			if (player_move=='R')
			{
				this.player_win();
			}
			if (player_move=='S')
			{
				this.draw();
			}
		}
	}

	private void player_win() {
		// TODO Auto-generated method stub
		this.player_wins++;
	}

	private void ai_win() {
		// TODO Auto-generated method stub
		this.ai_wins++;
	}

	private void draw()
	{
		this.draws++;
	}


	public char GUIplay(char p_move)
	{
		char ai_move = this.getMove();
		this.evaluate(ai_move,p_move);
		this.recent_moves = this.recent_moves.substring(1,5) + p_move;
		int start_index=0;
		if (this.recent_moves.charAt(0)=='_') start_index = this.recent_moves.lastIndexOf("_")+1;
		for (int i=start_index;i<5;i++)
		{
			this.past_moves.replace(this.recent_moves.substring(i, 5),this.past_moves.get(this.recent_moves.substring(i, 5))+1);
			//totals[4-i]++;
		}
		return ai_move;
	}

	public long getAi_wins() {
		return ai_wins;
	}

	public long getPlayer_wins() {
		return player_wins;
	}

	public long getDraws() {
		return draws;
	}
	
	
	public void test(RPS p2)
	{
		char[] p1moves = new char[100];
		char[] p2moves = new char[100];
		for (int i=0;i<100;i++)
		{
			char p1move=this.getMove();
			char p2move=p2.getMove();
			p1moves[i]=p1move;
			p2moves[i]=p2move;
			this.recent_moves=this.recent_moves.substring(1,5)+p1move;
			p2.recent_moves=p2.recent_moves.substring(1, 5)+p2move;
			int start_index=0;
			if (this.recent_moves.charAt(0)=='_') start_index = this.recent_moves.lastIndexOf("_")+1;
			for (int j=start_index;j<5;j++)
			{
				this.past_moves.replace(this.recent_moves.substring(j, 5),this.past_moves.get(this.recent_moves.substring(j, 5))+1);
				p2.past_moves.replace(p2.recent_moves.substring(j, 5),p2.past_moves.get(p2.recent_moves.substring(j, 5))+1);
				//totals[4-i]++;
			}
			this.evaluate(p1move, p2move);
		}
		for (int i=0;i<100;i++)
		{
			System.out.print(p1moves[i]);
		}
		System.out.println();
		for (int i=0;i<100;i++)
		{
			System.out.print(p2moves[i]);
		}
		System.out.println();
		this.displayScore();
	}
	
	public void submit()
	{
		try (Connection conn=ConnectionFactory.getInstance().getConnection())
		{
			String query = "INSERT INTO RPS_stats (games_played,player_wins,ai_wins,draws) VALUES"+
					"("+(player_wins+ai_wins+draws)+","+
					player_wins+","+
					ai_wins+","+
					draws+");";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}


}
