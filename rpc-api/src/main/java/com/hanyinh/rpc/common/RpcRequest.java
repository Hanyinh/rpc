package com.hanyinh.rpc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 供外部使用的调用情况
 *
 * @author Hanyinh
 * @date 2020/9/21 11:32
 */
@Data
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -8400231327253938569L;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class[] types;

    /**
     * 参数
     */
    private Object[] params;


}
