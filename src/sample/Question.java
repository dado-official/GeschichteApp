package sample;

import java.util.Arrays;

//Die Klasse welche eine Frage und die dazugehörigen Antworten speichert
public class Question {
    private String question;
    //Die Antwort an der Stelle answers[0] ist immer die korrekte antwort
    private String[] answers = new String[3];
    private int i = 0;

    public Question(String question){
        this.question = question;
    }

    public void addAnswer(String answer){
        answers[i] = answer;
        i++;
    }

    public String getAnswers(int i) {
        return answers[i];
    }
    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}
