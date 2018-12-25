package more.thread.test.设计模式.装饰模式;

/**
 * @ClassName PaintedDecorator
 * @Description
 * @Author root
 * @Date 18-12-17 下午4:57
 * @Version 1.0
 **/
public class PaintedDecorator extends RoomDecorator{
    public PaintedDecorator(Room roomToBeDecorated) {
        super(roomToBeDecorated);
    }

    @Override
    public String showRoom() {
        doPainting();
        return super.showRoom() + "刷墙漆";
    }

    private void doPainting() {

    }
}
