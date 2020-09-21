package com.hanyinh.rpc.dto.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 出参
 *
 * @author Hanyinh
 * @date 2020/9/20 12:27
 */
@Data
public class RpcResponseDTO implements Serializable {

    private static final long serialVersionUID = -5177032124024214741L;

    private Boolean valid;

}
