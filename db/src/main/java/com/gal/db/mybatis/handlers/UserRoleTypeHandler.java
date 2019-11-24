package com.gal.db.mybatis.handlers;

import com.gal.entities.enums.UserRole;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleTypeHandler implements TypeHandler<UserRole> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, UserRole userRole, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,userRole.gerRoleId());
    }

    @Override
    public UserRole getResult(ResultSet resultSet, String s) throws SQLException {
        return UserRole.valueOf(resultSet.getInt(s));
    }

    @Override
    public UserRole getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public UserRole getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
