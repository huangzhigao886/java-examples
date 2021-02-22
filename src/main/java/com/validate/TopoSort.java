package com.validate;

import com.validate.valid.VertexGraph;
import lombok.Data;


/**
 * @Auther: huangzhigao
 * @Date: 2020/4/13
 * @Description:
 */
@Data
public class TopoSort {

    private VertexGraph graph;
    //初始化一个数组，代表每个节点都没被访问过
    private boolean[] visited;

    //是否在当前路径下
    private boolean[] onPath;
    //图中有无环
    private boolean hasCycle;

    public TopoSort(VertexGraph graph) {

        this.graph = graph;
        visited = new boolean[graph.getV()];
        onPath = new boolean[graph.getV()];
        //因为会出现连通图的情况，即有一个顶点是跟其他顶点都没关系的顶点
        for (int v = 0; v < graph.getV(); v++) {
            //如果当前节点被访问过就不再访问
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }


    /**
     * 深度优先遍历核心
     *
     * @param v 某一个顶点
     */
    public boolean dfs(int v, int parent) {
        //当遍历到该节点，说明该节点已经被遍历
        visited[v] = true;
        //标记
        onPath[v] = true;
        //遍历所有与该节点有关的所有节点
        for (int w : graph.adj(v)) {
            //如果这个节点没有被遍历过，这遍历它下的所有节点
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            } else if (onPath[w]) {
                //如果与当前节点相邻的节点并不是当前节点的上一个节点，则代表有环
                return true;
            }
        }
        onPath[v] = false;

        return false;
    }

    public static void main(String[] args) {

        VertexGraph graph = new VertexGraph(VertexGraph.createFakeList());
        TopoSort graphDfs = new TopoSort(graph);
        System.out.println(graphDfs.isHasCycle());

    }


}
