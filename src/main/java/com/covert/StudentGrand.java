package com.covert;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther: huangzhigao
 * @Date: 2020/6/5
 * @Description:
 */
@Data
@AllArgsConstructor
public class StudentGrand {
    private String id;
    private String name;
    private String subject;
    private String score;
}
