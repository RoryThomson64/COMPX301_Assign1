import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class CreateRuns {
    //Need to add writing to files, find a way to check the validity of Output
    public static void main(String[]arg){
        long startTime = System.nanoTime();
        int heapCap= Integer.parseInt(arg[0]);
        myMinHeap minHeap = new myMinHeap(heapCap);
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        OutputStreamWriter writer =new OutputStreamWriter(System.out);
        String[] array= new String[heapCap];
        String[] leftOvers = new String[0];
        int current = 0;
        try {
            while(true){
            String line = buffReader.readLine();
            if(line != null){
                // String[] splitLine = line.split(" ");
                if(current < heapCap){
                    if(leftOvers.length >0){
                        for(int i = 0; i < leftOvers.length; i++){
                            array[i] = leftOvers[i];
                        }
                        current = leftOvers.length;
                        leftOvers = new String[0];
                    }
                    String[] splitLine= Arrays.stream(line.split(" ")).filter(word -> !word.isEmpty()).toArray(String[]::new);
                    int splitIndex = 0;
                    for(int i = 0; i < splitLine.length && current < heapCap; i++){
                        System.out.println(current);
                        array[current] = splitLine[i];
                        current= current +1;
                        splitIndex = i;
                    }
                    System.out.println(splitIndex+" | "+(splitLine.length-1));
                    if(splitLine.length > 0){
                        if(splitIndex != splitLine.length-1){
                            leftOvers = new String[(splitLine.length-1)-splitIndex];
                            for(int i = 0; i < (splitLine.length-1)-splitIndex;i++){
                                leftOvers[i] =  splitLine[splitIndex+i+1];
                            }
                        }
                    }
                    current =current+ splitIndex+1;
                }
                else{
                    minHeap.load(array);
                    array = new String[heapCap];
                    current = 0;
                }
                
                // System.out.println("LINE: "+line);
                // for(int i = 0; i< array.length; i++){
                //     // System.out.println(array[i]);
                // }
                // minHeap.load(array);
                // System.out.println(returnCode);
                // System.out.println(minHeap.heapSize);
                // if(returnCode > -1){
                //     for(int j = 0; j< array.length-1 ; j++){
                //         // System.out.println(j);
                //         // System.out.println("removeing");
                //         String val = minHeap.remove();
                //         writer.write(val+",");

                //     }
                //     writer.write("!!RunEnd!!,");
                //     current = 0;
                //     array = new String[Integer.parseInt(arg[0])];
                //     // array
                //     for(int i = returnCode; i< splitLine.length; i++){
                //         if(!splitLine[i].isEmpty()){
                //             array[current] = splitLine[i];
                //             current= current+1;
                //         }
                //     }
                //     returnCode = minHeap.load(array);
                // }
            }
            else{
                break;
            }

        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    

        System.out.println("Time To Run: "+ ((System.nanoTime()-startTime)/1000000)+" mS");
        
        // for(int i = 1; i< arg.length;i++){
        //     array[i-1] = arg[i];
        // }
        
        // System.out.println(minHeap.load(array));
    //     minHeap.insert("apple");
    //    minHeap.insert("dog");
    //    minHeap.insert("cat");
    //    minHeap.insert("bee");
    //    minHeap.insert("eagle");
    //    minHeap.insert("gorilla");
    //    minHeap.insert("fish");
    //    minHeap.insert("drongo");
    //    minHeap.insert("zztop");
    //    minHeap.insert("insect");
    //    minHeap.insert("whale");
    }
}