package com.alcor.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author guchun
 * @description 网络传输的一个端点
 * @date 2022/6/6 19:33
 */
@Data
@AllArgsConstructor
public class Peer implements Serializable {

    private String host;

    private int port;

}
