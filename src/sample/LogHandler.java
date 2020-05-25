package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import sample.GUI.AltesQuizController;
import sample.GUI.NeuesQuizController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class LogHandler {
    private File[] listOfFiles;
    public LogHandler() throws IOException {}

    public void checkForNewFiles() throws IOException {
        //Files in
        File folder = new File("Themenbereiche");
        listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        System.out.println(Arrays.toString(listOfFiles));

        //log = informationen
        File log = new File("log/log.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(log));
        //i = anzahl der Themebereiche
        String[] line = new String[listOfFiles.length];
        int i;
        for (i = 0; i < listOfFiles.length; i++) {
            line[i] = bufferedReader.readLine();
            if(line[i] == null|| line[i].equals("")){
                missingLogFile(line);
            }
        }
        bufferedReader.close();
    }


    private void missingLogFile(String[] line) throws IOException {
        boolean isHere = false;
        for (File f: listOfFiles) {
            String[] str = f.getName().split("\\.");
            for (String l: line) {
                if(l != null){
                    if(l.contains(str[0])){
                        isHere = true;
                        break;
                    }
                }
            }
            if(!isHere){
                //Quiz in log.txt hinzufügen
                String addString = str[0].concat(";1\n");
                try {
                    Files.write(Paths.get(getClass().getResource("log/log.txt").getPath()), addString.getBytes(), StandardOpenOption.APPEND);
                    System.out.println("new objekt added to log.txt --> " + f.getName());
                    FXMLLoader loader = new FXMLLoader();
                    Pane root = loader.load(getClass().getResource("GUI/neuesQuiz.fxml").openStream());
                    NeuesQuizController controller = loader.getController();
                    controller.addthema(f.getName());
                    System.out.println("Hinzugefügt");

                }catch (IOException e) {
                    //exception handling left as an exercise for the reader
                    e.printStackTrace();
                }
            }
            isHere = false;
        }
    }

    private int getAttemptsNumber(String topic) throws IOException {
        File log = new File(getClass().getResource("log/log.txt").getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(log));
        String line = " ";
        for (int i = 0; i < listOfFiles.length; i++) {
            line = bufferedReader.readLine();
            if(line.contains(topic)){
                String[] tmpStr = line.split(";");
                return Integer.parseInt(tmpStr[1]);
            }
        }
        return -1;
    }

    private void addAttempt(String topic) throws IOException {
        File log = new File(getClass().getResource("log/log.txt").getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(log));
        String[] line = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            line[i] = bufferedReader.readLine();
        }
        bufferedReader.close();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(log));
        for (String str: line) {
            if(str.contains(topic)){
                String[] tmpStrArr = str.split(";");
                int tmpStrInt = Integer.parseInt(tmpStrArr[1]);
                tmpStrInt++;
                tmpStrArr[1] = String.valueOf(tmpStrInt);

                str = tmpStrArr[0] + ";" + tmpStrArr[1];
            }
            System.out.println(str);
            bufferedWriter.write(str);
            bufferedWriter.write("\n");
        }
        bufferedWriter.close();
    }

    public void newAttempt(String topic) throws IOException {
        File f = new File(getClass().getResource("log/log.txt").getFile() + topic + getAttemptsNumber(topic) + ".txt");
        f.createNewFile();
        addAttempt(topic);
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("GUI/alteQuiz.fxml").openStream());
        AltesQuizController controller = loader.getController();
        controller.addMenuItem(topic+getAttemptsNumber(topic));
        System.out.println("Hinzugefügt");
    }

    public void writeQuestionAndAnswersToAttempt(Question question, String topic) throws IOException {
        //Frage mit Antworten in File schreiben
        try {
            Files.write(Paths.get(getClass().getResource("log/log.txt").getPath() + topic + (getAttemptsNumber(topic)-1) + ".txt"),
                    question.getQuestion().concat("\n")
                            .concat(question.getAnswers(0).concat("\n")
                            .concat(question.getAnswers(1).concat("\n")
                            .concat(question.getAnswers(2)).concat("\n"))).getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
            e.printStackTrace();
        }
    }

    public void writeClickedAnswer(String string, String topic){
        try {
            Files.write(Paths.get(getClass().getResource("log/log.txt").getPath() + topic + (getAttemptsNumber(topic)-1) + ".txt"),
                    string.concat("\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
            e.printStackTrace();
        }
    }
}
