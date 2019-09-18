package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.BookDao;
import kr.co.itcen.bookmall.vo.BookVo;
import kr.co.itcen.bookmall.vo.CategoryVo;


public class BookDaoTest {
	
	public static void main(String[] args) {
		insertTest();
		selectTest();
		deleteAllTest();
		
	}

	public static void insertTest() {

		BookVo bookVo1 = new BookVo();
		bookVo1.setTitle("흔한 남매2");
		bookVo1.setPrice(15000L);
		bookVo1.setAuthor("백난도");
		bookVo1.setPublisher("한빛");
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setNo(1L);
		bookVo1.setCategoryVo(categoryVo);

		BookVo bookVo2 = new BookVo();
		bookVo2.setTitle("당신은 뇌를 고칠 수 없다");
		bookVo2.setPrice(16000L);
		bookVo2.setAuthor("톰 오브라이언");
		bookVo2.setPublisher("브론스테인");
		CategoryVo categoryVo2 = new CategoryVo();
		categoryVo2.setNo(2L);
		bookVo2.setCategoryVo(categoryVo2);

		BookVo bookVo3 = new BookVo();
		bookVo3.setTitle("어느 날 공주가 되어버렸다");
		bookVo3.setPrice(15000L);
		bookVo3.setAuthor("플루토스");
		bookVo3.setPublisher("캐롯툰");
		CategoryVo categoryVo3 = new CategoryVo();
		categoryVo3.setNo(3L);
		bookVo3.setCategoryVo(categoryVo3);

		insertBook(bookVo1);
		insertBook(bookVo2);
		insertBook(bookVo3);

	}

	private static void insertBook(BookVo bookVo) {

		if(new BookDao().insert(bookVo)){
			System.out.println("Book Insert");
		}

	}
	
	private static void selectTest() {
		System.out.println("select test-----------------------");

		BookDao dao = new BookDao();

		List<BookVo> list = dao.getList();
		for(BookVo vo:list) {
			System.out.println(vo);
		}
	}
	private static void deleteAllTest(){
		new BookDao().delete();
	}
}
