# Complexity Cheat Sheet

Quick reference for every data structure and algorithm in AlgoCodeHub.

> **Legend:** `*` = amortized · `m` = key/string length · `V` = vertices · `E` = edges · `W` = knapsack capacity · `α(n)` = inverse Ackermann

---

## Data Structures

### Array — `datastructure.array.CustomArray`

| Operation | Time | Space |
|-----------|------|-------|
| `add` | O(1)* | — |
| `get` / `set` | O(1) | — |
| `removeLast` | O(1) | — |
| `clear` | O(n) | — |

Space: **O(n)** total

---

### Linked Lists — `datastructure.list`

| Operation | Singly | Doubly | Circular |
|-----------|--------|--------|----------|
| `add` (tail) | O(1) | O(1) | O(1) |
| `addAtPosition` | O(n) | O(n) | O(n) |
| `delete` (by value) | O(n) | O(n) | O(n) |
| `deleteAtPosition` | O(n) | O(n) | O(n) |
| `get(i)` | O(n) | O(n/2) | O(n) |
| `peekFirst` / `peekLast` | O(1) | O(1) | O(1) |
| `contains` | O(n) | O(n) | O(n) |

Space: **O(n)** · Doubly delete in **O(1)** with a direct node reference

---

### Stack — `datastructure.stack`

| Operation | LinkedStack | ArrayStack |
|-----------|-------------|------------|
| `push` | O(1) | O(1)* |
| `pop` | O(1) | O(1) |
| `peek` | O(1) | O(1) |

Space: **O(n)**

---

### Queue — `datastructure.queue`

| Operation | LinkedQueue | CircularArrayQueue | Min/Max PQ |
|-----------|-------------|--------------------|------------|
| enqueue / offer | O(1) | O(1)* | O(log n) |
| dequeue / poll | O(1) | O(1) | O(log n) |
| peek | O(1) | O(1) | O(1) |

Space: **O(n)**

---

### Deque — `datastructure.deque.LinkedDeque`

| Operation | Time |
|-----------|------|
| `addFirst` / `addLast` | O(1) |
| `removeFirst` / `removeLast` | O(1) |
| `peekFirst` / `peekLast` | O(1) |

Space: **O(n)**

---

### Tree — `datastructure.tree`

| Operation | BST (avg) | BST (worst) | AVL | Trie |
|-----------|-----------|-------------|-----|------|
| insert | O(log n) | O(n) | O(log n) | O(m) |
| delete | O(log n) | O(n) | O(log n) | O(m) |
| search / contains | O(log n) | O(n) | O(log n) | O(m) |
| `startsWith` | — | — | — | O(m) |
| `height()` | O(n) | O(n) | O(1) cached | — |

Space: **O(n)** nodes · Trie: **O(n · m)** worst case

---

### Heap — `datastructure.heap`

| Operation | MinHeap / MaxHeap |
|-----------|-------------------|
| `insert` | O(log n) |
| `extract` | O(log n) |
| `peek` | O(1) |

Space: **O(n)** array-backed

---

### Hash — `datastructure.map` · `datastructure.set`

| Operation | ChainedHashMap | HashSet |
|-----------|----------------|---------|
| `put` / `add` | O(1) avg · O(n) worst | O(1) avg |
| `get` / `contains` | O(1) avg · O(n) worst | O(1) avg |
| `remove` | O(1) avg · O(n) worst | O(1) avg |

Space: **O(n)** · Rehash at **75%** load factor

---

### Graph — `datastructure.graph`

| Operation | AdjacencyList | AdjacencyMatrix | UnionFind |
|-----------|---------------|-----------------|-----------|
| add vertex | O(1) | O(1) | — |
| add edge | O(1) | O(1) | — |
| remove vertex | O(V · deg) | O(V) | — |
| BFS / DFS | O(V + E) | O(V²) | — |
| `find` / `union` | — | — | O(α(n)) |
| `connected` | — | — | O(α(n)) |

Space: **O(V + E)** list · **O(V²)** matrix · **O(n)** Union-Find

---

### Advanced — `datastructure.advanced`

| Structure | Update | Query | Space |
|-----------|--------|-------|-------|
| Fenwick Tree | O(log n) | prefix / range O(log n) | O(n) |
| Segment Tree | O(log n) | range O(log n) | O(n) |
| LRU Cache | put/get O(1) | — | O(capacity) |

---

## Algorithms

### Search — `algorithm.search`

| Algorithm | Time | Space |
|-----------|------|-------|
| Linear search | O(n) | O(1) |
| Binary search | O(log n) | O(1) |
| Binary search (recursive) | O(log n) | O(log n) stack |
| Lower / upper bound | O(log n) | O(1) |
| Binary search on answer | O(log n · T) | O(1) |

*T = predicate cost per check*

---

### Sort — `algorithm.sort`

| Algorithm | Best | Average | Worst | Space | Stable |
|-----------|------|---------|-------|-------|--------|
| Bubble | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| Selection | O(n²) | O(n²) | O(n²) | O(1) | ❌ |
| Insertion | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| Merge | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ |
| Quick | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ |
| Heap | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ |
| Counting | O(n + k) | O(n + k) | O(n + k) | O(k) | ✅ |

*k = value range · Quick uses middle-element pivot*

---

### Techniques — `algorithm.technique`

| Pattern | Typical time | Space | Module |
|---------|--------------|-------|--------|
| Two pointer | O(n) | O(1) | `TwoPointer` |
| Sliding window (fixed) | O(n) | O(1) | `SlidingWindow` |
| Sliding window (variable) | O(n) | O(1)–O(Σ) | `SlidingWindow` |
| Prefix sum query | O(1) | O(n) build | `PrefixSum` |
| Difference array update | O(1) | O(n) | `DifferenceArray` |
| Monotonic stack (NGE) | O(n) | O(n) | `MonotonicStack` |
| Fast / slow pointer | O(n) | O(1) | `FastSlowPointer` |

---

### Dynamic Programming — `algorithm.dp`

| Problem | Time | Space |
|---------|------|-------|
| Fibonacci / climbing stairs | O(n) | O(1) |
| House robber | O(n) | O(1) |
| Max subarray (Kadane) | O(n) | O(1) |
| 0/1 Knapsack | O(n · W) | O(n · W) |
| Unbounded knapsack | O(n · W) | O(W) |
| Coin change | O(n · amount) | O(amount) |
| LCS | O(m · n) | O(m · n) |
| LIS | O(n²) | O(n) |
| Edit distance | O(m · n) | O(m · n) |
| Unique paths | O(m · n) | O(m · n) |
| Min path sum | O(m · n) | O(m · n) |

---

### Greedy — `algorithm.greedy`

| Algorithm | Time | Notes |
|-----------|------|-------|
| Activity selection | O(n log n) | Sort by end time |
| Merge intervals | O(n log n) | Sort by start |
| Jump game | O(n) | Greedy farthest |
| Min coins | O(n · amount) | Delegates to DP `coinChange` |

---

### Backtracking — `algorithm.backtracking`

| Problem | Time | Space |
|---------|------|-------|
| Subsets | O(n · 2ⁿ) | O(n) |
| Permutations | O(n!) | O(n) |
| Combination sum | O(2ⁿ) worst | O(target) |
| N-Queens count | O(n!) | O(n) |

---

### Graph — `algorithm.graphalgo`

| Algorithm | Time | Space | Notes |
|-----------|------|-------|-------|
| Dijkstra | O(V²) | O(V) | Adjacency matrix; use `GraphAlgorithms.INF` |
| Topological sort | O(V²) | O(V) | Kahn's; binary adjacency |
| Cycle detect (directed) | O(V + E) | O(V) | 3-color DFS |
| Cycle detect (undirected) | O(V + E) | O(V) | DFS |

---

### Tree — `algorithm.treealgo`

| Algorithm | Time | Space |
|-----------|------|-------|
| Max depth | O(n) | O(h) stack |
| Is balanced | O(n) | O(h) stack |
| Diameter | O(n) | O(h) stack |
| Same tree | O(n) | O(h) stack |

*h = height · n = nodes*

---

### Bit — `algorithm.bit`

| Operation | Time |
|-----------|------|
| Count set bits | O(log n) |
| Is power of two | O(1) |
| Single number (XOR) | O(n) |
| Set / clear / toggle / test bit | O(1) |

---

### Math — `algorithm.math`

| Algorithm | Time |
|-----------|------|
| GCD (Euclidean) | O(log min(a,b)) |
| LCM | O(log min(a,b)) |
| Fast power (mod) | O(log exp) |
| Prime check | O(√n) |
| Sieve of Eratosthenes | O(n log log n) |

---

## Quick Comparison — Pick the Right Tool

| Need | Use | Why |
|------|-----|-----|
| FIFO | `LinkedQueue` / `CircularArrayQueue` | O(1) enqueue & dequeue |
| LIFO | `LinkedStack` / `ArrayStack` | O(1) push & pop |
| Always sorted min/max | `MinHeap` / `MaxHeap` | O(log n) insert, O(1) peek |
| Key → value lookups | `ChainedHashMap` | O(1) average |
| Connected components | `UnionFind` | O(α(n)) near-constant |
| Prefix / range sums (mutable) | `FenwickTree` | O(log n) update & query |
| Range min/max/sum queries | `SegmentTree` | O(log n) |
| Recency cache | `LRUCache` | O(1) get & put |
| Sorted order + balance | `AVLTree` | O(log n) guaranteed |
| Prefix string search | `Trie` | O(m) per character |

---

<div align="center">

[← Back to README](README.md)

</div>
