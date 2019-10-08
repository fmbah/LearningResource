package io.netty.example.basics.B0001;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class MyServerChannel extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 未设置解码器 接收msg消息
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] msgByte = new byte[buf.readableBytes()];
//        buf.readBytes(msgByte);
//        System.out.print(new Date() + "接收到消息：");
//        System.out.println(new String(msgByte, Charset.forName("GBK")));

        // 设置解码器
        System.out.println(new Date() + "接收到消息：" + msg);
    }
}
