package io.netty.example.basics.B0003;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.NettyRuntime;

public class Netty0003 {

    public static void main(String[] args) {

        System.out.println(NettyRuntime.availableProcessors() * 2);

        new Netty0003().bind(9999);
    }

    private void bind(int port) {
        NioEventLoopGroup bossExecutors = new NioEventLoopGroup(1);
        NioEventLoopGroup workerExecutors = new NioEventLoopGroup(2);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossExecutors, workerExecutors)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
            ;
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossExecutors.shutdownGracefully();
            workerExecutors.shutdownGracefully();
        }
    }
}
