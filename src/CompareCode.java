

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CompareCode {
    ArrayList<String> gitFoldersList = new ArrayList<>();
    ArrayList<String> codes = new ArrayList<>();
    int count = 0;
    String filename = "grades.txt";

    public static void main(String[] args) throws IOException {
        /*
        String file1 = "C:\\Users\\11812508\\IdeaProjects\\Module0\\test_program1.java";
        String file2 = "C:\\Users\\11812508\\IdeaProjects\\Module0\\test_program2.java";
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
        int i=0;
        int score=0;

        StringBuilder code1 = new StringBuilder();
        StringBuilder code2 = new StringBuilder();
        String line1 = "";
        String line2 = "";
        while(((line1=reader1.readLine())!=null)&&((line2=reader2.readLine())!=null)){
            if(line1.equals(line2)){
                score++;
            }
            i++;
        }
        double grade = (Double.valueOf(score)/Double.valueOf(i-1))*100;
        System.out.println("Programs are " + (int)grade + "% similar!");
        if(grade>=70){
            System.out.println("Programs are plagiarized");


        }

         */
        CompareCode c = new CompareCode();
        c.run();
    }

    private void run() throws IOException {
        //getFilenames();
        /*
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter name of file to compare with: ");
        String filename = myObj.nextLine();
        */
        //ArrayList<String> codes = new ArrayList<>();
        String myFile;
        String filename;

        System.out.println();
        getGitFolders();
        for(int i = 0; i<gitFoldersList.size(); i++) {
            for (int j = 0; j < gitFoldersList.size(); j++) {
                System.out.println(gitFoldersList.get(i) + " and " + gitFoldersList.get(j));
                compareFolders(gitFoldersList.get(i), gitFoldersList.get(j));
                System.out.println("\n");
            }

        }

        //getFilesInFolders(gitFoldersList.get(1));

        /*
        getFilenames();
        for(int i=0; i<codes.size();i++){
            for(int j=0; j<codes.size(); j++){
                compareFiles(codes.get(i), codes.get(j));
            }
            System.out.println();
        }

         */

        //compareFiles("Matthew_main.cpp", "Matthew_main.cpp");
        //compareFolders(gitFoldersList.get(0), gitFoldersList.get(0));
    }

    private void getGitFolders(){
        File folder = new File("C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\GitHubSubmissions");
        File[] gitFolders = folder.listFiles();
        for(int i = 0; i<gitFolders.length; i++){
            System.out.println(gitFolders[i].getName());
            gitFoldersList.add(gitFolders[i].getName());
        }
    }

    private void getFilesInFolders(String folderName){
        File folder = new File("C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\GitHubSubmissions\\" + folderName);
        File[] codesList = folder.listFiles();
        for(int i =0; i<codesList.length; i++){
                System.out.println(codesList[i].getName());
                //codes.add(codesList[j].getName());
            System.out.println();
        }

    }

    private void getFilenames(){
        File folder = new File("C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\Submissions");
        File[] fileList = folder.listFiles();
        System.out.println("Available files: ");
        for(int i = 0; i<fileList.length; i++){
            codes.add(fileList[i].getName());
            System.out.println(codes.get(i));
        }
    }

    private void compareFolders(String folder1, String folder2) throws IOException {
        File firstFolder = new File("C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\GitHubSubmissions\\" + folder1);
        File secondFolder = new File("C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\GitHubSubmissions\\" + folder2);
        File[] fileList1 = firstFolder.listFiles();
        File[] fileList2 = secondFolder.listFiles();
        for(int i = 0; i<fileList1.length; i++){
            for(int j = 0; j<fileList2.length; j++){
                compareFiles(folder1, folder2, fileList1[i].getName(), fileList2[j].getName());
            }
        }
    }

    private void compareFiles(String folder1, String folder2, String thisfile, String comparefile) throws IOException {

            String file1 = "C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\GitHubSubmissions\\" + folder1 + "\\" + thisfile;
            String file2 = "C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\GitHubSubmissions\\" + folder2 + "\\" + comparefile;
            /*
            if (!codes.get(count).equals(myFile)) {
                file2 = "C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\Submissions\\" + codes.get(count);
            } else
                file2 = "C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\Submissions\\" + codes.get(count + 1);

             */
        InputStream firstFile = new FileInputStream(file1);
        InputStream secondFile = new FileInputStream(file2);
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(firstFile));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(secondFile));
        //int i = 0;
        double score = 0;
        double avg;
        double grade;

            /*
            StringBuilder code1 = new StringBuilder();
            StringBuilder code2 = new StringBuilder();
             */
        ArrayList < String > code1Words = new ArrayList < String > ();
        ArrayList < String > code2Words = new ArrayList < String > ();
        ArrayList <String> equalWords = new ArrayList<>();
        String line1 = "";
        String line2 = "";
        int lineCount1 = 0;
        int lineCount2 = 0;
            /*
            while((line1=reader1.readLine())!=null){
                line1 = line1.replaceAll("\\s+","");
                if((!line1.equals(""))&&(!line1.equals("\t"))&&(!line1.contains("}"))&&(!line1.equals("{"))){
                    //System.out.println(line1);
                    code1.add(line1);
                }
            }
            while((line2=reader2.readLine())!=null){
                line2 = line2.replaceAll("\\s+","");
                if ((!line2.equals(""))&&(!line2.equals("\t"))&&(!line2.contains("}"))&&(!line2.equals("{"))){
                    System.out.println(line2);
                    code2.add(line2);
                }
            }
           */
        while((line1=reader1.readLine())!=null){
            if(line1.isEmpty()) line1 = line1.replaceAll("\\s+","");
            line1 = line1.replaceFirst("^\\s+", "");
            line1 = line1.replace(";", "");
            line1 = line1.replace(",", " ");
            if((!line1.equals(""))&&(!line1.equals("\t"))&&(!line1.contains("}"))&&(!line1.equals("{"))) {
                //line1 = line1.replace("\n", "");
                lineCount1++;
                //System.out.println("Iteration " + i);
                //System.out.println(line1);
                code1Words.addAll(Arrays.asList(line1.split(" ")));
                StringTokenizer st = new StringTokenizer(line1);
                while(st.hasMoreTokens()) {
                    String thisWord = st.nextToken();
                    //System.out.println(thisWord);
                    //code1Words.add(thisWord);
                    code2Words.clear();
                    while ((line2 = reader2.readLine()) != null) {
                        if (line2.isEmpty()) line2 = line2.replaceAll("\\s+", "");
                        line2 = line2.replaceFirst("^\\s+", "");
                        line2 = line2.replace(";", "");
                        line2 = line2.replace(",", " ");
                        if ((!line2.equals("")) && (!line2.equals("\t")) && (!line2.contains("}")) && (!line2.equals("{"))) {
                            //line2 = line2.replace("\n", "");
                            //System.out.println(line2);
                            if (lineCount1 == 1) lineCount2++;
                            code2Words.addAll(Arrays.asList(line2.split(" ")));
                            if ((line2.contains(thisWord))) {
                                //System.out.println(thisWord);
                                if (!isInEqualList(equalWords, thisWord)) equalWords.add(thisWord);
                                //System.out.println(score);
                            }
                        }
                    }
                    reader2 = new BufferedReader(new FileReader(file2));
                }
                //i++;
            }
        }

            /*
            for(int i=0; i<code1.size();i++){
                for(int j=0; j<code2.size();j++){
                    if(code1.get(i).equals(code2.get(j))){
                        score++;
                    }
                }
            }
            /*
            System.out.println(score);
            System.out.println(lineCount1);
            System.out.println(lineCount2);
            System.out.println(score);
            System.out.println(code1Words.size());
            System.out.println(code2Words.size());
             */
        /*
        for(int i=0; i<equalWords.size(); i++){
            System.out.println(equalWords.get(i));
        }

         */
        grade = getGrade(code1Words, code2Words);
        grade = grade *100;
        //System.out.println(grade);
        /*
        score = equalWords.size();
        avg = (code1Words.size()+code2Words.size())/2;
        if(score>avg) grade = 100;
        else grade = (score/avg)*100;

         */
        System.out.println(thisfile + " - " + comparefile);
        System.out.println("  The programs are " + Math.round((grade*100)/100) + "% similar!");
            //compareFiles();
            //writeToFile(thisfile, y, grade);

    }

    private void compareFiles(String thisfile, String comparefile) throws IOException {

        String file1 = "C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\Submissions\\" + thisfile;
        String file2 = "C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\Submissions\\" + comparefile;
            /*
            if (!codes.get(count).equals(myFile)) {
                file2 = "C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\Submissions\\" + codes.get(count);
            } else
                file2 = "C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1\\Submissions\\" + codes.get(count + 1);

             */
        InputStream firstFile = new FileInputStream(file1);
        InputStream secondFile = new FileInputStream(file2);
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(firstFile));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(secondFile));
        //int i = 0;
        double score = 0;
        double avg;
        double grade;

            /*
            StringBuilder code1 = new StringBuilder();
            StringBuilder code2 = new StringBuilder();
             */
        ArrayList < String > code1Words = new ArrayList < String > ();
        ArrayList < String > code2Words = new ArrayList < String > ();
        ArrayList <String> equalWords = new ArrayList<>();
        String line1 = "";
        String line2 = "";
        int lineCount1 = 0;
        int lineCount2 = 0;
            /*
            while((line1=reader1.readLine())!=null){
                line1 = line1.replaceAll("\\s+","");
                if((!line1.equals(""))&&(!line1.equals("\t"))&&(!line1.contains("}"))&&(!line1.equals("{"))){
                    //System.out.println(line1);
                    code1.add(line1);
                }
            }
            while((line2=reader2.readLine())!=null){
                line2 = line2.replaceAll("\\s+","");
                if ((!line2.equals(""))&&(!line2.equals("\t"))&&(!line2.contains("}"))&&(!line2.equals("{"))){
                    System.out.println(line2);
                    code2.add(line2);
                }
            }
           */
        while((line1=reader1.readLine())!=null){
            if(line1.isEmpty()) line1 = line1.replaceAll("\\s+","");
            line1 = line1.replaceFirst("^\\s+", "");
            line1 = line1.replace(";", "");
            line1 = line1.replace(",", " ");
            if((!line1.equals(""))&&(!line1.equals("\t"))&&(!line1.contains("}"))&&(!line1.equals("{"))) {
                //line1 = line1.replace("\n", "");
                lineCount1++;
                //System.out.println("Iteration " + i);
                //System.out.println(line1);
                code1Words.addAll(Arrays.asList(line1.split(" ")));
                StringTokenizer st = new StringTokenizer(line1);
                while(st.hasMoreTokens()) {
                    String thisWord = st.nextToken();
                    //System.out.println(thisWord);
                    //code1Words.add(thisWord);
                    code2Words.clear();
                    while ((line2 = reader2.readLine()) != null) {
                        if (line2.isEmpty()) line2 = line2.replaceAll("\\s+", "");
                        line2 = line2.replaceFirst("^\\s+", "");
                        line2 = line2.replace(";", "");
                        line2 = line2.replace(",", " ");
                        if ((!line2.equals("")) && (!line2.equals("\t")) && (!line2.contains("}")) && (!line2.equals("{"))) {
                            //line2 = line2.replace("\n", "");
                            //System.out.println(line2);
                            if (lineCount1 == 1) lineCount2++;
                            code2Words.addAll(Arrays.asList(line2.split(" ")));
                            if ((line2.contains(thisWord))) {
                                //System.out.println(thisWord);
                                if (!isInEqualList(equalWords, thisWord)) equalWords.add(thisWord);
                                //System.out.println(score);
                            }
                        }
                    }
                    reader2 = new BufferedReader(new FileReader(file2));
                }
                //i++;
            }
        }

            /*
            for(int i=0; i<code1.size();i++){
                for(int j=0; j<code2.size();j++){
                    if(code1.get(i).equals(code2.get(j))){
                        score++;
                    }
                }
            }
            /*
            System.out.println(score);
            System.out.println(lineCount1);
            System.out.println(lineCount2);
            System.out.println(score);
            System.out.println(code1Words.size());
            System.out.println(code2Words.size());
             */
        /*
        for(int i=0; i<equalWords.size(); i++){
            System.out.println(equalWords.get(i));
        }

         */
        grade = getGrade(code1Words, code2Words);
        grade = grade *100;
        //System.out.println(grade);
        /*
        score = equalWords.size();
        avg = (code1Words.size()+code2Words.size())/2;
        if(score>avg) grade = 100;
        else grade = (score/avg)*100;

         */
        System.out.println(thisfile + " - " + comparefile);
        System.out.println("  The programs are " + Math.round((grade*100)/100) + "% similar!");
        //compareFiles();
        //writeToFile(thisfile, y, grade);

    }

    private double getGrade(ArrayList code1Words, ArrayList code2Words){
        double score=0;
        double grade;
        for(int i=0; i<code1Words.size(); i++){
            for(int j=0; j<code2Words.size(); j++){
                if(code1Words.get(i).equals(code2Words.get(j))){
                    score++;
                    break;
                }
            }
        }
        /*
        System.out.println("Score: " + score);
        System.out.println("Size of code2: " + code1Words.size());
        System.out.println("Size of code2: " + code2Words.size());

         */
        grade = (score/(((double)code1Words.size()+(double)code2Words.size())/2));
        //System.out.println("Grade: " + grade);
        return grade;
    }

    private boolean isInEqualList(ArrayList equalWords, String word){
        boolean present=false;
        if(equalWords.isEmpty()) present=false;
        else{
            for(int i = 0; i<equalWords.size(); i++){
                if(equalWords.get(i).equals(word)){
                    present=true;
                    break;
                }
            }
        }
        return present;
    }

    public void writeToFile(String name, int count, double grade) throws IOException {
        /*
        FileInputStream myXLSXFile = new FileInputStream("CorrelationMatrix.xlsx");

        XSSFWorkbook wBook = new XSSFWorkbook(myXLSXFile);

        XSSFSheet wSheet = wBook.getSheetAt(0);

        XSSFRow row = wSheet.createRow(i+1);
        row.createCell(j).setCellValue(grade);

        myXLSXFile.close();

        FileOutputStream output_file = new FileOutputStream(new File("CorrelationMatrix.xlsx"));
        wBook.write(output_file);
        output_file.close();


        FileWriter fw = new FileWriter(file);
        String newLine = System.getProperty("line.separator");
        BufferedWriter bw = new BufferedWriter(fw);
        if(count==34){
            bw.write(String.valueOf(grade/100) + newLine);
            bw.write("");
        }
        else bw.write(String.valueOf(grade/100) + newLine);
        bw.close();

         */

        FileWriter fw = new FileWriter(filename,true);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

        if(count==0) {
            out.write(name + "\n");
        }
        if(count==34){
            out.write(String.valueOf(grade/100) + "\n");
            out.write("\n\n");
        }
        else out.write(String.valueOf(grade/100) + "\n");
        out.close();
    }
}
