/*
 * daniel ding's code for the quiz
 * basically same idea i think(?)
 */
import java.util.*;
import java.io.*;

public class cowshopping {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int f = in.nextInt();
		int b = in.nextInt();
		int[][] friends = new int[f][2];
		for(int i = 0; i < f; i++) {
			friends[i][0] = in.nextInt();
			friends[i][1] = in.nextInt();
		}
		
		in.close();
		
		boolean[] visited = new boolean[n+1];
		
		for(int i = 0; i < f; i++) {
			for(int j = friends[i][0]; j < friends[i][0] + friends[i][1]; j++) {
				if(j >= n+1)	break;
				visited[j] = true;
			}
		}
		
		int constant = 0;
		for(int i = 0; i < n+1; i++) {
			if(visited[i])	constant++;
		}
		
		int answer = constant;
		int cow = 1;
		for(int i = 0; i < f; i++) {
			boolean[] copy = visited;
			int counter = 0;
			for(int j = friends[i][0]+friends[i][1]; j < friends[i][0] + friends[i][1]+b; j++) {
				if(j >= n+1)	break;
				if(copy[j] == false) counter++;
			}
			
			if(Math.max(constant+counter, answer) > answer) {
				answer = constant+counter;
				cow = i;
			}
		}
		
		
		System.out.println(cow+1);
		
	}
}
