import java.util.Scanner;
import java.util.HashMap;

public class Solution2
{
    public static void main(String []args)
    {
        // Taking input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String text = sc.nextLine();
        System.out.print("Enter the number of words to be replaced: ");
        int n = sc.nextInt();
        System.out.print("Enter the words to be replaced: ");
        String[] words = new String[n];
        for(int i=0; i<n; i++)
        {
            String temp = sc.next();
            temp = temp.toLowerCase();
            words[i] = temp;
        }


        //Storing the words in the HashMap
        HashMap<String, Boolean> map = new HashMap<>();
        for(int i=0; i<n; i++)
            map.put(words[i], true);

        String ans = ""; //will store the transformed paragraph

        //Traversing the paragraph
        for(String val: text.split(" "))
        {
            String temp = val.toLowerCase();
            for(int i=1; i<=temp.length(); i++)
            {
                String sub = temp.substring(0,i); //To check for cases like Happy!!
                String rem = temp.substring(i);
                if(map.containsKey(sub))
                {
                    val = val.substring(0,1);
                    for(int j=1; j<sub.length(); j++)
                        val += "*";
                    val += rem;
                    break;
                }
            }
            ans = ans + val + " ";
        }

        //Printing the final output
        System.out.println(ans);
    }
}
