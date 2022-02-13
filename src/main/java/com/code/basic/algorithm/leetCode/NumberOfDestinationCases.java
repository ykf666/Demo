package com.code.basic.algorithm.leetCode;

import com.google.common.graph.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 你在一个城市里，城市由 n 个路口组成，路口编号为 0 到 n - 1 ，某些路口之间有 双向 道路。输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。
 * 给你一个整数 n 和二维整数数组 roads ，其中 roads[i] = [ui, vi, timei] 表示在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路。你想知道花费
 * 最少时间 从路口 0 出发到达路口 n - 1 的方案数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-arrive-at-destination
 * <p>
 * 示例1：
 * 输入：n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * 输出：4
 * 解释：从路口 0 出发到路口 6 花费的最少时间是 7 分钟。
 * 四条花费 7 分钟的路径分别为：
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 * <p>
 * 示例2：
 * 输入：n = 2, roads = [[1,0,10]]
 * 输出：1
 * 解释：只有一条从路口 0 到路口 1 的路，花费 10 分钟。
 * <p>
 * Created by yankefei on 2022/2/12.
 */
public class NumberOfDestinationCases {

    public static void main(String[] args) {
        MutableValueGraph<Integer, Integer> graph = buildUndirectedGraph();
        System.out.println(graph.toString());

        MyGraph<Integer> myGraph = new MyGraph(7);
        for (int i = 0; i < 7; i++) {
            myGraph.insertVertex(i);
        }
        myGraph.putEdge(0, 6, 7);
        myGraph.putEdge(0, 1, 2);
        myGraph.putEdge(1, 2, 3);
        myGraph.putEdge(6, 3, 3);
        myGraph.putEdge(3, 5, 1);
        myGraph.putEdge(6, 5, 1);
        myGraph.putEdge(2, 5, 1);
        myGraph.putEdge(0, 4, 5);
        myGraph.putEdge(4, 6, 2);
        System.out.println(myGraph.toString());

    }

    private static String BreadthFirstSearch(MutableValueGraph graph) {

        return null;
    }

    private static MutableValueGraph<Integer, Integer> buildUndirectedGraph() {
        //使用guava构建带权无向图
        MutableValueGraph<Integer, Integer> graph =
                ValueGraphBuilder.undirected().allowsSelfLoops(false).nodeOrder(ElementOrder.insertion()).build();
        graph.putEdgeValue(0, 6, 7);
        graph.putEdgeValue(0, 1, 2);
        graph.putEdgeValue(1, 2, 3);
        graph.putEdgeValue(6, 3, 3);
        graph.putEdgeValue(3, 5, 1);
        graph.putEdgeValue(6, 5, 1);
        graph.putEdgeValue(2, 5, 1);
        graph.putEdgeValue(0, 4, 5);
        graph.putEdgeValue(4, 6, 2);
        return graph;
    }

    public static class MyGraph<E> {

        private int edgeNums;

        private Set<E> vertexSet;

        private int[][] martex;

        public MyGraph(int vertexNums) {
            this.vertexSet = new HashSet<>(vertexNums);
            this.martex = new int[vertexNums][vertexNums];
        }

        public void putEdge(int i, int j, int weight) {
            if (i < 0 || j < 0 || weight <= 0) {
                throw new IllegalArgumentException();
            }
            //对称矩阵
            martex[i][j] = weight;
            martex[j][i] = weight;
            this.edgeNums++;
        }

        public void insertVertex(E vertex) {
            this.vertexSet.add(vertex);
        }

        public String getEdges() {
            StringBuilder builder = new StringBuilder();
            int num = this.vertexSet.size();
            for (int i = 0; i < num; i++) {
                for (int j = i; j < num; j++) {
                    if (this.martex[i][j] != 0) {
                        if (builder.length() != 0) {
                            builder.append(", ");
                        }
                        builder.append("[" + i + ", " + j + "]=" + this.martex[i][j]);
                    }
                }
            }
            return builder.toString();
        }

        @Override
        public String toString() {
            return "vertexNums=" + vertexSet.size() +
                    ", edgeNums=" + edgeNums +
                    ", vertexSet=" + Arrays.toString(vertexSet.toArray()) +
                    ", edges={" + getEdges() +
                    '}';
        }
    }

    public static class Edge<E> {
        private E v1;
        private E v2;
        private int weight;

        public Edge(E v1, E v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }

}
