package class01x15x2023;
/* USACO Bronze 2022 Feb Problem 3 Blocks. COMPLETE
 * Bessie has 4 cubes w a letter on each of the 6 sides. Given these blocks,
 * see if she can spell each of N words.
 * 
 * idea:
 * create a set of all possible 1-4 letter words that Bessie CAN spell.
 * then for each word she wants to spell, see if they are in that set
 * 
 * difficulty: pretty ez once i started thinking in terms of data structures, decent
 * to implement. 20/20 test cases
 */
import java.util.*;

public class HW02 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of words
		char[][] blocks = new char[4][6]; // 4 rows 4 blocks|6 cols 6 chars
		for(int j=0;j<4;j++) {
			blocks[j]=in.next().toCharArray();
		}
		TreeSet<String> ts = new TreeSet<>();
		// put in all 1-char words
		for(int j=0;j<4;j++) {
			for(int k=0;k<6;k++) {
				ts.add(""+blocks[j][k]);
			}
		}
		// put in all 2-char words
		for(int j1=0;j1<4;j1++) {
			for(int j2=0;j2<4;j2++) {
				if(j2==j1)continue;
				for(int k1=0;k1<6;k1++) {
					for(int k2=0;k2<6;k2++) {
						ts.add(blocks[j1][k1]+""+blocks[j2][k2]);
					}
				}
			}
		}
		// put in all 3-char words
		for(int j1=0;j1<4;j1++) {
			for(int j2=0;j2<4;j2++) {
				if(j2==j1)continue;
				for(int j3=0;j3<4;j3++) {
					if(j3==j1 || j3==j2)continue;
					for(int k1=0;k1<6;k1++) {
						for(int k2=0;k2<6;k2++) {
							for(int k3=0;k3<6;k3++) {
								ts.add(blocks[j1][k1]+""+blocks[j2][k2]+""+blocks[j3][k3]);
							}
						}
					}
				}
			}
		}
		// put in all 4-char words
		for(int j1=0;j1<4;j1++) {
			for(int j2=0;j2<4;j2++) {
				if(j2==j1)continue;
				for(int j3=0;j3<4;j3++) {
					if(j3==j1 || j3==j2)continue;
					for(int j4=0;j4<4;j4++) {
						if(j4==j1||j4==j2||j4==j3)continue;
						for(int k1=0;k1<6;k1++) {
							for(int k2=0;k2<6;k2++) {
								for(int k3=0;k3<6;k3++) {
									for(int k4=0;k4<6;k4++) {
										ts.add(blocks[j1][k1]+""+blocks[j2][k2]+""+blocks[j3][k3]+""+blocks[j4][k4]);
									}
								}
							}
						}
				}
				}
			}
		}
		// for each word, check if its inside the set ts
		for(int j=0;j<n;j++) {
			if(ts.contains(in.next())) {
				System.out.println("YES");
			}
			else System.out.println("NO");
		}
		
		
		in.close();
	}
}
