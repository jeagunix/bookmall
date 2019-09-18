package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.UsersDao;

import kr.co.itcen.bookmall.vo.UsersVo;

public class UsersDaoTest {

	public static void main(String[] args) {
		insertTest();
		selectTest();
		deleteAllTest();
	}

	public static void insertTest() {
		UsersVo memberVo1 = new UsersVo();
		memberVo1.setId("dlwprn1541");
		memberVo1.setPasswd("1541");
		memberVo1.setName("이제구");
		memberVo1.setContact("010-5577-9719");
		memberVo1.setEmail("dlwprn1541@naver.com");

		UsersVo memberVo2 = new UsersVo();
		memberVo2.setId("dltmdcns1541");
		memberVo2.setPasswd("9719");
		memberVo2.setName("이승춘");
		memberVo2.setContact("010-5283-9719");
		memberVo2.setEmail("dltmdcns1541@gmail.com");

		insertMember(memberVo1);
		insertMember(memberVo2);

	}

	public static void insertMember(UsersVo memberVo) {
		if (new UsersDao().insert(memberVo)) {
			System.out.println("Memeber Insert");
		}
	}

	private static void selectTest() {
		System.out.println("select test-----------------------");

		UsersDao dao = new UsersDao();

		List<UsersVo> list = dao.getList();
		for (UsersVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void deleteAllTest() {
		new UsersDao().delete();
	}

}
