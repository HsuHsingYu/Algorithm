public class HW05_4108056052_3 extends LLK
{
    private static boolean ans;

    public boolean checkLLK(int[][] array)
    {

        int num_threads = Runtime.getRuntime().availableProcessors();
        Thread[] run_queue = new Thread[num_threads];
        Runnable[] runnables = new Runnable[num_threads];

        int batch_num = array.length / (num_threads - 1);
        runnables[0] = new CheckVerticalHorizontal(array);
        run_queue[0] = new Thread(runnables[0]);
        run_queue[0].start();
        for (int i = 1; i < num_threads - 1; i++)
        {
            runnables[i] = new CheckSlant(array, batch_num * (i - 1), batch_num * i);
            run_queue[i] = new Thread(runnables[i]);
            run_queue[i].start();
        }
        runnables[num_threads - 1] = new CheckSlant(array, batch_num * (num_threads - 2), array.length);
        run_queue[num_threads - 1] = new Thread(runnables[num_threads - 1]);
        run_queue[num_threads - 1].start();
        try
        {
            for (Thread v : run_queue)
            {
                if (ans) return true;
                v.join();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ans;
    }

    private static class CheckSlant implements Runnable
    {
        private final int[][] array;
        private final int from;
        private final int to;

        public CheckSlant(int[][] array, int from, int to)
        {
            this.from = from;
            this.to = to;
            this.array = array;
        }

        @Override
        public void run()
        {

            double slant;
            LineSet dot_lines;
            for (int i = this.from; i < this.to; i++)
            {
                dot_lines = new LineSet(this.array.length - this.from);
                for (int j = i + 1; j < this.array.length; j++)
                {
                    if (i == j)
                    {
                        continue;
                    }
                    if (this.array[j][0] == this.array[i][0])
                    {
                        continue;
                    }

                    slant = (double) (this.array[i][1] - this.array[j][1]) / (double) (this.array[i][0] - this.array[j][0]);

                    if (dot_lines.add(slant))
                    {
                        ans = true;
                        break;
                    }
                }
                if (ans)
                {
                    break;
                }
            }
        }

        private static class LineSet
        {
            private static class LineNode
            {
                private final double slant;
                public LineNode next;

                public LineNode(double slant)
                {
                    this.slant = slant;
                }
            }

            private final LineNode[] buckets;
            private final Object[] bucket_locks;

            public LineSet(int maximum_load)
            {
                int bucket_size = (int) (maximum_load / 0.75) - 1;
                bucket_size |= bucket_size >> 1;
                bucket_size |= bucket_size >> 2;
                bucket_size |= bucket_size >> 4;
                bucket_size |= bucket_size >> 8;
                bucket_size |= bucket_size >> 16;
                this.buckets = new LineNode[++bucket_size];
                this.bucket_locks = new Object[bucket_size];
                for (int i = 0; i < this.bucket_locks.length; i++)
                {
                    this.bucket_locks[i] = new Object();
                }
            }

            public boolean add(double slant)
            {
                int bucket = Double.hashCode(slant) & (this.buckets.length - 1);
                synchronized (this.bucket_locks[bucket])
                {
                    if (this.buckets[bucket] == null)
                    {
                        this.buckets[bucket] = new LineNode(slant);
                        return false;
                    }
                    else if (this.buckets[bucket].slant == slant)
                    {
                        return true;
                    }
                    LineNode loc = this.buckets[bucket];
                    while (loc.next != null)
                    {
                        loc = loc.next;
                        if (loc.slant == slant)
                        {
                            return true;
                        }
                    }
                    loc.next = new LineNode(slant);
                    return false;
                }
            }
        }
    }

    private static class CheckVerticalHorizontal implements Runnable
    {
        private final int[][] array;

        public CheckVerticalHorizontal(int[][] array)
        {
            this.array = array;
        }

        @Override
        public void run()
        {

            IntSet x_coords = new IntSet(this.array.length);
            IntSet y_coords = new IntSet(this.array.length);

            for (int[] ints : this.array)
            {
                if (x_coords.add(ints[0]))
                {
                    ans = true;
                    break;
                }
                if (y_coords.add(ints[1]))
                {
                    ans = true;
                    break;
                }
            }
        }

        private static class IntSet
        {
            private static class LineNode
            {
                private final int value;
                public int occurrence = 1;
                public LineNode next;

                public LineNode(int value)
                {
                    this.value = value;
                }
            }

            private final LineNode[] buckets;

            public IntSet(int maximum_load)
            {
                int bucket_size = (int) (maximum_load / 0.75) - 1;
                bucket_size |= bucket_size >> 1;
                bucket_size |= bucket_size >> 2;
                bucket_size |= bucket_size >> 4;
                bucket_size |= bucket_size >> 8;
                bucket_size |= bucket_size >> 16;
                this.buckets = new LineNode[++bucket_size];
            }

            public boolean add(int value)
            {
                int bucket = value & (this.buckets.length - 1);
                if (this.buckets[bucket] == null)
                {
                    this.buckets[bucket] = new LineNode(value);
                    return false;
                }
                else if (this.buckets[bucket].value == value)
                {
                    return (++this.buckets[bucket].occurrence) == 3;
                }
                LineNode loc = this.buckets[bucket];
                while (loc.next != null)
                {
                    loc = loc.next;
                    if (loc.value == value)
                    {
                        return (++loc.occurrence) == 3;
                    }
                }
                loc.next = new LineNode(value);
                return false;
            }
        }
    }
}