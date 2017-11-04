package com.crossp.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppJDBCService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private final String JOIN_CP = "insert into application_application(application_id, applications_id) values(?,?)";
	private final String BREAK_CP = "delete from application_application where application_id =? and applications_id =?";
	private final String MSG_READ_ONE = "update app_message set status = 1 where cid = ? and id = ?";
	private final String MSG_READ_MY = "update app_message set status = 1 where cid = ?";
	private final String MSG_DELETE_MY = "delete from app_message where cid = ?";

	@Autowired
	private DataSource dataSource;

	public void excuteUpdate(String sql) throws SQLException {
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		conn.close();
	}

	public void readMsgOne(long uid, long mid) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(MSG_READ_ONE);
			pstmt.setLong(1, uid);
			pstmt.setLong(2, mid);
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			log.error("SQL failed:", e);
		} finally {
			log.info("excute sql: {}", MSG_READ_ONE);
		}
	}

	public void readMsgALL(long uid) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(MSG_READ_MY);
			pstmt.setLong(1, uid);
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			log.error("SQL failed:", e);
		} finally {
			log.info("excute sql: {}", MSG_READ_MY);
		}
	}

	public void deteleMsg(long uid) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(MSG_DELETE_MY);
			pstmt.setLong(1, uid);
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			log.error("SQL failed:", e);
		} finally {
			log.info("excute sql: {}", MSG_DELETE_MY);
		}
	}

	public void joinCP(int wid, int rid) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(JOIN_CP);
			pstmt.setInt(1, wid);
			pstmt.setInt(2, rid);
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			log.error("SQL CP failed:", e);
		} finally {
			log.info("excute sql: {}", JOIN_CP);
		}
	}

	public void breakCP(int wid, int rid) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(BREAK_CP);
			pstmt.setInt(1, wid);
			pstmt.setInt(2, rid);
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			log.error("SQL CP failed:", e);
		} finally {
			log.info("excute sql: {}", BREAK_CP);
		}
	}
}
