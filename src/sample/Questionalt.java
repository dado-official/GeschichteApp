package sample;

public class Questionalt {
    private String question;
    //Die Antwort an der Stelle answers[0] ist immer die korrekte antwort
    //Die Antwort an der Stelle answers[3] ist immer die angeklickte antwort
    private int i = 0;
    Questionalt(String question){

        this.question = question;
    }
    private String[] answers = new String[4];



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
}
