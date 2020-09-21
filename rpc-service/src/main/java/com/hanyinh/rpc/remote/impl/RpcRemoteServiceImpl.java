package com.hanyinh.rpc.remote.impl;

import com.alibaba.fastjson.JSON;
import com.hanyinh.rpc.dto.Result;
import com.hanyinh.rpc.dto.req.RpcRequestDTO;
import com.hanyinh.rpc.dto.resp.RpcResponseDTO;
import com.hanyinh.rpc.remote.RpcRemoteService;
import com.hanyinh.rpc.service.RpcBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author Hanyinh
 * @date 2020/9/20 11:44
 */
@Slf4j
@Component
public class RpcRemoteServiceImpl implements RpcRemoteService {

    @Autowired
    private RpcBusinessService rpcBusinessService;

    @Override
    public Result<RpcResponseDTO> checkCode(RpcRequestDTO req) {
        log.error("校验验证码，入参：{}", JSON.toJSONString(req));
        RpcResponseDTO resp = checkCode1(req);
        log.error("校验验证码，结果：{}", JSON.toJSONString(resp));
        return Result.success(resp);
    }

    private static final String CODE = "123456";

    private static final String MOBILE = "13003915143";

    public RpcResponseDTO checkCode1(RpcRequestDTO req) {
        RpcResponseDTO resp = new RpcResponseDTO();
        if (MOBILE.equals(req.getMobile()) && CODE.equals(req.getCode())) {
            resp.setValid(true);
        } else {
            resp.setValid(false);
        }
        return resp;
    }
}
