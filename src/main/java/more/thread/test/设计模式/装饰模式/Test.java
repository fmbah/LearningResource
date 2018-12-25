package more.thread.test.设计模式.装饰模式;

/**
 * @ClassName Test
 * @Description
 *
 * https://www.ibm.com/developerworks/cn/java/j-lo-cdi-decorator-pattern/index.html
 *
 * @Author root
 * @Date 18-12-17 下午5:01
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        Room room = new BlankRoom();

        PaintedDecorator paintedDecorator = new PaintedDecorator(room);

        FlooredDecorator flooredDecorator = new FlooredDecorator(new PaintedDecorator(room));

        PaintedDecorator paintedDecorator1 = new PaintedDecorator(new FlooredDecorator(room));

        System.out.println(room.showRoom());

        System.out.println(paintedDecorator.showRoom());

        System.out.println(paintedDecorator1.showRoom());

        System.out.println(flooredDecorator.showRoom());
    }
}
