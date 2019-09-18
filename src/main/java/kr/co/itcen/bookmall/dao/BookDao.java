package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.itcen.bookmall.db.BookmallDB;
import kr.co.itcen.bookmall.vo.BookVo;
import kr.co.itcen.bookmall.vo.CategoryVo;

public class BookDao {

	BookmallDB bookmallDB;

	public BookDao() {
		bookmallDB = new BookmallDB();
	}

	public Boolean insert(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = bookmallDB.getConnection();
			String sql = "insert into book values(null, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bookVo.getTitle());
			pstmt.setLong(2, bookVo.getPrice());
			pstmt.setString(3, bookVo.getAuthor());
			pstmt.setString(4, bookVo.getPublisher());
			pstmt.setLong(5, bookVo.getCategoryVo().getNo());

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

	public List<BookVo> getList() {

		List<BookVo> result = new ArrayList<BookVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = bookmallDB.getConnection();
			String sql = "select a.no, a.title, a.price, a.author, a.publisher, b.category" + " from book a, category b"
					+ " where a.category_no = b.no";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookVo bookVo = new BookVo();
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long price = rs.getLong(3);
				String author = rs.getString(4);
				String publisher = rs.getString(5);
				String category = rs.getString(6);
				CategoryVo categoryVo = new CategoryVo();
				categoryVo.setCategory(category);

				bookVo.setNo(no);
				bookVo.setTitle(title);
				bookVo.setPrice(price);
				bookVo.setAuthor(author);
				bookVo.setPublisher(publisher);
				bookVo.setCategoryVo(categoryVo);

				result.add(bookVo);
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

			String sql = "delete from book";
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
