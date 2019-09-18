package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.BookDao;
import kr.co.itcen.bookmall.dao.CartDao;
import kr.co.itcen.bookmall.vo.BookVo;
import kr.co.itcen.bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		insertTest();
		deleteAllTest();
	}

	public static void insertTest() {

		CartVo cartVo = new CartVo();
		cartVo.setBookNo(1L);
		cartVo.setMemberId("dlwprn1541");
		cartVo.setAmount(3L);

		CartVo cartVo2 = new CartVo();
		cartVo2.setBookNo(2L);
		cartVo2.setMemberId("dltmdcns1541");
		cartVo2.setAmount(2L);
		
		insertCart(cartVo);
		insertCart(cartVo2);

	}

	public static void insertCart(CartVo cartVo) {

		if(new CartDao().insert(cartVo)) {
			System.out.println("Cart Insert");
		}
		

	}
	
	private static void deleteAllTest(){
		new CartDao().delete();
	}
}
