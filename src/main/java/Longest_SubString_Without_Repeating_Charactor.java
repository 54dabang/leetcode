import org.junit.Test;

import java.util.Stack;

/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/02/19 13:35
 * @description:
 * 该解答方式为O(n2)，主要使用的方式为拓展法，先将字符串的中间和两边位置都统一加入分隔符 '#'，然后从第一个位置开始向后拓展。
 * 一开始记录位置为0，最大扩展长度为0，如果新拓展的位置和长度已经大于当前最大拓展长度，则进行替换
 * eg:
 * #a#b#a#
 * #a#b#b#a#
 *
 */
public class Longest_SubString_Without_Repeating_Charactor {
    public String longestPalindrome(String s) {
        char[] expandedArray = new char[2 * s.length() + 1];//扩容后的数组
        int j = 0;
        for (int i = 0; i < s.length() && j< expandedArray.length; i++) {
            expandedArray[j] = '#';
            j++;
            expandedArray[j] = s.charAt(i);
            j++;
        }
        expandedArray[j] = '#';
        int longestLocation = 0;//最长回文串的中心位置
        int longestLength = 0; //最长回文串的长度
        for (int k = 0; k < expandedArray.length; k++) {
            int sideLength = 0;
            int lastSideLength = 0;
            while (k - sideLength >= 0 && k + sideLength < expandedArray.length) {
                if (expandedArray[k - sideLength] == expandedArray[k + sideLength]) {
                    lastSideLength = sideLength;
                    sideLength++;
                } else {
                    break;
                }

            }
            if (lastSideLength > longestLength) {
                longestLength = lastSideLength;
                longestLocation = k;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = -1 * longestLength; i <= longestLength; i++) {
            if (expandedArray[longestLocation + i] != '#') {
                sb.append(expandedArray[longestLocation + i]);

            }
        }

        return sb.toString();
    }

    @Test
    public void testLongestPalindrome() {
        System.out.println(longestPalindrome("abb"));
    }
}
