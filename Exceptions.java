package Week1;

import java.util.Scanner;
public class Exceptions {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a : ");
		int a = sc.nextInt();
		System.out.print("Enter b : ");
		int b = sc.nextInt();
		int c=0;
		try {
			if(b==0)
				throw new ArithmeticException("Not Divisible by Zero");
			c=a/b;
		}
		catch(ArithmeticException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(c);
		}
	}
}
