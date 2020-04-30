package sample;

import java.io.*;
import java.util.Arrays;

public class FileHandlerAlt {
    private File file;
    public Questionalt[] questions;

    FileHandlerAlt(String Filename) throws IOException {
        this.file = new File("src/sample/log/"+ Filename);
        init();
    }



    public void init() throws IOException {

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
        //Das Array wo die Fragen mit den dazugehörigen Antworten enthalten sind wird erstellt
        questions = new Questionalt[parts.length/5];
        fill(questions, parts);

        // --BEISPIEL-- Ausgabe der letzten Frage

        /*System.out.println(questions[9].getQuestion());
        System.out.println(questions[9].getAnswers(0));
        System.out.println(questions[9].getAnswers(1));
        System.out.println(questions[9].getAnswers(2));*/

    }

    //Initialisieren des Question Arrays
    private static Questionalt[] fill(Questionalt[] questions, String[] parts){
        int i = 0;
        int j = 0;
        while (i < parts.length){
            questions[j] = new Questionalt(parts[i]);
            questions[j].addAnswer(parts[++i]);
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
