package com.dto;

import lombok.Data;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/17
 * @Description:
 */
@Data
public class JdbcDto {
    private String id;
    private String className;
    private JdbcSourceInputParam jdbcSourceInputParam;


    public static void main(String[] args) {
        String res = " zhag ds";
        String ss = res.trim();
        System.out.println(res.length());
        System.out.println(ss.length());
        System.out.println(res.trim());
    }
}
