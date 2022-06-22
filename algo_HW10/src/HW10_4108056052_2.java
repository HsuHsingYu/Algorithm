public class HW10_4108056052_2 extends StringSort
{
    @Override
    public String[] checkString(String[] array)
    {
        int n = array.length;
        int R = 256;
        String[] aux = new String[n];
        int w = findLongestLength_4108056052(array);
        for (int d = w - 1; d >= 0; d--)
        {
            int[] count = new int[R + 1];
            for (int i = 0; i < n; ++i)
            {
                int c = findCharAtInString_4108056052(i, d, array);
                count[c + 1]++;
            }

            for (int r = 0; r < R; ++r)
            {
                count[r + 1] += count[r];
            }

            for (int i = 0; i < n; ++i)
            {
                int c = findCharAtInString_4108056052(i, d, array);
                aux[count[c]++] = array[i];
            }

            System.arraycopy(aux, 0, array, 0, n);
        }

        return array;
    }

    public static int findLongestLength_4108056052(String[] a)
    {
        int longest = 0;
        for (String s : a)
        {
            if (s.length() > longest)
            {
                longest = s.length();
            }
        }
        return longest;
    }

    public static int findCharAtInString_4108056052(int i, int d, String[] a)
    {
        if (d < 0 || d >= a[i].length())
        {
            return 0;
        }
        return a[i].charAt(d);
    }
}
