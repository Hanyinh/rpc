package com.hanyinh.rpc.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 入参
 *
 * @author Hanyinh
 * @date 2020/9/20 12:29
 */
@Data
public class RpcRequestDTO implements Serializable {

    private static final long serialVersionUID = -4380759976098675752L;

    private String code;

    private String mobile;
}
