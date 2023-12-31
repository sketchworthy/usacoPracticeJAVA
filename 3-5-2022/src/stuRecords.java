import java.util.*;
public class stuRecords {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); // # of records
		
		ArrayList<Student> students = new ArrayList<>();
		for(int j=0;j<N;j++) {
			String name1 = in.next();
			String name2 = in.next();
			int grade = in.nextInt();
			
			// find if name1 and name2 have appeared in students<>
			int loc = -1;
			for(int k=0;k<students.size();k++) {
				if(students.get(k).fname.equals(name1) && students.get(k).lname.equals(name2)) {
					loc=k;
					break;
				}
			}
			if(loc>=0) {
				students.get(loc).grades.add(grade);
			}
			else {
				Student s=new Student();
				s.fname=name1;
				s.lname=name2;
				s.grades.add(grade);
				students.add(s);
			}
		}
		// calculate standard deviation
		for (Student s:students) {
			s.findSD();
		}
		
		Collections.sort(students, 
				(s1,s2)->!s1.lname.equals(s2.lname)?s1.lname.compareTo(s2.lname):
													s1.fname.compareTo(s2.fname));
		for (Student s:students) System.out.println(s.sd);
		in.close();
	}

	static class Student{
		String fname;
		String lname;
		ArrayList<Integer> grades;
		double sd;
		
		Student() {
			grades = new ArrayList<>();
		}
		
		public void findSD() {
			if(grades.size()<2) {
				sd=01;
				return;
			}
			double sum=0;
			for(int g:grades) {
				sum+=g;
			}
			double avg=sum/grades.size();
			sum=0;
			for(int g:grades) {
				sum+=Math.pow(g-avg, 2);
			}
			sd = Math.sqrt(sum/(grades.size()-1)); // 

		}
	}
}
