/*
 * done a bit confusedly/trial-and-error-ly, recursion is hard
 * basic idea is recursion. literally just recursion
 */
import java.util.*;
public class Ex0502 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		
		System.out.println(S(n).charAt(k-1));
		
		in.close();
	}
	public static String S(int n) {
		if(n==1) return "0";
		return S(n-1)+"1"+reverseInvert(S(n-1));
	}
	public static String reverseInvert(String s) {
//		System.out.println(s);
		char[] str = s.toCharArray();
		char[] str2 = s.toCharArray();
		for(int i=0;i<s.length();i++) {
			if(str[i]=='0') str[i]='1';
			else str[i]='0';
			str2[s.length()-i-1]=str[i];
		}
		String ans = "";
		for(int i=0;i<s.length();i++) {
			ans+=str2[i];
		}
		return ans;
	}
}
