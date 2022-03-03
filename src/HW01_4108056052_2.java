public class HW01_4108056052_2 extends ArrayData
{
    int maximum = Integer.MIN_VALUE, minimum = Integer.MAX_VALUE;

    public HW01_4108056052_2(int[] A)
    {
        this.A = A;
        for (int i = 0; i < A.length - 1; i++)
        {
            int n1 = A[i];
            int n2 = A[++i];
            if (n1 > n2)
            {
                if (n1 > maximum)
                {
                    maximum = n1;
                }
                if (n2 < minimum)
                {
                    minimum = n2;
                }
            }
            else
            {
                if (n2 > maximum)
                {
                    maximum = n2;
                }
                if (n1 < minimum)
                {
                    minimum = n1;
                }
            }
        } // for
        if ((A.length % 2) != 0)
        {
            int n = A[A.length - 1];
            if (n > maximum)
            {
                maximum = n;
            }
            else if (n < minimum)
            {
                minimum = n;
            }
        }
    }

    public static void main(String[] args)
    {
        int[] A = {-100, 5, 2222, 45, 666, 90, 87, -55, -21123};
        HW01_4108056052_2 test = new HW01_4108056052_2(A);
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
