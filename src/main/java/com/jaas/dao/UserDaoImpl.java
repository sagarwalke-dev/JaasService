package com.jaas.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import com.jaas.entity.User;
import com.jaas.entity.UserDetails;
import com.jaas.utils.DBQueries;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public User getUserByName(String username) {
		try {
			log.info("calling getUser DAO");
			Object[] args = { username };
			log.info("DBQueries.GET_USER: {}", DBQueries.GET_USER);
			List<User> userList = jdbcTemplate.query(DBQueries.GET_USER, args, new BeanPropertyRowMapper(User.class));
			if (userList.isEmpty()) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (Exception e) {
			log.error("Error while getUser DAO: {}", ExceptionUtils.getStackTrace(e));
		} finally {
			log.info("getUser DAO completed");
		}
		return null;
	}

	@Override
	public Boolean addUser(UserDetails user) {
		try {
			log.info("calling addUser DAO");
			return jdbcTemplate.execute(DBQueries.ADD_USER, new PreparedStatementCallback<Boolean>() {
				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, user.getUsername());
					ps.setString(2, user.getPassword());
					ps.setDate(3, user.getDob());
					ps.setString(4, user.getMobileNo());
					ps.setString(5, user.getEmail());
					ps.setString(6, user.getCity());
					return ps.execute();
				}
			});
		} catch (Exception e) {
			log.error("Error in addUser DAO: {}", ExceptionUtils.getStackTrace(e));
		} finally {
			log.info("addUser DAO completed");
		}
		return false;
	}

}