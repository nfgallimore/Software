public class Scope { 

    public static void main(String[] args) {
        int x = 6; 
        int y = 4;
        int z = -2;  
        foo(y,x); 
        x = bar(z,x); 
        System.out.println(" x: " + x + 
                           " y: " + y + 
                           " z: " + z);  
    }

    static void foo(int x, int y) { 
        int z = x + y; 
    }

    static int bar(int y, int foo) { 
         foo += x; 
         y++; 
         return foo - y; 
    }
}
