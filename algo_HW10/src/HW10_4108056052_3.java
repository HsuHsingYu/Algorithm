public class HW10_4108056052_3 extends StringSort
{
    @Override
    public String[] checkString(String[] array)
    {
        sort(array);
        return array;
    }

    public static int findLongestLength(String[] a)
    {
        int longest = 0;
        for (int i = 0; i < a.length; ++i)
        {
            if (a[i].length() > longest)
            {
                longest = a[i].length();
            }
        }
        return longest;
    }

    public static int findCharAtInString(int i, int d, String[] a)
    {
        if (d < 0 || d >= a[i].length())
        {
            return 0;
        }
        return a[i].charAt(d);
    }

    public static void sort(String[] a)
    {
        int n = a.length;
        int R = 256;
        String[] aux = new String[n];
        int w = findLongestLength(a);
        for (int d = w - 1; d >= 0; d--)
        {
            int[] count = new int[R + 1];
            for (int i = 0; i < n; ++i)
            {
                int c = findCharAtInString(i, d, a);
                count[c + 1]++;
            }

            for (int r = 0; r < R; ++r)
            {
                count[r + 1] += count[r];
            }

            for (int i = 0; i < n; ++i)
            {
                int c = findCharAtInString(i, d, a);
                aux[count[c]++] = a[i];
            }

            for (int i = 0; i < n; ++i)
            {
                a[i] = aux[i];
            }
        }
    }
}
