package com.mock;


/**
 * @Auther: huangzhigao
 * @Date: 2020/10/15
 * @Description:
 */
public class HttpMockDemo {
    public static void main(String[] args) {
        String sql  = "create  ; dskds;  ";
        String[] sqls = sql.split(";");
        //计算分隔符个数，来判断有多少sql语句
        String tmpSql = sql;
        String copySql = tmpSql.replaceAll(";", "");
        int splitCharCount = sql.length() - copySql.length();
        for (int i = 0; i < splitCharCount; i++) {
            System.out.println(sqls[i]);
        }

//        System.out.println(sql.split(";").length);
//        String sql = "CREATE TABLE IF NOT EXISTS zhishiku.qgztry1112_copy (                                          \n" +
//                "   ztrybh STRING,                                                                  \n" +
//                "   xm STRING,                                                                      \n" +
//                "   bmch STRING,                                                                    \n" +
//                "   xbdm STRING,                                                                    \n" +
//                "   mzdm STRING,                                                                    \n" +
//                "   csrq STRING,                                                                    \n" +
//                "   hjdz_xzqhdm STRING,                                                             \n" +
//                "   hjdz_dzmc STRING,                                                               \n" +
//                "   xzz_xzqhdm STRING,                                                              \n" +
//                "   xzz_dzmc STRING,                                                                \n" +
//                "   sfzh STRING,                                                                    \n" +
//                "   cyzj STRING,                                                                    \n" +
//                "   sgxx STRING,                                                                    \n" +
//                "   sgsx STRING,                                                                    \n" +
//                "   asjbh STRING,                                                                   \n" +
//                "   tpsj STRING,                                                                    \n" +
//                "   tpfx_jyqk STRING,                                                               \n" +
//                "   tjjbdm STRING,                                                                  \n" +
//                "   ztjj STRING,                                                                    \n" +
//                "   ztrylxdm STRING,                                                                \n" +
//                "   ladw_gajgjgdm STRING,                                                           \n" +
//                "   zbr_xm STRING,                                                                  \n" +
//                "   zbr_lxdh STRING,                                                                \n" +
//                "   id INT,                                                                         \n" +
//                "   zjlid STRING,                                                                   \n" +
//                "   bz STRING,                                                                      \n" +
//                "   xxrksj TIMESTAMP                                                                \n" +
//                " )                                                                                 \n" +
//                "  COMMENT '百万数据入库数据'                                                       \n" +
//                " STORED AS PARQUET                                                                 \n" +
//                " LOCATION 'hdfs://ha-nameservice/encdata/impala/storage/zhishiku/qgztry1112_copy';\n" +
//                "\n" +
//                "insert into zhishiku.qgztry1112_copy  select * from originald.qgztry1112;\n";
//        String s = sql.replaceAll("\n", " \n").replaceAll("--", " --");
//        System.out.println("value:" + s);
//        System.out.println("sql:" + sql);
//        System.out.println(sql.length());
//        System.out.println("aa");
//        System.out.println(s.length());
    }
}
