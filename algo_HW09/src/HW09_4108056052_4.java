import java.util.ArrayList;

public class HW09_4108056052_4 extends LSD
{

    public int Distance(int[][] array)
    {

        Graph the_graph = new Graph();
        for (int[] edge : array)
        {
            the_graph.add_edge(edge[0], edge[1]);
        }

        int vertex = 0, depth, max_depth = 0;
        while (true)
        {
            depth = the_graph.get_further_distance(vertex);
            if (depth > max_depth)
            {
                max_depth = depth;
                vertex = the_graph.farthest_min_degree_vertex;
            }
            else
            {
                break;
            }
        }
        return max_depth;
    }

    private static class LinkedListQueue
    {
        private static class QueueNode
        {
            private final int value;
            private QueueNode next;

            public QueueNode(int value)
            {
                this.value = value;
            }
        }

        private QueueNode myHead;
        private QueueNode myEnd;
        private int size = 0;

        public void add(int value)
        {
            if (this.size == 0)
            {
                this.myHead = this.myEnd = new QueueNode(value);
            }
            else
            {
                this.myEnd.next = new QueueNode(value);
                this.myEnd = this.myEnd.next;
            }
            this.size++;
        }

        public int remove()
        {
            QueueNode tmp = this.myHead;
            if (this.size == 1)
            {
                this.myHead = this.myEnd = null;
            }
            else
            {
                this.myHead = this.myHead.next;
            }
            this.size--;
            return tmp.value;
        }

        public Boolean isEmpty()
        {
            return this.size == 0;
        }

        public int[] toArray()
        {
            int[] result = new int[this.size];
            int counter = 0;
            QueueNode ptr = this.myHead;
            while (counter < this.size)
            {
                result[counter++] = ptr.value;
                ptr = ptr.next;
            }
            return result;
        }
    }

    private static class Graph
    {
        private final ArrayList<Integer> vertices;
        private final ArrayList<ArrayList<Integer>> edges;
        private int farthest_min_degree_vertex;

        public Graph()
        {
            this.vertices = new ArrayList<>();
            this.edges = new ArrayList<>();
        }

        public void add_edge(int from, int to)
        {
            int from_index = this.vertices.indexOf(from);
            int to_index = this.vertices.indexOf(to);
            if (from_index == -1)
            {
                this.vertices.add(from);
                this.edges.add(new ArrayList<>());
                from_index = this.vertices.size() - 1;
            }
            if (to_index == -1)
            {
                this.vertices.add(to);
                this.edges.add(new ArrayList<>());
                to_index = this.vertices.size() - 1;
            }
            this.edges.get(from_index).add(to_index);
            this.edges.get(to_index).add(from_index);
        }

        public int get_further_distance(int vertex)
        {
            LinkedListQueue tmp_queue = new LinkedListQueue();
            tmp_queue.add(vertex);

            boolean[] traveled = new boolean[this.vertices.size()];
            traveled[vertex] = true;

            int this_level = 1, next_level = 0, depth = 0;
            boolean last_for_this_level;
            int[] backup = new int[0];
            while (!tmp_queue.isEmpty())
            {
                int this_vertex = tmp_queue.remove();
                if (this_level == 1)
                {
                    last_for_this_level = true;
                }
                else
                {
                    this_level--;
                    last_for_this_level = false;
                }
                for (Integer next : this.edges.get(this_vertex))
                {
                    if (!traveled[next])
                    {
                        traveled[next] = true;
                        tmp_queue.add(next);
                        next_level++;
                    }
                }
                if (last_for_this_level)
                {
                    this_level = next_level;
                    next_level = 0;
                    if (!tmp_queue.isEmpty())
                    {
                        depth++;
                        backup = tmp_queue.toArray();
                    }
                }
            }

            this.farthest_min_degree_vertex = this.vertices.size();
            int degree, min_degree = this.vertices.size();
            for (int farthest_vertex : backup)
            {
                degree = this.edges.get(farthest_vertex).size();
                if (degree < min_degree)
                {
                    min_degree = degree;
                    this.farthest_min_degree_vertex = farthest_vertex;
                }
            }

            return depth;
        }
    }
}