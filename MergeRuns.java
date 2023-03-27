import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

public class MergeRuns {
    static int numFiles;
    public static void main(String[] arg){
        numFiles =Integer.parseInt(arg[0]);
        new DistributeRuns(numFiles);
        mergeFiles();

    }
    static String[] merge(String[] array,int left,int mid,int right){
        int leftArrayLen = mid - left;
        int rightArrayLen = right - mid;
        
        String[] leftArray = new String[leftArrayLen];
        String[] rightArray = new String[rightArrayLen];

        for(int i =0; i< leftArrayLen; i++){
            leftArray[i] = array[left+i];
        }
        for(int i = 0; i< rightArrayLen; i++){
            rightArray[i] = array[mid + i];
        }


        int leftArrayIndex =0;
        int rightArrayIndex=0;

        int arrayIndex = left;
        while(leftArrayIndex < leftArrayLen && rightArrayIndex < rightArrayLen){
            if(leftArray[leftArrayIndex].compareTo(rightArray[rightArrayIndex]) <=0){
                array[arrayIndex] = leftArray[leftArrayIndex];
                leftArrayIndex++;
            }
            else{
                array[arrayIndex] =rightArray[rightArrayIndex];
                rightArrayIndex++;
            }
            arrayIndex++;
        }

        while(leftArrayIndex < leftArrayLen){
            array[arrayIndex] = leftArray[leftArrayIndex];
            leftArrayIndex++;
            arrayIndex++;
        }

        while(rightArrayIndex < rightArrayLen){
            array[arrayIndex] = rightArray[rightArrayIndex];
            rightArrayIndex++;
            arrayIndex++;
        }
        return array;
    }

    static String[] mergeSort(String[] array, int left, int right){
        // System.err.println(Integer.toString(left) +" "+ Integer.toString(right));
        if(left < right){
            int mid = (left +(right-1))/2;
            array = mergeSort(array, left, mid);
            array =mergeSort(array, mid+1, right);

            array =merge(array, left, mid, right);
        }
        return array;
    }

    static void mergeFiles(){
        BufferedReader[] readers = new BufferedReader[numFiles];
        Writer[] writers= new Writer[numFiles];  
        String[][] splitLines = new String[numFiles][];
        int[] counts =new int[numFiles];
        int longestLine =0;
        int completeFiles =0;
        for(int i =0; i< numFiles; i++){
            try{
            readers[i] = new BufferedReader(new FileReader(i+".runs"));
            writers[i]= new FileWriter((numFiles+1)+".runs",true);
            counts[i] = 0;
            splitLines[i] = readers[i].readLine().split("///");
            }
            catch(Exception e){
                System.err.println("First: Error while trying to access file "+i+".runs");
                e.printStackTrace();

            }
        }

        int currentOutputFile=0;
        while(true){
            if(counts[0]>0){
                break;
            }
            String min = splitLines[0][counts[0]];
            int minRun = 0;
            boolean finishedRun = false;
            
            while(!finishedRun){
                for(int i =0; i < numFiles; i++){
                    if(counts[i]<splitLines[i].length){
                        if(splitLines[i][counts[i]].compareTo(min) <0){
                            min = splitLines[i][counts[i]];
                            minRun = i;
                        }
                    }
                }
                // System.err.println(min);
                try{
                    writers[currentOutputFile].write(min+"\r\n");
                }
                catch(Exception e){
                    System.err.println("Error while writing to "+(currentOutputFile+numFiles)+".runs");
                    e.printStackTrace();
                }

                
                counts[minRun] = counts[minRun] +1;
                finishedRun = true;
                for(int i =0; i< numFiles; i++){
                    // System.err.println("Count "+i+": "+counts[i]);
                    if(counts[i]<splitLines[i].length){
                        min = splitLines[i][counts[i]];
                        minRun =i;
                        finishedRun = false;
                    }
                }
            }
            currentOutputFile++;
            if(currentOutputFile >= numFiles){
                currentOutputFile =0;
            }
            for(int i =0; i< numFiles - completeFiles; i++){
                try{
                    counts[i] = 0;
                    String line = readers[i].readLine();
                    if(line != null){
                        splitLines[i] = line.split("///");
                    }
                    else{
                        completeFiles++;
                    }
                    
                }
                catch(Exception e){
                    System.err.println("Second: Error while trying to access file "+i+".runs");
                    e.printStackTrace();
                    return;
                }
            }
            if(splitLines[numFiles-1].length <1){
                break;
            }
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