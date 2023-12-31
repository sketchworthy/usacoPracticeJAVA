/* USACO 2019 January Contest, Bronze Problem 1. Shell Game
 * Really easy once I got the basic idea down! :D
 * I like my code for this. could be shorter (I could've used a loop)
 *  but i think it's clearer. and there's only 3 things to copy paste soo :)
 * basic idea: simulate if the pebble is under each of the 3 shells it
 * could possibly be under, then return max val
 */
import java.util.*;
import java.io.*;
public class Ex0503 {
	public static void main(String[] args) throws Exception{
		PrintWriter out=new PrintWriter(new File("shell.out"));
		Scanner in = new Scanner(new File("shell.in"));
		int n=in.nextInt();
		int[][] swaps = new int[n][2]; // swapped shells on kth swap are 
		// 												swaps[k][0]&[k][1]
		int[] guess = new int[n]; // guess[k] is Elsie's guess on kth swap
		for(int j=0;j<n;j++) {
			swaps[j][0]=in.nextInt()-1; // subtract 1 so its an index val
			swaps[j][1]=in.nextInt()-1;
			guess[j]=in.nextInt()-1; 
		}
		boolean[] shells = {true,false,false};
		//shells[x]=true if pebble there
		// simulate for each current location of the pebble, starting with 0

		int cnt0=0; // # of correct guesses when pebble starts index 0
		for(int j=0;j<n;j++) { // simulate each swap
			boolean swap1=shells[swaps[j][0]]; // value of first shell being swapped
			boolean swap2=shells[swaps[j][1]];
			shells[swaps[j][0]]=swap2;
			shells[swaps[j][1]]=swap1;
			if(shells[guess[j]]==true) cnt0++;// check guess
		}
		
		shells[0]=false;shells[1]=true;shells[2]=false;
		int cnt1=0; // # of correct guesses when pebble starts index 1
		for(int j=0;j<n;j++) { // simulate each swap
			boolean swap1=shells[swaps[j][0]]; // value of first shell being swapped
			boolean swap2=shells[swaps[j][1]];
			shells[swaps[j][0]]=swap2;
			shells[swaps[j][1]]=swap1;
			if(shells[guess[j]]==true) cnt1++;// check guess
		}
		
		shells[0]=false;shells[1]=false;shells[2]=true;
		int cnt2=0; // # of correct guesses when pebble starts index 2
		for(int j=0;j<n;j++) { // simulate each swap
			boolean swap1=shells[swaps[j][0]]; // value of first shell being swapped
			boolean swap2=shells[swaps[j][1]];
			shells[swaps[j][0]]=swap2;
			shells[swaps[j][1]]=swap1;
			if(shells[guess[j]]==true) cnt2++;// check guess
		}
		
//		System.out.println(Math.max(Math.max(cnt0, cnt1), cnt2));
		out.print(Math.max(Math.max(cnt0, cnt1), cnt2));
		
		in.close();
		out.close();
	}
}