package more.thread.test.resJava查缺补漏;/**
 * @Auther: root
 * @Date: 18-10-24 19:46
 * @Description:
 */

import org.junit.Test;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * @ClassName Java自定义注解
 * @Description
 * @Author root
 * @Date 18-10-24 下午7:46
 * @Version 1.0
 **/
public class Java自定义注解 {

    @Test
    public void test() {
        Field[] declaredFields = ExampleAnnotation.class.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Name.class)) {
                Name annotation = field.getAnnotation(Name.class);
                System.out.println(annotation.name());
            }
        }
    }

}

@Target(ElementType.FIELD)//注解用于什么地方
@Retention(RetentionPolicy.RUNTIME)//什么时候使用该注解
@Documented//注解是否包含在JavaDoc中
@Inherited//是否允许子类继承该注解
@interface Name{
    String name() default "defaultName";
}

class ExampleAnnotation {

//    @Name(name = "ExampleAnnotation")
    @Name
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


