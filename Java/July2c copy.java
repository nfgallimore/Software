class July2c
{
    public static void main (String [] args)
    {
        int heads = 0, tails = 0;
        for (int i = 1; i <= 1000; i++ )
        {
            if (Math.random ()  > 0.5 )  heads++;
            else tails++;
        }
        System.out.println ("# heads = " + heads + ", and # tails = " + tails);
    }
}
