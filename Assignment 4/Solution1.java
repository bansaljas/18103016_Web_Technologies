import java.util.Scanner;
import java.util.*;
import java.lang.Math;

class Solution1
{
    public static void main(String[] args)
    {
        int n, m;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        in.nextLine();
        String[] crops = new String[n];
        for (int i=0;i<n;i++)
        {
            //trim removes the trailing and leading spaces
            crops[i]=in.next().trim();
        }

        System.out.print(replant(crops));
    }

    public static int replant(String[] crops)
    {
        // Write your code here
        // Return the number of replanted crops
        int n = crops.length;
        int m = crops[0].length();
        char[][] farm = new char[n][m];
        for(int i=0; i<n; i++)
            farm[i] = crops[i].toCharArray();
        return replant_helper(farm, 0, 0, n, m);
    }

    private static int replant_helper(char[][] farm, int x, int y, int n, int m)
    {
        if(x<0 || y<0 || x>=n)
            return 0;

        for(int i=x; i<n; i++)
        {
            int j=0;
            if(i == x) j = y;
            for( ; j<m; j++)
            {
                int left=0, right=0, top=0,bottom=0;
                if(j-1>=0 && farm[i][j-1] == farm[i][j]) left = 1;
                if(j+1<m && farm[i][j+1] == farm[i][j]) right = 1;
                if(i-1>=0 && farm[i-1][j] == farm[i][j]) top = 1;
                if(i+1<n && farm[i+1][j] == farm[i][j]) bottom = 1;

                if(left==1 || right==1 || top==1 || bottom==1)
                {
                    int ans = Integer.MAX_VALUE;
                    char plant = farm[i][j];
                    if(left == 1)
                    {
                        farm[i][j-1] = '#';
                        ans = Math.min(ans,replant_helper(farm, i, j+1, n, m));
                        farm[i][j-1] = plant;
                    }
                    if(right == 1)
                    {
                        farm[i][j+1] = '#';
                        ans = Math.min(ans,replant_helper(farm, i, j+1, n, m));
                        farm[i][j+1] = plant;
                    }
                    if(top == 1)
                    {
                        farm[i-1][j] = '#';
                        ans = Math.min(ans,replant_helper(farm, i, j+1, n, m));
                        farm[i-1][j] = plant;
                    }
                    if(bottom == 1)
                    {
                        farm[i+1][j] = '#';
                        ans = Math.min(ans,replant_helper(farm, i, j+1, n, m));
                        farm[i+1][j] = plant;
                    }

                    farm[i][j] = '#';
                    ans = Math.min(ans, replant_helper(farm, i, j+1, n, m));
                    farm[i][j] = plant;

                    return 1+ans;
                }
            }
        }
        return 0;
    }
}
