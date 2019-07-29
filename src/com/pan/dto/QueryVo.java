package com.pan.dto;

import lombok.*;

import java.sql.Date;


/**
 * @Author: PanWei
 * @Date: 2019-07-04 15:56
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryVo {
    private Integer query_id;
    private String query_name;

    private Date start_time;
    private Date end_time;

    public QueryVo(Integer query_id) {
        this.query_id = query_id;
    }
}
