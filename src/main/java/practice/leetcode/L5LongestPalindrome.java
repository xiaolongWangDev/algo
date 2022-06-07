package practice.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class L5LongestPalindrome {

    /**
     * / 0 1 2 3 4 5
     * 0 T x y z a b
     * 1   T x y z a
     * 2     T x y z
     * 3       T x y
     * 4         T x
     * 5           T
     */
    public static String longestPalindromeDp(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        // just need to fill the top right triangle. go diagonal
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            if (i < s.length() - 1) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            }
        }
        for (int k = 2; k < s.length(); k++) {
            for (int l = 0; l < s.length() - k; l++) {
                dp[l][l + k] = dp[l + 1][l + k - 1] && s.charAt(l) == s.charAt(l + k);
            }
        }

        int max = 0;
        int l = -1;
        int r = -1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (dp[i][j] && max < j - i + 1) {
                    max = j - i + 1;
                    l = i;
                    r = j;
                }
            }
        }
        return s.substring(l, r + 1);
    }

    // Manacher
    public static String longestPalindrome(String s) {
        List<Character> padded = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            padded.add('#');
            padded.add(s.charAt(i));
        }
        padded.add('#');

        int[] radiusAtI = new int[padded.size()];
        int lastCenter = -1;
        int lastRadius = 0;
        for (int i = 0; i < padded.size(); i++) {
            if (i > rightPointIndex(lastCenter, lastRadius)) {
                radiusAtI[i] = maxPalindromeRadius(padded, i, 0);
                if (rightPointIndex(i, radiusAtI[i]) > rightPointIndex(lastCenter, lastRadius)) {
                    lastCenter = i;
                    lastRadius = radiusAtI[i];
                }
            } else {
                int mirrorCenter = 2 * lastCenter - i;
                int mirrorCircleRadius = radiusAtI[mirrorCenter];

                if (leftPointIndex(mirrorCenter, mirrorCircleRadius) > leftPointIndex(lastCenter, lastRadius)) {
                    // mirror circle is entirely inside the big circle
                    radiusAtI[i] = mirrorCircleRadius;
                } else if (leftPointIndex(mirrorCenter, mirrorCircleRadius) < leftPointIndex(lastCenter, lastRadius)) {
                    // part of the mirror circle is outside the big circle
                    radiusAtI[i] = mirrorCircleRadius - (leftPointIndex(lastCenter, lastRadius) - leftPointIndex(mirrorCenter, mirrorCircleRadius));
                } else {
                    // part of the mirror circle is outside the big circle
                    radiusAtI[i] = maxPalindromeRadius(padded, i, radiusAtI[2 * lastCenter - i]);
                    if (rightPointIndex(i, radiusAtI[i]) > rightPointIndex(lastCenter, lastRadius)) {
                        lastCenter = i;
                        lastRadius = radiusAtI[i];
                    }
                }
            }
        }

        int maxRadius = -1;
        int maxRadiusIndex = -1;
        for (int i = 0; i < radiusAtI.length; i++) {
            if (maxRadius < radiusAtI[i]) {
                maxRadius = radiusAtI[i];
                maxRadiusIndex = i;
            }
        }

        return padded.subList(maxRadiusIndex - maxRadius, maxRadiusIndex + maxRadius + 1).stream().filter(c -> c != '#').map(String::valueOf).collect(Collectors.joining());
    }

    private static int maxPalindromeRadius(List<Character> characters, int center, int startRadius) {
        int radius = startRadius;
        int leftPoint;
        int rightPoint;
        do {
            radius++;
            leftPoint = leftPointIndex(center, radius);
            rightPoint = rightPointIndex(center, radius);
        } while (leftPoint >= 0 && rightPoint < characters.size() && characters.get(leftPoint) == characters.get(rightPoint));
        return radius - 1;
    }

    private static int leftPointIndex(int centerIndex, int radius) {
        return centerIndex - radius;
    }

    private static int rightPointIndex(int centerIndex, int radius) {
        return centerIndex + radius;
    }

    public static void main(String[] args) {
//        System.out.println(longestPalindrome("122131221"));
//        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindromeDp("babad"));
        System.out.println(longestPalindromeDp("122131221"));
    }
}
