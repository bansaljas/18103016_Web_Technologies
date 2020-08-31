public class Solution4
{
    public static void main(String[] args)
    {
        long sum = 1;
        long n = 1;

        System.out.print("The numbers satisfying the equation are: ");
        while(n <= Integer.MAX_VALUE)
        {
            if(sum == n*n)
                System.out.println(n);
            n++;
            sum+=n;
        }
    }
}
