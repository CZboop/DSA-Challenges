import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
//        example of running the sort
//        prints before and after sorting
        int[] testArray = createArray(20, 500);
        System.out.println(Arrays.toString(testArray));
        quickSort(testArray, 0, testArray.length - 1);
        System.out.println(Arrays.toString(testArray));
    }

    //    setup method to create random array of ints with given length and upper limit for each element
    public static int[] createArray(int len, int randomBound){
        Random rand = new Random();

        int[] newArray = new int[len];
        for (int i=0; i< len; i++){
            newArray[i] = rand.nextInt(randomBound) + 1;
        }
        return newArray;
    }

    public static void quickSort(int[] arr, int start, int stop){
//        another way of representing same base case as merge sort - array of length 1
//        so else is the base case
        if (start < stop){
//            setting up some pointers to increment and decrement from start and end of array/segment
            int startIndex = start, stopIndex = stop;

//            setting pivot as variable despite having set index for it for ease of reading/comparison
            int pivot = arr[stop];

//            while pointers not overlapped
            while (startIndex < stopIndex) {
//                and value of elements as would want relative to pivot (left less than or equal, right greater or equal)
//                increment or decrement both pointers
                while (arr[startIndex] <= pivot && startIndex < stopIndex) {
                    startIndex += 1;
                }
                while (arr[stopIndex] >= pivot && startIndex < stopIndex) {
                    stopIndex -= 1;
                }
//                then swap at pointer positions, will now be the first non-sorted index for each
                swapElements(arr, startIndex, stopIndex);
            }

//            swapping the left element at pointer with the pivot once the pointers meet
            swapElements(arr, startIndex, stop);

//            recursively sorting each portion
            quickSort(arr, start, stopIndex - 1);
            quickSort(arr, startIndex + 1, stop);

        }
//        base case to end sort
        else {
            return;
        }
    }

//    reusable method to swap two elements in place in the given array
    public static void swapElements(int[] arr, int firstIndex, int secondIndex){
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}