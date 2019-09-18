package kr.co.itcen.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.co.itcen.bookmall.db.BookmallDB;
import kr.co.itcen.bookmall.vo.CartVo;


public class CartDao {

	BookmallDB bookmallDB;

	public CartDao() {
		bookmallDB = new BookmallDB();
	}

	public Boolean insert(CartVo cartVo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = bookmallDB.getConnection();
			String sql = "insert into cart values(?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, cartVo.getBookNo());
			pstmt.setString(2, cartVo.getMemberId());
			pstmt.setLong(3, cartVo.getAmount());

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

	public List<CartVo> getList(String memberId) {

		List<CartVo> result = new ArrayList<CartVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = bookmallDB.getConnection();
			String sql = "select a.book_no, concat(b.title,' ', a.amount,'권' ), a.amount, (a.amount * b.price) as 'price'"
					+ " from cart a, book b" + " where a.book_no = b.no" + " and a.member_id = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartVo cartVo = new CartVo();
				Long bookNo = rs.getLong(1);
				String title = rs.getString(2);
				Long amount = rs.getLong(3);
				Long price = rs.getLong(4);

				cartVo.setBookNo(bookNo);
				cartVo.setMemberId(memberId);
				cartVo.setTitle(title);
				cartVo.setAmount(amount);
				cartVo.setPrice(price);

				result.add(cartVo);
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

			String sql = "delete from cart";
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