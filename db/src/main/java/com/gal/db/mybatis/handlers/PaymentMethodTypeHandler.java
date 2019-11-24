package com.gal.db.mybatis.handlers;

import com.gal.entities.enums.PaymentMethod;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMethodTypeHandler implements TypeHandler<PaymentMethod> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, PaymentMethod paymentMethod, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,paymentMethod.getMethod());
    }

    @Override
    public PaymentMethod getResult(ResultSet resultSet, String s) throws SQLException {
        return PaymentMethod.valueOf(resultSet.getInt(s));
    }

    @Override
    public PaymentMethod getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public PaymentMethod getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
