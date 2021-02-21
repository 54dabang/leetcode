/**
 * Created by leixingbang on 2021/2/21.
 */
public class Reverse_Integer {
    public int reverse(int x) {
        Long longX = Long.valueOf(x) ;
        boolean isfushu = longX <  0;


     String s = isfushu ? String.valueOf(-1*longX) :String.valueOf(longX);
     StringBuilder sb = new StringBuilder();
        for(int i = s.length() -1;i>=0;i--){
         sb.append(s.charAt(i));
     }
        Long longValue = Long.parseLong(sb.toString());
        if(longValue>Integer.MAX_VALUE){
            return 0;
        }else {
            return isfushu ? -1*Integer.parseInt(sb.toString()):Integer.parseInt(sb.toString());
        }
    }

    public static void main(String[] args) {
        Reverse_Integer reverse = new Reverse_Integer();
        System.out.println( reverse.reverse(-2147483648));
        System.out.println(8/10);
        System.out.println(9/10);
    }
}
