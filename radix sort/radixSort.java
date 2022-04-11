import java.util.Arrays;
import java.util.Random;

public class RadixSort {
    public static void main(String[] args) {
//        example of running the sort
//        prints before and after sorting
        int[] testArray = createArray(20, 500);
        System.out.println(Arrays.toString(testArray));
        radixSort(testArray);
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

//    main sort method
    public static void radixSort(int[] arr){
//        need to get max number of digits or otherwise know when to stop trying to get next multiple of ten
        int maxLen = getMaxLen(arr);
        for (int i = 0; i < maxLen; i++){
//            initialising buckets for each stage of sorting, nested list where index will match remainder basically
            Integer[][] buckets = new Integer[10][arr.length];
            for (int itemInd = 0; itemInd < arr.length; itemInd++){
//                getting the bucket that this item will fall under
//                using modulo 10 from number divided by 10 ** i to get digit value working back from end of the number
                int nestedInd = (int) Math.floor( arr[itemInd] / Math.pow(10,  i) ) % 10;
//                adding to first available index in nested list found above
                for (int x = 0; x < arr.length; x++){
                    if (buckets[nestedInd][x] == null){
                        buckets[nestedInd][x] = arr[itemInd];
                        break; // need to break so only adds once
                    }
                }
            }
//            setting part sorted buckets back into one array
            int indexCounter = 0; // to use as index back in the original array
//            just looping through the buckets and retrieving any int values
            for (int n = 0; n < 10; n++){
                for (int j = 0; j < arr.length; j++){
                    if (buckets[n][j] != null){
//                        adding back into array in this order will be part sorted as per counting sort
                        arr[indexCounter] = buckets[n][j];
                        indexCounter ++;
                    }
                }
            }
        }
    }


//    getting number of digits in the longest number in the array, to not have more of the main loops than needed
    public static int getMaxLen(int[] arr){
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++){
            int currentLen = Integer.toString(arr[i]).length();
            if (currentLen > maxLen){
                maxLen = currentLen;
            }
        }
        return maxLen;
    }
}
