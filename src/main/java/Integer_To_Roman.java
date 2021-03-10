import java.util.HashMap;
import java.util.Map;

/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/03/09 11:37
 * @description:
 */
public class Integer_To_Roman {
    private static int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public static Map<String,Integer> romanToIntegerMap ;
    static {
        romanToIntegerMap = new HashMap<>();
        for(int i=0;i<symbols.length;i++){
            romanToIntegerMap.put(symbols[i],values[i]);
        }
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num >= 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
    public int getNext(String str,int start,int skip){
        String sub = str.substring(start,start+skip);
        return romanToIntegerMap.get(sub);
    }

    public int romanToInt(String str) {
        int result = 0;

        for (int i = 0; i < str.length();) {

            if (i + 1 >= str.length()) {
                //已经走到末尾，则累加最后一位数字
                result = result + getNext(str,i,1);
                return result;
            } else if (i + 2 <= str.length()) {
                String sub = str.substring(i, i + 2);
                if (romanToIntegerMap.keySet().contains(sub) && getNext(str,i,2) > getNext(str,i,1)) {
                    result = result + getNext(str,i,2);
                    i = i + 2;
                } else {

                    result = result + getNext(str,i,1);
                    i = i + 1;
                }

            } else {
                result = result + getNext(str,i,1);
                i = i + 1;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        /*System.out.println(values.length == symbols.length);
        Integer_To_Roman integerToRoman = new Integer_To_Roman();
        System.out.println(integerToRoman.intToRoman(3100));
        System.out.println(integerToRoman.romanToInt("MMMC"));
        System.out.println(integerToRoman.romanToInt("IV"));*/
        String str = "112s";
        System.out.println(str.substring(0,4));

    }
}
