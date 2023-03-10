import java.util.ArrayList;
import java.util.List;

//ID: 1522996
//Name: Rory Thomson


//!!! You need to refactor alot of this code!!!
// Make sure you are properly comparing values, and not just what the key corresponds
//with on the array.


public class myMinHeap {

    static String[] array;
    static int heapSize;
    public static void main(String[] args){
        // System.out.println("Prog Frog:"+args[0]);
        int heapCap;
        try {
            heapCap = Integer.valueOf(args[0]);
            if(heapCap <= 0){
                // throw new NegativeHeapCap("Heap capacity is less than or equal to zero");
                throw new Exception("Heap capacity is less than or equal to zero");
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Expected Positive int: Setting heap Capcity to 31");
            heapCap = 31;
        }
        
       myMinHeap minHeap = new myMinHeap(heapCap);

       minHeap.insert("apple");
       minHeap.insert("dog");
       minHeap.insert("cat");
       minHeap.insert("bee");
       minHeap.insert("eagle");
       minHeap.insert("gorilla");
       minHeap.remove(0);
       minHeap.insert("fish");
       minHeap.insert("drongo");
       minHeap.insert("zztop");
       minHeap.insert("insect");
    //    minHeap.remove(10);
    minHeap.remove(2);
    minHeap.remove(6);

       minHeap.insert("whale");
       minHeap.reheap();
    //    minHeap.remove(5);


    }


    public myMinHeap(int heapCap){
        array = new String[heapCap];
        heapSize = 0;
        System.out.println(array.length);
        // System.out.println(array[0]);
    }
    public  void insert(String newElement){
        // System.out.println(array.length);
        System.out.println("Adding: "+ newElement);
    if(heapSize < array.length){
        array[heapSize] = newElement;
        int index = heapSize;
        heapSize +=1;
        
        while(index != 0&& array[index].compareTo(array[parent(index)])< 0){
            String temp = array[index-1];
            array[index-1] = array[index];
            array[index] = temp;
            swap(parent(index), index);
            // swap(index,parent(index) );
            index = index -1;
        }
        dump();
    }
    else{
        System.out.println("heap full");
        // System.out.println(heapSize);
    }
    }
    public void remove(int remIndex){
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

        decreaseKey(remIndex, Integer.MIN_VALUE);
    }
    public void replace(){

    }
    public void peek(){

    }
    public void load(){

    }
    public void reheap(){
        reheap(0);
    }
    public void reheap(int i){
        System.out.println("reheaping: "+ i);
        int left = leftChild(i);
        int right = rightChild(i);
        int min = i;
        if(left < heapSize && array[left].compareTo(array[i]) <0){
            min = left;
        }
        if(right < heapSize && array[right].compareTo(array[min]) <0){
            min = right;
        }
        if(min != i){
            swap(i, min);
            dump();
            reheap(min);
        }
    }
    public void dump(){
        int ind = 0;
        String output = "";
        while(array[ind] != null){
            // System.out.println(array[ind]);
            output = output + array[ind]+", ";
            ind += 1;
        }
        
        System.out.println(output);
    }

    int parent(int i){
        return (i-1/2);
    }
    int leftChild (int i){
        return(2*i +1);
    }
    int rightChild(int i){
        return(2*i +2);
    }
    void swap(int first, int second){
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
    String popMin(){
        String min = array[0];
        heapSize--;
        array[0] = array[heapSize];
        if(heapSize>1){
            reheap();
        }
        return min;
    }


}

// public class NegativeHeapCap extends Exception{
//     public NegativeHeapCap (String str){
//         super(str);
//     }
// }