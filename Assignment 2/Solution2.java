import java.util.Scanner;

public class Solution2
{
    public static void main(String []args)
    {
        int[] count = new int[21];
        for(int i=0; i<21; i++)
            count[i] = 0;
        Scanner sc = new Scanner(System.in);
        char ch = 'Y';
        while(ch != 'N')
        {
            System.out.print("Enter the number between 0 to 20: ");
            int num = sc.nextInt();
            count[num]++;
            System.out.print("Do you want to enter another number (Y/N)? ");
            sc.nextLine();
            ch = sc.nextLine().charAt(0);
        }

        System.out.println("The numbers entered in the sorted order are: ");
        for(int i=0; i<21; i++)
        {
            while(count[i] > 0)
            {
                System.out.print(i + " ");
                count[i]--;
            }
        }
    }
}
