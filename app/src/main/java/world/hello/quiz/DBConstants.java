package world.hello.quiz;

public class DBConstants {
    //DATABASE
    public static final Integer DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Questions";

    //TABLE Question
    public static final String TABLE_QUESTION_NAME = "Question";
    public static final String TABLE_QUESTION_FIELD_ID = "_Id";
    public static final String TABLE_QUESTION_FIELD_LABEL = "Label";
    public static final String TABLE_QUESTION_FIELD_ANSWER1 = "Answer1";
    public static final String TABLE_QUESTION_FIELD_ANSWER2 = "Answer2";
    public static final String TABLE_QUESTION_FIELD_ANSWER3 = "Answer3";
    public static final String TABLE_QUESTION_FIELD_ANSWER4 = "Answer4";
    public static final String TABLE_QUESTION_FIELD_CORRECTANSWER = "CorrectAnswer";

    //TABLE Result
    public static final String TABLE_RESULT_NAME = "Results";
    public static final String TABLE_RESULT_FIELD_ID = "_Id";
    public static final String TABLE_RESULT_FIELD_NAME = "Name";
    public static final String TABLE_RESULT_FIELD_CORRECT_ANSWER = "CorrectAnswer";
    public static final String TABLE_RESULT_FIELD_TOTAL_QUESTIONS = "TotalQuestions";

}
