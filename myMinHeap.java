import java.util.ArrayList;
import java.util.List;

//ID: 1522996
//Name: Rory Thomson





public class myMinHeap {

    static String[] array;
    static int heapSize;
    // public static void main(String[] args){
    //     // System.out.println("Prog Frog:"+args[0]);
    //     int heapCap;
    //     try {
    //         heapCap = Integer.valueOf(args[0]);
    //         if(heapCap <= 0){
    //             // throw new NegativeHeapCap("Heap capacity is less than or equal to zero");
    //             throw new Exception("Heap capacity is less than or equal to zero");
    //         }
    //     } catch (Exception e) {
    //         System.out.println(e);
    //         System.out.println("Expected Positive int: Setting heap Capcity to 31");
    //         heapCap = 31;
    //     }
        
    //    myMinHeap minHeap = new myMinHeap(heapCap);

       
    // //    minHeap.reheap();
    //     minHeap.remove();



    // }


    public myMinHeap(int heapCap){
        array = new String[heapCap];
        heapSize = 0;
        // System.out.println(array.length);
        // System.out.println(array[0]);
    }
    public static void insert(String newElement){
        // System.out.println(array.length);
        System.out.println("Adding: "+ newElement);
        if(heapSize < array.length){
            array[heapSize] = newElement;
            int index = heapSize;
            heapSize +=1;
            // System.out.println(array[parent(index)]);
            while(index != 0&& array[index].compareTo(array[parent(index)])< 0){
                 // String temp = array[index-1];
                String temp = array[parent(index)];
                // System.out.println(temp+" is less than "+ array[index]);
                // array[index-1] = array[index];
                // array[parent(index)] = array[index];
                // array[index] = temp;
                swap(parent(index), index);
                // swap(index,parent(index) );
                // index = index -1;
                index = parent(index);
        }
        // dump();
        }
        else{
            System.out.println("heap full");
            // System.out.println(heapSize);
        }
    }
    public String remove(){
        // if(array[remIndex] == null){
        //     System.out.println("Tryed to remove: "+ remIndex+" Found Null");
        //     return;
        // }
        // System.out.println("Removing: "+ array[remIndex]+" At: "+remIndex);
        // // array[remIndex] = null;
        // int increment = 0;
        // while(array[remIndex+ increment] != null){
        //     array[remIndex+ increment] = array[remIndex+ increment+1];
        //     increment +=1;
        // }
        // heapSize -= 1;
        // dump();

        // decreaseKey(remIndex, Integer.MIN_VALUE);
        
        String min = array[0];
        // System.out.println("Removing "+ min);
        array[0] = array[heapSize-1];
        array[heapSize-1] = null;
        heapSize = heapSize-1;
        dump();
        reheap();
        
        return min;
    }
    public void replace(){

    }
    public String peek(){
        return array[0];
    }
    public static int load(String[] loadedData){
        // System.out.println(loadedData[0]);
        // System.out.println(heapSize);
        for(int i = 0; i< loadedData.length; i++){
            String temp = loadedData[i];
            if(temp != null){
                System.out.println(loadedData[i]);
                myMinHeap.insert(loadedData[i]);
                if(heapSize >= array.length){
                 return i;
                }
            }
        }
        
        // heapSize = heapSize + loadedData.length;
        // array=loadedData.clone();
        // System.out.println("Length: "+array.length);
        reheap();
        dump();
        return -1;
    }
    public static void reheap(){
        reheap(0);
    }
    public static void reheap(int i){
        // System.out.println("reheaping: "+ i);
        int left = leftChild(i);
        int right = rightChild(i);
        int min = i;
        // System.out.println(min);
        // System.out.println(left);
        // System.out.println(right);
        // System.out.println(heapSize);
        // System.out.println(array[min]);
        if(left< heapSize){
        if(array[left] != null && left < heapSize && array[left].compareTo(array[min]) <0){
            min = left;
        }
        System.out.println(array[min]);
        if(array[right] !=null &&right < heapSize && array[right].compareTo(array[min]) <0){
            min = right;
        }
        if(min != i){
            swap(i, min);
            // dump();
            reheap(min);
        }
    }
    }
    public static void dump(){
        int ind = 0;
        // String output = "";
        // while(array[ind] != null){
        //     // System.out.println(array[ind]);
        //     output = output + array[ind]+", ";
        //     ind += 1;
        // }
        System.out.println("<---- DUMP START ---->");
        while(ind < heapSize && array[ind] != null){
            if(ind *2+2 < heapSize){
            System.out.println("INDEX: "+ ind);
            System.out.println("PARENT: "+ array[parent(ind)]);
            System.out.println("CURRENT: "+ array[ind]);
            System.out.println("LEFT: "+ array[leftChild(ind)]);
            System.out.println("RIGHT: "+ array[rightChild(ind)]+"\r\n");
            }
            ind =ind+ 1 ;
        }
        
        // System.out.println(output);
    }

    static int parent(int i){
        return ((i-1)/2);
    }
    static int leftChild (int i){
        return(2*i +1);
    }
    static int rightChild(int i){
        return(2*i +2);
    }
    static void  swap(int first, int second){
        String temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
    void decreaseKey(int i, int newI){
        array[i] = array[newI];
        while( i!= 0 && array[parent(i)].compareTo(array[i]) > 0){
            swap(i, parent(i));
            i = parent(i);
        }
    }


}

// public class NegativeHeapCap extends Exception{
//     public NegativeHeapCap (String str){
//         super(str);
//     }
// }