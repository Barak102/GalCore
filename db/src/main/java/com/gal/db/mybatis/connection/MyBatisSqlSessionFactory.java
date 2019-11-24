package com.gal.db.mybatis.connection;

import java.io.IOException;
import java.io.Reader;

import javax.inject.Singleton;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@Singleton
public class MyBatisSqlSessionFactory {
    private static final SqlSessionFactory FACTORY;


    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis/SqlMapConfig.xml");
            FACTORY = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("Error: "+e,e);
        }
    }


    public static SqlSessionFactory getSqlSessionFactory() {
        return FACTORY;
    }


    // add AOP that close the connection
    public SqlSession Connect() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis/SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }
}
