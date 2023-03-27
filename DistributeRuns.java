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

                    writer = new FileWriter(currentFile+".runs", true);
                    while(line != null&&!line.equals("~~~")){
                        // System.err.println(line);
                            // System.err.println(line);
                        // }
                    
                        // if(line != "~~~"){
                        linesSeen = linesSeen + 1;
                        writer.write(line+"\r\n");
                        runs++;
                        lastLine = line;
                        // }
                        // else{
                        //     writer.write("~~~\r\n");
                        //     break;
                        // }
                        line = buffReader.readLine();
                    }
                    
                    writer.write("~~~\r\n");
                    writer.flush();
                    writer.close();
                    currentFile +=1;
                    if(line == null){
                        break;
                    }
                }

                lastLine =line;
                currentFile =0;
                if(line == null){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("Distribute runs saw "+linesSeen+" lines");
        System.err.println("Distribute runs wrote "+runs+" runs");


    }
}