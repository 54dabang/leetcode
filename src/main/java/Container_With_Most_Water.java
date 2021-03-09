import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/03/09 09:18
 * @description: 解题思路 双指针规则
 * 假如a[i]和a[j]分别位于两边位置，a[i]<a[j]。此时只有移动左边的i,才有可能面积增大。如果将a[j]向左移动
 * 无论a[j]有多大，都没有用，因为短板是a[i],每次取到的高智能是a[i].同理由于对称性，当a[i]>a[j]时，j向左移动知道i==j结束。
 * 并取得最大值
 */
public class Container_With_Most_Water {
    public int maxArea2(int[] height) {
        if (height.length == 0 || height.length == 1) {
            return 0;
        }
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = getArea(i, j, height);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
     return maxArea;
    }

    public int maxArea(int []height){
        if (height.length == 0 || height.length == 1) {
            return 0;
        }
        int maxArea = 0;
        int i=0,j=height.length-1;
        while (i != j){
            int area = getArea(i,j,height);
            maxArea = Math.max(maxArea,area);
            if(height[i]<height[j]){
                i++;
            }else {
                j--;
            }
        }
        return maxArea;
    }

    public int getArea(int i, int j, int[] height) {
        int width = Math.abs(j - i);
        int h = Math.min(height[j] ,height[i]);
        return width * h;
    }

    public static void main(String[] args) {
        Container_With_Most_Water c = new Container_With_Most_Water();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(c.maxArea2(height));
        System.out.println(c.maxArea(height));
    }
}
