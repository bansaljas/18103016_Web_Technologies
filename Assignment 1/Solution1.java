import java.util.Scanner;
import java.util.Arrays;

public class Solution1
{
    //Function to return the sorted string
    public static String sortString(String s)
    {
        char []tempArray = s.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static void main(String []args)
    {
        // Taking input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String s = sc.nextLine();
        System.out.print("Enter the substring to be searched: ");
        String sub = sc.nextLine();

        //Sort the substring
        sub = sortString(sub);

        //Variables needed
        int count = 0;
        int len = s.length(); //length of the string
        int n = sub.length(); //length of the substring

        //Looping through the string
        for(int i=0; i<len-n+1; i++)
        {
            String temp = s.substring(i,i+n);
            temp = sortString(temp);
            if(sub.equals(temp))
                count++;
        }

        //Printing the final result
        System.out.println("The count of the substring in the given string is: " + count);
    }
}
