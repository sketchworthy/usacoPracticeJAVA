import java.util.ArrayList;

public class ABRunner {
	public static void main(String[] args) {
		
		ArrayList<A> list = new ArrayList<A>();
		for(int j=0;j<5;j++) {
			list.add(new B());
		}
		
		for(int j=5;j<10;j++) {
			list.add(new D());
		}
		
		for(int j=0;j<10;j++) {
			list.get(j).beg();
		}
//		A[] players = new A[10];
//		for(int j=0;j<5;j++) {
//			players[j]=new B();
//		}
//		
//		for (int j=5;j<10;j++) {
//			players[j]=new D();
//		}
//		
//		for (int j=0;j<10;j++) {
//			players[j].beg();
//		}
		
//		A jack = new A();
//		jack.beg();
//		
//		B john = new B();
////		System.out.println(john.x);
//		System.out.println(john.getX());
//		john.beg();
//	// B inherits everything A has
//// A is parent/superclass, B is child/subclass
//		
//		D joe = new D();
//		joe.beg(); // D class beg overrides A beg
////		System.out.println(joe.x);
//		System.out.println(joe.getX());
	}
}
