package com.gal.db.mybatis.handlers;

import com.gal.entities.enums.LocationType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationTypeTypeHandler implements TypeHandler<LocationType> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, LocationType locationType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,locationType.getLocationTypeId());

    }

    @Override
    public LocationType getResult(ResultSet resultSet, String s) throws SQLException {
        return LocationType.valueOf(resultSet.getInt(s));
    }

    @Override
    public LocationType getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public LocationType getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
