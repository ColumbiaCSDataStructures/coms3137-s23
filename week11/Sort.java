public class Sort {

        public static void selectionSort(Integer[] arr) {
        
          int p = 0; 

          while (p < arr.length-1) {  

            int minPosition = p; 

            // N + (N-1) + (N-2) +...+ 1 = O(N^2) 

            for (int i = p; i < arr.length; i++) { 
              if (arr[i] < arr[minPosition]) 
                minPosition = i;  
            } 
            Integer tmp = arr[p];
            arr[p] = arr[minPosition]; 
            arr[minPosition] = tmp;
          
            p++;
          }
          
        }

        public static void insertionSort(Integer[] arr) {
          int p = 1; // unsorted partition starts at index p. 
          
          while (p < arr.length) {   // N times
            
            int curr = p; 
            while (curr > 0 && arr[curr-1] > arr[curr]) { // worst: O(N^2) 
              Integer tmp = arr[curr-1];                  // best: O(N)
              arr[curr-1] = arr[curr];
              arr[curr] = tmp;
              curr--;
            }
            p++;
          }
        } 


        public static void merge(Integer[] arr, int aCtr, int bCtr, int rightEnd) {

        }

        public static void mergeSort(Integer[] arr) {
          mergeSortRecursive(arr, 0, arr.length-1);
        }

        public static void mergeSortRecursive(Integer[] arr, int left, int right) {

        }


        public static void main(String[] args) {
            
            Integer[] test = {1,7,3,2,9,8,4,5,6};
                
            insertionSort(test);
            
            for (Integer i :test) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();

            //MERGE TEST CASE
            /*Integer[] test = {1,3,4,7,  2,5,9,12};
            merge(test,0,4,7);
            
            for (Integer i :test) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
            */
            
        }
}
