public class HW03_4108056052_2 extends HillFinding
{
//    public static void main(String[] args)
//    {
//        HW03_4108056052_2 test = new HW03_4108056052_2();
//        int[] A = new TestDataGenerator().readData();
// //       int[] A = {3,3,3,3,3,1,2};
//        Stopwatch stopwatch = new Stopwatch();
//        System.out.println("Hill is:" + test.H_Finding(A));
//        double time = stopwatch.elapsedTime();
//        System.out.println("time:" + time);
//    }

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

        for (int mid = (start + end) >> 1; mid != start; mid = (start + end) >> 1)
        {
            if (A[mid] >= A[start])
            {
                start = mid;
            }
            else
            {
                end = mid;
            }
        }

        return len_index - end;
    }
}
