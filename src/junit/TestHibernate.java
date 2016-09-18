package junit;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.zhang.javabean.File;
import com.zhang.mrbs.domain.MrbsText;



public class TestHibernate {

	/**测试保存*/
	@Test
	public void save(){
		Configuration configuration = new Configuration();
		configuration.configure();//加载classpath下的hibernate.cfg.xml的文件
		SessionFactory sf = configuration.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		MrbsText mrbsText = new MrbsText();
		mrbsText.setTextName("测试hibernate名称");
		mrbsText.setTextDate(new Date());
		mrbsText.setTextRemark("测试hibernate备注");
		s.save(mrbsText);

		tr.commit();
		s.close();
	}
	
	@Test
	public void save1(){
		Configuration configuration = new Configuration();
		configuration.configure();//加载classpath下的hibernate.cfg.xml的文件
		SessionFactory sf = configuration.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		/*MrbsText mrbsText = new MrbsText();
		mrbsText.setTextName("测试hibernate名称");
		mrbsText.setTextDate(new Date());
		mrbsText.setTextRemark("测试hibernate备注");
		s.save(mrbsText);*/
		
		File file = new File();
		file.setFilename("asdaaa");
		file.setRoomnum(0);
		Date now = new Date();
		file.setUploadtime(now);
		s.save(file);
		
		
		tr.commit();
		s.close();
	}
}
