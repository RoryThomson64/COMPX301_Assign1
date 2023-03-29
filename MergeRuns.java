//ID: 1522996
//Name: Rory Thomson



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MergeRuns {
    static int numFiles;
    public static void main(String[] arg){
        numFiles =Integer.parseInt(arg[0]);
        new DistributeRuns(numFiles);
        MergeFiles();

    }

    static void MergeFiles(){
        BufferedReader[] readers = new BufferedReader[numFiles];
        Writer[] writers= new Writer[numFiles];  
        String[] topValues = new String[numFiles];
        boolean switchFiles =false;
        while(true){
            for(int i =0; i< numFiles; i++){
                try{
                readers[i] = new BufferedReader(new FileReader(((!switchFiles)?i:i+numFiles)+".runs"));
                writers[i]= new BufferedWriter(new FileWriter(((!switchFiles)?numFiles+i:i)+".runs",false));
                topValues[i] = readers[i].readLine();
                }
                catch(Exception e){
                    System.err.println("First: Error while trying to access file "+i+".runs");
                    e.printStackTrace();
                    return;
                }
            }
            
            int currentOutputFile=0;
            int numRuns =0;
            int numWords =0;
            while(true){
                while(!runsEmpty(topValues)){
                    int minRun =0;
                    for(int i =0; i< topValues.length; i++){
                        try { 
                            if(topValues[i].compareTo(topValues[minRun])<0){
                                minRun = i;
                            }   
                        } catch (Exception e) {
                            if(e instanceof NullPointerException){

                            }
                            else{
                                e.printStackTrace();
                            }
                        }
                    }
                    try{
                        writers[currentOutputFile].write(topValues[minRun]+"\r\n");
                        numWords++;
                        String temp = readers[minRun].readLine();
                        if(!temp.equals(null)){
                            topValues[minRun] = temp;
                        }
                        else{
                            topValues[minRun]="~~~";
                        }
                    }
                    catch(Exception e){
                        if(e instanceof NullPointerException){

                        }
                        else{
                            e.printStackTrace();
                        }
                        return;
                    }
                }
                try{
                    numRuns++;
                    writers[currentOutputFile].write("~~~\r\n");
                    for(int i=0;i < topValues.length;i++){
                        topValues[i]=readers[i].readLine();
                    }
                }
                catch(Exception e){
                    if(e instanceof NullPointerException){

                    }
                    else{
                        e.printStackTrace();
                    }
                    return;
                }
                if(topValues[0] == null){
                    break;
                }
                currentOutputFile++;
                
                if(currentOutputFile>=numFiles){
                    currentOutputFile=0;
                } 
            }
            for(int i =0; i< writers.length;i++){
                try{
                    writers[i].flush();
                    writers[i].close();
                    readers[i].close();
                }
                catch(Exception e){
                    if(e instanceof NullPointerException){

                    }
                    else{
                        e.printStackTrace();
                    }
                    return;
                }
            }
            System.err.println("Number of runs: "+numRuns);
            System.err.println("Number of words: "+numWords);
            if(numRuns==1){
                int finalFile = currentOutputFile+1 + ((switchFiles)?0:numFiles);
                System.err.println(finalFile);
                try {
                    BufferedReader buffReader = new BufferedReader(new FileReader((finalFile-1)+".runs"));
                    OutputStreamWriter writer = new OutputStreamWriter(System.out);
                    while(true){
                    String outputLine = buffReader.readLine();
                        if(outputLine.compareToIgnoreCase( "~~~")==0){
                            writer.flush();
                            writer.close();
                            return;
                        }
                        writer.write(outputLine+"\r\n");
                    }
                } catch (Exception e) {
                    if(e instanceof NullPointerException){

                    }
                    else{
                        e.printStackTrace();
                    }
                }
                return;
            }
            switchFiles=!switchFiles;
        }
    }

    static boolean runsEmpty(String[] array){
        for(int i =0; i<array.length;i++){
            try {
                if(!(array[i].compareToIgnoreCase("~~~")==0)){
                    return false;
                }
            } catch (Exception e) {
                if(e instanceof NullPointerException){

                }
                else{
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}