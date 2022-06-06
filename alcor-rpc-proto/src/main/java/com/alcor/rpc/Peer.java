package com.alcor.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author guchun
 * @description 网络传输的一个端点
 * @date 2022/6/6 19:33
 */
@Data
@AllArgsConstructor
public class Peer {

    private String host;

    private int port;

}
