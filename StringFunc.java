package Week1;

import java.util.Scanner;

public class StringFunc{

    public static String reverseString(String str){
        String s="";
        for(int i=str.length()-1;i>=0;i--){
            s+=str.charAt(i);
        }
        return s;
    }

    public static int countOccurrences(String str, String subs){
        int l = subs.length();
        int count=0;
        for(int i=0;i<str.length()-l+1;i++){
            if(str.substring(i, i+l).equals(subs)){
                count++;
            }
        }
        return count;
    }

    public static void splitAndCapitalize(String str){
        String[] sl = str.split(" ");
        for(int i=0;i<sl.length;i++){
            System.out.print((sl[i].substring(0,1)).toUpperCase()+sl[i].substring(1)+" ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a String : ");
        String s = sc.nextLine();
        System.out.print("Enter a sub string : ");
        String sb = sc.next();
        System.out.println(reverseString(s));
        System.out.println(countOccurrences(s,sb));
        splitAndCapitalize(s);
    }
}
