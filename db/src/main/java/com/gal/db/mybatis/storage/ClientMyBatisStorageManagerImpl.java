package com.gal.db.mybatis.storage;

import java.io.IOException;
import java.util.Collection;

import com.gal.db.mybatis.connection.MyBatisStorageManagerBase;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.gal.entities.GalClient;
import gap.app.contracts.ClientStorageManager;

@Service("ClientMyBatisStorageManagerImpl")
public class ClientMyBatisStorageManagerImpl extends MyBatisStorageManagerBase implements ClientStorageManager {

    @Override
    public int save(GalClient obj) throws IOException {
        SqlSession sqlSession = openSqlSession();
        if (obj.getId() > 0) {
            sqlSession.update("Client.update", obj);
        } else {
            sqlSession.insert("Client.insert", obj);
        }
        sqlSession.commit();
        sqlSession.close();
        return obj.getId();
    }


    @Override
    public GalClient getById(int id) throws IOException {
        SqlSession sqlSession = openSqlSession();
        GalClient client = (GalClient) sqlSession.selectOne("Client.getById", id);
        sqlSession.commit();
        sqlSession.close();
        return client;
    }

    @Override
    public Collection<GalClient> getByIds(int[] ids) {
        SqlSession sqlSession = openSqlSession();
        Collection<GalClient> clients = sqlSession.selectList("Client.getByIds", ids);
        sqlSession.commit();
        sqlSession.close();
        return clients;
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = openSqlSession();
        sqlSession.delete("Client.deleteById", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Collection<GalClient> getAll() {
        SqlSession sqlSession = openSqlSession();
        Collection<GalClient> clients = sqlSession.selectList("Client.getAll");
        sqlSession.commit();
        sqlSession.close();
        return clients;
    }
}
