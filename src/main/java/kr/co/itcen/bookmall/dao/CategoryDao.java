package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kr.co.itcen.bookmall.db.BookmallDB;
import kr.co.itcen.bookmall.vo.CategoryVo;

public class CategoryDao {

	BookmallDB bookmallDB;

	public CategoryDao() {
		bookmallDB = new BookmallDB();
	}

	public Boolean insert(String category) {

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = bookmallDB.getConnection();
			String sql = "insert into category values(null, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, category);

			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
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

	public List<CategoryVo> getList() {

		List<CategoryVo> result = new ArrayList<CategoryVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = bookmallDB.getConnection();
			String sql = "select no, category from category";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long no = rs.getLong(1);
				String category = rs.getString(2);

				CategoryVo categoryVo = new CategoryVo();
				categoryVo.setNo(no);
				categoryVo.setCategory(category);

				result.add(categoryVo);
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

			String sql = "delete from category";
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
