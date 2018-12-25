package more.thread.test.设计模式.装饰模式;

/**
 * @ClassName RoomDecorator
 * @Description
 * @Author root
 * @Date 18-12-17 下午4:55
 * @Version 1.0
 **/
public abstract class RoomDecorator implements Room{

    protected Room roomToBeDecorated;

    public RoomDecorator(Room roomToBeDecorated) {
        this.roomToBeDecorated = roomToBeDecorated;
    }

    @Override
    public String showRoom() {
        return roomToBeDecorated.showRoom();
    }


}
