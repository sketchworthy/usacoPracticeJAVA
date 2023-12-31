import java.util.Arrays;
import java.util.Scanner;

public class partyGabrielXiong {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int teles = in.nextInt();
        int end = in.nextInt();
        int[][] arr = new int[teles][3];
        for (int i = 0; i < teles; i++) {
            arr[i][0] = in.nextInt(); //start
            arr[i][1] = in.nextInt(); //end
            //arr[i][2] = arr[i][1] - arr[i][0]; //distance saved not accounting for going over
        }

        for (int k = 0; k < teles; k++) {
              int distance=arr[k][1] - arr[k][0];
              if (arr[k][1]>end)
                  distance+=-arr[k][1]+end;
              arr[k][2]=distance;
        }
        Arrays.sort(arr, (r1,r2)-> r2[2]-r1[2]); //sort arrays based on distance saved
        //System.out.println(Arrays.deepToString(arr));
        for (int p=0; p<teles;p++)
        {
            for (int k=p+1; k<teles;k++)
            {
                if (arr[p][0]+arr[p][1]>arr[k][0])
                    arr[k][2]=0;
            }
        }
        int distanceSaved=0;

        //System.out.println(Arrays.deepToString(arr));
        for (int i=0; i<teles; i++)
        {
            distanceSaved+=arr[i][2];
        }
        //System.out.println(distanceSaved);
        System.out.println(end-1-distanceSaved);
    }
}
