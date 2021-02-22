package com.validate.valid;

import com.validate.Vertex;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 节点Graph
 *
 * @Auther: huangzhigao
 * @Date: 2020/4/13
 * @Description:
 */
@Data
public class VertexGraph {

    private int V;

    private boolean hasCycle;

    /**
     * 链表
     */
    private TreeSet<Integer>[] adj;

    /**
     * 入读
     */
    private int[] inDegree;

    /**
     * 出度
     */
    private int[] outDegree;

    //拓扑排序的结果
    private List<Integer> res;


    /**
     * 节点名称和节点的对应，即：1->sort1;
     */
    private Map<String, Integer> map;

    private List<Vertex> list;

    /**
     * 前后字段是否一致
     */
    private boolean schemaConsistent;


    public VertexGraph(List<Vertex> list) {
        this.schemaConsistent = true;
        V = list.size();
        map = new HashMap<>(V);
        inDegree = new int[V];
        outDegree = new int[V];
        adj = new TreeSet[V];
        res = new ArrayList<>(V);
        List<String> from = list.get(2).getFrom();
        //初始化节点的入读，出度
        for (int i = 0; i < V; i++) {
            //节点名称和节点名的映射
            map.put(list.get(i).getVertexName(), i);
            if (CollectionUtils.isEmpty(list.get(i).getFrom())) {
                inDegree[i] = 0;
            } else {
                inDegree[i] = list.get(i).getFrom().size();

            }
            if (CollectionUtils.isEmpty(list.get(i).getTo())) {
                outDegree[i] = 0;
            } else {
                outDegree[i] = list.get(i).getTo().size();
            }

            adj[i] = new TreeSet<>();
        }

        //初始化treeSet链表
        for (int i = 0; i < V; i++) {
            List<String> to = list.get(i).getTo();
            if (null == to) {
                continue;
            }
            for (String vertexName : to) {
                adj[i].add(map.get(vertexName));
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        //将入读为0的加入队列
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                if (null != list.get(i).getTo()) {
                    //父节点的输入schema
                    List<String> inputSchema = list.get(i).getInputSchema();
                    List<String> toList = list.get(i).getTo();
                    for (String to : toList) {
                        List<String> toSchema = list.get(map.get(to)).getInputSchema();
                        if (CollectionUtils.isNotEmpty(toSchema)) {
                            toSchema.addAll(inputSchema);
                        }
                        list.get(map.get(to)).setOutputSchema(toSchema);
                    }
                }
            }
        }

        //进行环校验
        while (!queue.isEmpty()) {
            Integer remove = queue.remove();
            res.add(remove);
            List<String> inputSchema = list.get(remove).getOutputSchema();
            for (int w : adj(remove)) {
                inDegree[w]--;
                if (inDegree[w] == 0) {
                    queue.add(w);
                }
            }


            String operatorType = list.get(remove).getOperatorType();
            if (operatorType.equals("source")) {
                continue;
            }
            List<String> tmpSchema = new ArrayList<>();
            for (int i = 0; i < list.get(remove).getFrom().size(); i++) {
                Integer tmpParent = map.get(list.get(remove).getFrom().get(i));
                tmpSchema.addAll(list.get(tmpParent).getOutputSchema());
            }



            //然后进行规则里涉及到的字段进行校验
            for (String schema : list.get(remove).getRuleSchema()) {
                if (tmpSchema.contains(schema)) {
                    schemaConsistent = false;
                    break;
                }
            }

            if (!schemaConsistent) {
                break;
            }

            if (!UtilConstants.changeStructureOperaList.contains(operatorType)) {
                list.get(remove).setOutputSchema(inputSchema);
            }
        }


        if (schemaConsistent && res.size() != V) {
            hasCycle = true;
        }

    }


    /**
     * 展示图的邻接表
     *
     * @return
     */
    public String showGraph() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) {
            sb.append(i + ":");
            for (int w : adj[i]) {
                sb.append(String.format("%d ", w));
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    /**
     * 制造假节点数据
     *
     * @return
     */
    public static List<Vertex> createFakeList() {
        List<Vertex> list = new ArrayList<>();
        Vertex vertex1 = new Vertex();
        vertex1.setVertexName("a");
        vertex1.setFrom(null);
        vertex1.setTo(Stream.of("c").collect(Collectors.toList()));

        Vertex vertex2 = new Vertex();
        vertex2.setVertexName("b");
        vertex2.setFrom(Stream.of("d").collect(Collectors.toList()));
        vertex2.setTo(Stream.of("c").collect(Collectors.toList()));

        Vertex vertex3 = new Vertex();
        vertex3.setVertexName("c");
        vertex3.setFrom(Stream.of("a", "b").collect(Collectors.toList()));
        vertex3.setTo(Stream.of("d").collect(Collectors.toList()));

        Vertex vertex4 = new Vertex();
        vertex4.setVertexName("d");
        vertex4.setFrom(Stream.of("e", "c").collect(Collectors.toList()));
        vertex4.setTo(Stream.of("f", "b").collect(Collectors.toList()));

        Vertex vertex5 = new Vertex();
        vertex5.setVertexName("e");
        vertex5.setFrom(null);
        vertex5.setTo(Stream.of("d").collect(Collectors.toList()));


        Vertex vertex6 = new Vertex();
        vertex6.setVertexName("f");
        vertex6.setFrom(Stream.of("d").collect(Collectors.toList()));
        vertex6.setTo(Stream.of("g", "h").collect(Collectors.toList()));

        Vertex vertex7 = new Vertex();
        vertex7.setVertexName("g");
        vertex7.setTo(null);
        vertex7.setFrom(Stream.of("f").collect(Collectors.toList()));
        Vertex vertex8 = new Vertex();
        vertex8.setVertexName("h");
        vertex8.setTo(null);
        vertex8.setFrom(Stream.of("f").collect(Collectors.toList()));
        list.add(vertex1);
        list.add(vertex2);
        list.add(vertex3);
        list.add(vertex4);
        list.add(vertex5);
        list.add(vertex6);
        list.add(vertex7);
        list.add(vertex8);
        return list;
    }

    private void validateVertex(int v) {
        if (v > V || v < 0) {
            //代表顶点根本不存在于图中
            throw new IllegalArgumentException("vertex" + v + "is invalid");
        }
    }

    //判断V和W之间是否存在边
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    /**
     * 返回v指向的顶点
     *
     * @param v
     * @return
     */
    public TreeSet<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }


    /**
     * 返回入读
     *
     * @param v
     * @return
     */
    public int getInDegree(int v) {
        return inDegree[v];
    }


    public static void main(String[] args) {
        List<Vertex> fakeList = createFakeList();
        VertexGraph vertexGraph = new VertexGraph(fakeList);
//        System.out.println(vertexGraph.showGraph());
        System.out.println(vertexGraph.hasCycle);

    }
}
