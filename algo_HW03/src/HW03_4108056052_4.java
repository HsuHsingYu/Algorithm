public class HW03_4108056052_4 extends HillFinding
{
    //HW03 while ver
    public static void main(String[] args)
    {
        HW03_4108056052_4 test = new HW03_4108056052_4();
        int[] A = new TestDataGenerator().readData();
 //       int[] A = {3,3,3,3,3,1,2};
        Stopwatch stopwatch = new Stopwatch();
        System.out.println("Hill is:" + test.H_Finding(A));
        double time = stopwatch.elapsedTime();
        System.out.println("time:" + time);
    }

    @Override
    public int H_Finding(int[] A)
    {
        //find from mid
        //if start > mid -> new array's right
        //if start = mid -> new array's left
        int len_index = A.length - 1;
        int start = 0, end = len_index;
        if (A[start] < A[end])
        {
            return -1;
        }

        int mid = (start + end) / 2;
        while (mid != start)
        {
            if (A[mid] >= A[start])
            {
                start = mid;
            }
            else
            {
                end = mid;
            }
            mid = (start + end) / 2;
        }

        return len_index - end;
    }
}
