package more.thread.test.设计模式.装饰模式;

/**
 * @ClassName BlankRoom
 * @Description
 * @Author root
 * @Date 18-12-17 下午4:54
 * @Version 1.0
 **/
public class BlankRoom implements Room{
    @Override
    public String showRoom() {
        return "毛坯房";
    }
}
