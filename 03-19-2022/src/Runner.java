// run Circle.java
public class Runner {
	public static void main(String[] args) {
		Circle jack = new Circle(); // create object of class Circle
		jack.laugh();
		
		Circle john = new Circle();
		System.out.println(john.getCx());
		john.setCx(4);
		System.out.println(john.getCx());
		
		Circle jane = new Circle(3,19);
		jane.setRadius(10);
		System.out.println(jane.getArea());
		System.out.println(jane.getPerimeter());
		System.out.println(jane.isInside(3, 20));
	}

}
