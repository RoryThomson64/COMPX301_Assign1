//ID: 1522996
//Name: Rory Thomson


public class myMinHeap {

    static String[] array;
    static int heapSize;
    static int _heapCap;
    public myMinHeap(int heapCap){
        _heapCap = heapCap;
        array = new String[heapCap];
        heapSize = 0;
    }
    public static void insert(String newElement){
        if(heapSize < array.length){
            array[heapSize] = newElement;
            int index = heapSize;
            heapSize +=1;
            while(index != 0&& array[index].compareTo(array[parent(index)])< 0){
                String temp = array[parent(index)];
                swap(parent(index), index);
                index = parent(index);
            }
        }
    }
    public String remove(){
        String min = array[0];
        // System.out.println(heapSize);
        array[0] = array[heapSize-1];
        array[heapSize-1] = null;
        heapSize = heapSize-1;
        reheap(0);
        return min;
    }
    public void replace(String input){
        array[0] = input;
        reheap(0);
    }
    public String peek(){
        return array[0];
    }
    public void load(String[] loadedData){
        array = new String[_heapCap];
        heapSize = 0;
        for(int i = 0; i< loadedData.length; i++){
            if(loadedData[i]!= null){
            array[i] = loadedData[i];
            heapSize = heapSize+1;
            }
            else{
                break;
            }
        }
        for(int i = (heapSize/2); i>= 0; i--){
            reheap(i);
        }
        // verify();
        
    }
    public static void reheap(int i){
        int left = leftChild(i);
        int right = rightChild(i);
        int min = i;

        if(left < heapSize&& array[left] != null &&  array[left].compareTo(array[min]) <0){
            min = left;
        }
        if(right < heapSize &&array[right] !=null && array[right].compareTo(array[min]) <0){
            min = right;
        }
        if(min != i){
            swap(i, min);
            // System.out.println("Reheaping "+ min);
            reheap(min);
        }
        
    
    }
    public static void dump(){
        int ind = 0;
        System.out.println("<---- DUMP START ---->");
        while(ind < heapSize && array[ind] != null){
            // if(ind *2+2 < heapSize){
            System.out.println("INDEX: "+ ind);
            System.out.println("PARENT: "+ array[parent(ind)]);
            System.out.println("CURRENT: "+ array[ind]);
            if(leftChild(ind) < _heapCap){
                System.out.println("LEFT: "+ array[leftChild(ind)]);
            }
            if(rightChild(ind) < _heapCap){
                System.out.println("RIGHT: "+ array[rightChild(ind)]+"\r\n");
            }
            // }
            ind =ind+ 1 ;
        }
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
    static void verify(){
        for(int i = 0; i < heapSize; i++){
            if(leftChild(i) < heapSize){
                if(array[i].compareTo(array[leftChild(i)]) >0){
                    System.err.println("Error in heap property");
                }
            }
            if(rightChild(i) < heapSize){
                if(array[i].compareTo(array[rightChild(i)]) >0){
                    System.err.println("Error in heap property");
                }
            }
        }
    }
}