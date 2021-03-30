
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    static Scanner in = new Scanner(System.in);
    static int X,Y;
    static int [][] dp;
    static String str;
    static int SENT = -2_000_000_000;
    
    public static void main(String[] args) {
        int T = in.nextInt();
        for(int i = 0 ; i < T ; i++) {
            solve(i);    
        }
    }

    public static void solve(int tt){
        X = in.nextInt();
        Y = in.nextInt();
        str = in.next();
        dp = new int[2][str.length()+1];
        for ( int i = 0 ; i <dp.length; i++ ) Arrays.fill(dp[i], SENT);
        // System.out.println(String.format("Case #%S: %S", i+1,solveForMin(str, X, Y)));
        System.out.println(String.format("Case #%S: %S", tt+1,Math.min(go(0,0),go(1,0))));
    }

    public static int go(int placed, int index) {
        if (dp[placed][index]!= SENT ) return dp[placed][index];
        if((str.charAt(index) == 'C' && placed ==1) ||
        (str.charAt(index) == 'J' && placed == 0)) {
            return dp[placed][index] = (int)1e9;
        }
        if(index == str.length()-1) return 0;

        int best = (int)1e9;

        best = Math.min(best,(placed!=0?Y:0)+go(0,index+1));

        best = Math.min(best, (placed!=1?X:0)+go(1,index+1));

        return dp[placed][index]= best;

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
