package com.alcor.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author guchun
 * @description 处理网络请求
 * @date 2022/6/6 20:25
 */
public interface RequestHandler {

    void onRequest(InputStream receive, OutputStream toResp);
}
