package helper;

public class NullSafe {
    public static Integer nullSafeAdd(Integer... vals) {
        int sum = 0;
        for (Integer val : vals) {
            if (val == null) return null;
            sum += val;
        }
        return sum;
    }

    public static Integer nullSafeMinOf(Integer... vals) {
        Integer i = null;
        for (Integer val : vals) {
            if (val == null) continue;
            if (i == null) {
                i = val;
            } else {
                i = Math.min(i, val);
            }
        }
        return i;
    }

    public static int nullSafeMaxOf(Integer... vals) {
        Integer i = null;
        for (Integer val : vals) {
            if (val == null) continue;
            if (i == null) {
                i = val;
            } else {
                i = Math.max(i, val);

            }
        }
        return i;
    }
}
