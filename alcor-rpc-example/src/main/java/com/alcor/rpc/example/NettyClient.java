package com.alcor.rpc.example;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

/**
 * @author guchun
 * @description TODO
 * @date 2022/7/10 00:13
 */
public class NettyClient {

    private final ByteBuffer readHandler = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);
    private final ByteBuffer writeHandler = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);
    private SocketChannel channel;

    public void sendMessage(byte[] body) throws Exception{
        // 创建客户端通道
        channel = SocketChannel.open();
        channel.socket().setSoTimeout(60000);
        channel.connect(new InetSocketAddress("127.0.0.1", 8888));

        // send
        writeWithHandler(channel, body);

        // receive server message
        readHandler.clear();
        read(channel, readHandler);
        int bodyLen = readHandler.getInt(0);
        ByteBuffer bodyBuffer = ByteBuffer.allocate(bodyLen).order(ByteOrder.BIG_ENDIAN);
        read(channel, bodyBuffer);
        System.out.println("客户端受到响应为：" + new String(bodyBuffer.array(), "UTF-8") +", 长度：" + bodyLen);

    }

    public void writeWithHandler(SocketChannel channel, byte[] body) throws Exception{
        // clear buffer
        writeHandler.clear();
        writeHandler.putInt(body.length);
        // reset buffer position
        writeHandler.flip();
        channel.write(ByteBuffer.wrap(body));
    }

    public void read(SocketChannel channel, ByteBuffer buffer) throws Exception{
        while (buffer.hasRemaining()) {
            int r = channel.read(buffer);
            if (r == -1) {
                throw new Exception("end of stream");
            }
        }
    }

    public static void main(String[] args) {
        String body = "客服端测试";

        try {
            new NettyClient().sendMessage(body.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
