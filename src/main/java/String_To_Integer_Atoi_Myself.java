import java.util.*;

/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/02/25 20:51
 * @description:
 */
public class String_To_Integer_Atoi_Myself {
    public int myAtoi(String str) {
        MyAutomaton automaton = new MyAutomaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.put(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }
    public class MyAutomaton{
        private int sign = 1;//默认为正数
        private long ans = 0;//结束状态或者其他 均为0

        private String state = "start";

        public  Map<String, List<String>> stateTable = new HashMap<String, List<String>>(){
            {
                put("start", Arrays.asList("start","signed","number","end"));
                put("signed",Arrays.asList("end","end","number","end"));
                put("number",Arrays.asList("end","end","number","end"));
                put("end",Arrays.asList("end","end","end","end"));
            }
        };
        public void put(char c){
            state = stateTable.get(state).get(getLocation(c));
            if(state.equals("number")){
                ans = ans * 10 + c-'0';
                if(sign == 1){
                    ans = ans > Integer.MAX_VALUE? Integer.MAX_VALUE:ans;
                }else {
                    ans = ans > -1*(long)Integer.MIN_VALUE ? -1*(long)Integer.MIN_VALUE : ans;//取最小的值
                }
            }
            if(state.equals("signed")){
               sign = c == '+'? 1: -1;
            }
        }

        public int getLocation(char c){
            if(c == ' '){
                return 0;
            }
            if(c == '+' || c == '-'){
                return 1;
            }
            if(c >= '0' && c <= '9'){
                return 2;
            }
            return 3;
        }

    }

    public static void main(String[] args) {
        long x = -1 * (long)Integer.MIN_VALUE;
        System.out.println(  x);
        String_To_Integer_Atoi_Myself ss = new String_To_Integer_Atoi_Myself();
      //  System.out.println( ss.myAtoi("   89"));
        System.out.println( ss.myAtoi(" -89"));
        System.out.println( ss.myAtoi("   -890000000000000000000000000"));
        System.out.println( ss.myAtoi("   890000000000000000000000000"));
        System.out.println( ss.myAtoi("   a-89"));
        System.out.println( ss.myAtoi("   -89 "));
    }
}
