package com.hanyinh.rpc.service;

import com.hanyinh.rpc.dto.req.RpcRequestDTO;
import com.hanyinh.rpc.dto.resp.RpcResponseDTO;

/**
 * TODO
 *
 * @author Hanyinh
 * @date 2020/9/20 12:47
 */
public interface RpcBusinessService {

    RpcResponseDTO checkCode(RpcRequestDTO req);
}
