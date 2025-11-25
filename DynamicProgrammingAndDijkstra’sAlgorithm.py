"""
Name/Author: Zoe Yow Cui Yi
Student ID: 33214476
FIT2004 Assignment 1
"""

# ======================================================================================================================
# Q1
def fuse(fitmons: list) -> int:
    """
    This function fuses all fitmons together into one fitmon where that one fitmon's cuteness_score is the largest.
    Written by Zoe Yow

    Pre-condition: Fitmons list must have a length >= 1
    Post-condition: Last fitmon fused must have the largest cuteness_score

    Input:
        fitmons: A list of lists that includes each fitmon's left_affinity, cuteness_score, and right_affinity.
    Return:
        cuteness_score: The largest non-zero positive integer after fusing all fitmons together

    Time complexity:
        Best case analysis:
            O(N^3), where N is the number of elements in the fitmons list.
            Looping through the fitmons list 3 times will always occur.
        Worst case analysis:
            O(N^3), where N is the number of elements in the fitmons list.
            Looping through the fitmons list 3 times will always occur.
    Space complexity:
        Input space analysis:
            O(N), where N is the number of elements in the fitmons list.
        Aux space analysis:
            O(N^2), where N is the number of elements in the fitmons list.
            The table size to save all fused fitmons is N*N
    """
    # Get the length of the fitmons list
    n = len(fitmons)

    # Initialize a table to store the maximum cuteness_score for each sub-problem
    memo = [[0] * n for _ in range(n)]

    # set the original fitmons into the memory table at their respective position, e.g. fitmons[1] will be placed in memory[1][1]
    for i in range(n):
        memo[i][i] = fitmons[i]

    # Fill the table using bottom-up dynamic programming approach
    for length in range(2, n + 1):
        for i in range(n - length + 1):
            j = i + length - 1

            # Find the maximum cuteness score for fusing fitmons[i] to fitmons[j]
            memo[i][j] = [0, 0, 0]
            affinity_left = 0
            max_cuteness = 0
            affinity_right = 0
            for k in range(i, j):
                affinity_left = memo[i][k][0]

                affinity_right = memo[k + 1][j][2]

                cuteness_score = int(memo[i][k][2] * memo[i][k][1] + memo[k + 1][j][1] * memo[k + 1][j][0])

                if cuteness_score > max_cuteness:
                    max_cuteness = cuteness_score

            memo[i][j] = [affinity_left, max_cuteness, affinity_right]

    # The maximum cuteness score for the entire fusion process is stored at dp[0][n - 1]
    return memo[0][n - 1][1]


# ======================================================================================================================
# Q2
class Vertex:
    """
    Vertex class:
        Creates a vertex object
        Adds edges to the vertex through method: add_edge
    """
    def __init__(self, tree_id: int) -> None:
        """
        Initialises the vertex and its edges, discovered, visited, time_taken and previous.
        :param tree_id: It is the vertex's id when initialised
        :return: None
            Time complexity: O(1)
            Aux space complexity: O(1)
        """
        self.id = tree_id

        # list
        self.edges = []

        # for traversal
        self.discovered = False
        self.visited = False

        # for time (weight)
        self.time_taken = float('inf')

        # for backtracking
        self.previous = None

    def add_edge(self, edge) -> None:
        """
        Adds an edge to the vertex.
        :param edge: An edge object that will be added to the vertex
        :return: None
            Time complexity: O(1)
            Aux space complexity: O(1)
        """
        # add edges to the vertex
        self.edges.append(edge)

    def __str__(self):
        return_string = str(self.id)
        return return_string


class Edge:
    """
    Edge class:
        Creates an edge object
    """
    def __init__(self, u: Vertex, v: Vertex, w: int) -> None:
        """
        Initialises the edge object from vertex u to vertex v with a weight of w.
        :param u: u is the first vertex
        :param v: v is the second vertex
        :param w: w is the weight of the edge (time)
        :return: None
            Time complexity: O(1)
            Aux space complexity: O(1)
        """
        self.u = u
        self.v = v
        self.w = w

    def __str__(self):
        return_string = str(self.u) + ',' + str(self.v) + ',' + str(self.w)
        return return_string


class MinHeap:
    def __init__(self) -> None:
        """
        Initialises the heap and its length
        :input: no input
        :return: None
            Time complexity: O(1)
            Aux space complexity: O(1)
        """
        self.length = 0
        self.heap = [None]

    def __len__(self) -> int:
        """
        Returns the length of the heap
        :input: no input
        :return: int
            Time complexity: O(1)
            Aux space complexity: O(10
        """
        return self.length

    def is_full(self) -> bool:
        """
        Determines if the heap is full.
        :input: no input
        :return: bool
            Time complexity: O(1)
            Aux space complexity: O(1)
        """
        return self.length + 1 == len(self.heap)

    def rise(self, k: int) -> None:
        """
        Rise element at index k to its correct position
        :input: k is an index where the item is.
        :pre: 1 <= k <= self.length
        :return: None
            Time complexity: O(log |N|) where N is the number of elements in the heap
            Aux space complexity: O(1)
        """
        item = self.heap[k]
        while k > 1 and item[1] < self.heap[k // 2][1]:
            self.heap[k] = self.heap[k // 2]
            k = k // 2
        self.heap[k] = item

    def add(self, tree: Vertex, time: int) -> None:
        """
        Adds tree and time as a tuple to the heap and swaps elements while rising.
        :input: tree is the vertex and time is the vertex's time_taken
        :return: None
            Time complexity: O(log|N|) where N is the number of elements in the heap
            Aux space complexity: O(1)
        """
        self.length += 1
        element = (tree, time)
        self.heap.append(element)
        self.rise(self.length)

    def smallest_child(self, k: int) -> int:
        """
        Returns the index of k's child with the smallest value.
        :input: k is the index where the element is.
        :pre: 1 <= k <= self.length // 2
        :return: int
            Time complexity: O(N) where N is the number of elements in the heap
            Aux space complexity: O(1)
        """
        if 2 * k == self.length or \
                self.heap[2 * k][1] < self.heap[2 * k + 1][1]:
            return 2 * k
        else:
            return 2 * k + 1

    def sink(self, k: int) -> None:
        """
        Make the element at index k sink to the correct position.
        :input: k is the index where the element is
        :pre: 1 <= k <= self.length
        :return: None
            Time complexity: O(log |N|) where N is the number of elements in the heap
            Aux space complexity: O(1)
        """
        item = self.heap[k]

        while 2 * k <= self.length:
            min_child = self.smallest_child(k)
            if item[1] <= self.heap[min_child][1]:
                break
            self.heap[k] = self.heap[min_child]
            k = min_child

        self.heap[k] = item

    def serve(self) -> Vertex:
        """
        Swaps the first element with the last element and pops it out.
        It then performs sink to the last element that has been swapped in the heap.
        :input: None
        :return: Vertex, the first element (tree, time_taken) in the heap
            Time complexity: O(log|N|) where N is the number of elements in the heap
            Aux space complexity: O(1)
        """
        temp = self.heap[1]
        self.heap[1] = self.heap[self.length]
        self.heap[self.length] = temp

        self.length -= 1
        self.sink(1)
        queue = self.heap.pop()

        return queue


class TreeMap:
    """
    TreeMap class:
        Creates a graph that is an adjacency list contains trees and roads in the Delulu Forest. The graph also contains
            a multiverse graph that is connected by the solulu trees and their claw time. This class also contains a
            method that gets the index of the tree_id given: get_index. It also contains a method that sets all its
            vertices' discovered and visited to False: reset. The last method has is: escape, where it uses dijkstra
            algorithm to find the shortest way out with having a solulu tree destroyed.
    """

    def __init__(self, roads, solulus):
        """
        This init function initialises the graph using an adjacency list where the roads are the edges and the trees
            are the vertices. It also initialises the multiverse graph using an adjacency list where the solulus are the
            edges that connects the graph wit the multiverse graph.
        Written by Zoe Yow.

        Pre-condition:
            Length of roads must be >= 1 and time_taken must be >= 0
        Post-condition:
            self.trees should contain all vertices representing trees in the forest, with each tree having its
            associated edges. The first graph should be correctly connected to the multiverse graph based on the solulu
            trees.

        Input:
            roads:
                A list of tuples that contains edges that connects vertex u to vertex v with a time of w in the graph.
            solulus:
                A list of tuples that contains edges that connects vertex u to the multiverse's vertex v with a claw
                time of w.
        Return:
            None

        Time complexity:
            Best case analysis:
                O(|T|+|R|), where T is the number of unique trees in roads and R is the number of roads.
            Worst case analysis:
                O(|T|+|R|), where T is the number of unique trees in roads and R is the number of roads.
        Space complexity:
            Input space analysis:
                O(|T|+|R|), where T is the number of unique trees in roads and R is the number of roads.
                At most, the size of solulus is T.
            Aux space analysis::
                O(|T|+|R|), where T is the number of unique trees in roads and R is the number of roads.
                The multiverse graph has the exact same vertices and edges as the first graph, and at most, the size of
                solulus is T.
        """
        # find the number of trees there are in the forest
        self.max_trees = 0
        for road in roads:
            if road[0] > self.max_trees:
                self.max_trees = road[0]
            if road[1] > self.max_trees:
                self.max_trees = road[1]
        self.max_trees += 1

        # initialise a vertex at each tree
        self.trees = [None] * self.max_trees * 2
        for i in range(self.max_trees):
            self.trees[i] = Vertex(i)
            # for multiverse graph
            self.trees[i+self.max_trees] = Vertex(i+self.max_trees)

        # find the edges for the trees
        for road in roads:
            u = road[0]
            v = road[1]
            w = road[2]
            edge = Edge(self.trees[u], self.trees[v], w)
            self.trees[u].add_edge(edge)

            # for multiverse graph
            u = road[0] + self.max_trees
            v = road[1] + self.max_trees
            edge = Edge(self.trees[u], self.trees[v], w)
            self.trees[u].add_edge(edge)

        # for solulu trees to connect the first graph to the multiverse graph
        for solulu in solulus:
            tree = solulu[0]
            claw_time = solulu[1]
            teleport_to = solulu[2] + self.max_trees
            edge = Edge(self.trees[tree], self.trees[teleport_to], claw_time)
            self.trees[tree].add_edge(edge)

        # for index mapping
        self.tree_index_map = [tree.id for tree in self.trees]

    def get_index(self, tree_id) -> int:
        """
        Gets the index of the tree_id given.
        :param tree_id: The vertex id that will be used to find the index of.
        :return: the index of the tree_id given
            Time complexity: O(N) where N is the number of tree_id in the tree_index_map
            Aux space complexity: O(1)
        """
        return self.tree_index_map.index(tree_id)

    def reset(self):
        """
        Resets all the vertices discovered and visited to False.
        :param: None
        :return: None
            Time complexity: O(N) where N is the number of trees in self.trees
            Aux space complexity: O(1)
        """
        for tree in self.trees:
            tree.discovered = False
            tree.visited = False

    def escape(self, start, exits):
        """
        This escape function uses Dijkstra algorithm to find the shortest path from the start to the exit where there
            may be multiple exits, and it chooses the optimal exit with the shortest time with one solulu tree being
            destroyed.
        Written by Zoe Yow.

        Pre-condition:
            start must be >= 0 and exits must have a size >= 0.
        Post-condition:
            return final_route and min_time where it must be the shortest path with one solulu tree being destroyed.
            If not, return None.

        Input:
            start: start is the starting tree of the graph.
            exits: exits is the list of exits that is used to find the shortest exit path.
        Return:
            (min_time, final_route):
            min_time:
                The shortest time to get from the start to one of the exit with one solulu tree being destroyed.
            final_route:
                The route that gives the shorted time to get from the start to one of the exit with one solulu tree
                being destroyed.

        Time complexity:
            Best case analysis:
                O(|R|log|T|) where R is the number of roads and T is the number of unique trees in roads.
            Worst case analysis:
                O(|R|log|T|) where R is the number of roads and T is the number of unique trees in roads.
        Space complexity:
            Input space analysis:
                O(|T|), where T is the number of unique trees in roads.
                At most, size of exits is T.
            Aux space analysis:
                O(|T|+|R|), where T is the number of unique trees in roads and R is the number of roads.
        """
        # get the starting tree index (int)
        start_tree_index = self.get_index(start)

        # get the starting tree (vertex) and change its time_taken to 0
        start_tree = self.trees[start_tree_index]
        start_tree.time_taken = 0

        # initialise a min heap for dijkstra
        discovered = MinHeap()
        # add the source vertex into the min heap
        discovered.add(start_tree, start_tree.time_taken)

        # traverse through the graph
        while len(discovered) > 0:
            u = discovered.serve()

            # perform edge relaxation
            for edge in u[0].edges:
                v = edge.v
                if v.discovered == False:
                    # have been discovered so update it
                    v.discovered = True
                    # update time taken
                    v.time_taken = u[0].time_taken + edge.w
                    # update previous for backtracking
                    v.previous = u[0]
                    # add to min heap
                    discovered.add(v, v.time_taken)
                elif v.visited == False:
                    if v.time_taken > u[0].time_taken + edge.w:
                        # update time taken
                        v.time_taken = u[0].time_taken + edge.w
                        # update previous
                        v.previous = u[0]
                        # update heap
                        discovered.add(v, v.time_taken)

        min_time = float('inf')
        final_route = []
        for exit in exits:
            # get exit tree index of second graph
            exit_tree_index = self.get_index(exit + self.max_trees)
            # get exit tree from second graph
            exit_tree = self.trees[exit_tree_index]

            # traverse backwards from exit tree
            current_tree = exit_tree
            # exit tree already calculated total time so initialise total time to exit_tree.time_taken
            total_time = current_tree.time_taken
            # to backtrack the trees from the exit tree
            route = []
            route.append(current_tree.id - self.max_trees)

            # backtrack until the starting tree
            while current_tree != start_tree:
                if (self.get_index(current_tree.id) - self.max_trees) >= 0:
                    current_tree_index = self.get_index(current_tree.id) - self.max_trees
                else:
                    current_tree_index = self.get_index(current_tree.id)

                # checks if the current tree has a previous, if not go to next exit
                if current_tree.previous is not None:
                    previous_tree_index = self.get_index(current_tree.previous.id)

                    # add to route only if it did not teleport from the first graph to itself in the multiverse graph
                    if previous_tree_index != current_tree_index:
                        current_tree = current_tree.previous

                        if (current_tree.id - self.max_trees) >= 0:
                            route.append(current_tree.id - self.max_trees)
                        else:
                            route.append(current_tree.id)

                    # else, go to the tree's previous-previous
                    else:
                        current_tree = current_tree.previous.previous

                        # check if it has a previous, if not go to next exit
                        if current_tree is not None:
                            route.append(current_tree.id)
                        else:
                            current_tree = start_tree
                else:
                    current_tree = start_tree

            # find optimal route and optimal time taken to exit using the route
            if total_time < min_time:
                min_time = total_time
                final_route = route
                final_route.reverse()

        # reset the graph after traversal
        self.reset()

        # if optimal time is still infinity, then return None, else return the optimal time and its route as a tuple
        if min_time == float('inf'):
            return None
        return (min_time, final_route)
