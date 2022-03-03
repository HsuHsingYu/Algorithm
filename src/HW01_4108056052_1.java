public class HW01_4108056052_1 extends ArrayData
{
    int maximum, minimum;
    public HW01_4108056052_1(int[] A)
    {
        this.A = A;
        maximum = A[0];
        minimum = A[0];
        for (int i = 1; i < A.length; i++)
        {
            if (A[i] > maximum)
            {
                maximum = A[i];
            }
            else
            {
                if (A[i] < minimum)
                {
                    minimum = A[i];
                }
            }
        }
    }

    public static void main(String[] args)
    {
        int[] A = {-100, 5, 2222, 45, 666, 90, 87, -55, 123, -88888};
        HW01_4108056052_1 test = new HW01_4108056052_1(A);
        System.out.println(test.max());
        System.out.println(test.min());
        long endTime = System.currentTimeMillis();
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
