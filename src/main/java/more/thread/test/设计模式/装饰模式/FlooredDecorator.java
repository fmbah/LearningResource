package more.thread.test.设计模式.装饰模式;

/**
 * @ClassName FlooredDecorator
 * @Description
 * @Author root
 * @Date 18-12-17 下午5:00
 * @Version 1.0
 **/
public class FlooredDecorator extends RoomDecorator{
    public FlooredDecorator(Room roomToBeDecorated) {
        super(roomToBeDecorated);
    }

    @Override
    public String showRoom() {
        doFlooring();
        return super.showRoom() + "铺地板";
    }

    private void doFlooring() {

    }
}
