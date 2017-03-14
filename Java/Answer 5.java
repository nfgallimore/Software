public class Answer 
{  
  String text, comment;  
  int value;			// Note: Value of the answer is weighted by value of question
  int type;

  public static final int INCORRECT = 0;
  public static final int PARTIALLY_CORRECT = 5;
  public static final int CORRECT = 10;  
  public static final int MAX_VALUE = 10;
  public static final String NO_ANSWER = "(No answer)";

////////////////////// Constructors ///////////////////////

  public Answer()  
   {
    this(NO_ANSWER, new String(), Answer.INCORRECT);
   }  

  public Answer(String text)   
   {
    this(text, new String(), Answer.INCORRECT);
   }

  public Answer(String text, String comment)   
   {
    this(text, comment, Answer.INCORRECT);
   }

  public Answer(String text, int type)  
   {
    this(text, new String(), type);
   }

  public Answer(String text, String comment, int type)  
   {
    this.text = text;
    this.comment = comment;
    this.type = type;
    this.value = type;
   }

////////////////////// Data entry ///////////////////////

  public void setText(String text)
   {
    this.text = text;
   }

  public void setComment(String comment)
   {
    this.comment = comment;
   }

  public void setValue(int value)
   {
    this.value = value;
   }

  public void setType(int type)
   {
    this.type = type; 
   }

  public void appendComment(String append)
   {
    if (comment.length() == 0)
      comment = append;
    else
      comment += "\n" + append;
   }

////////////////////// Queries ///////////////////////

  public String getText()
   {
    return text;
   }

  public String getComment()
   {
    return comment;
   }

  public boolean hasComment()
   {
    return (comment.length() > 0);
   }

  public int getValue()
   {
    return value;
   }

  public int getType()
   {
    return type;
   }

  public boolean isEmpty()
   {
    return text.matches(NO_ANSWER);
   }

  public String getVerdict()
   {
    switch (type)
     {
      case INCORRECT:         return "Incorrect";
      case PARTIALLY_CORRECT: return "Partially Correct";
      case CORRECT:           return "Correct";
      default:                return "Not Classifiable";
     }
   }
 }