import java.util.Scanner;

public class Solution1
{
    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first string: ");
        String a = sc.nextLine();
        System.out.print("Enter the second string: ");
        String b = sc.nextLine();

        int m = a.length();
        int n = b.length();
        boolean found = false;

        for(int i=0; i<m; i++)
        {
            if(i >= n || a.charAt(i) > b.charAt(i))
            {
                System.out.println("The lexographically smaller string is: "+ b);
                found = true;
                break;
            }
            if(a.charAt(i) < b.charAt(i))
            {
                System.out.println("The lexographically smaller string is: "+ a);
                found = true;
                break;
            }
        }
        if(!found && n==m)
            System.out.println("Both the strings are similar lexographically.");
        else if(!found)
            System.out.println("The lexographically smaller string is: "+ a);
    }
}
