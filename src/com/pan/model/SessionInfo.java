package com.pan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: PanWei
 * @Date: 2019-07-12 20:13
 * @Version 1.0
 */
@Getter
@Setter
public class SessionInfo {
    private User user;
    private List<String> resourceList;


}
