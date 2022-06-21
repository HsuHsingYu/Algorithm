public class HW03_4108056052_5 extends HillFinding
{
    //ver 3 while ver

    @Override
    public int H_Finding(int[] A)
    {
        //find from mid
        //if start > mid -> new array's right
        //if start = mid -> new array's left
        int end, len_index, mid, start = 0;
        end = len_index = A.length - 1;
        if (A[start] < A[end])
        {
            return -1;
        }

        while ((mid = (start+end)/2) != start)
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