package com.alcor.rpc.example;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.Executors;

/**
 * @author guchun
 * @description TODO
 * @date 2022/7/10 00:13
 */
public class NettyServer {

    public void bind(int port) throws Exception{
        ServerBootstrap b = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()));
        b.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipelines = Channels.pipeline();
                pipelines.addLast(MessageHandler.class.getName(), new MessageHandler());
                return pipelines;
            }
        });

        b.bind(new InetSocketAddress(port));
    }

    static class MessageHandler extends SimpleChannelHandler {
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
            String message = new String(buffer.readBytes(buffer.readableBytes()).array(), "UTF-8");
            System.out.println("受到的内容为" + message);

            // 回送消息
            byte[] body = "已受到消息".getBytes();
            byte[] header = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(body.length).array();
            Channels.write(ctx.getChannel(), ChannelBuffers.wrappedBuffer(header, body));
            System.out.println("发送完成 ");
        }
    }

    public static void main(String[] args) {
        try {
            new NettyServer().bind(8888);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
