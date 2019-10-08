package io.netty.example.basics.B0002;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Netty0002 {
    public static void main(String[] args) {
        new Netty0002().bind(9999);
    }

    private void bind(int port) {
        NioEventLoopGroup parentLoopGroup = new NioEventLoopGroup();
        NioEventLoopGroup childLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentLoopGroup, childLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1)
                    .childHandler(new MyChannelInitializer())
            ;
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parentLoopGroup.shutdownGracefully();
            childLoopGroup.shutdownGracefully();
        }
    }
}
