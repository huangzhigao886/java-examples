package com.sqlparse;

import com.sqlparse.model.TableColumn;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sqlparse.instants.Constants.COLUMN;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/10
 * @Description:
 */
public class JsqParse {

    private static final Logger logger = LoggerFactory.getLogger(JsqParse.class);

    private PlainSelect plainSelect;
    private String sql;

    public JsqParse(String sql) {
        this.sql = sql;
        init(sql);
    }

    /**
     * 解析sql，将结果返回给plainSelect
     *
     * @param sql
     */
    private void init(String sql) {
        Select parse = null;
        try {
            parse = (Select) CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            logger.error("sql parse faile :" + e);
        }
        plainSelect = (PlainSelect) parse.getSelectBody();
    }


    /**
     * 获取select的查询字段
     *
     * @return
     */
    public Map<String, TableColumn> getSelectItems() {
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        Map<String, TableColumn> selectItemMap = new HashMap<>();
        TableColumn tableColumn = null;
        for (SelectItem selectItem : selectItems) {
            Expression expression = ((SelectExpressionItem) selectItem).getExpression();
            String className = expression.getClass().getName();
            if (className.contains("CaseExpression")) {
                continue;
            }
            String columnName = ((Column) expression).getColumnName();
            String tableName = ((Column) expression).getTable().getName();
            Alias alias = ((SelectExpressionItem) selectItem).getAlias();
            String columnAlias = alias == null ? columnName : alias.getName();
            tableColumn = new TableColumn(columnName, columnAlias);
            selectItemMap.put(tableName, tableColumn);
        }
        return selectItemMap;
    }


    public static void main(String[] args) throws JSQLParserException {
        String sql = "select distinct t2.id as aid,t1.name as bid from test02 t1 left join test12 t2 on t1.aid = t2.bid where age>5";
//        String sql1 = "select t2.id from test t2";
//        String sql = "select t2.id from flink.test t2";
        String sql12 = "SELECT XXZJBH,CASE WHEN LENGTH(SFZHM) = 18 THEN CONCAT(SUBSTR(SFZHM, 1, 6), '************') WHEN LENGTH(SFZHM)  = 15 \n" +
                "THEN CONCAT(SUBSTR(SFZHM, 1, 6), '*********')ELSE '0' END AS SFZHM,CSRQ,MZ,ZZMM,CYM  from h_admin_message_2792 where sfzhm >= '15' or sfzhm <= '18'   \n" +
                "order by xxzjbh asc";
        JsqParse jsqParse = new JsqParse(sql);

        Map<String, TableColumn> selectItems1 = jsqParse.getSelectItems();
        for(Map.Entry<String,TableColumn> entry:selectItems1.entrySet()){
            System.out.println(entry.getValue());
            System.out.println(entry.getKey());
        }
    }
}
