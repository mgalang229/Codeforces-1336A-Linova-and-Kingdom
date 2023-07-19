import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.StringTokenizer;

public class OtherSol {
	
	static ArrayList<Integer>[] adj;
	static int[] depth, parent, cost, children;
	static PriorityQueue<Pair> pq;
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
//		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt(), k = fs.nextInt();
			adj = new ArrayList[n+1];
			for (int i = 0; i < n + 1; i++) {
				adj[i] = new ArrayList<>();
			}
			for (int i = 0; i < n - 1; i++) {
				int u = fs.nextInt(), v = fs.nextInt();
				adj[u].add(v);
				adj[v].add(u);
			}
			depth = new int[n+1];
			Arrays.fill(depth, -1);
			
			parent = new int[n+1];
			Arrays.fill(parent, 0);

			cost = new int[n+1];
			Arrays.fill(cost, 0);
			
			children = new int[n+1];
			Arrays.fill(children, 0);
			
			depth[1] = 0;
			pq = new PriorityQueue<>();
			dfs(1);
			long ans = 0;
			while (k > 0) {
				Pair top = pq.poll();
				int value = top.first, u = top.second;
				k--;
				ans += value;
				if (parent[u] != 0) {
					cost[parent[u]] += cost[u] + 1;
					children[parent[u]]--;
					if (children[parent[u]] == 0) {
						pq.add(new Pair(depth[parent[u]] - cost[parent[u]], parent[u]));
					}
				}
			}
			System.out.println(ans);
		}
		out.close();
	}
	
	static void dfs(int u) {
		for (int v : adj[u]) {
			if (depth[v] == -1) {
				depth[v] = depth[u] + 1;
				parent[v] = u;
				children[u]++;
				dfs(v);
			}
		}
		if (adj[u].size() == 1 && adj[u].get(0) == parent[u]) {
			pq.add(new Pair(depth[u], u));
		}
	}
	
	static class Pair implements Comparable<Pair> {
		int first, second;
		
		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
		
		@Override
		public int compareTo(Pair o) {
			return -Integer.compare(this.first, o.first);
		}
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
