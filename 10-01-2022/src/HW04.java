/*
 * this code WORKS ugh except for the fact that 1. doesn't account for
 * mirrored pairs (asymmetric operations like - and /) and also 
 * it doesn't account for inbetween double operations
 * TODO: teacher's code had same idea (bottom up approach) but accounted
 *   for intermediate double results by adding a rounding system where a
 *   tiny # was used to determine if a double was actually an int or not
 */
import java.util.*;
import java.io.*;
public class HW04 {
    static int [][] p = {{0,1,2,3},{0,2,1,3},{0,3,1,2},{1,2,0,3},{1,3,0,2},{2,3,0,1}};
    static Set<Integer> possibleResults(int[] arr){
        Set <Integer> m = new HashSet();
        for (int i=0; i<6; i++){
            int[] v = {arr[p[i][0]], arr[p[i][1]], arr[p[i][2]], arr[p[i][3]]};
            double [] pr1 = pairResults(v[0], v[1]);
            for (int j=0; j<6; j++){
                double [] pr2 = pairResults(pr1[j],v[2]);
                for (int k=0;k<6;k++){
                    double [] pr3 = pairResults(pr2[k], v[3]);
                    for (double r:pr3){
                    	if((int)Math.floor(r)==r) // if r is an integer result
                    		m.add((int)r);
                    }
                }
            }
            
        }
        for (int i=0; i<6; i++){
            int[] v = {arr[p[i][0]], arr[p[i][1]], arr[p[i][2]], arr[p[i][3]]};
            double [] pr1 = pairResults(v[0], v[1]);
            for (int j=0; j<6; j++){
                double [] pr2 = pairResults(v[2],pr1[j]);
                for (int k=0;k<6;k++){
                    double [] pr3 = pairResults(v[3],pr2[k]);
                    for (double r:pr3){
                    	if((int)Math.floor(r)==r) // if r is an integer result
                    		m.add((int)r);
                    }
                }
            }
            
        }
        for (int i=0; i<3; i++){
            int []v = {arr[p[i][0]], arr[p[i][1]], arr[p[i][2]], arr[p[i][3]]};

            double [] pr1 = pairResults(v[0],v[1]);
            double [] pr2 = pairResults(v[2],v[3]);
            for (double x:pr1){
                for (double y:pr2){
                    double [] pr3 = pairResults(x,y);
                    for (double r:pr3){
                    	if((int)Math.floor(r)==r) // if r is an integer result
                    		m.add((int)r);
                    }
                }
            }
        }
        return m;

    }

    static double[] pairResults(double a, double b){
        double [] r = new double[6];
        r[0] = a+b;
        r[1] = a*b;
        r[2] = a-b;
        if (b==0)
            r[3] = r[2];
        else
            r[3] = ((double)a)/b;
        r[4] = b-a;
        if (a==0)
            r[5] = r[2];
        else
            r[5] = ((double) b)/a;
        return r;
    }
    public static void main(String[] args) throws Exception {
    	Scanner in2 = new Scanner(System.in);
    	String fileName = in2.next();
    	in2.close();
    	Scanner in = new Scanner(new File(fileName));
    	int q=in.nextInt();
    	int ans = 0; // # of targets that can be obtained
    	for(int j=0;j<q;j++) {
    		int[] arr=new int[4];
    		arr[0]=in.nextInt();
    		arr[1]=in.nextInt();
    		arr[2]=in.nextInt();
    		arr[3]=in.nextInt();
    		int target=in.nextInt();
            Set r = possibleResults(arr);
//            System.out.println(r.toString());
            if(r.contains(target)) {
            	ans++;
//            	System.out.println("+1 to "+ans+" for case "+j);
            }
    	}
    	System.out.println(ans);
//      int[] arr = {1,1,1,1};
//      Set r = possibleResults(arr);
//      System.out.println(r.toString());
        in.close();
    }
}