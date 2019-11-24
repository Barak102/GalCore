package com.gal.db.mybatis.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class ZeroToNullTypeHandler extends IntegerTypeHandler {


    public ZeroToNullTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Integer parameter, JdbcType jdbcType) throws SQLException {
        if(parameter > 0) {
            ps.setInt(i,parameter);
        }
        else {
            ps.setNull(i, Types.INTEGER);
        }
    }
    @Override
    public Integer getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getInt(columnName);
    }
    @Override
    public Integer getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getInt(columnIndex);
    }
    @Override
    public Integer getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getInt(columnIndex);
    }

/*    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Integer integer, JdbcType jdbcType) throws SQLException {
        if(integer.intValue() > 0) {
            preparedStatement.setInt(i,integer.intValue());
        }
        else {
            preparedStatement.setNull(i,Types.NULL);
        }
    }

    @Override
    public Integer getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Integer getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Integer getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }*/
}
