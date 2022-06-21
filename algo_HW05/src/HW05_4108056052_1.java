public class HW05_4108056052_1 extends LLK
{
    volatile boolean ans;
    int tNum = 32,len;
    int[][] array;
    int hashCap;

    public boolean checkLLK(int[][] array)
    {
        this.array = array;
        len = array.length;
        hashCap = 1 << (32 - Integer.numberOfLeadingZeros(len));

        if (len > tNum)
        {
            Thread4108056052[] t = new Thread4108056052[tNum];

            int t_thread = -1;
            while (++t_thread < tNum)
            {
                t[t_thread] = new Thread4108056052(t_thread);
                t[t_thread].start();
            }

            try
            {
                for(int i = 0; i < tNum; i++)
                {
                    t[i].join();
                }
            }
            catch(InterruptedException e) {}
            return ans;
        }
        else
        {
            int deltaX, deltaY, gcd;
            HashMap m = new HashMap(hashCap);
            int i = -1, j;
            while (++i < len)
            {
                j = i;
                while (++j < len)
                {
                    deltaX = array[i][0] - array[j][0];
                    deltaY = array[i][1] - array[j][1];

                    gcd = gcd(deltaY, deltaX);

                    deltaX = (deltaX / gcd);
                    deltaY = (deltaY / gcd);
                    if (deltaX < 0)
                    {
                        deltaX = ~deltaX + 1;
                        deltaY = ~deltaY + 1;
                    }

                    if (m.containOrPut(deltaX, deltaY))
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
            public int key1;
            public int key2;
            public Entry next;
        }

        private final int cap;
        private Entry[] list;

        HashMap(int size)
        {
            this.cap = size;
            this.list = new Entry[this.cap];
        }

        final public void reset()
        {
            this.list = new Entry[this.cap];
        }

        final public boolean containOrPut(int key1, int key2)
        {
            int index = ((key1 + key2) & 0x7fffffff) & (this.cap - 1);
            for (Entry current = list[index]; current != null; current = current.next)
            {
                if (current.key1 == key1 && current.key2 == key2)
                {
                    return true;
                }
            }
            Entry newEntry = new Entry();
            newEntry.key1 = key1;
            newEntry.key2 = key2;
            newEntry.next = list[index];
            list[index] = newEntry;
            return false;
        }
    }

    public int gcd(int a, int b)
    {
        return b == 0 ? a : gcd(b, a % b);
    }

    class Thread4108056052 extends Thread
    {
        int tr;

        public Thread4108056052(int tr)
        {
            this.tr = tr;
        }

        public void run()
        {
            int start = len * tr / tNum;
            int end = len * (tr + 1) / tNum;
            HashMap m = new HashMap(hashCap);

            int deltaX, deltaY,gcd;
            for (int i = start; i < end && !ans; i++)
            {
                for (int j = i + 1; j < len && !ans; j++)
                {
                    deltaX = array[i][0] - array[j][0];
                    deltaY = array[i][1] - array[j][1];

                    gcd = gcd(deltaY, deltaX);
                    deltaX = (deltaX / gcd);
                    deltaY = (deltaY / gcd);

                    if(deltaX < 0)
                    {
                        deltaX = ~deltaX + 1;
                        deltaY = ~deltaY + 1;
                    }

                    if(m.containOrPut(deltaX, deltaY))	// if it is already store in hashmap, return true.
                    {
                        ans = true;
                    }
                }
                m.reset();
            }
        }
    }
}