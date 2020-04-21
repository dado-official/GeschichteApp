package sample;

import java.io.*;
//Klasse zum lesen des Files und erstellen der Fragen mit den dazugehörigen antworten
public class FileHandler {
    public static void main(String[] args) throws IOException {
        File file = new File("Balkankriege_quiztest.txt");
        BufferedReader buf = null;

        //BufferedReader wird erstellt
        try {
            buf = new BufferedReader(new FileReader(file));
        }catch (FileNotFoundException e){
            System.out.println("Error: File not found");
        }

        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();

        //File wird als string eingelesen
        while(line != null){
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        String fileAsString = sb.toString();

        //String wird aufgeteilt
        String[] parts = fileAsString.split("\n");
        //Das Array wo die Fragen mit den dazugehörigen Antworten entahlten sind wird erstellt
        Question[] questions = new Question[parts.length/4];
        fill(questions, parts);

        // --BEISPIEL-- Ausgabe der letzten Frage
        System.out.println(questions[9].getQuestion());
        System.out.println(questions[9].getAnswers(0));
        System.out.println(questions[9].getAnswers(1));
        System.out.println(questions[9].getAnswers(2));
    }

    //Initialisieren des Question Arrays
    private static Question[] fill(Question[] questions, String[] parts){
        int i = 0;
        int j = 0;
        while (i < parts.length){
            questions[j] = new Question(parts[i]);
            questions[j].addAnswer(parts[++i]);
            questions[j].addAnswer(parts[++i]);
            questions[j].addAnswer(parts[++i]);
            j++;
            i++;
        }
        return questions;
    }
}
