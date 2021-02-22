package com.model;

import com.enums.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;

/**
 * 任务描述类<BR>
 * 此类为任务模型的核心类，将单个数据处理的任务分为通用配置、c、Sources、Operators、Sinks和Streams六大部分。<BR>
 * <ul>
 * <li>通用配置：包含任务名，任务类型，配置信息、数据错误处理原则、检查点配置等任务全局参数</li>
 * <li>公共BEAN：定义后续数据处理节点中可以使用到的通用bean组件，主要用于定义Schema，复杂构造参数等</li>
 * <li>Sources：用于描述数据任务的数据源信息，单个任务可以包含1~N个数据源</li>
 * <li>Operators：用于定义数据任务中对任务的处理部分，可以有0~N个</li>
 * <li>Sinks：用于定义数据任务的输出部分，单个任务可以包含1~N个输出组件</li>
 * <li>streams：用于定义数据任务的流信息。Sources、Operators和Sinks是任务图中的定点，而Stream是图的边，串联各个顶点后形成完整的DAG图</li>
 * </ul>
 * <BR>
 * 所有的顶点必须在某条边中用到至少一次，不允许出现没有被串联起来的孤立的定点。每条完整的图路径必须是从Sources出发，到Sinks结束。
 *
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public class JobDef {
    private static final Logger LOG = LoggerFactory.getLogger(JobDef.class);

    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务类型，当前仅支持BATCH和STREAM两种类型
     */
    private JobType jobType;
    /**
     * 当任务为STEAM类型时，时间窗口采用何种时间类型
     */
    private TimeType timeOfData;
    /**
     * 检查点保存时间间隔，任务为STEAM类型时有效
     */
    private int checkPointInterval;
    /**
     * 检查点保存原则
     */
    private CheckPointPolicy checkPointType;
    /**
     * 数据处理出现错误时的处理原则，可以是跳过次数据或者异常终止
     */
    private OnErrorPolicy onErrorPolicy = OnErrorPolicy.SKIP;

    /**
     * 公共BEAN
     */
    private Map<String, BeanDef> componentMap = new LinkedHashMap<String, BeanDef>(); // not required
    /**
     * 导入其他配置文件，当前不使用
     */
    private List<IncludeDef> includes; // not required
    /**
     * 通用任务配置，比如缓存类型，路径等
     */
    private Map<String, Object> config = new HashMap<String, Object>();
    /**
     * 数据源节点
     */
    private Map<String, SourceDef> sourceMap = new LinkedHashMap<>();
    /**
     * 数据处理节点
     */
    private Map<String, OperatorDef> operatorMap = new LinkedHashMap<>();
    /**
     * 数据输出节点
     */
    private Map<String, SinkDef> sinkMap = new LinkedHashMap<>();
    /**
     * 数据处理流
     */
    private List<StreamDef> streams = new ArrayList<StreamDef>();

    /**
     * 所有节点，用于构建DAG图
     */
    private transient Map<String, BeanDef> allNodeMap = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the name of the topology.
     *
     * @param name     topology name
     * @param override whether to override if already set
     */
    public void setName(String name, boolean override) {
        if (this.name == null || override) {
            this.name = name;
        } else {
            LOG.warn("Ignoring attempt to set property 'name' with override == false.");
        }
    }

    /**
     * Returns all source components.
     *
     * @return source components
     */
    public List<SourceDef> getSources() {
        ArrayList<SourceDef> retval = new ArrayList<>();
        retval.addAll(this.sourceMap.values());
        return retval;
    }

    /**
     * Set source definitions.
     *
     * @param sources source definitions
     */
    public void setSources(List<SourceDef> sources) {
        this.sourceMap = new LinkedHashMap<>();
        for (SourceDef source : sources) {
            this.sourceMap.put(source.getId(), source);
        }
    }

    /**
     * Returns sink definitions.
     *
     * @return sink definitions
     */
    public List<SinkDef> getSinks() {
        ArrayList<SinkDef> retval = new ArrayList<>();
        retval.addAll(this.sinkMap.values());
        return retval;
    }

    /**
     * Sets sink definitions.
     *
     * @param sinks sink definitions
     */
    public void setSinks(List<SinkDef> sinks) {
        this.sinkMap = new LinkedHashMap<>();
        for (SinkDef sink : sinks) {
            this.sinkMap.put(sink.getId(), sink);
        }
    }

    /**
     * Returns all operator components.
     *
     * @return operator components
     */
    public List<OperatorDef> getOperators() {
        ArrayList<OperatorDef> retval = new ArrayList<>();
        retval.addAll(this.operatorMap.values());
        return retval;
    }

    /**
     * Set operator definitions.
     *
     * @param operators operator definitions
     */
    public void setOperators(List<OperatorDef> operators) {
        this.operatorMap = new LinkedHashMap<>();
        for (OperatorDef operator : operators) {
            this.operatorMap.put(operator.getId(), operator);
        }
    }

    public List<StreamDef> getStreams() {
        return streams;
    }

    public void setStreams(List<StreamDef> streams) {
        this.streams = streams;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    /**
     * Returns a list of all component definitions.
     *
     * @return components
     */
    public List<BeanDef> getComponents() {
        ArrayList<BeanDef> retval = new ArrayList<BeanDef>();
        retval.addAll(this.componentMap.values());
        return retval;
    }

    /**
     * Sets the list of component definitions.
     *
     * @param components components definitions
     */
    public void setComponents(List<BeanDef> components) {
        this.componentMap = new LinkedHashMap<>();
        for (BeanDef component : components) {
            this.componentMap.put(component.getId(), component);
        }
    }

    public List<IncludeDef> getIncludes() {
        return includes;
    }

    public void setIncludes(List<IncludeDef> includes) {
        this.includes = includes;
    }

    public SourceDef getSourceDef(String id) {
        return this.sourceMap.get(id);
    }

    public SinkDef getSinkDef(String id) {
        return this.sinkMap.get(id);
    }

    public OperatorDef getOperatorDef(String id) {
        return this.operatorMap.get(id);
    }

    public BeanDef getComponent(String id) {
        return this.componentMap.get(id);
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public TimeType getTimeOfData() {
        return timeOfData;
    }

    public void setTimeOfData(TimeType timeOfData) {
        this.timeOfData = timeOfData;
    }

    public int getCheckPointInterval() {
        return checkPointInterval;
    }

    public void setCheckPointInterval(int checkPointInterval) {
        this.checkPointInterval = checkPointInterval;
    }

    public CheckPointPolicy getCheckPointType() {
        return checkPointType;
    }

    public void setCheckPointType(CheckPointPolicy checkPointType) {
        this.checkPointType = checkPointType;
    }

    /**
     * Adds a list of operator definitions. Optionally overriding existing
     * definitions if one with the same ID already exists.
     *
     * @param operators operator definitions
     * @param override  whether or not to override existing definitions
     */
    public void addAllOperators(List<OperatorDef> operators, boolean override) {
        for (OperatorDef operator : operators) {
            String id = operator.getId();
            if (this.operatorMap.get(id) == null || override) {
                this.operatorMap.put(operator.getId(), operator);
            } else {
                LOG.warn("Ignoring attempt to create operator '{}' with override == false.", id);
            }
        }
    }

    /**
     * Adds a list of source definitions. Optionally overriding existing definitions
     * if one with the same ID already exists.
     *
     * @param sources  source definitions
     * @param override whether or not to override existing definitions
     */
    public void addAllSources(List<SourceDef> sources, boolean override) {
        if (CollectionUtils.isEmpty(sources)) {
            return;
        }
        for (SourceDef source : sources) {
            String id = source.getId();
            if (this.sourceMap.get(id) == null || override) {
                this.sourceMap.put(source.getId(), source);
            } else {
                LOG.warn("Ignoring attempt to create source '{}' with override == false.", id);
            }
        }
    }

    /**
     * Adds a list of sink definitions. Optionally overriding existing definitions
     * if one with the same ID already exists.
     *
     * @param sinks    sink definitions
     * @param override whether or not to override existing definitions
     */
    public void addAllSinks(List<SinkDef> sinks, boolean override) {
        if (CollectionUtils.isEmpty(sinks)) {
            return;
        }
        for (SinkDef sink : sinks) {
            String id = sink.getId();
            if (this.sinkMap.get(id) == null || override) {
                this.sinkMap.put(sink.getId(), sink);
            } else {
                LOG.warn("Ignoring attempt to create sink '{}' with override == false.", id);
            }
        }
    }

    /**
     * Adds a list of component definitions. Optionally overriding existing
     * definitions if one with the same ID already exists.
     *
     * @param components component definitions
     * @param override   whether or not to override existing definitions
     */
    public void addAllComponents(List<BeanDef> components, boolean override) {
        if (CollectionUtils.isEmpty(components)) {
            return;
        }
        for (BeanDef bean : components) {
            String id = bean.getId();
            if (this.componentMap.get(id) == null || override) {
                this.componentMap.put(bean.getId(), bean);
            } else {
                LOG.warn("Ignoring attempt to create component '{}' with override == false.", id);
            }
        }
    }

    /**
     * Adds a list of stream definitions. Optionally overriding existing definitions
     * if one with the same ID already exists.
     *
     * @param streams  stream definitions
     * @param override whether or not to override existing definitions (currently
     *                 ignored)
     */
    public void addAllStreams(List<StreamDef> streams, boolean override) {
        if (CollectionUtils.isEmpty(streams)) {
            return;
        }
        this.streams.addAll(streams);
    }

    public void addAllConfig(Map<String, Object> otherConfig, boolean override) {
        if (override) {
            config.putAll(otherConfig);
        } else {
            for (Entry<String, Object> entry : otherConfig.entrySet()) {
                if (config.containsKey(entry.getKey())) {
                    LOG.warn("Ignoring attempt to set topology config property '{}' with override == false",
                            entry.getKey());
                } else {
                    config.put(entry.getKey(), otherConfig.get(entry.getValue()));
                }
            }
        }
    }

    /**
     * Determines is this represents a valid Topology.
     *
     * @return true if valid
     */
    public boolean validate() {
        boolean hasSources = this.sourceMap != null && this.sourceMap.size() > 0;
        boolean hasSinks = this.sinkMap != null && this.sinkMap.size() > 0;
        boolean hasStreams = this.streams != null && this.streams.size() > 0;

        // job must has sources and sinks,
        // operator is not necessary when data convert is passed
        if (!hasSources || !hasSinks || !hasStreams) {
            return false;
        }

        // 检查所有组件，需要满足以下条件：
        // 1、Components+source+sink+operator中，各组件的ID, className必须定义。
        // 2、Components+source+sink+operator中，各组件的ID保持唯一，不能重复
        // 3、source+sink+operator必须在stream中使用至少一次，不允许有未出现在stream中的单独的点。
        // 4、source+sink+operator在streams中形成的图不能存在环状结构。

        // check sources
        Set<String> idSet = new HashSet<>();
        checkComponentDef(componentMap, idSet);
        checkComponentDef(sourceMap, idSet);
        checkComponentDef(operatorMap, idSet);
        checkComponentDef(sinkMap, idSet);

        // 去除component的id，进行stream检查
        idSet.removeAll(componentMap.keySet());
        checkComponentUsed(idSet);

        return true;
    }

    private boolean checkComponentUsed(Set<String> idSet) {
        Set<String> tmpSet = new HashSet<>();
        Set<String> streamNameSet = new HashSet<>();
        tmpSet.addAll(idSet);
        for (StreamDef stream : streams) {
            String from = stream.getFrom();
            String to = stream.getTo();
            String streamName = stream.getName();

            if (streamNameSet.contains(streamName)) {
                LOG.error("Duplicated stream name:{}", streamName);
                return false;
            } else {
                streamNameSet.add(streamName);
            }

            if (stream.getOperation() == Operation.JOIN) {
                if (null == stream.getJoinSide()) {
                    LOG.error("Join type of {} must be specified when method is join", streamName);
                    return false;
                }
            }

            // stream中出现的from 和to 必须要定义在source、operator和sink中
            if (!checkStreamSide(idSet, tmpSet, streamName, from) || !checkStreamSide(idSet, tmpSet, streamName, to)) {
                return false;
            }
        }
        // 全部遍历后，如果tmpSet还有残余，说明有定义的组件没有在任何stream中用到，提示错误
        if (tmpSet.size() > 0) {
            LOG.error("Some component is not used in streams: {}", StringUtils.join(tmpSet.iterator(), ","));
            return false;
        }

        return true;
    }

    /**
     * 防止多线程同时获取，加上对象锁
     *
     * @return
     */
    public synchronized Map<String, BeanDef> getAllNode() {
        if (allNodeMap.size() != sourceMap.size() + operatorMap.size() + sinkMap.size()) {
            allNodeMap.clear();
            allNodeMap.putAll(sourceMap);
            allNodeMap.putAll(operatorMap);
            allNodeMap.putAll(sinkMap);
        }

        return MapUtils.unmodifiableMap(allNodeMap);
    }

    public OnErrorPolicy getOnErrorPolicy() {
        return onErrorPolicy;
    }

    public void setOnErrorPolicy(OnErrorPolicy onErrorPolicy) {
        this.onErrorPolicy = onErrorPolicy;
    }

    public void include(JobDef jobDef, boolean override) {
        if (null == jobDef) {
            return;
        }

        setName(jobDef.getName(), override);
        addAllConfig(jobDef.getConfig(), override);
        addAllComponents(jobDef.getComponents(), override);
        addAllSources(jobDef.getSources(), override);
        addAllOperators(jobDef.getOperators(), override);
        addAllSinks(jobDef.getSinks(), override);
        addAllStreams(jobDef.getStreams(), override);
    }

    private boolean checkStreamSide(Set<String> idSet, Set<String> tmpSet, String streamName, String from) {
        if (!idSet.contains(from)) {
            LOG.error("No component(id: {}) found in sources, operators or sinks, stream name: {}", from, streamName);
            return false;
        } else {
            tmpSet.remove(from);
            return true;
        }
    }

    private boolean checkComponentDef(Map<String, ? extends ObjectDef> defs, Set<String> idSet) {
        for (Entry<String, ? extends ObjectDef> entry : defs.entrySet()) {
            String nodeId = entry.getKey();
            if (StringUtils.isEmpty(nodeId)) {
                LOG.error("Component id is empty");
                return false;
            }
            if (idSet.contains(nodeId)) {
                LOG.error("Duplicated component id:{}", nodeId);
                return false;
            }

            idSet.add(nodeId);

            ObjectDef def = entry.getValue();
            String className = def.getClassName();
            if (StringUtils.isEmpty(className)) {
                LOG.error("Classname of {} is empty", nodeId);
                return false;
            }
        }
        return true;
    }
}
