package com.hikmath.LearningPortal.entity;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.String.valueOf;

@Slf4j
public class customCourseIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {

        String prefix = "course_";
        session.getJdbcConnectionAccess();

        try {
            JdbcConnectionAccess jdbcConnectionAccess = session.getJdbcConnectionAccess();
            Connection connection = jdbcConnectionAccess.obtainConnection();
            Statement statement = connection.createStatement();
            String query = "select count(course_id) as Id from courses";

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int id = resultSet.getInt(1) + 1;
                return prefix + valueOf(id);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            log.error("An error occurred while generating category ID", e);
        }
        return null;
    }
}


