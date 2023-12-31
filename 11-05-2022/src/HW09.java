/* Was easier than i expected, using recursion it's surprisingly slick
 * basic idea: check if the start and end chars are the same. once they're not the same, return the
 * longer string when removing the 1st char of one string, or the 1st char of the other one.
 */
import java.util.*;
import java.io.*;
public class HW09 {
	public static void main(String[] args) throws Exception {
		Scanner in2=new Scanner(System.in);
		String fileName = in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		String s1=in.next();
		String s2=in.next();
		
		String max = maxCommonSubseq(s1,s2);
//		System.out.println(max);
		System.out.println(max.length());
		
		in.close();
	}
	
	public static String maxCommonSubseq(String s1,String s2) {
		// returns the longest common subseq of s1 and s2
		if(s1.isEmpty()||s2.isEmpty()) return "";
		if(s1.equals(s2)) return s1;
		if(s1.charAt(0)==s2.charAt(0)) return s1.charAt(0)+maxCommonSubseq(s1.substring(1),s2.substring(1));
		if(s1.charAt(s1.length()-1)==s2.charAt(s2.length()-1))
			return maxCommonSubseq(s1.substring(0, s1.length()-1),s2.substring(0, s2.length()-1))+s1.charAt(s1.length()-1);
		
		// if both start and end vals are different
		// return the longer string that is returned when the 1st char of s1 is removed and s2 is untouched,
		//     or the 1st char of s2 is removed and s1 untouched
		String sA = maxCommonSubseq(s1.substring(1),s2);
		String sB = maxCommonSubseq(s1,s2.substring(1));
		if(sA.length()>sB.length())return sA;
		else return sB;
		
	}
}
