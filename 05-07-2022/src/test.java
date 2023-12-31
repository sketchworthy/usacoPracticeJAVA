import java.util.*;
public class test {
	public static void main(String[] args) {
		System.out.println("floor "+Math.floor(-18.5));
		System.out.println("round "+Math.round(-18.5));
		double x = -0.185;
		System.out.println(roundTwoDec(x));
		System.out.println(roundTwoDec(-0.355));
		System.out.println(roundTwoDec(0.365));
		System.out.println(roundTwoDec(153.264));
//		x*=100;
//		System.out.println(Math.floor(x));
//		x = Math.floor(100.0*x+0.5)/100.0;
//		System.out.println(Math.floor(x*100+0.5*x*Math.abs(x))/100.0);
//		double imagAns = Math.round(100*(-0.32+2*0.15*0.45))/100.0;
//		double imagAns = Math.round(100.0*(-0.185))/100.0;
//		System.out.println(Math.round(-0.185,2));

//		double realZ = -0.1;
//		double imagZ = 0.75;
//		double realC = -0.1;
//		double imagC = 0.75;
//		System.out.println(Math.round(100*(realZ*realZ-imagZ*imagZ+realC))/100.0);
//		double realAns = Math.round(100*(realZ*realZ-imagZ*imagZ+realC))/100.0;
//		System.out.println(imagAns);
		
		
//		Scanner in = new Scanner(System.in);
//		int k = in.nextInt(); 
//
////		int placeVal;
//		long max=1;
//		for(int numDig=1;numDig<=18;numDig++) {
//			long tenNDpow=1;
//			for(int j=0;j<numDig;j++) tenNDpow*=10; // 10^numDig
//			max+=9*tenNDpow/10*numDig;
//			System.out.println("Max: "+max);
//			if(k<max) {
//				System.out.println(numDig-1);
//				break;
//			}
//		}
//		
//		
//		in.close();
	}
	
	public static double roundTwoDec(double x) {
		x*=100;
		if(x<0) { // x= -18.5, should round to -19
			x=Math.abs(x); // x = 18.5
			if(x-Math.floor(x)>=0.5) { // 18.5-18=0.5, so rounds up
				x=-(Math.floor(x)+1);
			}
			else{ // if rounding down
				x=-Math.floor(x);
			}
		}
		else { // if x is positive
			if(x-Math.floor(x)>=0.5) {
				x=Math.floor(x)+1;
			}
			else {
				x=Math.floor(x);
			}
		}
		x=x/100.0;
		return x;
	}

}
