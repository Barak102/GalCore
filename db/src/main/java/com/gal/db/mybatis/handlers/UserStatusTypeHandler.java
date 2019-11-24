package com.gal.db.mybatis.handlers;

import com.gal.entities.enums.OrderStatus;
import com.gal.entities.enums.UserStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatusTypeHandler implements TypeHandler<UserStatus> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, UserStatus userStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,userStatus.getStatusId());
    }

    @Override
    public UserStatus getResult(ResultSet resultSet, String s) throws SQLException {
        return UserStatus.valueOf(resultSet.getInt(s));
    }

    @Override
    public UserStatus getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public UserStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
