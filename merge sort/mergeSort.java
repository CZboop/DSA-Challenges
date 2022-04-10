import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
//        example of running the sort
//        prints before and after sorting
        int[] testArray = createArray(20, 50);
        System.out.println(Arrays.toString(testArray));
        sort(testArray);
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

//    sorts given array in place with nothing returned
    public static void sort(int[] array){
//        base case - one element, already sorted
        if (array.length < 2){
            return;
        }
        else {
            int mid = array.length / 2; // this will round down floats
            int[] leftArray = new int[mid];
            int[] rightArray = new int[array.length - mid]; // account for odd lens

            for (int i=0; i < mid; i++){
                leftArray[i] = array[i];
            }

            for (int i=0; i < array.length - mid; i++){
                rightArray[i] = array[mid + i];
            }

//            recursive bit, call main sort method on each half
            sort(leftArray);
            sort(rightArray);

//            merge bit, set up incrementer to track where in each array got up to
            int left_counter = 0, right_counter = 0, array_counter = 0;

//            while neither half out of elements
            while (left_counter < leftArray.length && right_counter < rightArray.length) {
//                add the smallest element to current point in original array
                if (rightArray[right_counter] > leftArray[left_counter]){
                    array[array_counter] = leftArray[left_counter];
                    left_counter += 1;
                } else {
                    array[array_counter] = rightArray[right_counter];
                    right_counter += 1;
                }
//                increment the index of the half that lost and element above
//                and the index of the main array below
                array_counter+=1;
            }
//            uneven elements cleanup
//            if one half all added to original, can just add the other to the original array
//            since each half already sorted and above evaluation was to merge back in sorted order
            while (left_counter < leftArray.length){
                array[array_counter] = leftArray[left_counter];
                left_counter += 1;
                array_counter += 1;
            }

            while (right_counter < rightArray.length){
                array[array_counter] = rightArray[right_counter];
                right_counter += 1;
                array_counter += 1;
            }
        }
    }
}
