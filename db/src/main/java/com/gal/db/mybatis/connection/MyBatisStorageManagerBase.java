package com.gal.db.mybatis.connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.gal.db.mybatis.connection.MyBatisSqlSessionFactory;

public abstract class MyBatisStorageManagerBase {
    protected SqlSession openSqlSession() {
        SqlSessionFactory sessionFactory = MyBatisSqlSessionFactory.getSqlSessionFactory();
        return sessionFactory.openSession();
    }
}
