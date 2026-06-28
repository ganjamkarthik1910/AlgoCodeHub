# Changelog

All notable changes to AlgoCodeHub are documented here.

Format based on [Keep a Changelog](https://keepachangelog.com/).

---

## [1.0.0] — 2026-06-27

First stable release.

### Added

- **Gradle build** — `.github/build/build.gradle.kts` for library JAR + runnable fat JAR
- **JPMS exports** — `module-info.java` for modular library use
- **Foundation layer** — `foundation.ds` and `foundation.algo` (interfaces, abstractions, factories)
- **25+ data structures** under `datastructure/` — array, lists, stack, queue, deque, tree, heap, map, set, graph, advanced
- **10 algorithm categories** under `algorithm/` — search, sort, technique, dp, greedy, backtracking, graphalgo, treealgo, bit, math
- **Playgrounds** — `DSPlayground` and `AlgoPlayground` (interactive + CLI args)
- **Docs** — `README.md`, `COMPLEXITY.md`

### Optimizations

- O(1) LRU cache (map + doubly linked list with node index)
- O(1) `peekLast` on tail-backed lists
- O(n/2) `get(i)` on doubly linked list
- In-place heap sort, Union-Find path compression + rank, hash map rehash at 75% load

### Fixed

- Greedy `minCoins` delegates to DP `coinChange` (correct for all coin systems)
- Dijkstra demo uses `GraphAlgorithms.INF` sentinel
- Edge-case guards in DP (empty array, zero-dimension grids)

---

## [Unreleased]

### Added

- **GitHub Actions** — automatic JAR build on push; attach to Release on `v*` tags
- **Gradle wrapper** — `.github/build/gradlew` for CI and local builds (run from that folder)
- **CODEOWNERS** — maintainer review required for `.github/` changes
- **SECURITY.md** — vulnerability reporting policy
- **Dependabot** — monthly GitHub Actions dependency updates

### Changed

- **Clean repo root** — only `src/`, `README.md`, `COMPLEXITY.md`, `LICENSE` at top level; build tooling under `.github/build/`
- **README** — requirements, get started, JAR download, structure, license, and interview coverage
- **CI security** — read-only default permissions; release jobs run in separate steps with elevated access only on trusted pushes

### Fixed

- `DSAbstract` — added missing `NodeAccess` import (cascade compile errors on fresh Eclipse import)
- `BinarySearchTree` / `AVLTree` — inner `Node` is non-static (static nested class cannot use outer `T`)

### Planned

- `program/dp`, `program/graph`, `program/bt` — topic-wise problem packages
- Heap-optimized Dijkstra, LCA, string matching (KMP)
