package com.gal.db.mybatis.storage;

import java.io.IOException;
import java.util.Collection;

import com.gal.db.mybatis.connection.MyBatisStorageManagerBase;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.gal.entities.Order;
import com.gal.entities.enums.OrderStatus;
import gap.app.contracts.OrderStorageManager;

@Service("OrderMyBatisStorageManagerImpl")
public class OrderMyBatisStorageManagerImpl extends MyBatisStorageManagerBase implements OrderStorageManager {
    @Override
    public int save(Order obj) throws IOException {
        SqlSession sqlSession = openSqlSession();
        sqlSession.insert("Order.insert", obj);
        sqlSession.commit();
        sqlSession.close();
        return obj.getId();
    }

    @Override
    public Order getById(int id) throws IOException {
        SqlSession sqlSession = openSqlSession();
        Order order = sqlSession.selectOne("Order.getById", id);
        if(order != null) {
          getOrderData(order, sqlSession);
        }
        sqlSession.commit();
        sqlSession.close();
        return order;
    }

    @Override
    public Collection<Order> getByIds(int[] ids) {
        SqlSession sqlSession = openSqlSession();
        Collection<Order> orders = sqlSession.selectList("Order.getByIds", ids);
        sqlSession.commit();
        sqlSession.close();
        return orders;

    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = openSqlSession();
        sqlSession.delete("Order.deleteById", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Collection<Order> getAll() {
        SqlSession sqlSession = openSqlSession();
        Collection<Order> orders = sqlSession.selectList("Order.getAll");
        sqlSession.commit();
        sqlSession.close();
        return orders;
    }

    @Override
    public void cancel(int orderId) {
        SqlSession sqlSession = openSqlSession();

        Order order = sqlSession.selectOne("Order.getById", orderId);

        if (order != null) {
            if (order.getStatus() == OrderStatus.APPROVED) {
                sqlSession.update("Order.cancel", orderId);
            }
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void approve(int orderId) {
        SqlSession sqlSession = openSqlSession();

        Order order = sqlSession.selectOne("Order.getById", orderId);
        if (order != null) {
            order.setOrderItems(sqlSession.selectList("OrderItem.getByOrderId", orderId));
            if (order.getStatus() == OrderStatus.PROPOSED && order.getOrderItems() != null) {
                if (!order.getOrderItems().isEmpty()) {
                    sqlSession.update("Order.approve", orderId);
                }
            }
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Collection<Order> getByClientId(int clientId) {
        SqlSession sqlSession = openSqlSession();
        Collection<Order> orders = sqlSession.selectList("Order.getByClientId", clientId);
        sqlSession.commit();
        sqlSession.close();
        return orders;
    }

    @Override
    public Collection<Order> getByTagsName(String[] tags) {
        SqlSession sqlSession = openSqlSession();
        Collection<Order> orders = sqlSession.selectList("Order.getByTagNames", tags);
        /*
         * Collection<Order> orders = new ArrayList<>();
         * if (!tagsResult.isEmpty()) {
         * orders = sqlSession.selectList("Order.getByIds", tagsResult.stream().map(Tag::getId).collect(Collectors.toList()));
         * }
         */
        sqlSession.commit();
        sqlSession.close();
        return orders;
    }

    @Override
    public Collection<Order> getByTagsIds(int[] tagsIds) {
        SqlSession sqlSession = openSqlSession();
        Collection<Order> orders = sqlSession.selectList("Order.getByTagIds", tagsIds);
        sqlSession.commit();
        sqlSession.close();
        return orders;
    }


    private void getOrderData(Order order, SqlSession session) {
        if (session != null) {
            int orderId = order.getId();
            order.setOrderItems(session.selectList("OrderItem.getByOrderId", orderId));
            order.setPayments(session.selectList("Payment.getByOrderId", orderId));
            order.setTags(session.selectList("Tag.getByOrderId", orderId));
        }
    }

}
