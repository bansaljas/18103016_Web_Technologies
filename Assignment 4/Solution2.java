class Solution2
{
    public static void main(String[] args)
    {
        int num = -1;
        byte b = (byte)num;
        char c = (char)b;
        int final_num = c;
        System.out.println("Initial number is: " + num);
        System.out.println("Number after converting to byte: " + b);
        System.out.println("Byte after being converted to character: " + c);
        System.out.println("Final number is: " + final_num);
    }
}

/*
UNDERSTANDING THE CONCEPT:
    - integer -1 after converting to byte is -1. This is because byte in Java is also
      signed. And as 1 is in range of byte, the number remains -1.

    - converting byte to char, now char is unsigned. So it gets converted to 2^16 - 1.

    - converting the char to integer is a widening typecast, so final num becomes 2^16 - 1 = 65535
*/
