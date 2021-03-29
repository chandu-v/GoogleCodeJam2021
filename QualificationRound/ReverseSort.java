
import java.util.Arrays;
import java.util.Scanner;

class SolutionReverseSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int t = 0 ; t < T ; t++) {
            int n = in.nextInt();
            int [] arr = new int[n];
            for(int i = 0 ; i < n ; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(String.format("Case #%S: %S", t+1 , solve(arr)));
        }
        
    }
    
    static int findMinElementIndex(int [] arr, int start, int end) {
        int minIndex = start;
        int minElement = Integer.MAX_VALUE;
        for(int i = start ; i <= end ; i++) {
            if(arr[i] < minElement) {
                minElement = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static void printArray(int [] arr) {
        for(int i : arr) {
            System.out.print(i+",");
        }
        System.out.println();
    }

    static int [] reverse (int [] arr, int start , int end) {
        int []result = new int[arr.length];
        int j = end;
        for(int i = 0 ; i < arr.length ; i++) {
            result[i] = arr[i];
        }
        for(int i = start ; i <= end ; i++) {
            result[i] = arr[j];
            j--;
        }
        return result;
    }
    static int solve(int [] arr) {
        int cost = 0 ;
        // printArray(arr);
        for(int i = 0 ; i < arr.length-1 ; i++) {
            int j = findMinElementIndex(arr, i, arr.length-1);
            cost += j-i+1;
            arr = reverse(arr, i, j);
            // printArray(arr);
        }
        return cost;
    }
}