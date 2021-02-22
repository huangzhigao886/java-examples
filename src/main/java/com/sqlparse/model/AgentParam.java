package com.sqlparse.model;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @auth:qiss
 * @see: [相关类/方法]（可选）
 * @since enc-springcloud
 */
@Data
public class AgentParam {
    private String tempStatusPath;
    private String tempResPath;
    private String readSourceCount;
    private String kerberosUser;
    private String hdfsSitePath;
    private String coreSitePath;
    private String keyTabPath;
    private String kerberosPath;
    private String jaasConf;
    private Map<String, Map<String, Object>> connectorInfo = new HashMap<>();
    private Map<String, Object> tableSchema = new HashMap<>();
//    private ModeSaveInfo modeSaveInfo;
//    private ModelInfo oldModelInfo;

    public AgentParam() {

    }

    public AgentParam(String tempStatusPath, String tempResPath, String readSourceCount, String kerberosUser, String kerberosPath, String hdfsSitePath, String coreSitePath, String keyTabPath, String jaasConf) {
        this.tempStatusPath = tempStatusPath;
        this.tempResPath = tempResPath;
        this.readSourceCount = readSourceCount;
        this.kerberosUser = kerberosUser;
        this.kerberosPath = kerberosPath;
        this.keyTabPath = keyTabPath;
        this.hdfsSitePath = hdfsSitePath;
        this.coreSitePath = coreSitePath;
        this.jaasConf = jaasConf;
    }
}
