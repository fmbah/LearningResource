package io.netty.example.basics.B0003;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.stream.Collectors;

public class MyServerChannel extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int size = channelGroup.stream().filter(e -> e != ctx.channel()).collect(Collectors.toList()).size();
        if (0 == size) {
            channelGroup.add(ctx.channel());
        }
        super.channelActive(ctx);

        String warn = "连接开始, 连接地址：" + ctx.channel().remoteAddress();
        System.out.println(warn);

        ctx.channel().writeAndFlush(warn+"\r\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("收到客户端消息，客户端地址：" + ctx.channel().remoteAddress() + ", 消息：" + msg);
        ctx.channel().writeAndFlush("走起。。。\r\n");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        channelGroup.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
