/* USACO 2023 Dec Silver _status{COMPLETE or not}_
 * Given T targets at diff positions, find max targets
 * Bess can hit given a set of instructions. You can change
 * up to 1 command in the str of instructions.
 * 
 * idea: loop thru every possible one of the commands
 * bess can change, then simulate. to simulate more effectively,
 * keep prefix and suffix arrs on how many targets you'd hit
 * at each point if you were transformed right 2, left 2,
 * right 1, left 1, or stayed the same
 * 
 * at every point in your commands, 
 * if you have a L:
 * you can change to a R, shifting all future positions you go to by +2
 * you can change to a F, shifting all future positions you go to by +1 
 * if you have a R:
 * you can change to a L, shifting all future positions you go to by -2
 * you can change to a F, shifting all future positions you go to by -1 
 * if you have a F:
 * you can change to a R, shifting all future positions you go to by +1
 * you can change to a L, shifting all future positions you go to by -1 
 * 
 * [for all u also affect the # of targets u hit]
 * 
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class silver3 {
	public static void main(String[] args) throws Exception {
		// take input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int t = Integer.parseInt(st.nextToken()); // # of targets
		int c = Integer.parseInt(st.nextToken()); // # of commands
		st = new StringTokenizer(in.readLine());
		HashMap<Integer,Boolean> targets = new HashMap<>(); // locs of targets, false if not hit yet, true if hit
		for(int j=0;j<t;j++)
			targets.put(Integer.parseInt(st.nextToken()),false);
		char[] commands = new char[c];
		commands = in.readLine().toCharArray();
		
		int[] pos0 = new int[c]; // describes how many targets hit after each command (0shift)
		
		int[] pos1L = new int[c]; // L not F on command 1. how many targets hit after each command
		int[] pos2L = new int[c]; // L not R on command 1. how many targets hit after each command
		int[] pos1R = new int[c]; // R not F on command 1.				// describes 1Rshift locs after each command
		int[] pos2R = new int[c]; // R not L on command 1.				// describes 2Rshift locs after each command
		pos1L[0]=0;	pos2L[0]=0;	pos1R[0]=0;	pos2R[0]=0;
		
		int currPos=0;
		int ogTsHit=0;
		if(commands[0]=='L')currPos=-1;
		else if(commands[0]=='R')currPos=1;
		else {
			if(targets.containsKey(currPos)) {
				ogTsHit=1;
				targets.put(currPos, true);
			}
		}
		pos0[0]=ogTsHit;
		
		// iterate thru all commands
		for(int j=1;j<c;j++) {
			if(commands[j]=='L') {
				currPos--;
			}
			else if(commands[j]=='R') {
				currPos++;
			}
			else {
				if(targets.containsKey(currPos) && !targets.get(currPos)) {
					ogTsHit++;
					targets.put(currPos, true);
				}
				
				if(targets.containsKey(currPos-1) && !targets.get(currPos-1)) {
					pos1L[j]=pos1L[j-1]+1;
//					targets.put(currPos-1, true);
				}
				else pos1L[j]=pos1L[j-1];
				if(targets.containsKey(currPos-2) && !targets.get(currPos-2)) {
					pos2L[j]=pos2L[j-1]+1;
//					targets.put(currPos-2, true);
				}
				else pos2L[j]=pos2L[j-1];
				if(targets.containsKey(currPos+1) && !targets.get(currPos+1)) {
					pos1R[j]=pos1R[j-1]+1;
//					targets.put(currPos+1, true);
				}
				else pos1R[j]=pos1R[j-1];
				if(targets.containsKey(currPos+2) && !targets.get(currPos+2)) {
					pos2R[j]=pos2R[j-1]+1;
//					targets.put(currPos+2, true);
				}
				else pos2R[j]=pos2R[j-1];
			}
//			pos0[j]=currPos;
			pos0[j]=ogTsHit;
			
			
		}
		
		out.println(ogTsHit);
		
		in.close();
		out.close();
	}
}
