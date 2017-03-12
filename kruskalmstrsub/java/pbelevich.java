import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public static class UF {

        public int[] id;

        public UF(int N) {
            this.id = new int[N];
            for (int i = 0; i < this.id.length; i++) {
                this.id[i] = i;
            }
        }

        public void union(int p, int q) {
            id[find(p)] = find(q);
        }

        public int find(int i) {
            while (i != id[i]) {
                i = id[i];
            }
            return i;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

    }

    public static class WeightedEdge implements Comparable<WeightedEdge> {

        public final int v;
        public final int w;
        public final int length;

        public WeightedEdge(int v, int w, int length) {
            this.v = v;
            this.w = w;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WeightedEdge that = (WeightedEdge) o;

            return this.length == that.length && ((this.v == that.v && this.w == that.w) || (this.v == that.w && this.w == that.v));
        }

        @Override
        public int hashCode() {
            int result = v + w;
            result = 31 * result + length;
            return result;
        }

        @Override
        public String toString() {
            return "WeightedEdge{" +
                    "v=" + v +
                    ", w=" + w +
                    ", length=" + length +
                    '}';
        }

        @SuppressWarnings("NullableProblems")
        public int compareTo(WeightedEdge that) {
            return Integer.compare(this.length, that.length);
        }
    }

    public static class WeightedGraph {

        public final int v;
        public final int e;
        public final Collection<WeightedEdge>[] adj;
        public final Collection<WeightedEdge> edges;

        @SuppressWarnings("unchecked")
        public WeightedGraph(int v, int e) {
            this.v = v;
            this.e = e;
            this.adj = (Collection<WeightedEdge>[]) new Collection[this.v];
            for (int i = 0; i < this.v; i++) {
                this.adj[i] = new ArrayList<WeightedEdge>();
            }
            this.edges = new ArrayList<WeightedEdge>();
        }

        public void addEdge(WeightedEdge e) {
            this.adj[e.v].add(e);
            this.adj[e.w].add(e);
            this.edges.add(e);
        }

    }

    public static class Input {

        public WeightedGraph graph;
        public int s;

        public Input(WeightedGraph graph, int s) {
            this.graph = graph;
            this.s = s;
        }

    }

    public static class InputReader {

        public Input read(Reader reader) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            String[] parts = line.split("\\s+");
            int v = Integer.parseInt(parts[0]);
            int e = Integer.parseInt(parts[1]);
            WeightedGraph graph = new WeightedGraph(v, e);
            for (int j = 0; j < e; j++) {
                line = bufferedReader.readLine();
                parts = line.split("\\s+");
                int v1 = Integer.parseInt(parts[0]) - 1;
                int v2 = Integer.parseInt(parts[1]) - 1;
                int length = Integer.parseInt(parts[2]);
                graph.addEdge(new WeightedEdge(v1, v2, length));
            }
            line = bufferedReader.readLine();
            int s = Integer.parseInt(line) - 1;
            bufferedReader.close();
            return new Input(graph, s);
        }

    }

    public static class Output {

        public int result;

        public Output(int result) {
            this.result = result;
        }

    }

    public static class OutputWriter {

        void write(Output output, Writer writer) {
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println(output.result);
            printWriter.close();
        }

    }

    public static void main(String[] args) throws IOException {
        Input input = new InputReader().read(new InputStreamReader(System.in));
        Output output = solve(input);
        new OutputWriter().write(output, new OutputStreamWriter(System.out));
    }

    public static Output solve(Input input) {
        return new Output(mst(input.graph, input.s));
    }

    @SuppressWarnings("UnusedParameters")
    public static int mst(WeightedGraph graph, int s) {
        int weight = 0;
        int count = 0;
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>(10, new Comparator<WeightedEdge>() {
            public int compare(WeightedEdge e1, WeightedEdge e2) {
                int result = e1.compareTo(e2);
                if (result == 0) {
                    result = Integer.compare(e1.v + e1.length + e1.w, e2.v + e2.length + e2.w);
                }
                return result;
            }
        });
        pq.addAll(graph.edges);
        UF uf = new UF(graph.v);
        while (!pq.isEmpty() && count < graph.v - 1) {
            WeightedEdge e = pq.poll();
            if (!uf.connected(e.v, e.w)) {
                weight += e.length;
                uf.union(e.v, e.w);
                count++;
            }
        }
        return weight;
    }

}
