//ID: 1522996
//Name: Rory Thomson


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CreateRuns {
    public static void main(String[]arg){
        long startTime = System.nanoTime();
        int heapCap;
        try{
            if(Integer.parseInt(arg[0]) >0){
                heapCap= Integer.parseInt(arg[0]);
            }
            else{
                heapCap= 31;
            }
        }
        catch(Exception e){
            heapCap= 31;
        }
        myMinHeap minHeap = new myMinHeap(heapCap);
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        OutputStreamWriter writer =new OutputStreamWriter(System.out);
        String[] array= new String[heapCap];
        int lineCountIn =0;
        int lineCountOut=0;
        int current = 0;
        int runs =0;
        String lastLine =null;
        try {
            while(true){
                String line;
                if(lastLine != null){
                    line = lastLine;
                    lastLine = null;
                }
                else{
                    line = buffReader.readLine();
                    
                }
                if(line != null){
                    if(current < heapCap){
                        array[current] = line;
                        current = current+1;                   
                    }
                    else{
                        lastLine = line;
                        runs++;
                        minHeap.load(array);
                        lineCountIn = lineCountIn + current;
                        array = new String[heapCap];
                        current = 0;
                        while(minHeap.heapSize > 0){
                            String currentValue = minHeap.remove();
                            lineCountOut = lineCountOut +1;
                            writer.write(currentValue+"\r\n");
                        }
                        writer.write("~~~\r\n");
                    }
                }
                else{
                    if(current > 0){
                        runs++;
                        minHeap.load(array);
                        lineCountIn = lineCountIn + current;
                        array = new String[heapCap];
                        current = 0;
                        while(minHeap.heapSize > 0){
                            String currentValue = minHeap.remove();
                            lineCountOut = lineCountOut +1;
                            writer.write(currentValue+"\r\n");
                        }
                        writer.write("~~~\r\n");
                    }
                    writer.flush();
                    writer.close();
                    
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        System.err.println("Time To Run CreateRuns: "+ ((System.nanoTime()-startTime)/1000000)+" mS");
        System.err.println("Runs: "+runs);
        System.err.println("Line Input Count: "+ lineCountIn);
        System.err.println("Line Output Count: "+ lineCountOut);
    }
}