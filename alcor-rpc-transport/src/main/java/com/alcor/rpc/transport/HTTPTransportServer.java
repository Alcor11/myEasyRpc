package com.alcor.rpc.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author guchun
 * @description 服务端
 * @date 2022/6/6 20:39
 */
@Slf4j
public class HTTPTransportServer implements TransportServer{
    private RequestHandler handler;
    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        log.info("client init");
        this.handler = handler;
        this.server = new Server(port);

        //servlet 接收请求
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");
    }

    @Override
    public void start() {
        try {
            log.info("client start");
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        try {
            log.info("client stop");
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("client connect");
            InputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();

            if (handler != null) {
                handler.onRequest(in, out);
            }
            out.flush();
        }
    }
}
