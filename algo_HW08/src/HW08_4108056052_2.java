public class HW08_4108056052_2 extends Buy_Phone_v2
{

    static int[][] array;
    final static public int[][] tmp = new int[10000][6];
    final static public int[] d0 = new int[10000];
    final static public int[] d1 = new int[10000];
    final static public int[] d2 = new int[10000];
    final static public int[] d3 = new int[10000];
    final static public int[] d4 = new int[10000];
    final static public int[] d5 = new int[10000];
    static int[][] ans;

    @Override
    public int[][] bestPhone(int[][] inputArr)
    {
        array = inputArr;
        int len = array.length;

        sort(array, 0, len - 1);

        int max1, max2, max3, max4, max5;
        
        int end = len - 1;
        d0[end] = array[end][0];
        max1 = d1[end] = array[end][1];
        max2 = d2[end] = array[end][2];
        max3 = d3[end] = array[end][3];
        max4 = d4[end] = array[end][4];
        max5 = d5[end] = array[end][5];

        int top = len - 2;
        for (int i = top; i > -1; i--)
        {
            boolean isAns = false;

            if (array[i][1] > max1)
            {
                max1 = array[i][1];
                isAns = true;
            }
            if (array[i][2] > max2)
            {
                max2 = array[i][2];
                isAns = true;
            }
            if (array[i][3] > max3)
            {
                max3 = array[i][3];
                isAns = true;
            }
            if (array[i][4] > max4)
            {
                max4 = array[i][4];
                isAns = true;
            }
            if (array[i][5] > max5)
            {
                max5 = array[i][5];
                isAns = true;
            }

            if (isAns)
            {
                d0[top] = array[i][0];
                d1[top] = array[i][1];
                d2[top] = array[i][2];
                d3[top] = array[i][3];
                d4[top] = array[i][4];
                d5[top] = array[i][5];
                top--;
            }
        }

        ans = new int[len - top - 1][6];
        top++;
        for (int i = 0, lim = len - top; i < lim; i++)
        {
            ans[i][0] = d0[top];
            ans[i][1] = d1[top];
            ans[i][2] = d2[top];
            ans[i][3] = d3[top];
            ans[i][4] = d4[top];
            ans[i][5] = d5[top];
            top++;
        }

        return ans;
    }

    private static void sort(int[][] array, int lo, int hi)
    {
        if (hi - lo < 1)
            return;
        int mid = (hi + lo) >> 1;
        sort(array, lo, mid);
        sort(array, mid + 1, hi);
        merge(array, lo, mid, hi);
    }

    private static void merge(int[][] array, int lo, int mid, int hi)
    {
        for (int k = lo; k < hi + 1; k++)
            tmp[k] = array[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k < hi + 1; k++)
        {
            if (i > mid)
            {
                array[k] = tmp[j++];
            }
            else if (j > hi)
            {
                array[k] = tmp[i++];
            }
            else if (tmp[i][0] > tmp[j][0])
            {
                array[k] = tmp[j++];
            }
            else if (tmp[i][0] < tmp[j][0])
            {
                array[k] = tmp[i++];
            }
            else
            {
                if (tmp[i][1] > tmp[j][1])
                {
                    array[k] = tmp[j++];
                }
                else if (tmp[i][1] < tmp[j][1])
                {
                    array[k] = tmp[i++];
                }
                else
                {
                    if (tmp[i][2] > tmp[j][2])
                    {
                        array[k] = tmp[j++];
                    }
                    else if (tmp[i][2] < tmp[j][2])
                    {
                        array[k] = tmp[i++];
                    }
                    else
                    {
                        if (tmp[i][3] > tmp[j][3])
                        {
                            array[k] = tmp[j++];
                        }
                        else if (tmp[i][3] < tmp[j][3])
                        {
                            array[k] = tmp[i++];
                        }
                        else
                        {
                            if (tmp[i][4] > tmp[j][4])
                            {
                                array[k] = tmp[j++];
                            }
                            else if (tmp[i][4] < tmp[j][4])
                            {
                                array[k] = tmp[i++];
                            }
                            else
                            {
                                if (tmp[i][5] > tmp[j][5])
                                {
                                    array[k] = tmp[j++];
                                }
                                else
                                {
                                    array[k] = tmp[i++];
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}