package more.thread.test.mybatis源码阅读;

import more.thread.test.Blog;
import more.thread.test.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName Mybatis0
 * @Description
 * @Author root
 * @Date 18-11-22 下午3:19
 * @Version 1.0
 **/
public class Mybatis0 {

    /**
     * 参考:https://gitbook.cn/books/5a37b6b66eec7c4f044a75d0/index.html
     *     https://github.com/tuguangquan/mybatis
     *
     * 功能描述: 一个最简单的列子,用来为读源代码开路,根据代码写的顺序来读
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-22 下午4:11
     */
    public static void main (String args[]) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {

            inputStream = Resources.getResourceAsStream(resource);//通过Resouce类的ClassLoaderWrapper类加载器进行资源加载,一个五个类加载器,只要一个加载成功就返回流对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Connection connection = sqlSession.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from tb_user");
            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Object> objects = sqlSession.selectList("select 1");
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            System.out.println(i);

            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            List<Blog> blogs = mapper.getBlogs();
            System.out.println(blogs.size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
