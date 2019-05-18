package org.lyh.java.mybatis.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeHandler implements TypeHandler<UserType> {

    public void setParameter(PreparedStatement preparedStatement, int i, UserType userType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, userType.getName());
    }

    public UserType getResult(ResultSet resultSet, String s) throws SQLException {
        return UserType.getDict().get(resultSet.getString(s));
    }

    public UserType getResult(ResultSet resultSet, int i) throws SQLException {
        return UserType.getDict().get(resultSet.getString(i));
    }

    public UserType getResult(CallableStatement callableStatement, int i) throws SQLException {
        return UserType.getDict().get(callableStatement.getString(i));
    }
}