// File Movie2.java

class Movie2
{
    private int script;
    private int directing;
    private int acting;

    public Movie2 (int s, int a, int d)
    {
        script = s;
        acting = a;
        directing = d;
    }

    public Movie2 ()     // 0-parameter version
    {
        script = 5;
        acting = 5;
        directing = 5;
    }

    public int rating()
    {
       return   (script + directing + acting);
    }
}
