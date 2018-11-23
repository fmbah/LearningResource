package more.thread.test;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @ClassName ExamplePlugin
 * @Description
 * @Author root
 * @Date 18-11-22 下午5:10
 * @Version 1.0
 **/
@Intercepts({@Signature(
        type= Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class})})
public class ExamplePlugin implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("======================================intercept===========================================");
        return invocation.proceed();
    }
    public Object plugin(Object target) {
        System.out.println("======================================plugin===========================================");
        return Plugin.wrap(target, this);
    }
    public void setProperties(Properties properties) {
        System.out.println("======================================setProperties===========================================");
    }
}
