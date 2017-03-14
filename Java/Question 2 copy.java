public class Question
{  
  String text, comment;  
  ShuffleVector answers;
  int value;
  public static final int DEFAULT_VALUE = 10;
  public static final String NO_QUESTION = "(No question)";

////////////////////// Constructors ///////////////////////

  public Question()
   {
    this(NO_QUESTION, new String(), false, 0);
   }

  public Question(String text)
   {
    this(text, new String(), false, Question.DEFAULT_VALUE);
   }

  public Question(String text, String comment)
   {
    this(text, comment, false, Question.DEFAULT_VALUE);
   }

  public Question(String text, boolean doShuffle)
   {
    this(text, new String(), doShuffle, Question.DEFAULT_VALUE);
   }

  public Question(String text, boolean doShuffle, int value)
   {
    this(text, new String(), doShuffle, value);
   }

  public Question(String text, String comment, boolean doShuffle, int value)
   {
    this.text = text;
    this.comment = comment;
    this.value = value;
    answers = new ShuffleVector(doShuffle);
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

  public void addAnswer(Answer ans)
   {
    answers.add(ans);
   }

  public void appendComment(String append)
   {
    if (comment.length() == 0)
      comment = append;
    else
      comment += "\n" + append;
   }

////////////////////// Methods ///////////////////////

  public void shuffleAnswers()
   {
    answers.shuffle();
   }

  public void unshuffleAnswers()
   {
    answers.unshuffle();
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

  public int getMaxScore()
   {
    return value;
   }

  public int getScore(Answer ans)
   {
    return getMaxScore() * ans.getValue() / ans.MAX_VALUE;
   }

  public Answer getAnswer(int i)
   {
    if (i < getNumAnswers())
      return (Answer) answers.getShuffled(i);
    else
      return new Answer();
   }

  public String getAnswerText(int i)
   {
    return getAnswer(i).getText();
   }

  public int getNumAnswers()
   {
    return answers.size();
   }

  public int getCorrectAnswer()
   {
    for (int i = 0; i < getNumAnswers(); i++)
      if (getAnswer(i).getType() == Answer.CORRECT)
        return i;
    return 0;
   }

  public boolean isEmpty() 
   {
    return text.equals(NO_QUESTION);    
   }
 }