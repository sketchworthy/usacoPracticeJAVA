import java.util.*;
public class pShoot {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); // # of ints
		
		int[] A = new int[N+1]; // initial lineup
		for(int j=1;j<=N;j++) {
			A[j]=in.nextInt();
		}
		int[] B = new int[N+1]; // final lineup
		for(int j=1;j<=N;j++) {
			B[j]=in.nextInt();  // B[j]=x
		}
		
		int[] BI = new int[N+1]; // new name (ID)
		for(int j=0;j<=N;j++) {
			BI[B[j]]=j; // BI[x]=j
		}
		
		System.out.println(Arrays.toString(B));
		System.out.println(Arrays.toString(BI));
		
		int[] AI = new int[N+1]; // initial lineup using new IDs
		for(int j=1;j<=N;j++) {
			AI[j]=BI[A[j]];
		}
		
		System.out.println(Arrays.toString(AI));
		
		int cnt=0; // min # of modifications needed
		// work on the array AI:
		// let M_j be max value of AI[1...j]
		// for each j, if M_{j-1} > AI[j], cnt++;
		//             M_j = max(M_{j-1}, AI[j])
		
		int M=0;
		for(int j=1;j<=N;j++) {
			if(M>AI[j]) {
				cnt++;
			}
			M = Math.max(M,AI[j]);
		}
		
		System.out.println(cnt);
		
		in.close();
	}

}
