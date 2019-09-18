package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.BookDao;
import kr.co.itcen.bookmall.dao.CategoryDao;
import kr.co.itcen.bookmall.vo.BookVo;
import kr.co.itcen.bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		insertTest();
		selectTest();
		deleteAllTest();
		
	}
	
	public static void insertTest() {
		
		String category1 = "IT";
		String category2 = "인문";
		String category3 = "소설";
		
		insertCategory(category1);
		insertCategory(category2);
		insertCategory(category3);

	}

	public static void insertCategory(String category) {

		if(new CategoryDao().insert(category)) {
			System.out.println("Category Insert");
		}

	}
	private static void selectTest() {
		System.out.println("select test-----------------------");

		CategoryDao dao = new CategoryDao();

		List<CategoryVo> list = dao.getList();
		for(CategoryVo vo:list) {
			System.out.println(vo);
		}
	}
	private static void deleteAllTest(){
		new CategoryDao().delete();
	}
}
