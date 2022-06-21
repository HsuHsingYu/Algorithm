public class HW05_4108056052_5 extends LLK
{
    boolean ans;
    int tNum = 32;
    int lgTNum = 5;
    Thread[] t = new Thread[tNum];

    public static void main(String[] args)
    {
        HW05_4108056052_5 test = new HW05_4108056052_5();
        int[][] array = TestDataGenerator.readData();
        System.out.println("case2:");
        Stopwatch stopwatch = new Stopwatch();
        System.out.println(test.checkLLK(array));
        double time = stopwatch.elapsedTime();
        System.out.println("elapsed time " + time);
    }

    public boolean checkLLK(int[][] array)
    {
        int len = array.length;
        int hashCap = 1 << (32 - Integer.numberOfLeadingZeros(len));

        if (len > tNum)
        {
            for (int k = 0; k < tNum; k++)
            {
                final int finalK = k;
                t[k] = new Thread(() ->
                {
                    final int start = (len * finalK) >> lgTNum;
                    final int end = (len * (finalK + 1)) >> lgTNum;
                    HashMap m = new HashMap(hashCap);

                    for (int i = start; i < end && !ans; i++)
                    {
                        for (int j = i + 1; j < len && !ans; j++)
                        {
                            if (m.containOrPut((double) (array[i][1] - array[j][1]) / (double) (array[i][0] - array[j][0])))
                            {
                                ans = true;
                            }
                        }
                        m.reset();
                    }
                });
                t[k].start();
            }

            try
            {
                for (Thread v : t)
                {
                    if (ans) return true;
                    v.join();
                }
            }
            catch (InterruptedException ignored)
            {
            }

            return ans;
        }
        else
        {
            int i, j;
            HashMap m = new HashMap(hashCap);
            for (i = 0; i < len; i++)
            {
                for (j = i + 1; j < len; j++)
                {
                    if (m.containOrPut((double) (array[i][1] - array[j][1]) / (double) (array[i][0] - array[j][0])))
                    {
                        return true;
                    }
                }
                m.reset();
            }

            return false;
        }
    }

    private static class HashMap
    {
        static class Entry
        {
            Entry(double key, Entry next)
            {
                this.key = key;
                this.next = next;
            }

            final public double key;
            final public Entry next;
        }

        final private int cap;
        final private int hashValue;
        private Entry[] list;

        HashMap(int size)
        {
            this.cap = size;
            this.hashValue = 0x7fffffff & (this.cap - 1);
            this.list = new Entry[this.cap];
        }

        final public void reset()
        {
            this.list = new Entry[this.cap];
        }

        final public boolean containOrPut(double key)
        {
            int index = Double.hashCode(key) & this.hashValue;

            for (Entry current = list[index]; current != null; current = current.next)
            {
                if (current.key == key)
                {
                    return true;
                }
            }

            list[index] = new Entry(key, list[index]);
            return false;
        }
    }
}