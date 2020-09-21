package com.hanyinh.rpc.remote;

import com.hanyinh.rpc.dto.Result;
import com.hanyinh.rpc.dto.req.RpcRequestDTO;
import com.hanyinh.rpc.dto.resp.RpcResponseDTO;

/**
 * rpc提供的接口
 *
 * @author Hanyinh
 * @date 2020/9/20 11:41
 */
public interface RpcRemoteService {

    Result<RpcResponseDTO> checkCode(RpcRequestDTO req);
}
