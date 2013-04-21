package com.jkonury.www.user.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jkonury.www.user.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"	
})
@Transactional
public class UserDaoTest {
	@Autowired
	UserDao userDao;
	
//	test fixture
	User user1;
	User user2;
	User user3;
	
	@Before
	public void setUp() {
		user1 = new User(0, "user", "userPass", "user@email.com", "홍길동", "02-111-2222",  "010-1111-2222", "001", null, "150-222", "1 경주시 황성동", "1328-45번지", 0);
		user2 = new User(0, "test", "testPass", "test@email.com", "테스트", "031-111-2222", "011-1111-2222", "002", null, "250-222", "2 경주시 황성동", "2328-45번지", 0);
		user3 = new User(0, "hong", "hongPass", "hong@email.com", "장지홍", "054-111-2222", "017-1111-2222", "003", null, "350-222", "3 경주시 황성동", "3328-45번지", 0);
	}
	
	@Test
	public void notNull() {
		assertNotNull(userDao);
	}
	
	@Test
	public void addAndGet() {
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));
		
		int userSeq1 = userDao.add(user1);
		assertThat(userDao.getCount(), is(1));
		
		int userSeq2 = userDao.add(user2);
		int userSeq3 = userDao.add(user3);
		assertThat(userDao.getCount(), is(3));
		
		User getUser1 = userDao.get(userSeq1);
		User getUser2 = userDao.get(userSeq2);
		User getUser3 = userDao.get(userSeq3);
		
		checkSameUser(getUser1, user1);
		checkSameUser(getUser2, user2);
		checkSameUser(getUser3, user3);
	}

	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getPasswd(), is(user2.getPasswd()));
		assertThat(user1.getEmail(), is(user2.getEmail()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getTel(), is(user2.getTel()));
		assertThat(user1.getPhone(), is(user2.getPhone()));
		assertThat(user1.getRole(), is(user2.getRole()));
		assertThat(user1.getZipCode(), is(user2.getZipCode()));
		assertThat(user1.getDftAddr(), is(user2.getDftAddr()));
		assertThat(user1.getDtlAddr(), is(user2.getDtlAddr()));
	}
	
	@Test
	public void updateAnddelete() {
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));
		
		int userSeq1 = userDao.add(user1);
		assertThat(userDao.getCount(), is(1));
		
		int userSeq2 = userDao.add(user2);
		int userSeq3 = userDao.add(user3);
		
		assertThat(userDao.getCount(), is(3));
		
		User getUser2 = userDao.get(userSeq2);
		checkSameUser(getUser2, user2);
		
		userDao.deleteById(userSeq1);
		assertThat(userDao.getCount(), is(2));
		
		userDao.delete(getUser2);
		assertThat(userDao.getCount(), is(1));
		
		User getUser3 = userDao.get(userSeq3);
		checkSameUser(getUser3, user3);
		
		getUser3.setPasswd("passwdUpdate");
		getUser3.setEmail("updateEmail");
		getUser3.setName("이름 변경");
		getUser3.setTel("02-9999-0000");
		getUser3.setPhone("010-3333-4444");
		getUser3.setRole("999");
		getUser3.setDftAddr("주소 변경");
		getUser3.setDtlAddr("기본주소 변경");
		
		String getUser3_mod_date = getUser3.getModifiedDate();
		
		userDao.update(getUser3);
		
		
		User updateGetUser3 = userDao.get(userSeq3);
		assertThat(getUser3_mod_date, not(updateGetUser3.getModifiedDate()));
		checkSameUser(getUser3, updateGetUser3);
		
		userDao.deleteById(userSeq3);
		assertThat(userDao.getCount(), is(0));
	}
}
