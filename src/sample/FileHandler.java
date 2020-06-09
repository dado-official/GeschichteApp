package sample;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

//Klasse zum lesen des Files und erstellen der Fragen mit den dazugehörigen antworten
public class FileHandler {
    private File file;
    public Question[] questions;

    public FileHandler(String Filename) throws IOException {
        this.file = new File("Themenbereiche/"+ Filename);
        init();
    }



    public void init() throws IOException {

        BufferedReader buf = null;

        //BufferedReader wird erstellt
        try {
            buf = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8.name()));
        }catch (FileNotFoundException e){
            System.out.println("Error: File not found");
        }

        assert buf != null;
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
        //Das Array wo die Fragen mit den dazugehörigen Antworten enthalten sind wird erstellt
        questions = new Question[parts.length/4];
        fill(questions, parts);

        // --BEISPIEL-- Ausgabe der letzten Frage

        /*System.out.println(questions[9].getQuestion());
        System.out.println(questions[9].getAnswers(0));
        System.out.println(questions[9].getAnswers(1));
        System.out.println(questions[9].getAnswers(2));*/

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

    public String getAnswer(int frage, int antw){
        return questions[frage].getAnswers(antw);
    }

    @Override
    public String toString() {
        return "FileHandler{" +
                "questions=" + Arrays.toString(questions) +
                '}';
    }
}
