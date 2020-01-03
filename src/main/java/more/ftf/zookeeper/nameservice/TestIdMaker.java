package more.ftf.zookeeper.nameservice;

/**
 * @author a8079
 * @title: TestIdMaker
 * @projectName nio
 * @description: TODO
 * @date 2020/1/220:12
 */
public class TestIdMaker {

    public static void main(String[] args) throws Exception {
        IdMaker idMaker = new IdMaker("192.168.56.104:2181",
                "/NameService/IdGen", "ID");
        idMaker.start();

        try {
            for (int i = 0; i < 1000000; i++) {
                String id = idMaker.generateId(IdMaker.RemoveMethod.DELAY);
                System.out.println(id);
            }
        } finally {
            idMaker.stop();

        }
    }

}
