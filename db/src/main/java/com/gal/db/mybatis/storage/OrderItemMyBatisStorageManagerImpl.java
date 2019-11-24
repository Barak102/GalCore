package com.gal.db.mybatis.storage;

import java.io.IOException;
import java.util.Collection;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.gal.db.mybatis.connection.MyBatisStorageManagerBase;
import com.gal.entities.OrderItem;
import gap.app.contracts.OrderItemStorageManager;

@Service("OrderItemMyBatisStorageManagerImpl")
public class OrderItemMyBatisStorageManagerImpl extends MyBatisStorageManagerBase implements OrderItemStorageManager {

    @Override
    public Collection<OrderItem> getByOrderId(int orderId) {
        SqlSession sqlSession = openSqlSession();
        Collection<OrderItem> orderItems = sqlSession.selectList("OrderItem.getByOrderId",orderId);
        sqlSession.commit();
        sqlSession.close();
        return orderItems;
    }

    @Override
    public void saveOrderItems(Collection<OrderItem> orderItems) {
        SqlSession sqlSession = openSqlSession();
        sqlSession.insert("OrderItem.insertMany",orderItems);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int save(OrderItem obj) throws IOException {
        SqlSession sqlSession = openSqlSession();
        if(obj.getId() > 0) {
            sqlSession.update("OrderItem.update",obj);
        }
        else {
        sqlSession.insert("OrderItem.insert",obj);
        }
        sqlSession.commit();
        sqlSession.close();
        return obj.getId();
    }

    @Override
    public OrderItem getById(int id) throws IOException {
        SqlSession sqlSession = openSqlSession();
        OrderItem orderItem =  sqlSession.selectOne("OrderItem.getById",id);
        sqlSession.commit();
        sqlSession.close();
        return orderItem;
    }

    @Override
    public Collection<OrderItem> getByIds(int[] ids) {
        SqlSession sqlSession = openSqlSession();
        Collection<OrderItem> orderItems =  sqlSession.selectList("OrderItem.getByIds",ids);
        sqlSession.commit();
        sqlSession.close();
        return orderItems;
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = openSqlSession();
        sqlSession.delete("OrderItem.deleteById",id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Collection<OrderItem> getAll() {
        SqlSession sqlSession = openSqlSession();
        Collection<OrderItem> orderItems = sqlSession.selectList("OrderItem.getAll");
        sqlSession.commit();
        sqlSession.close();
        return orderItems;
    }
}
