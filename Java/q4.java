class q4
{
    public static void main(String[] args) {
	codeFrag(0);
	codeFrag(-5);
	codeFrag(5);
    }

    public static void codeFrag(int y) {
	int x = y;
	
	while(x > 0) { x--; }
	System.out.println("x = " + x);

	x = y;

	do { x--; } while(x > 0);
	System.out.println("x = " + x);
    }
}
