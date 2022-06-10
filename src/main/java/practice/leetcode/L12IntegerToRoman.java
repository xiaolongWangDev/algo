package practice.leetcode;

import java.util.List;

public class L12IntegerToRoman {
    private static class StrAndValue {
        String str;
        int val;
        boolean multiple;

        public StrAndValue(String str, int val, boolean multiple) {
            this.str = str;
            this.val = val;
            this.multiple = multiple;
        }
    }
    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        List<StrAndValue> strAndValues = List.of(
                new StrAndValue("M", 1000, true),
                new StrAndValue("CM", 900, false),
                new StrAndValue("D", 500, false),
                new StrAndValue("CD", 400, false),
                new StrAndValue("C", 100, true),
                new StrAndValue("XC", 90, false),
                new StrAndValue("L", 50, false),
                new StrAndValue("XL", 40, false),
                new StrAndValue("X", 10, true),
                new StrAndValue("IX", 9, false),
                new StrAndValue("V", 5, false),
                new StrAndValue("IV", 4, false),
                new StrAndValue("I", 1, true)
        );
        for(StrAndValue sv : strAndValues){
            if(sv.multiple) {
                while(num >= sv.val){
                    sb.append(sv.str);
                    num -= sv.val;
                }
            } else {
                if(num >= sv.val){
                    sb.append(sv.str);
                    num -= sv.val;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(1997));
    }
}
