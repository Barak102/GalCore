package com.gal.db.mybatis.storage;

import java.io.IOException;
import java.util.Collection;

import com.gal.db.mybatis.connection.MyBatisStorageManagerBase;
import com.gal.entities.Order;
import com.gal.entities.enums.OrderStatus;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.gal.entities.Payment;
import gap.app.contracts.PaymentStorageManager;

@Service("PaymentMyBatisStorageManagerImpl")
public class PaymentMyBatisStorageManagerImpl extends MyBatisStorageManagerBase implements PaymentStorageManager {
    @Override
    public Collection<Payment> getByOrderId(int paymentId) {
        SqlSession sqlSession = openSqlSession();
        Collection<Payment> payments = sqlSession.selectList("Payment.getByOrderId", paymentId);
        sqlSession.commit();
        sqlSession.close();
        return payments;
    }

    @Override
    public int save(Payment obj) throws IOException {
        SqlSession sqlSession = openSqlSession();

        if (obj.getId() > 0) {
            sqlSession.update("Payment.update", obj);
        } else {
            sqlSession.insert("Payment.insert", obj);
        }
        sqlSession.commit();
        sqlSession.close();
        return obj.getId();
    }

    @Override
    public Payment getById(int id) throws IOException {
        SqlSession sqlSession = openSqlSession();
        Payment payment = sqlSession.selectOne("Payment.getById", id);
        sqlSession.commit();
        sqlSession.close();
        return payment;
    }

    @Override
    public Collection<Payment> getByIds(int[] ids) {
        SqlSession sqlSession = openSqlSession();
        Collection<Payment> payments = sqlSession.selectList("Payment.getByIds", ids);
        sqlSession.commit();
        sqlSession.close();
        return payments;
    }

    @Override
    public Collection<Payment> getAll() {
        SqlSession sqlSession = openSqlSession();
        Collection<Payment> payments = sqlSession.selectList("Payment.getAll");
        sqlSession.commit();
        sqlSession.close();
        return payments;
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = openSqlSession();
        Order order = sqlSession.selectOne("Order.getByPaymentId");
        boolean deleteAvailable = true;
        if(order != null) {
            if(order.getStatus() != OrderStatus.PROPOSED) {
                deleteAvailable = false;
            }
        }
        if(deleteAvailable) {
            sqlSession.delete("Payment.deleteById", id);
        }
        sqlSession.commit();
        sqlSession.close();
    }


}
