<div align="center">

# AlgoCodeHub

**Every data structure & algorithm pattern — built from scratch in Java.**

[![Java 23](https://img.shields.io/badge/Java-23-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![Build JAR](https://github.com/OWNER/AlgoCodeHub/actions/workflows/build.yml/badge.svg)](https://github.com/OWNER/AlgoCodeHub/actions/workflows/build.yml)
[![DS 25+](https://img.shields.io/badge/DS-25%2B-blue?style=flat-square)](src/datastructure/)
[![Algo 10](https://img.shields.io/badge/Algo-10-green?style=flat-square)](src/algorithm/)
[![java.util](https://img.shields.io/badge/java.util-0-red?style=flat-square)](src/datastructure/)

```
foundation.*  ──►  datastructure.*  +  algorithm.*  ──►  program/
     DS · Algo factories              pure implementations        playgrounds
```

[Quick Start](#quick-start) · [Structure](#structure) · [Library & JAR](#library-and-jar) · [Whats Inside](#whats-inside) · [Complexity](COMPLEXITY.md) · [Changelog](CHANGELOG.md) · [Interview Value](#interview-value) · [Whats Next](#whats-next)

</div>

---

## Why this exists

Most people learn DSA by **calling** `java.util` and never seeing what's underneath.

**AlgoCodeHub flips that** — 25+ hand-written structures and 10 algorithm categories, wired through compact hub files so you can **instantiate, debug, extend, and explain** without rewriting boilerplate.

> *Don't just solve problems. Own the machinery.*

---

## Quick Start

```java
import foundation.ds.DS;
import foundation.algo.Algo;
import algorithm.technique.TwoPointer;
import algorithm.graphalgo.GraphAlgorithms;

// Data structures
var stack = DS.stack(1, 2, 3);
var tree  = DS.<Integer>avlTree();
var cache = DS.<String, Integer>lruCache(100);

// Algorithms
Algo.mergeSort().sort(new int[]{5, 2, 8, 1});
TwoPointer.twoSumSorted(new int[]{1, 2, 4, 6}, 8);

// Graph — use GraphAlgorithms.INF for no edge
int INF = GraphAlgorithms.INF;
GraphAlgorithms.dijkstra(new int[][]{{0, 4, INF}, {4, 0, 2}, {INF, 2, 0}}, 0);
```

**Run in Eclipse (Java 23):** `DSPlayground` · `AlgoPlayground`  
AlgoPlayground CLI: `all` · `search` · `sort` · `technique` · `dp` · `greedy` · `graph` · `tree` · `bit`

| Use case | Import |
|----------|--------|
| Create anything | `foundation.ds.DS` · `foundation.algo.Algo` |
| Solve problems | `datastructure.*` · `algorithm.*` directly |
| Learn by debugging | Breakpoints in `program/DSPlayground` or `AlgoPlayground` |

---

## Structure

```mermaid
flowchart LR
    F["foundation/<br/>DS · Algo hubs"]
    D["datastructure/<br/>list · heap · graph …"]
    A["algorithm/<br/>sort · dp · technique …"]
    P["program/<br/>playgrounds"]

    F --> D & A --> P
```

```
src/
├── foundation/ds/       DSInterface · DSAbstract · DS
├── foundation/algo/     AlgoInterface · AlgoAbstract · Algo
├── datastructure/       array · list · stack · queue · deque · tree · heap
│                        map · set · graph · advanced
├── algorithm/           search · sort · technique · dp · greedy · backtracking
│                        graphalgo · treealgo · bit · math
└── program/             DSPlayground · AlgoPlayground
```

Zero `java.util` collections · composition over reinvention · O(1) where it matters (LRU, tail pointers, Union-Find, rehashing)

---

## Library and JAR

### The pitch — why this exists alongside `java.util`

The JDK ships **production engines** — `HashMap`, `PriorityQueue`, `TreeMap`. Oracle will never add AVL trees, Fenwick trees, LRU wiring, monotonic-stack templates, or a knapsack cookbook to `java.util`. That's not their job.

**AlgoCodeHub is the layer the platform leaves open:**

| JDK gives you | AlgoCodeHub gives you |
|---------------|----------------------|
| Black-box collections tuned for apps | **Readable source** for every structure |
| One balanced map (`TreeMap`) | **BST + AVL + Trie** for learning |
| No segment/Fenwick trees | **Range-query structures** interviews ask about |
| No algorithm pattern library | **Two pointer, sliding window, DP, graph** modules |
| "Use it" | **Own it** — step into rehash, rotation, path compression |

> **Not a replacement for `java.util` in production.**  
> An **educational extension** for learners who won't rewrite a heap today but refuse a black box — plus niches the standard library will never ship.

**Who uses the JAR?**

- DSA students practicing patterns without rebuilding 25 structures first  
- Interview prep with **depth** (LRU, Union-Find, hash chaining you can explain)  
- Bootcamps enforcing **no `java.util` collections**  
- You — solving problems under `program/` with `DS.*` and `algorithm.*`

```java
// Add algocodehub-1.0.0.jar to your classpath, then:
import foundation.ds.DS;
import algorithm.dp.Knapsack;
import algorithm.technique.MonotonicStack;

var cache = DS.<String, Integer>lruCache(100);   // O(1) — source included
Knapsack.coinChange(new int[]{1, 2, 5}, 11);
MonotonicStack.nextGreaterElement(new int[]{2, 1, 2, 4, 3});
```

---

### Build the JAR (Gradle)

**Requires:** [JDK 23](https://openjdk.org/) — wrapper is included (`./gradlew` / `gradlew.bat`)

| Command | Output | Use |
|---------|--------|-----|
| `./gradlew jar` | `build/libs/algocodehub-1.0.0.jar` | **Library** — add to your project's classpath |
| `./gradlew fatJar` | `build/libs/algocodehub-1.0.0-all.jar` | **Runnable** — AlgoPlayground menu |
| `./gradlew fatJar -Pmain=program.DSPlayground` | same name, different entry | DS structure demos |
| `./gradlew runAlgo` | — | Run AlgoPlayground from source |
| `./gradlew runAlgo -Pargs="dp"` | — | Run one section |
| `./gradlew runDS` | — | Run DSPlayground |

**Run the fat JAR** (no Eclipse needed):

```bash
java -jar build/libs/algocodehub-1.0.0-all.jar
java -jar build/libs/algocodehub-1.0.0-all.jar search
java -jar build/libs/algocodehub-1.0.0-all.jar all
```

**Use as a library in another Java project:**

```bash
# Copy JAR into your project
cp build/libs/algocodehub-1.0.0.jar /path/to/YourProject/libs/
```

Eclipse → your project → **Build Path → Add External JARs** → select `algocodehub-1.0.0.jar`

Or Gradle consumer:

```kotlin
dependencies {
    implementation(files("libs/algocodehub-1.0.0.jar"))
}
```

---

### Download the JARs

**Easiest — GitHub Releases (recommended)**

After a push to `main`, open:

**Releases → [Latest JAR Build](https://github.com/OWNER/AlgoCodeHub/releases/tag/latest-jars)**

Under **Assets**, click to download:

| File | Use |
|------|-----|
| `algocodehub-1.0.0.jar` | Library — add to Eclipse classpath |
| `algocodehub-1.0.0-all.jar` | Runnable — `java -jar algocodehub-1.0.0-all.jar` |

> Replace `OWNER` with your GitHub username in links above.

**Versioned release** — push tag `v1.0.0` → JARs attach to that release instead.

**Alternative — Actions Artifacts**

Only works for pushes to `main` (not pull requests).  
Actions → green run → bottom **Artifacts** → click **`algocodehub-jars`**.

If the name is not clickable, you likely ran from a **PR** — use **Releases** instead.

### GitHub Actions (automatic build)

Every push to `main` triggers [`.github/workflows/build.yml`](.github/workflows/build.yml):

1. JDK 23 + Gradle on Ubuntu  
2. `./gradlew clean jar fatJar`  
3. JARs published to **Releases → Latest JAR Build**  
4. Also saved under **Actions → Artifacts** (main branch only)

**Tag a versioned release:**

```bash
git tag -a v1.0.0 -m "AlgoCodeHub v1.0.0"
git push origin v1.0.0
```

→ Creates release `v1.0.0` with both JARs attached.

---

### Release on GitHub (manual)

1. **Commit & push** all changes to `main`
2. **Build artifacts:**
   ```bash
   ./gradlew clean jar fatJar
   ```
3. **Tag the release:**
   ```bash
   git tag -a v1.0.0 -m "AlgoCodeHub v1.0.0"
   git push origin v1.0.0
   ```
4. GitHub → **Releases → Draft a new release**
   - Tag: `v1.0.0`
   - Title: `AlgoCodeHub v1.0.0`
   - Attach: `algocodehub-1.0.0.jar` + `algocodehub-1.0.0-all.jar`
   - Paste highlights from [CHANGELOG.md](CHANGELOG.md)
5. **Download on any machine** → add library JAR to classpath or run fat JAR with JDK 23

**No Gradle?** Eclipse → right-click project → **Export → Runnable JAR file** → choose `AlgoPlayground` or `DSPlayground` as main class.

---

## Whats Inside

<details>
<summary><b>Data structures</b> — <code>DS.*</code> factory</summary>

| Category | Implementation | Factory |
|----------|----------------|---------|
| Array | `CustomArray` | `DS.array()` |
| Lists | Singly · Doubly · Circular · Doubly-Circular | `DS.singlyList()` … |
| Stack / Queue / Deque | Linked + array variants, min/max PQ | `DS.stack()` · `DS.queue()` · `DS.deque()` |
| Tree | BST · AVL · Trie | `DS.bst()` · `DS.avlTree()` · `DS.trie()` |
| Heap | Min / Max | `DS.minHeap()` · `DS.maxHeap()` |
| Hash | Chained HashMap · HashSet | `DS.map()` · `DS.set()` |
| Graph | Adj list · matrix · Union-Find | `DS.graph()` · `DS.unionFind(n)` |
| Advanced | Fenwick · Segment Tree · LRU (O(1)) | `DS.fenwickTree(n)` · `DS.lruCache(n)` |

</details>

<details>
<summary><b>Algorithms</b> — static utilities + <code>Algo.*</code> sorters</summary>

| Category | Highlights |
|----------|------------|
| Search | Binary, bounds, search on answer |
| Sort | 7 sorters incl. in-place heap sort |
| Technique | Two pointer, sliding window, prefix/difference array, monotonic stack, fast/slow |
| DP | Knapsack, LCS, LIS, edit distance, coin change, grid paths |
| Greedy | Intervals, jump game, activity selection |
| Backtracking | Subsets, permutations, N-Queens |
| Graph / Tree | Dijkstra, topo sort, cycle detect · depth, diameter, balance |
| Bit / Math | XOR tricks, GCD, sieve, fast power |

</details>

---

## Interview Value

### What this library gives you

If you've read the source, you walk into interviews with real depth — not memorized slides.

| You can… | Because… |
|----------|----------|
| **Implement LRU cache** on a whiteboard | O(1) map + doubly linked list with node index — it's in the repo |
| **Explain hash map internals** | Separate chaining, 75% load factor, rehashing — readable source |
| **Discuss AVL / Union-Find trade-offs** | Full rotations, path compression + rank |
| **Recognize ~60% of medium LC patterns** | Two pointer, window, monotonic stack, core DP templates |
| **Recover from bugs under pressure** | You understand the machinery, not just the pattern name |

### Honest coverage

| Topic | Status |
|-------|--------|
| Arrays, two pointer, sliding window, binary search | ✅ |
| Hash map/set, stack/queue, heap, trie | ✅ |
| Linked list tricks, tree traversals, BST/AVL | ✅ |
| Graph BFS/DFS, topo sort, Union-Find, core DP, backtracking | ✅ |
| Bit tricks, segment tree, Fenwick, LRU | ✅ |
| Dijkstra (heap-optimized), LCA, string matching (KMP) | ⚠️ partial / not yet |
| MST, SCC, Bellman-Ford, lazy segment tree | ❌ planned |

**Verdict:** ⭐⭐⭐⭐⭐ for **understanding & follow-up questions** · ⭐⭐⭐ for **timed interview coding** on its own.

Not a FAANG shortcut — a **force multiplier**. Pair with 150–300 timed LeetCode/NeetCode problems. Use this repo to learn *why*; use LC stubs when the clock is running.

---

## Whats Next

Topic-wise problem packages under `program/` — each solution built on `DS.*` and `algorithm.*`:

`program/dp` · `program/graph` · `program/bt` · `program/hash` · `program/string` · `program/matrix` · `program/bit`

---

<div align="center">

<br/>

**Built for learners who read the source, not just the solution.**

```java
import foundation.ds.DS;
DS.avlTree().insert("you");
```

</div>
