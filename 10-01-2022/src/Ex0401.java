/* Helper class
 * 
 */
import java.util.*;

public class Ex0401 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n= in.nextInt(); // # of records
		ArrayList<Student> st = new ArrayList<>();
		
		for(int j=0;j<n;j++) {
			String name1=in.next();
			String name2=in.next();
			int grade=in.nextInt();
			
			int loc=0;
			for(; loc<st.size(); loc++) {
				if(st.get(loc).fname.equals(name1)&&st.get(loc).lname.equals(name2)) {
					break;
				}
			}
			
			if(loc!=st.size()) { // { note: this shit needs to be changed slightly i think
				st.get(loc).grades.add(grade);
			}
			else {
				Student s1 = new Student();
				s1.fname=name1;
				s1.lname=name2;
				s1.grades.add(grade);
				st.add(s1);
			} // }
		}
		
		Collections.sort(st,(s1,s2)->
		!s1.lname.equals(s2.lname) ? s1.lname.compareTo(s2.lname)
				: s1.fname.compareTo(s2.fname) );
		// sort first by last name. if last name is equal, then sort by 1st name.
		
		for(Student s:st) {
			System.out.println(s.computeSD()); // TODO need to cut the .0 if there's a .0
		}
		in.close();
	}
	
	static class Student {
		String fname;
		String lname;
		ArrayList<Integer> grades;
		
		Student() {
			grades = new ArrayList<Integer>();
		}

		public double computeSD() {
			if(grades.size()<2)return -1;
			double total = 0;
			for(int grade : grades) {
				total+=grade;
			}
			double avg = total/grades.size();
			double SD = 0;
			for(int grade:grades) {
				SD+=(grade-avg)*(grade-avg);
			}
			SD/=grades.size()-1;
			SD=Math.pow(SD, 0.5);
			return SD;
		}
		
		
	}
}
