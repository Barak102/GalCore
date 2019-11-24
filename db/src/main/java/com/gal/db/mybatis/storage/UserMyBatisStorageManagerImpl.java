package com.gal.db.mybatis.storage;

import java.io.IOException;
import java.util.Collection;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gal.db.mybatis.connection.MyBatisStorageManagerBase;
import com.gal.entities.User;
import gap.app.contracts.UserStorageManager;

@Repository("UserMyBatisStorageManagerImpl")
public class UserMyBatisStorageManagerImpl extends MyBatisStorageManagerBase implements UserStorageManager {
    @Override
    public int save(User obj) throws IOException {
        SqlSession sqlSession = openSqlSession();
        try {
            if (obj.getId() > 0) {
                sqlSession.update("User.update", obj);
            } else {
                sqlSession.insert("User.insert", obj);
            }
        }
        finally {
            sqlSession.commit();
            sqlSession.close();

        }
        return obj.getId();
    }

    @Override
    public User getById(int id) throws IOException {
        SqlSession sqlSession = openSqlSession();
        User user = sqlSession.selectOne("User.getById", id);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    @Override
    public Collection<User> getByIds(int[] ids) {
        SqlSession sqlSession = openSqlSession();
        Collection<User> users = sqlSession.selectList("User.getByIds", ids);
        sqlSession.commit();
        sqlSession.close();
        return users;
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = openSqlSession();
        sqlSession.update("User.deleteUser", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Collection<User> getAll() {
        SqlSession sqlSession = openSqlSession();
        Collection<User> users = sqlSession.selectList("User.getAll");
        sqlSession.commit();
        sqlSession.close();
        return users;
    }

    @Override
    public User getByEmail(String email) {
        SqlSession sqlSession = openSqlSession();
        User user = sqlSession.selectOne("User.getByEmail", email);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }
}
