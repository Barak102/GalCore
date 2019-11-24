package com.gal.db.mybatis.handlers;

import com.gal.entities.enums.OrderStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class OrderStatusTypeHandler implements TypeHandler<OrderStatus> {


        @Override
        public void setParameter(PreparedStatement preparedStatement, int i, OrderStatus orderStatus, JdbcType jdbcType) throws SQLException {
            preparedStatement.setInt(i,orderStatus.getStatus());
        }

        @Override
        public OrderStatus getResult(ResultSet resultSet, String s) throws SQLException {
            return OrderStatus.valueOf(resultSet.getInt(s));
        }

        @Override
        public OrderStatus getResult(ResultSet resultSet, int i) throws SQLException {
            return null;
        }

        @Override
        public OrderStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
            return null;
        }
    }
