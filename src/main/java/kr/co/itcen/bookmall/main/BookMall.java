package kr.co.itcen.bookmall.main;

import java.util.List;
import kr.co.itcen.bookmall.dao.BookDao;
import kr.co.itcen.bookmall.dao.CartDao;
import kr.co.itcen.bookmall.dao.CategoryDao;
import kr.co.itcen.bookmall.dao.UsersDao;
import kr.co.itcen.bookmall.dao.OrderDao;
import kr.co.itcen.bookmall.dao.test.BookDaoTest;
import kr.co.itcen.bookmall.dao.test.CartDaoTest;
import kr.co.itcen.bookmall.dao.test.CategoryDaoTest;
import kr.co.itcen.bookmall.dao.test.UsersDaoTest;
import kr.co.itcen.bookmall.dao.test.OrderDaoTest;
import kr.co.itcen.bookmall.vo.BookVo;
import kr.co.itcen.bookmall.vo.CartVo;
import kr.co.itcen.bookmall.vo.CategoryVo;
import kr.co.itcen.bookmall.vo.UsersVo;
import kr.co.itcen.bookmall.vo.OrderBookVo;
import kr.co.itcen.bookmall.vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		insertAll();
		selectAll();
		
	}

	
	private static void insertAll() {
		UsersDaoTest.insertTest();
		CategoryDaoTest.insertTest();
		BookDaoTest.insertTest();
		CartDaoTest.insertTest();
		OrderDaoTest.insertTest();
	}

	private static void selectAll() {
		selectMemberList();
		selectCategoryList();
		selectBookList();
		selectCartListByMemberId();
		selectOrderListByMemberId();
		selectOrderedBookListByOrderNo();
	}

	private static void selectMemberList() {

		List<UsersVo> memberList = new UsersDao().getList();
		System.out.println("===================멤버 리스트====================");
		for (UsersVo memberVo : memberList) {
			System.out.println(memberVo.toString());
		}
		System.out.println();
	}

	private static void selectCategoryList() {

		List<CategoryVo> categoryList = new CategoryDao().getList();
		System.out.println("===================카테고리 리스트====================");
		for (CategoryVo categoryVo : categoryList) {
			System.out.println(categoryVo.toString());
		}
		System.out.println();
	}

	private static void selectBookList() {

		List<BookVo> bookList = new BookDao().getList();
		System.out.println("===================도서 리스트====================");
		for (BookVo bookVo : bookList) {
			System.out.println(bookVo.toString());
		}
	}

	private static void selectCartListByMemberId() {

		String memberId = "dlwprn1541";

		List<CartVo> cartList = new CartDao().getList(memberId);
		System.out.println("===================카트 리스트====================");
		for (CartVo cartVo : cartList) {
			System.out.println(cartVo.toString());
		}
		System.out.println();
	}

	private static void selectOrderListByMemberId() {

		String memberId = "dlwprn1541";

		System.out.println("=====================주문 리스트====================");
		List<OrderVo> orderList = new OrderDao().getOrderList(memberId);
		for (OrderVo orderVo : orderList) {
			System.out.println(orderVo.toString());
		}
		System.out.println();
	}

	private static void selectOrderedBookListByOrderNo() {

		Long orderNo = 1L;

		System.out.println("===================주문한 도서리스트====================");
		List<OrderBookVo> orderBookVoList = new OrderDao().getOrderBookList(orderNo);
		for (OrderBookVo orderBookVo : orderBookVoList) {

			System.out.println(orderBookVo.toString());

		}
		System.out.println();
	}
}
