/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/02/25 21:44
 * @description:
 */
public class Palindrome_Number {
    public boolean isPalindrome(int x) {

      boolean isPalind = true;
      String str = String.valueOf(x);
      for(int i=0,j=str.length()-1; i<=j;i++,j--){
          if(str.charAt(i) == str.charAt(j)){
              continue;
          }else {
              isPalind =false;
          }
      }
      return isPalind;
    }

    public static void main(String[] args) {
        Palindrome_Number p = new Palindrome_Number();
        System.out.println(p.isPalindrome(1));
        System.out.println(p.isPalindrome(11));
        System.out.println(p.isPalindrome(-1));
        System.out.println(p.isPalindrome(121));
        System.out.println(p.isPalindrome(12218));
    }
}
