package com.velocity.flinkJson.bean;

/**
 * Abstract parent class of component definitions. (source/operator/sink)
 */
public abstract class VertexDef extends BeanDef {

    private int parallelism = 1;

    public int getParallelism() {
        return parallelism;
    }

    public void setParallelism(int parallelism) {
        this.parallelism = parallelism;
    }
}
