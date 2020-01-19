package more.ftf.zookeeper.queue;

import java.io.Serializable;

/**
 * @author a8079
 * @title: User
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1717:04
 */
public class User implements Serializable {

    String name;
    String id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
