// ACSL programming problem, Fibonacci & Mandelbrot Contest 4 2022
// intermediate Division

// passed 7/10 test cases, failed 2 hidden and 1 unhidden. unhidden
//  had input -1.21\n-0.32, which shoul have returned 8 but instead
//  returned ESCAPES 19
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'cycleLength' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. FLOAT realPartC
     *  2. FLOAT imagPartC
     */

    public static String cycleLength(float realPartC, float imagPartC) {
        ArrayList<String> sequence = new ArrayList<String>(); // will contain f(0),f(f(0)),etc..
        // each will be of the form "a b", symbolizing a+bi
        double r = Math.round(realPartC*100)/100.0;
        double i = Math.round(imagPartC*100)/100.0;
        String term = String.valueOf(r)+" "+String.valueOf(i);
        sequence.add(term);
        while(true){
            term = fib(getReal(term),getImag(term),r,i);
            System.out.println(sequence);
            // System.out.println(term);
            for(int j=0;j<sequence.size();j++){
                if(term.equals(sequence.get(j))){
                    return String.valueOf(sequence.size());
                }
                else if(getReal(term)*getReal(term)+getImag(term)*getImag(term)>16){
                    return "ESCAPES "+String.valueOf(sequence.size()+1);
                }
            }
            sequence.add(term);
        }
    }
    public static String fib(double realZ, double imagZ, double realC, double imagC){
        // System.out.println(realZ);
        // System.out.println(imagZ);
        // System.out.println(realC);
        // System.out.println(imagC);
        // finds next term in sequence of f(0),f(f(0)), etc... and returns a+bi form of "a b"
        
        // double realAns = Math.round(100*(realZ*realZ-imagZ*imagZ+realC))/100.0; // real part of answer
        double realAns = roundTwoDec(realZ*realZ-imagZ*imagZ+realC);
        System.out.println(realAns);
        // double imagAns = Math.round(100*(imagC+2*realZ*imagZ))/100.0; // imaginary part of answer
        double imagAns = roundTwoDec(imagC+2*realZ*imagZ);
        System.out.println(imagAns);
        // if(imagAns>0){
        //     imagAns = Math.floor(imagAns*100+0.5)/100.0;
        // }
        // else if (imagAns<0){
        //     imagAns = Math.floor(imagAns*100-0.5)/100.0;
        // }
        // System.out.println(String.valueOf(realAns)+" "+String.valueOf(imagAns));
        return String.valueOf(realAns)+" "+String.valueOf(imagAns);
    }
    public static double getReal(String complexN){
        String[] complex = complexN.split(" ");
        // System.out.println(Double.parseDouble(complex[0]));
        return Double.parseDouble(complex[0]);
    }
    public static double getImag(String complexN){
        String[] complex = complexN.split(" ");
        // System.out.println(Double.parseDouble(complex[1]));
        return Double.parseDouble(complex[1]);
    }
    public static double roundTwoDec(double x){ // round x to 2 decimal places
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
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        float realPartC = Float.parseFloat(bufferedReader.readLine().trim());

        float imagPartC = Float.parseFloat(bufferedReader.readLine().trim());

        String result = Result.cycleLength(realPartC, imagPartC);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
