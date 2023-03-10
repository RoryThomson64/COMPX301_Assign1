//ID: 1522996
//Name: Rory Thomson

public class myMinHeap {
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
    }
    public myMinHeap(int heapCap){

    }
    public static void insert(){

    }
    public static void remove(){

    }
    public static void replace(){

    }
    public static void peek(){

    }
    public static void load(){

    }
    public static void rehead(){

    }

}

// public class NegativeHeapCap extends Exception{
//     public NegativeHeapCap (String str){
//         super(str);
//     }
// }