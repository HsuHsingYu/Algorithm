public class HW04_4108056052_1_old extends One_0k_rock
{
    @Override
    public boolean[] one0k(String[] str)
    {
        int len = str.length;
        boolean[] ans = new boolean[len];
        String s;
        int i = -1, j;
        while (++i < len)
        {
            s = str[i];
            int s_maxIndex = s.length() - 1;
            ans[i] = true;
            if (((s_maxIndex + 1) % 2) == 0)
            {
                int check_time = s_maxIndex / 2;
                j = check_time+1;
                while (++j <= check_time) // from middle
                {
                    if (s.charAt(j) == '1' || s.charAt(s_maxIndex - j) == '0')
                    {
                        ans[i] = false;
                        break;
                    }
                }
            }
            else
            {
                ans[i] = false;
            }
        }
        return ans;
    }
}
