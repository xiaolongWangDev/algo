package code.string;

// a#b#a
public class Manacher {
    public int[] getPalindromeRadius(String str) {
        char[] filled = new char[2 * str.length() + 1];
        for (int i = 0; i < str.length(); i++) {
            filled[2 * i] = '#';
            filled[2 * i + 1] = str.charAt(i);
        }
        filled[2 * str.length()] = '#';
        System.out.println(filled);

        int[] radiusArr = new int[filled.length];
        int lastCenter = -1;
        int lastCenterRadius = 0;
        for (int i = 0; i < filled.length; i++) {
            if (i > rightRadiusIndex(lastCenter, lastCenterRadius)) {
                int radius = tryRadius(filled, i, 0);
                radiusArr[i] = radius;
                if (rightRadiusIndex(i, radius) > rightRadiusIndex(lastCenter, lastCenterRadius)){
                    lastCenter = i;
                    lastCenterRadius = radius;
                }
            } else {
                int mirrorIndex = 2 * lastCenter - i; // index of left mirror of point i
                if (leftRadiusIndex(mirrorIndex, radiusArr[mirrorIndex]) > leftRadiusIndex(lastCenter, lastCenterRadius)) { // small circle is inside large circle
                    radiusArr[i] = radiusArr[mirrorIndex];
                } else if (leftRadiusIndex(mirrorIndex, radiusArr[mirrorIndex]) < leftRadiusIndex(lastCenter, lastCenterRadius)) { // small circle is outside large circle
                    radiusArr[i] = mirrorIndex - leftRadiusIndex(lastCenter, lastCenterRadius);
                } else {
                    int radius = tryRadius(filled, i, rightRadiusIndex(lastCenter, lastCenterRadius) - i); // small circle touching large circle internally
                    radiusArr[i] = radius;
                    if (rightRadiusIndex(i, radius) > rightRadiusIndex(lastCenter, lastCenterRadius)){
                        lastCenter = i;
                        lastCenterRadius = radius;
                    }
                }
            }
        }

        return radiusArr;
    }

    private int tryRadius(char[] filled, int i, int initRadius) {
        int radius = initRadius;
        while (rightRadiusIndex(i, radius + 1) < filled.length
                && leftRadiusIndex(i, radius + 1) >= 0
                && filled[rightRadiusIndex(i, radius + 1)] == filled[leftRadiusIndex(i, radius + 1)]) {
            radius += 1;
        }
        return radius;
    }

    private int leftRadiusIndex(int centerIndex, int radius) {
        return centerIndex - radius;
    }

    private int rightRadiusIndex(int centerIndex, int radius) {
        return centerIndex + radius;
    }

    public static void main(String[] args) {
        Manacher algo = new Manacher();
        for (int r : algo.getPalindromeRadius("122131221")) {
            System.out.print(r);
        }
        System.out.println();
    }
}
