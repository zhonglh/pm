package test.pm;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.common.beans.Pager;
import com.pm.domain.system.User;
import com.pm.service.IUserService;



@ContextConfiguration(locations={"classpath:applicationContext*.xml"})
public class TestUserService  extends AbstractTransactionalJUnit4SpringContextTests{
	

	@Autowired
	IUserService userService;

	@Test
	@Rollback(false)
	public void testQueryUser(){
		Pager<User> pager = new Pager<User>();
		pager.setPageNo(1);
		pager.setPageSize(20);
		pager = userService.queryUser(null, null, pager);
	}
	
}

