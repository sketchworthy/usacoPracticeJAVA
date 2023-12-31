import java.util.*;
public class ranking {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Group[] groups = new Group[N];
		for(int j=0;j<N;j++) {
			groups[j]=new Group(in.nextInt(),j+1, 0);
		}
		
		Arrays.sort(groups,(g1,g2)->g1.value-g2.value); // sort groups in ascending order by values
		
		// displaying original locations of ints after they've been sorted
		for(int j=0;j<N;j++) {
			System.out.print(groups[j].location+" ");
			groups[j].rank = j+1;
		}
		System.out.println();
		
		// displaying integer ranks in their original order
		
		Arrays.sort(groups,(g1,g2)->g1.location-g2.location); // sort in ascending order of location
		
		for(int j=0;j<N;j++) {
			System.out.print(groups[j].rank+" ");
		}
		
		in.close();
	}


	static class Group {
		int value;
		int location;
		int rank;
		
		Group(int _value, int _location, int _rank){
			value = _value;
			location = _location;
			rank = _rank;
		}
	}
}