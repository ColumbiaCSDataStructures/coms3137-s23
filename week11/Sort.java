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


        public static void merge(Integer[] arr, Integer[] tmp,  int aCtr, int bCtr, int rightEnd) {
          
          // aCtr: beginning of left partition
          // bCtr: beginning of right partition 
          // rightEnd: end of the right partition 
          int leftEnd = bCtr -1; // end of the left partition
          int numElements = rightEnd - aCtr + 1;  
          
          int cCtr = aCtr;

          while (aCtr <= leftEnd && bCtr <= rightEnd) {
            if (arr[aCtr] <= arr[bCtr]) {
              tmp[cCtr++] = arr[aCtr++];
            } else {
              tmp[cCtr++] = arr[bCtr++];
            }
          }          
         
          // only one of the following while loops will run 
          // copy remaining elements from left partition
          while (aCtr <= leftEnd) 
            tmp[cCtr++] = arr[aCtr++];
          // copy remaining elements from left partition
          while (bCtr <= rightEnd) 
            tmp[cCtr++] = arr[bCtr++];

          for (int i = 0; i < numElements; i ++) {
            arr[rightEnd] = tmp[rightEnd];
            rightEnd--;
          } 

        }

        public static void mergeSort(Integer[] arr) {
          Integer[] tmp = new Integer[arr.length];
          mergeSortRecursive(arr, tmp, 0, arr.length-1);
        }

        public static void mergeSortRecursive(Integer[] arr, Integer[] tmp, int left, int right) {
          if (left < right) {
            int center = (left + right) / 2; 
            mergeSortRecursive(arr, tmp, left, center);
            mergeSortRecursive(arr, tmp, center + 1, right);  
            merge(arr, tmp, left, center + 1, right);
          }
        }


        public static void main(String[] args) {
            
            /*Integer[] test = {1,7,3,2,9,8,4,5,6};
                
            insertionSort(test);
            
            for (Integer i :test) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();*/

            //MERGE TEST CASE
            Integer[] test = {1,3,4,7,2,5,9,12,0,4,3,2,6};
            //merge(test,2,6,9);
            mergeSort(test);           
 
            for (Integer i :test) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
            
        }
}
