import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class MergeRuns {
    static int numFiles;
    public static void main(String[] arg){
        numFiles =Integer.parseInt(arg[0]);
        new DistributeRuns(numFiles);
        mergeFiles();

    }

    static void mergeFiles(){
        BufferedReader[] readers = new BufferedReader[numFiles];
        Writer[] writers= new Writer[numFiles];  
        String[] topValues = new String[numFiles];
        for(int i =0; i< numFiles; i++){
            try{
            readers[i] = new BufferedReader(new FileReader(i+".runs"));
            writers[i]= new FileWriter((numFiles+1)+".runs",true);
            topValues[i] = readers[i].readLine();
            }
            catch(Exception e){
                System.err.println("First: Error while trying to access file "+i+".runs");
                e.printStackTrace();

            }
        }

        int currentOutputFile=0;
        while(true){
            String min = topValues[0];
            int minRun = 0;
            
            boolean finishedRun = false;
            
            while(!finishedRun){
                for(int i =0; i < numFiles; i++){
                    if(!topValues[i].equals("~~~")){
                        if(topValues[i].compareTo(min) <0){
                            min = topValues[i];
                            minRun = i;
                            
                        }
                }
                }
                // System.err.println(min);
                try{
                    writers[currentOutputFile].write(min+"\r\n");
                    topValues[minRun] = readers[minRun].readLine();
                }
                catch(Exception e){
                    System.err.println("Error while writing to "+(currentOutputFile+numFiles)+".runs");
                    e.printStackTrace();
                }

                
                // counts[minRun] = counts[minRun] +1;
                // finishedRun = true;
                // for(int i =0; i< numFiles; i++){
                //     if(counts[i]<splitLines[i].length){
                //         min = splitLines[i][counts[i]];
                //         minRun =i;
                //         finishedRun = false;
                //     }
                // }
            }
            currentOutputFile++;
            if(currentOutputFile >= numFiles){
                currentOutputFile =0;
            }
            // for(int i =0; i< numFiles; i++){
            //     try{
            //         counts[i] = 0;
            //         String line = readers[i].readLine();
            //         if(line != null){
            //             splitLines[i] = line.split("~~~");
            //         }
            //         else{
            //             completeFiles++;
            //         }
                    
            //     }
            //     catch(Exception e){
            //         System.err.println("Second: Error while trying to access file "+i+".runs");
            //         e.printStackTrace();
            //         return;
            //     }
            // }
            // if(){
            //     break;
            // }
        }
        System.err.println("closing readers");
        for(int i = 0; i< numFiles; i++){
            try{
                writers[i].close();
            }
            catch(Exception e){
                System.err.println(e);
                e.printStackTrace();
            }
        }
 
    }
}