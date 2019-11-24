package com.gal.db.mybatis.storage;

import java.io.IOException;
import java.util.Collection;

import com.gal.db.mybatis.connection.MyBatisStorageManagerBase;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.gal.entities.ServiceType;
import gap.app.contracts.ServiceTypeStorageManager;

@Service("ServiceTypeMyBatisStorageManagerImpl")
public class ServiceTypeMyBatisStorageManagerImpl extends MyBatisStorageManagerBase implements ServiceTypeStorageManager {
    @Override
    public int save(ServiceType obj) throws IOException {
        SqlSession sqlSession = openSqlSession();
        if (obj.getId() > 0) {
            sqlSession.update("ServiceType.update", obj);
        } else {
            sqlSession.insert("ServiceType.insert", obj);
        }
        sqlSession.commit();
        sqlSession.close();
        return obj.getId();


    }

    @Override
    public ServiceType getById(int id) throws IOException {
        SqlSession sqlSession = openSqlSession();
        ServiceType serviceType = sqlSession.selectOne("ServiceType.getById", id);
        sqlSession.commit();
        sqlSession.close();
        return serviceType;
    }

    @Override
    public Collection<ServiceType> getByIds(int[] ids) {
        SqlSession sqlSession = openSqlSession();
        Collection<ServiceType> serviceType = sqlSession.selectList("ServiceType.getByIds", ids);
        sqlSession.commit();
        sqlSession.close();
        return serviceType;
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = openSqlSession();
        sqlSession.delete("ServiceType.deleteById", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Collection<ServiceType> getAll() {
        SqlSession sqlSession = openSqlSession();
        Collection<ServiceType> serviceType = sqlSession.selectList("ServiceType.getAll");
        sqlSession.commit();
        sqlSession.close();
        return serviceType;
    }

}
