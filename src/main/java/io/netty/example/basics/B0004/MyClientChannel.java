package io.netty.example.basics.B0004;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

public class MyClientChannel extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Channel channel = ctx.channel();
        System.out.println("连接服务端成功....服务端地址：" + channel.remoteAddress());
        channel.writeAndFlush("客户端【" + channel.localAddress() + "】, 连接成功\r\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        Channel channel = ctx.channel();
        channel.writeAndFlush("客户端【" + channel.localAddress() + "】, 断开连接\r\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if (msg instanceof HttpRequest) {
            super.channelRead(ctx, msg);
            System.out.println("客户端收到消息：【"+ msg +"】");
            Channel channel = ctx.channel();
            channel.writeAndFlush(msg+"\r\n");
//        }
    }



    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.out.println("客户端读取数据完成.....");
    }
}
