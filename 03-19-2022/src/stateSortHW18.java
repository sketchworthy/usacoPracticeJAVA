/*
 * unfinished
 */
import java.util.*;
import java.io.*;
public class stateSortHW18 {
	public static void main(String[] args) throws Exception{
		State[] S = new State[50];
		Scanner in1 = new Scanner(new File("capital.txt"));
		for(int j=0;j<50;j++) {
			
			S[j] = new State();
			S[j].setName(in1.next());
			in1.skip(" - ");
			S[j].setCapital(in1.next());
		}
		in1.close();
		Scanner in2 = new Scanner(new File("population.txt"));
		for(int j=0;j<50;j++) {
			S[j].setPopulation(in2.nextInt());
		}
		in2.close();
		Scanner in3 = new Scanner(new File("gdp-per-capita.txt"));
		for(int j=0;j<50;j++) {
			S[j].setGDP(in3.nextLong());
		}
		in3.close();
		
		for(int j=0;j<50;j++) {
			System.out.println(S[j].getName()+" "+S[j].getCapital()
					+" "+S[j].getPopulation()+" "+S[j].getGDP());
		}
	}
}
