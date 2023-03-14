import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateRuns {
    //Need to add writing to files, find a way to check the validity of Output
    public static void main(String[]arg){
        myMinHeap minHeap = new myMinHeap(Integer.parseInt(arg[0]));
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        String[] array= new String[Integer.parseInt(arg[0])];
        int returnCode = -1;
        int current =0;
        try {
            while(true){
            String line = buffReader.readLine();
            if(line != null){
                current =0;
                String[] splitLine = line.split(" ");
                for(int i = 0; i< splitLine.length; i++){
                    array[current] = splitLine[i];
                    current= current+1;
                }
                // System.out.println("LINE: "+line);
                returnCode = minHeap.load(array);
                System.out.println(returnCode);
                if(returnCode > -1){
                    for(int j = 0; j< array.length-1 ; j++){
                        System.out.println("removeing");
                        minHeap.remove();
                    }
                    current = 0;
                    for(int i = returnCode; i< splitLine.length; i++){
                        array[current] = splitLine[i];
                        current= current+1;
                        returnCode = minHeap.load(array);
                    }
                }
            }
            else{
                break;
            }

        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        
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