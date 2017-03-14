public class RollDice {

    public static void main(String[] args) { 
        rollDie(); 
        rollDie();  

	// Print out whether you would have won if you were trying
	// to get doubles
	// Print out whether you would have won if you were trying
	// to roll an even number
    }

    static void rollDie() { 
        int result = (int) (Math.random() * sides) + 1; 
        System.out.println(result); 
    }
}
