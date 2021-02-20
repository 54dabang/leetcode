import java.util.ArrayList;
import java.util.List;

/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/02/20 15:06
 * @description:第六题 Z字变形
 */
public class Zig_Zag_Conversion {
    public String convert(String s, int numRows) {
        int sum = s.length();
        if(numRows == 1){
            return s;
        }
        int yushu = sum % (2 * numRows - 2);
        int count = sum / (2 * numRows - 2);
        int columnNumber;//对应的列
        if (yushu <= numRows) {
            columnNumber = count * (numRows - 1) + 1;
        } else {
            columnNumber = count * (numRows - 1) + 1 + yushu - numRows;
        }
        System.out.println("列数:" + columnNumber);
        char [][]array = new char[numRows][columnNumber];
        int location = 0; //当前字符串中的位置

        for(int i =0 ; i<numRows;i++){
            for(int j=0; j<columnNumber;j++){
                array[i][j] = '#';
            }
        }
        int j = 0;
        while (location < s.length()){
            char c = s.charAt(location);
            int innerYushu = (location+1) %(2 * numRows - 2);
            int num = (location+1) /(2 * numRows - 2);

            if(innerYushu == 0){
                innerYushu = 2*numRows -2;
            }
            if(innerYushu <= numRows){
                j = num*(numRows  -1);
                array[innerYushu -1][j] = c;

            }else {
                j++;
                int innerYushu2 = innerYushu - numRows;
                int i = numRows - innerYushu2 -1;
                array[i][j] = c;
            }
            location++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i =0 ; i<numRows;i++){
            for( j=0; j<columnNumber;j++){
                System.out.print(array[i][j]);
                if(array[i][j] != '#'){
                    sb.append(array[i][j]);
                }
            }
            System.out.println();
        }

     return sb.toString();
    }

    /**
     * 想像按照走向，每一行用一个StringBuilder来表示，走到哪一行就用哪一行的StringBuilder
     * 当发生转向的时候就是在最顶部或者最尾部时候因此可以使用这种方式
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        if(s == null || s.length() <= 0){
            return s;
        }
        List<StringBuilder> list = new ArrayList<StringBuilder>(numRows);
        for(int i = 0; i < list.size(); i++){
            list.add(new StringBuilder());
        }
        int direc = 1;
        int index = 0;
        for(int i=0;i<s.length();i++){
            StringBuilder sb = list.get(i);
            sb.append(s.charAt(i));
            if(index + direc == numRows || index +direc == 0 ){//发生转向
                direc = -1*direc;
            }
            index = index + direc;
        }
        StringBuilder total = new StringBuilder();
        for(StringBuilder sb : list){
            total.append(sb.toString());
        }
        return total.toString();
    }

    public static void main(String[] args) {
        Zig_Zag_Conversion zig = new Zig_Zag_Conversion();
        int num = 14;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i< num; i++){
            sb.append("A");
        }
        System.out.println(zig.convert("PAYPALISHIRING",3).equals("PAHNAPLSIIGYIR"));
        System.out.println(zig.convert("PAYPALISHIRING",4).equals("PINALSIGYAHRPI"));
        System.out.println(zig.convert("A",1).equals("A"));

        System.out.println(zig.convert2("PAYPALISHIRING",3).equals("PAHNAPLSIIGYIR"));
        System.out.println(zig.convert2("PAYPALISHIRING",4).equals("PINALSIGYAHRPI"));
    }
}
