package iorichina.hellojava.hellosample.clone_graph;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import static org.junit.Assert.*;

public class SolutionTest {
    // 辅助方法：创建图
    private Node createGraph(int[][] adjList) {
        if (adjList == null || adjList.length == 0) return null;

        // 创建所有节点
        Node[] nodes = new Node[adjList.length + 1]; // 1-indexed
        for (int i = 1; i <= adjList.length; i++) {
            nodes[i] = new Node(i);
        }

        // 建立邻居关系
        for (int i = 0; i < adjList.length; i++) {
            int nodeVal = i + 1;
            for (int neighborVal : adjList[i]) {
                nodes[nodeVal].neighbors.add(nodes[neighborVal]);
            }
        }

        return nodes[1]; // 返回第一个节点
    }

    // 辅助方法：将图转为字符串表示
    private String graphToString(Node node) {
        if (node == null) return "null";

        StringBuilder sb = new StringBuilder();
        Set<Integer> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        visited.add(node.val);
        queue.add(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            sb.append("[").append(curr.val).append("]: [");
            for (Node neighbor : curr.neighbors) {
                sb.append(neighbor.val).append(", ");
            }
            if (!curr.neighbors.isEmpty()) {
                sb.setLength(sb.length() - 2); // 移除最后一个逗号和空格
            }
            sb.append("]\n");

            for (Node neighbor : curr.neighbors) {
                if (!visited.contains(neighbor.val)) {
                    visited.add(neighbor.val);
                    queue.add(neighbor);
                }
            }
        }

        return sb.toString();
    }

    // 辅助方法：检查两个图是否相等
    private boolean graphsEqual(Node g1, Node g2) {
        if (g1 == null && g2 == null) return true;
        if (g1 == null || g2 == null) return false;

        Set<Integer> visited1 = new HashSet<>();
        Set<Integer> visited2 = new HashSet<>();
        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();

        q1.add(g1);
        q2.add(g2);
        visited1.add(g1.val);
        visited2.add(g2.val);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Node n1 = q1.poll();
            Node n2 = q2.poll();

            if (n1.val != n2.val || n1.neighbors.size() != n2.neighbors.size()) {
                return false;
            }

            for (int i = 0; i < n1.neighbors.size(); i++) {
                Node neighbor1 = n1.neighbors.get(i);
                Node neighbor2 = n2.neighbors.get(i);
                if (!visited1.contains(neighbor1.val) && !visited2.contains(neighbor2.val)) {
                    visited1.add(neighbor1.val);
                    visited2.add(neighbor2.val);
                    q1.add(neighbor1);
                    q2.add(neighbor2);
                } else if (visited1.contains(neighbor1.val) && !visited2.contains(neighbor2.val) ||
                        !visited1.contains(neighbor1.val) && visited2.contains(neighbor2.val)) {
                    return false;
                }
            }
        }

        return q1.isEmpty() && q2.isEmpty();
    }

    // 测试空图
    @Test
    public void testCloneGraph_NullGraph() {
        Solution solution = new Solution();
        Node result = solution.cloneGraph(null);
        System.out.println("空图测试结果: " + (result == null ? "null" : result.val));
        assertNull("Expected null for empty graph", result);
    }

    // 测试单个节点图
    @Test
    public void testCloneGraph_SingleNode() {
        Solution solution = new Solution();
        Node input = new Node(1);
        System.out.println("单个节点图测试入参: \n" + graphToString(input));
        Node result = solution.cloneGraph(input);
        System.out.println("单个节点图测试结果: " + (result == null ? "null" : result.val));
        assertNotNull("Expected non-null result for single node graph", result);
        assertEquals("Expected node value 1", 1, result.val);
        assertTrue("Expected empty neighbors list for single node graph", result.neighbors.isEmpty());
    }

    // 测试简单连接图
    @Test
    public void testCloneGraph_SimpleGraph() {
        Solution solution = new Solution();
        int[][] adjList = {{2, 3}, {1}, {1}};
        Node input = createGraph(adjList);
        System.out.println("简单连接图测试入参: \n" + graphToString(input));
        Node result = solution.cloneGraph(input);
        System.out.println("简单连接图测试结果: \n" + graphToString(result));
        assertTrue("Expected graphs to be equal for simple graph case", graphsEqual(input, result));
    }

    // 测试复杂连接图
    @Test
    public void testCloneGraph_ComplexGraph() {
        Solution solution = new Solution();
        int[][] adjList = {{2, 3, 4}, {1, 3}, {1, 2, 4}, {1, 3}};
        Node input = createGraph(adjList);
        System.out.println("循环图测试入参: \n" + graphToString(input));
        Node result = solution.cloneGraph(input);
        System.out.println("复杂连接图测试结果: \n" + graphToString(result));
        assertTrue("Expected graphs to be equal for complex graph case", graphsEqual(input, result));
    }

    // 测试带有循环的图
    @Test
    public void testCloneGraph_CyclicGraph() {
        Solution solution = new Solution();
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node4.neighbors.add(node5);
        node5.neighbors.add(node6);
        node6.neighbors.add(node4);
        System.out.println("循环图测试入参: \n" + graphToString(node4));
        Node result = solution.cloneGraph(node4);
        System.out.println("循环图测试结果: \n" + graphToString(result));
        assertNotNull("Expected non-null result for cyclic graph", result);
        assertEquals("Expected node value 4 for cyclic graph result", 4, result.val);
        assertEquals("Expected one neighbor for cyclic graph result", 1, result.neighbors.size());
        assertEquals("Expected neighbor value 5 for cyclic graph result", 5, result.neighbors.get(0).val);
    }
}