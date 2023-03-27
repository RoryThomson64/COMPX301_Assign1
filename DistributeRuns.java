import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Writer;

public class DistributeRuns {
    int _numFiles;
    public DistributeRuns(int numFiles){
        if(numFiles >0){
            _numFiles = numFiles;
        }
        else{
            _numFiles = 2;
        }
        Distribute();
    }

    public void Distribute(){
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        int currentFile = 0;
        FileWriter writer;
        try {
            while(currentFile < _numFiles){
                writer = new FileWriter(currentFile+".runs");
                writer.write("");
                writer.close();
                currentFile += 1;
            }
            currentFile =0;
        } catch (Exception e) {
            System.err.println(e);
        }
        String line = null;
        String lastLine =null;
        int linesSeen =0;
        int runs =0;
        try {
            
            while(true){
                
                while(currentFile < _numFiles){
                    // if( lastLine != null){
                    //     // System.err.println(lastLine);
                    //     line = lastLine;
                    //     lastLine = null;
                        
                    // }
                    // else{
                        line = buffReader.readLine();
                        // System.err.println(line);
                    // }
                
                    if(line != null){
                        linesSeen = linesSeen +line.split("///").length;
                        // String[] splitLine = line.split("///");
                        // String runOutput = "";
                        // writer = new FileWriter(currentFile+".runs", true);
                        // for(int i =0;i < splitLine.length && splitLine[i].compareTo("!!RunEnd!!")!=0;i++){
                        //     // writer.write(splitLine[i]+"\r\n");
                        //     runOutput= runOutput + splitLine[i]+"///";
                        // }
                        // // writer.write("!!RunEnd!!\r\n");
                        // writer.write(runOutput+"\r\n");
                        // writer.close();
                        
                        writer = new FileWriter(currentFile+".runs", true);
                        writer.write(line+"\r\n");
                        runs++;
                        writer.close();

                    }
                    else{
                        System.err.println("Saw null!!");
                        break;
                    }
                    currentFile +=1;
                }

                lastLine =line;
                currentFile =0;
                if(line == null){
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        System.err.println("Distribute runs saw "+linesSeen+" lines");
        System.err.println("Distribute runs wrote "+runs+" runs");


    }
}