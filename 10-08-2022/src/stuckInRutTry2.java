// i know how to do it now but the implementation is killing me.
/*
 * 
 */

import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class stuckInRutTry2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> Eis = new ArrayList<Integer>(); // east-facing indexes of cows
		ArrayList<Integer> Nis = new ArrayList<Integer>(); // north-facing indexes of cows
		int n = in.nextInt(); // # of cows
		int[][] C = new int[n][5];// kth cows x coord: C(k,0). y-coord:C(k,1)
		//direction: C(k,2)1 if N, 0 if E. # of cells eaten: C(k,3), 
		// -1 if infinity. C(k,4)=0 if still alive, 1 if cow's been stopped.
		for(int j=0;j<n;j++) {
			String dir = in.next();
			if(dir.equals("N")) {
				C[j][2]=1;
				Nis.add(j);
			}
			else if(dir.equals("E")) {
				C[j][2]=0;
				Eis.add(j);
			}
			C[j][0]=in.nextInt(); C[j][1]=in.nextInt();
			C[j][3]=-1;
			C[j][4]=0;
		}
		
		ArrayList<ArrayList<Integer>> meetings = new ArrayList<ArrayList<Integer>>();
		
		// create list of meetings
		for(int j:Eis) { // compare east-facing cow j against
			for(int k:Nis) { // north-facing cow k
				// check which cow is stopped and which continues
				int ix=C[k][0]; int iy=C[j][1]; // intersection point
				int jD = ix-C[j][0]; // distance from j to intersection point
				int kD = iy-C[k][1]; // distance from k to intersection point
//				System.out.println("j: "+j+". k: "+k+". jD: "+jD+". C[j][0]="+C[j][0]+". kD: "+kD);
				if(jD<kD&&jD>0) { // if k is stopped
//					C[k][3]=Math.min(kD, C[k][3]);
//					System.out.println("Cow E "+j+" met cow N "+k+" and cow "+k+" was stopped");
					ArrayList<Integer> info = new ArrayList<Integer>();
					info.add(kD); // time that eaten grass is hit
					info.add(k); // cow that was stopped
					info.add(j);info.add(k); // two meeting cow indexes
					info.add(jD); // time that eaten grass is created
					meetings.add(info);
				}
				else if(kD<jD&&kD>0) { // if j is stopped
//					C[j][3]=Math.min(jD, C[j][3]);
//					System.out.println("Cow E "+j+" met cow N "+k+" and cow "+j+" was stopped");
					ArrayList<Integer> info = new ArrayList<Integer>();
					info.add(jD); info.add(j);info.add(j);info.add(k);
					info.add(kD);
					meetings.add(info);
				}
//				else { // if equal both keep going
//					
//				}
			}
		}
//		System.out.println("hello");
//		for(ArrayList<Integer> j:meetings) {
//			for(int k:j) {
//				System.out.print(k+" ");
//			}
//			System.out.println();
//		}
		
		Collections.sort(meetings, (meet1,meet2)-> meet1.get(0)-meet2.get(0));
		
//		System.out.println("hello");
//		for(ArrayList<Integer> j:meetings) {
//			for(int k:j) {
//				System.out.print(k+" ");
//			}
//			System.out.println();
//		}
		
		//^sort by timepoints
		for(ArrayList<Integer> timepoint:meetings) {
			// if both cows are still not stopped
			if(C[timepoint.get(2)][4]==0&&C[timepoint.get(3)][4]==0) {
				// make 1 cow stop
				C[timepoint.get(1)][4]=1;
				C[timepoint.get(1)][3]=timepoint.get(0);
			}
		}
		
		for(int j=0;j<n;j++) {
			if(C[j][3]==-1)System.out.println("Infinity");
			else System.out.println(C[j][3]);
		}
		in.close();
	}
}