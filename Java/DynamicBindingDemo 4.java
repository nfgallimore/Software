/* 
 * DynamicBindingDemo.java
 * 
 * Computer Science E-119, Harvard University
 */

public class DynamicBindingDemo {
    public static void main(String[] args) {
	Bag b = new ArrayBag();
	b.add("foobar");
	b.add(new Integer(23));
	b.add(56);
	b.add(new Object());
	System.out.println(b);
    }
}
