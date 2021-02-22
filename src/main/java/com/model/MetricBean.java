package com.model;

import lombok.Data;

/**
 * @description:
 * @author: hzg
 * @create: 2019-09-27 09:59
 **/

@Data
public class MetricBean {
    private long numBytesOut;
    private long numBytesIn;
    private long numRecordsOut;
    private long numRecordsIn;
}
