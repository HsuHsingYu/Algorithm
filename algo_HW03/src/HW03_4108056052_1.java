public class HW03_4108056052_1 extends HillFinding
{
    //ver 3 if else exchange ver

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

        for (int mid = (start + end) / 2; mid != start; mid = (start + end) / 2)
        {
            if (A[mid] < A[start])
            {
                end = mid;
            }
            else
            {
                start = mid;
            }
        }

        return len_index - end;
    }
}
