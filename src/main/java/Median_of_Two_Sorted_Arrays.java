/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/02/16 16:10
 * @description: 找到两个有序数组之间的中位数
 * 看到时间复杂度为Olog(m+n)，首先应该想到二分。
 * 此类型的题目可以归纳为两个有序数组，求第k大的数。第k大的数的特点是，在一个完整有序数组中，大于前k/2 -1 个数，小于后k/2+1个数。
 * 则我们可以先对只有一个数组的情况下来区分一下条件。在只有一个有序数组的情况下，如果数组的长度是奇数，eg:{1,2,3}
 * 则其中位数的位置为(a.length+1)/2
 * 对于有偶数个数字的数组来说，eg:{1,2,3,4}其中位的位置为 （a.length+1）/2 和 （a.length+2）/2 注意这里取了一个小技巧
 *
 */
public class Median_of_Two_Sorted_Arrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 2d;
    }
}
