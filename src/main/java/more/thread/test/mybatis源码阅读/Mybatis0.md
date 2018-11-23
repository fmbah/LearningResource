````
String resource = "mybatis-config.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);

public static InputStream getResourceAsStream(String resource) throws IOException {
    return getResourceAsStream((ClassLoader)null, resource);
}

public static InputStream getResourceAsStream(ClassLoader loader, String resource) throws IOException {
    InputStream in = classLoaderWrapper.getResourceAsStream(resource, loader);
    if (in == null) {
        throw new IOException("Could not find resource " + resource);
    } else {
        return in;
    }
}
//通过ClassLoaderWrapper类加载器进行加载资源,可查看ClassLoaderWrapper类,其中有5个类加载器
ClassLoader[] getClassLoaders(ClassLoader classLoader) {
    return new ClassLoader[]{classLoader, this.defaultClassLoader, Thread.currentThread().getContextClassLoader(), this.getClass().getClassLoader(), this.systemClassLoader};
}
InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
    ClassLoader[] var3 = classLoader;
    int var4 = classLoader.length;

    for(int var5 = 0; var5 < var4; ++var5) {
        ClassLoader cl = var3[var5];
        if (null != cl) {
            InputStream returnValue = cl.getResourceAsStream(resource);
            if (null == returnValue) {
                returnValue = cl.getResourceAsStream("/" + resource);
            }

            if (null != returnValue) {
                return returnValue;
            }
        }
    }

    return null;
}

//从上面获取到了文件的流,下面代码根据流获取SqlSessionFactory工厂对象
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
public SqlSessionFactory build(InputStream inputStream) {
    return this.build((InputStream)inputStream, (String)null, (Properties)null);
}
public SqlSessionFactory build(InputStream inputStream, Properties properties) {
    return this.build((InputStream)inputStream, (String)null, properties);
}
public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
    SqlSessionFactory var5;
    try {
        XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
        var5 = this.build(parser.parse());
    } catch (Exception var14) {
        throw ExceptionFactory.wrapException("Error building SqlSession.", var14);
    } finally {
        ErrorContext.instance().reset();

        try {
            inputStream.close();
        } catch (IOException var13) {
            ;
        }

    }

    return var5;
}
````