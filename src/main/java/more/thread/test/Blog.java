package more.thread.test;

import java.io.Serializable;

/**
 * @ClassName Blog
 * @Description
 * @Author root
 * @Date 18-11-22 下午3:33
 * @Version 1.0
 **/
public class Blog implements Serializable {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
