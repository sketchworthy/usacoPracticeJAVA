
public class combination {

	public static void main(String[] args) {
		System.out.println(findOddEvenMax(1836311903));
	}

    public static String findOddEvenMax(int fibNumber) {
        // find which nth fibonacci number fibNumber is
        if(fibNumber==1) return "1 0 1";
        else if(fibNumber==2) return "2 0 1";
        int fib1=1;
        int fib2=1;
        int fib3=2;
        long count=0;
        while (fib3!=fibNumber&&count<=43){
            fib1=fib2;
            fib2=fib3;
            fib3=fib1+fib2;
            count++;
        }
        count+=3; // fibNumber is the count'th fibonacci #
        System.out.println(count);
        // generate diagonal #s of counth diagonal of pascal's triangle
        int len = (int)((count+1)/2); // # of diagonal #s
        long[] A = new long[len]; // array to store diagonal #s 
        A[0]=1;
        for(int k=1;k<len;k++){ // k is kth # in level of pascal's triangle
            // calculate A[k], or (count-1-k) choose (k)
        	A[k]=choose(count-1-k,k);
//            int N = (int)(count-1-k);
//            System.out.println(k+" "+N);
//            if(k<=N-k){
//                // numerator
//                long totalN = 1;
//                for(int i=N-k+1;i<=N;i++){
//                    totalN=totalN*i;
//                }
//                // denominator
//                long totalD = 1;
//                if(k>1){
//                    for(int i=2;i<=k;i++){
//                        totalD=totalD*i;
//                    }
//                }
//                else totalD=1;
//                A[k]=(int)(totalN/totalD);
//                System.out.println(totalN+"//"+totalD+"="+A[k]+"   1"); // test
//            }
//            else{
//                // numerator
//                long totalN = 1;
//                for(int i=k+1;i<=N;i++){
//                    totalN=totalN*i;
//                }
//                // denominator
//                long totalD = 1;
//                for(int i=2;i<=N-k;i++){
//                    totalD=totalD*i;
//                }
//                A[k]=(int)(totalN/totalD);
//                System.out.println(totalN+"//"+totalD+"="+A[k]+"   2"); // test
//            }
        }
        // A[] is now a list of all #s in the diagonal
        int evenC = 0; // even count
        int oddC = 0; // odd count
        long maxN = 1; // max num
        for(int j=0;j<len;j++){
            if(A[j]%2==1) oddC++;
            else if(A[j]%2==0) evenC++;
            if(A[j]>maxN) maxN=A[j];
        }
        // // test
        // String test = "";
        // for (int i=0;i<A.length;i++){
        //     test+=A[i]+" ";
        // }
        // return test;
        return oddC+" "+evenC+" "+maxN;
    }
    
    public static long choose(long N,int k) { // N choose k
        // (N choose i) = (N choose i-1) * (N-i+1)/(i)
     	long total = 1; // N choose 0 is 1
     	for(int i=1;i<=k;i++) {
     		total = total*(N-i+1)/(i);
     	}
     	return total;	
     }
	
}