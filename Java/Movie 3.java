class Movie
{
    // define fields of information
    // these are known as "instance variables"

    int acting;
    int directing;
    int script;

    // first define a constructor for Movie objects
    Movie ()
    {
	acting = directing = script = 5;
    }

    Movie (int a, int d, int s)
    {
	acting = a;
	directing = d;
	script = s;
    }

    // now define some instance methods
    int rating()
    {
	return (acting + directing + script);
    }
}
