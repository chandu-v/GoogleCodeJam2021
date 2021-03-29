
import java.util.Scanner;

class Solution {
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        int T = in.nextInt();
        for(int i = 0 ; i < T ; i++) {
            solve(i);    
        }
    }

    public static void solve(int i){
        int X = in.nextInt();
        int Y = in.nextInt();
        String str = in.next();
        System.out.println(String.format("Case #%S: %S", i+1,solveForMin2(str, X, Y)));
    }

    public static int solveForMin2(String str, int X , int Y) {
        int last_c = 0;
        int last_j = 0;
        final int INF = Integer.MAX_VALUE;
        for(int i = 0 ; i < str.length() ; i++) {
            int new_last_c = INF-X;
            int new_last_j = INF-Y;
            if(str.charAt(i) == '?' || str.charAt(i) == 'C') {
                new_last_c = Math.min(last_c, last_j+Y);
            }
            if(str.charAt(i) == '?' || str.charAt(i) == 'J') {
                new_last_j = Math.min(last_j, last_c+X);
            }
            last_c = new_last_c;
            last_j = new_last_j;
        }
        return Math.min(last_c, last_j);
    }
    public static int solveForMin(String str,int X, int Y) {
        if(!str.contains("?")) {
            int cost = calculateCost(str, X, Y);
            return cost;
        }
        String s1 = str.replaceFirst("\\?", "C");
        String s2 = str.replaceFirst("\\?", "J");
        return Integer.min(
            solveForMin(s1,X,Y), 
            solveForMin(s2,X,Y));
    }

    public static int calculateCost(String str,int X, int Y) {
        int xCount = numberOfOccurences(str, "CJ");
        int yCount = numberOfOccurences(str, "JC");
        if(xCount == 0 && yCount == 0) return 0;
        else if (xCount == 0) return ((yCount) * Y);
        else if (yCount == 0) return ((xCount) * X);
        else return ((yCount) * Y) + ((xCount) * X);
    }

    public static int numberOfOccurences(String str, String sub) {
        int index = str.indexOf(sub);
        int count = 0;
        while(index != -1) {
            count++;
            str = str.substring(index+1);
            index = str.indexOf(sub);
        }
        return count;
    }
}
