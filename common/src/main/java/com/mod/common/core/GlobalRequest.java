package com.mod.common.core;

import lombok.Data;

@Data
public class GlobalRequest {
    private String userName;
    private String uri;
    private String ip;
    private Long start;
}
