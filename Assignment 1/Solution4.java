import java.util.Scanner;
import java.util.Arrays;

public class Solution4
{
    public static String sortString(String s)
    {
        char []tempArray = s.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static void main(String[] args)
    {
        // Taking input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first string: ");
        String a = sc.nextLine();
        System.out.print("Enter the second string: ");
        String b = sc.nextLine();

        //Sorting the strings
        a = sortString(a);
        b = sortString(b);

        //check if anagrams
        if(a.equals(b))
            System.out.println("The strings entered are Anagrams!");
        else
            System.out.println("The strings entered are not Anagrams!");
    }
}
