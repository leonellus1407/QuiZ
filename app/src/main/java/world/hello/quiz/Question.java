package world.hello.quiz;

public class Question {
    public String question;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;
    public Integer correctAnswer;

    public Question(String question, String answer1, String answer2, String answer3, String answer4, Integer correctAnswer){
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }

    public Boolean isAnswerCorrect(Integer pickedAnswer){
        return pickedAnswer == this.correctAnswer;
    }
}
