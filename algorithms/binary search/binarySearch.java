import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
    public static void main(String[] args) {
//        creating and sorting a random array
        int len = 100;
        int bound = 1000;
        Random rand = new Random();

        int[] exampleArray = new int[len];
        for (int i = 0; i < len; i++ ){
            exampleArray[i] = rand.nextInt(bound);
        }
        Arrays.sort(exampleArray);
//        getting target element and it's index to check method
        int targetIndex = rand.nextInt(len);
        int target = exampleArray[targetIndex];
//printing array
        System.out.println(Arrays.toString(exampleArray));
//        running method
        int indexFound = binarySearch(exampleArray, target);
        System.out.println(indexFound);
//        printing boolean whether index is what expected
//        random may create duplicates that mess with this quick check of index, and may be edge cases missed
        System.out.println(indexFound == targetIndex);

    }

//
    public static int binarySearch(int[] toSearch, int toFind){
//        manual binary search - track the indexes that are the upper and lower bounds you are searching, and half each time
//        keep half depending on whether middle is higher or lower than the target
        int lower = 0;
        int upper = toSearch.length-1;

        while (lower <= upper){
//            getting index to split at, halfway point
            int split = lower + ((upper - lower) / 2);
            if (toSearch[split] == toFind){
//                if found return index
                return split;
            } else if (toSearch[split] > toFind){
//                if the midpoint is higher than the target number, can discard the upper half by changing the bounds
//                since sorted the target is definitely still in our new search area
                upper = split -1;
//                don't need the if? but for clarity
            } else if (toSearch[split] < toFind){
//                same again here the midpoint must have been lower than/before the target
//                so make the new search area halved in the direction of the end of the array
                lower = split +1;
            }
        }
        return -1; // if not found
    }
}