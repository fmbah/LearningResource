package io.netty.example.basics.B0001;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyChannelInitializer  extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("客户端已连接，IP:【" + socketChannel.localAddress().getHostString() + "】, PORT: 【" + socketChannel.localAddress().getPort() + "】");
    }
}
