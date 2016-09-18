package junit;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.star.util.DateTime;
import com.zhang.javabean.File;
import com.zhang.mrbs.dao.IFileDeleteDao;
import com.zhang.mrbs.dao.IFindFileByIdDao;
import com.zhang.mrbs.dao.IMrbsTextDao;
import com.zhang.mrbs.domain.MrbsText;



public class TestDao {

	/**保存*/
	@Test
	public void save(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IMrbsTextDao mrbsTextDao  = (IMrbsTextDao) ac.getBean(IMrbsTextDao.SERVICE_NAME);
		
		MrbsText mrbsText = new MrbsText();
		mrbsText.setTextName("测试Dao名称11");
		mrbsText.setTextDate(new Date());
		mrbsText.setTextRemark("测试Dao备注");
		mrbsTextDao.save(mrbsText);
		
	}
	
	/**更新*/
	@Test
	public void update(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IMrbsTextDao mrbsTextDao  = (IMrbsTextDao) ac.getBean(IMrbsTextDao.SERVICE_NAME);
		
		MrbsText mrbsText = new MrbsText();
		mrbsText.setTextID("4028ad81571e437c01571e4380af0001");
		mrbsText.setTextName("测试Dao名称1111");
		mrbsText.setTextDate(new Date());
		mrbsText.setTextRemark("测试Dao备注");
		mrbsTextDao.update(mrbsText);
		
	}
	
	/**使用主键ID查询对象的对象*/
	@Test
	public void findObjectById(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IMrbsTextDao mrbsTextDao = (IMrbsTextDao) ac.getBean(IMrbsTextDao.SERVICE_NAME);
		Serializable id = "4028ad815737206a0157372070650001";
		MrbsText mrbsText = mrbsTextDao.findObjectByID(id);
		System.out.println(mrbsText.getTextName()+"    "+mrbsText.getTextRemark());
		
		Date date =  mrbsText.getTextDate();
		String DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		System.out.println(DATE_FORMAT);
	}
	
	/**使用主键ID查询对象的对象*/
	@Test
	public void findObjectById1(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IFindFileByIdDao mrbsTextDao = (IFindFileByIdDao) ac.getBean(IFindFileByIdDao.SERVICE_NAME);
		Serializable id = "80";
		File mrbsText = mrbsTextDao.findObjectByID(80);
		System.out.println(mrbsText.getUploadtime()+"    "+mrbsText.getUploadtime());
		
		/*Date date =  mrbsText.getTextDate();
		String DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		System.out.println(DATE_FORMAT);*/
	}
	
	
	/**删除（使用主键ID删除）*/
	@Test
	public void deleteObjectByIds(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IMrbsTextDao mrbsTextDao = (IMrbsTextDao) ac.getBean(IMrbsTextDao.SERVICE_NAME);
		//Serializable [] ids = {"4028ad8155d3d8730155d3d875bc0001","4028ad8155d3d6500155d3d652e70001"};
		Serializable ids = "4028ad8157233c9e0157233cc6d60001";
		mrbsTextDao.deleteObjectByIds(ids);
	}
	
	/**删除（使用主键ID删除）*/
	@Test
	public void deleteObjectByIds1(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		/*IMrbsTextDao mrbsTextDao = (IMrbsTextDao) ac.getBean(IMrbsTextDao.SERVICE_NAME);
		//Serializable [] ids = {"4028ad8155d3d8730155d3d875bc0001","4028ad8155d3d6500155d3d652e70001"};
		Serializable ids = "4028ad8157233c9e0157233cc6d60001";*/
		
		IFileDeleteDao fileDeleteDao = (IFileDeleteDao) ac.getBean(IFileDeleteDao.SERVICE_NAME);
		Serializable [] ids = {49};
		fileDeleteDao.deleteObjectByIds(65);
		System.out.println("123");
	}
	
	
	/**删除（使用集合List进行删除）*/
	@Test
	public void deleteObjectByCollection(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IMrbsTextDao mrbsTextDao = (IMrbsTextDao) ac.getBean(IMrbsTextDao.SERVICE_NAME);
		List<MrbsText> list = new ArrayList<MrbsText>();
		MrbsText mrbsText1 = new MrbsText();
		mrbsText1.setTextID("4028ad81571e437c01571e4380af0001");
		
		MrbsText mrbsText2 = new MrbsText();
		mrbsText2.setTextID("4028ad81571e4bd301571e4bd7c60001");
		
		MrbsText mrbsText3 = new MrbsText();
		mrbsText3.setTextID("4028ad81571e92bc01571e93eb510001");
		
		list.add(mrbsText1);
		list.add(mrbsText2);
		list.add(mrbsText3);
		
		mrbsTextDao.deleteObjectByCollection(list);
	}
}
