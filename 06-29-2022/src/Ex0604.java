import java.util.Scanner;
public class Ex0604 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int l = in.nextInt();
		int k = in.nextInt();
		in.next();
		char[][] strs = new char[n][l];
		int[] nums = new int[n];
		for(int i=0;i<n;i++) {
			strs[i]=in.next().toCharArray();
//			System.out.println("HHH");
			int num = 0;
			System.out.println(num);
			for(int j=0;j<l;j++) {
				if(strs[i][j]=='O') {
					System.out.println(Math.pow(3, l-j-1)); // note: Integer.parseInt("0122",3) converts from b3 to b10
					num+=(int)Math.pow(3, l-j-1); // similarly Integer.toString(x,3) converts x_10 to x_3
				}
				else if(strs[i][j]=='W') num+=(int)Math.pow(3, l-j);
			}
			nums[i]=num;
		}
//		System.out.println("uh");
		int current = 0;
		while(k>0) {
			boolean same = false;
			for(int i=0;i<n;i++) {
				// check if current's the same as any nums
				if(current==nums[i]) {
					same=true;
					break;
				}
			}
			if(same=false) k--;
			current++;
			// if it is, increase current by 1
			// if its not, decrease k by 1 and increase current by 1
		}
		System.out.println(current);
		// convert current to base 3 and then into COW str format
		// also make sure it has l chars
		String c = "";
		while(current>0) {
			c+=current%3;
			current/=3;
		}
		// c is now current in base 3
		String ccow = "";
		for(int i=0;i<c.length();i++) {
			if(c.charAt(i)=='C') ccow+='0';
			else if(c.charAt(i)=='O') ccow+='1';
			else ccow+='2';
		}
		for(int i=0;i<l-c.length();i++) {
			ccow="C"+ccow;
		}
		System.out.println(ccow);
		in.close();
	}
}
