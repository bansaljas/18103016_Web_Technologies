import java.util.Scanner;

public class Solution6
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number for the Hailstone series: ");
        long n = sc.nextInt();
        if(n <= 0)
        {
            System.out.print("This is a not valid input. Please enter a positive integer: ");
            n = sc.nextInt();
        }

        System.out.println("\nThe Hailstone series for the number "+ n + " is: ");
        System.out.print(n + " ");
        while(n != 1)
        {
            if(n%2 == 0)
                n = n/2;
            else
                n = n*3+1;
            if(n > Integer.MAX_VALUE)
            {
                System.out.println("\nInteger Overflow!");
                break;
            }
            System.out.print(n + " ");
        }
    }
}
