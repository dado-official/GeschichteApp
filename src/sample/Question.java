package sample;

import java.util.Arrays;

//Die Klasse welche eine Frage und die dazugehörigen Antworten speichert
public class Question {
    private String question;
    //Die Antwort an der Stelle answers[0] ist immer die korrekte antwort
    private int i = 0,anz;
    Question(String question){

        this.question = question;
        this.anz=anz;
    }
    private String[] answers = new String[3];



    void addAnswer(String answer){
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
