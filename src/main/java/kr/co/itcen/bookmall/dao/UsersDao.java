package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.itcen.bookmall.db.BookmallDB;
import kr.co.itcen.bookmall.vo.UsersVo;

public class UsersDao {

	BookmallDB bookmallDB;

	public UsersDao() {
		bookmallDB = new BookmallDB();
	}

	public boolean insert(UsersVo usersVo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = bookmallDB.getConnection();

			String sql = "insert into users values(?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, usersVo.getId());
			pstmt.setString(2, usersVo.getPasswd());
			pstmt.setString(3, usersVo.getName());
			pstmt.setString(4, usersVo.getContact());
			pstmt.setString(5, usersVo.getEmail());

			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public List<UsersVo> getList() {

		List<UsersVo> result = new ArrayList<UsersVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = bookmallDB.getConnection();
			String sql = "select id, name, contact, email from users";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String contact = rs.getString(3);
				String email = rs.getString(4);

				UsersVo usersVo = new UsersVo();
				usersVo.setId(id);
				usersVo.setName(name);
				usersVo.setContact(contact);
				usersVo.setEmail(email);

				result.add(usersVo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public void delete() {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = bookmallDB.getConnection();

			String sql = "delete from users";
			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
