package kr.co.itcen.bookmall.dao.test;

import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.bookmall.dao.CategoryDao;
import kr.co.itcen.bookmall.dao.OrderDao;
import kr.co.itcen.bookmall.vo.UsersVo;
import kr.co.itcen.bookmall.vo.OrderBookVo;
import kr.co.itcen.bookmall.vo.OrderVo;

public class OrderDaoTest {
	
	public static void main(String[] args) {
		insertTest(); 
		deleteAllTest();
	}
	
	public static void insertTest() {

		OrderVo orderVo = new OrderVo();
		orderVo.setPaymentPrice(22000L);
		orderVo.setDestination("경기도 부천시 오정구 고강본동");
		orderVo.setDeliveryStatus("상품준비중");
		orderVo.setOrderDate("2011-02-03");
		UsersVo memberVo = new UsersVo();
		memberVo.setId("dlwprn1541");
		orderVo.setMemberId(memberVo.getId());

		List<OrderBookVo> orderBookList = new ArrayList<OrderBookVo>();
		OrderBookVo orderBookVo1 = new OrderBookVo(1L, 2L); // 1번책 2권 주문을 위해 객체생성
		OrderBookVo orderBookVo2 = new OrderBookVo(2L, 3L); // 2번책 3권 주문을 위해 객체생성
		orderBookList.add(orderBookVo1);
		orderBookList.add(orderBookVo2);
		
		insertOrder(orderVo, orderBookList);

	}

	public static void insertOrder(OrderVo orderVo, List<OrderBookVo> orderBookList) {

		OrderDao orderDao = new OrderDao();
		Long lastOrderNo = orderDao.insertOrder(orderVo);

		for (OrderBookVo orderBookVo : orderBookList) {
			if(orderDao.insertOrderBook(lastOrderNo, orderBookVo.getBookNo(), orderBookVo.getBookAmount())) {
				System.out.println("OrderBook Insert");
			}
		}
	}
	private static void deleteAllTest(){
		new OrderDao().deleteOrder();
		new OrderDao().deleteOrderBook();
	}
}
