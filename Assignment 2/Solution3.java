import java.util.Scanner;

public class Solution3
{
    private static void merge(char[] arr, int st, int mid, int en)
    {
        int i=st, j=mid+1;
        char[] temp = new char[en-st+1];
        int k = 0;

        while(i<mid+1 && j<en+1)
        {
            if(arr[i] < arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while(i<mid+1)
            temp[k++] = arr[i++];
        while(j<en+1)
            temp[k++] = arr[j++];

        for(int t=st; t<=en; t++)
            arr[t] = temp[t-st];
    }

    private static void mergesort(char[] arr, int st, int en)
    {
        if(st < en)
        {
            int mid = st + (en-st)/2;
            mergesort(arr, st, mid);
            mergesort(arr, mid+1, en);
            merge(arr, st, mid, en);
        }
    }

    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String s = sc.nextLine();

        int n = s.length();
        char[] arr = new char[n];
        for(int i=0; i<n; i++)
            arr[i] = s.charAt(i);

        mergesort(arr, 0, n-1);  

        for(int i=0; i<n; i++)
            System.out.print(arr[i]);
    }
}
