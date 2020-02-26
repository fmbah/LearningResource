package more.ftf.zookeeper.my_balance.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author fb
 * @title: ClientHandler
 * @projectName nio
 * @description:
 * @date 2020/2/2513:53
 */
public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.channel().closeFuture().sync();
    }
}
