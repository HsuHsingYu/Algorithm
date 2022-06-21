public class HW01_4108056052_3 extends ArrayData
{
    int maximum, minimum;

    public HW01_4108056052_3(int[] A)
    {
        this.A = A;
        maximum = A[0];
        minimum = A[0];
        for (int i = 1; i < A.length; i++)
        {
            int n = A[i];
            if (n > maximum)
            {
                maximum = n;
            }
            else
            {
                if (n < minimum)
                {
                    minimum = n;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        int[] A = {-100, 5, 2222, 45, 666, 90, 87, -55, 123, -88888};
        HW01_4108056052_3 test = new HW01_4108056052_3(A);
        System.out.println(test.max());
        System.out.println(test.min());
    }

    public int max()
    {
        return maximum;
    }

    public int min()
    {
        return minimum;
    }

}
