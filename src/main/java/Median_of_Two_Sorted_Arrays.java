import org.junit.Test;

/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/02/16 16:10
 * @description: 找到两个有序数组之间的中位数
 * 看到时间复杂度为Olog(m+n)，首先应该想到二分。
 * 此类型的题目可以归纳为两个有序数组，求第k大的数。第k大的数的特点是，在一个完整有序数组中，大于前k/2 -1 个数，小于后k/2+1个数。
 * 则我们可以先对只有一个数组的情况下来区分一下条件。在只有一个有序数组的情况下，如果数组的长度是奇数，eg:{1,2,3}
 * 则其中位数的位置为(a.length+1)/2
 * 对于有偶数个数字的数组来说，eg:{1,2,3,4}其中位的位置为 （a.length+1）/2 和 （a.length+2）/2 注意这里取了一个小技巧
 * 即使数字为奇数个，我们也可以认为（a.length+1）/2和（a.length+2）/2的位置求和取中位数仍然是其本身。
 *
 * 假定k定义为在数组a和数组b中的第k位数
 * 当数字 a[k/2] 小于 b[k/2]时，则数组a的前k/2个数可以排除（寻找的是第k位数 可以确定小于第k/2的前几位数字中一定不包含第k位数）
 * 特殊情况下，当数组a[]出现越界时，我们不能确定中位数是否在数组a[]当中（数组a可能会有比较大的数），但是能够确定肯定不在数组b的前k/2位中
 * 在越界时，因此可以将数组b的前k/2位移除。
 *
 *
 *
 */
public class Median_of_Two_Sorted_Arrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int sum = findKth(nums1,0,nums2,0,(m+n+1)/2)+findKth(nums1,0,nums2,0,(m+n+2)/2);
        return sum/2.0;

    }

    /**
     * 在两个数组中寻找第k大的数
     * @param num1
     * @param start1
     * @param num2
     * @param start2
     * @param k
     * @return
     */
    public int findKth(int[]num1,int start1,int[]num2,int start2,int k){
      //说明第一个数组已经为空数组
      if(start1 >= num1.length){
          return num2[start2+k-1];
      }
      if(start2 >= num2.length){
          return num1[start1+k-1];
      }
      if(k == 1){
          return Math.min(num1[start1],num2[start2]);
      }
      //当已经越界的情况下，需要强制在已经越界的数组中继续查找
      Integer midValue1 = start1 + k/2 -1 >= num1.length ?  Integer.MAX_VALUE : num1[start1 + k/2 -1];
       Integer midValue2 = start2 + k/2 -1 >= num2.length ?  Integer.MAX_VALUE : num2[start2 + k/2 -1];
      if(midValue1 < midValue2){
          return findKth(num1,start1+k/2,num2,start2,k - k/2);
      }else {
          return findKth(num1,start1,num2,start2+k/2,k- k/2);
      }
    }


    @Test
    public void findMedianSortedArrays() {
        int []num1 = {1,3};
        int []num2 = {2,6,7,9};
        System.out.println(findKth(num1,0,num2,0,2));
        System.out.println(findMedianSortedArrays(num1,num2));
    }
}
