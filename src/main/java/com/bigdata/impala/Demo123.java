package com.bigdata.impala;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2020/7/7
 * @Description:
 */
public class Demo123 {
    public static void main(String[] args) {
        String sql = "zjlid,\n" +
                "id_src,\n" +
                "xm,\n" +
                "bmch,\n" +
                "cym,\n" +
                "xb_dm,\n" +
                "xb_mc,\n" +
                "sg,\n" +
                "xx,\n" +
                "sx,\n" +
                "xz,\n" +
                "tz,\n" +
                "nl,\n" +
                "csrq,\n" +
                "mz_dm,\n" +
                "mz_mc'民族名称',\n" +
                "xl'学历',\n" +
                "dzyx'邮箱',\n" +
                "zy02'职业',\n" +
                "zzmm'政治面貌',\n" +
                "byxx'毕业学校',\n" +
                "wxhm'微信号',\n" +
                "qqhm'QQ号',\n" +
                "zp'照片',\n" +
                "byzt_dm'兵役状态代码',\n" +
                "byzt_mc,\n" +
                "csd_dm,\n" +
                "csd_mc'出生地名称',\n" +
                "csd_xz'出生地详址',\n" +
                "gj_dm'国籍代码',\n" +
                "gj_mc'国籍名称',\n" +
                "gxpcs_dm'管辖派出所代码',\n" +
                "gxpcs_mc'管辖派出所名称',\n" +
                "gzdw'工作单位',\n" +
                "hjdqh_dm'户籍地区划代码',\n" +
                "hjdqh_mc'户籍地区划名称',\n" +
                "hjdz'户籍地详址',\n" +
                "hyzk_dm'婚姻状况代码',\n" +
                "hyzk_mc'婚姻状况名称',\n" +
                "jgdqh_dm'籍贯地区划代码',\n" +
                "jgdqh_mc'籍贯地区划名称',\n" +
                "lxdh'联系电话',\n" +
                "lxdh01'联系电话2',\n" +
                "gmsfzh'公民身份证号',\n" +
                "ryzt_dm'人员状态代码',\n" +
                "ryzt_mc'人员状态名称',\n" +
                "xzz'现住地址',\n" +
                "zfb'支付宝',\n" +
                "zjhm'证件号码',\n" +
                "zjlx_dm'证件类型代码',\n" +
                "zjlx_mc'证件类型名称',\n" +
                "zjxy_dm'宗教信仰代码',\n" +
                "zjxy_mc'宗教信仰名称',\n" +
                "zxrq,\n" +
                "sjgxsj,\n" +
                "xxrksj,\n" +
                "bz,\n" +
                "enc_db_src'数据来源数据来源'";
        String s = sql.replaceAll("'.*'", "");
        System.out.println(s);
    }
}
