/*
 * wasn't really binary, but base #s
 */
import java.util.Scanner;
public class Ex0603 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for(int i=0;i<q;i++) {
			
			int n = in.nextInt();
			int b = in.nextInt();
			
			int cnt1s = 0; // # of digits=1
			int cnt2s = 0; // # of digits>=2
			while(n>0) {
				int r = n%b;
				if(r!=0) {
					if(r==1) {
						cnt1s++;
						if (cnt1s>1) break;
					}
					else {
						cnt2s++;
						break;
					}
				}
				n/=b;
			}
			System.out.println(cnt1s==1&&cnt2s==0);
		}
		in.close();
	}
}
