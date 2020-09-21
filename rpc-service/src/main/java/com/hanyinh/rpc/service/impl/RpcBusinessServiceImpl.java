package com.hanyinh.rpc.service.impl;

import com.hanyinh.rpc.dto.req.RpcRequestDTO;
import com.hanyinh.rpc.dto.resp.RpcResponseDTO;
import com.hanyinh.rpc.service.RpcBusinessService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author Hanyinh
 * @date 2020/9/20 12:48
 */
@Service
public class RpcBusinessServiceImpl implements RpcBusinessService {

    private static final String CODE = "123456";

    private static final String MOBILE = "13003915143";

    @Override
    public RpcResponseDTO checkCode(RpcRequestDTO req) {
        RpcResponseDTO resp = new RpcResponseDTO();
        if (MOBILE.equals(req.getMobile()) && CODE.equals(req.getCode())) {
            resp.setValid(true);
        } else {
            resp.setValid(false);
        }
        return resp;
    }
}
