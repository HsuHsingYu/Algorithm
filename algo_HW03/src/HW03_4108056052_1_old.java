public class HW03_4108056052_1_old extends HillFinding
{
    public static void main(String[] args)
    {
        HW03_4108056052_1_old test = new HW03_4108056052_1_old();
//        int[] A = new TestDataGenerator().readData();
        int[] A = {3,3,3,3,3,1,2};
        Stopwatch stopwatch = new Stopwatch();
        System.out.println("Hill is:" + test.H_Finding(A));
        double time = stopwatch.elapsedTime();
        System.out.println("time:" + time);
    }

    @Override
    public int H_Finding(int[] A)
    {
        //find from left and right
        //if left > right -> find
        //return origin cut -> len-i
        int len_index = A.length - 1;
        int i = -1;
        while (++i <= len_index)
        {
            int right = len_index - i;
            if (A[i] > A[i + 1])
            {
                return len_index - i - 1;
            }
            else if (A[right] < A[right - 1])
            {
                return len_index - right;
            }
        }

        return -1;
    }
}
