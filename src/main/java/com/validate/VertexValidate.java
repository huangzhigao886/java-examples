package com.validate;

import com.validate.valid.VertexGraph;

import java.util.*;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/9
 * @Description:
 */
public class VertexValidate {

    public static void main(String[] args) {
        Map<String, List<Vertex>> maps = new HashMap<>();
    }


    /**
     * 节点校验
     *
     * @return
     */
    public static String validateVertex(Map<String, List<Vertex>> maps) throws VertexException {
        //统计所有的节点
        List<String> allVertexList = new ArrayList<>();
        for (Map.Entry<String, List<Vertex>> entry : maps.entrySet()) {
            for (Vertex vertex : entry.getValue()) {
                allVertexList.add(vertex.getVertexName());
            }
        }

        List<Vertex> sourceLists = maps.get("sources");
        List<Vertex> operatorLists = maps.get("operators");
        List<Vertex> sinkLists = maps.get("sinks");

        if (sourceLists == null && sourceLists.size() == 0) {
            throw new VertexException("缺失源点节点");
        }

        if (sinkLists == null && sinkLists.size() == 0) {
            throw new VertexException("缺失目标节点");
        }

        //验证源端
        validateSources(sourceLists, allVertexList);
        validateSinks(sinkLists, allVertexList);
        validateOperator(operatorLists, allVertexList);
        //验证是否存在环
        VertexGraph vertexGraph = new VertexGraph(VertexGraph.createFakeList());
        if (vertexGraph.isHasCycle()) {
            throw new VertexException("模型存在环结构");
        }
        if (!vertexGraph.isSchemaConsistent()) {
            throw new VertexException("前后字段不一致");
        }

        //针对groupBy和unique操作，需要判断
        for (int i = 0; i < operatorLists.size(); i++) {
            if (operatorLists.get(i).getOperatorType().equals("groupBy") || operatorLists.get(i).getOperatorType().equals("unique")) {
                //比较分组字段与输出字段是否一致
            }
        }


        //验证目标端 ,还需要校验目标表的字段与最后输出字段是否匹配


        //验证处理节点

        return null;
    }


    /**
     * source端验证
     *
     * @param vertexList
     * @param allVertexList
     * @return
     */
    public static void validateSources(List<Vertex> vertexList, List<String> allVertexList) throws VertexException {
        for (Vertex vertex : vertexList) {
            if (vertex.getFrom() != null || vertex.getFrom().size() > 0) {
                throw new VertexException(vertex.getVertexName() + "源端节点不能有输入节点");
            }
            for (String to : vertex.getTo()) {
                if (!allVertexList.contains(to)) {
                    throw new VertexException(vertex.getVertexName() + "的下一个节点" + to + "不存在");
                }
            }
        }
    }


    /**
     * sink端验证
     *
     * @param vertexList
     * @param allVertexList
     * @return
     */
    public static void validateSinks(List<Vertex> vertexList, List<String> allVertexList) throws VertexException {
        for (Vertex vertex : vertexList) {
            if (vertex.getTo() != null || vertex.getTo().size() > 0) {
                throw new VertexException(vertex.getVertexName() + "目标端节点不能有输出节点");
            }
            for (String from : vertex.getFrom()) {
                if (!allVertexList.contains(from)) {
                    throw new VertexException(vertex.getVertexName() + "的上一个节点" + from + "不存在");
                }
            }
        }
    }


    /**
     * 校验中间的处理节点
     *
     * @param vertexList
     * @param allVertexList
     */
    public static void validateOperator(List<Vertex> vertexList, List<String> allVertexList) throws VertexException {
        for (Vertex vertex : vertexList) {
            if (vertex.getOperatorType().equals("关联")) {
                if (vertex.getVertexName() == null || vertex.getFrom().size() != 2) {
                    throw new VertexException(vertex.getVertexName() + "节点异常,当前节点为关系型节点，异常原因可能是输入节点数量不等于2");
                }
            }
            if (vertex.getFrom() == null || vertex.getFrom().size() > 2) {
                throw new VertexException(vertex.getVertexName() + "节点异常,当前节点为非关系型节点，异常原因可能是输入节点数量不等于1");
            }
            for (String from : vertex.getFrom()) {
                if (!allVertexList.contains(from)) {
                    throw new VertexException(vertex.getVertexName() + "的上一个节点" + from + "不存在");
                }
            }
            for (String to : vertex.getTo()) {
                if (!allVertexList.contains(to)) {
                    throw new VertexException(vertex.getVertexName() + "的下一个节点" + to + "不存在");
                }
            }
        }
    }


    public void test() {

    }


}
