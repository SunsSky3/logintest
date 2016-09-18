package junit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhang.mrbs.domain.MrbsText;
import com.zhang.mrbs.service.IMrbsTextService;

public class TestService {

	/**保存*/
	@Test
	public void save(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IMrbsTextService elecTextService = (IMrbsTextService) ac.getBean(IMrbsTextService.SERVICE_NAME);
		
		MrbsText elecText = new MrbsText();
		elecText.setTextName("测试Service名称");
		
		Date date = new Date();
		String sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		
		elecText.setTextDate(new Date());
		elecText.setTextRemark("测试Service备注");
		elecTextService.saveMrbsText(elecText);
		
	}
	
	/**模拟Action层，测试底层方法，指定查询条件查询结果列表*/
	@Test
	public void findCollectionByConditionNoPage(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IMrbsTextService mrbsTextService = (IMrbsTextService) ac.getBean(IMrbsTextService.SERVICE_NAME);
		
		//模型驱动中封装结果
		MrbsText mrbsText = new MrbsText();
		//mrbsText.setTextName("测试Dao名称");// 条件
		//mrbsText.setTextRemark("张");
		List<MrbsText> list = mrbsTextService.findCollectionByConditionNoPage(mrbsText);
		if(list!=null && list.size()>0){
			for(MrbsText text:list){
				System.out.println(text.getTextName()+"   "+text.getTextRemark());
			}
		}
		
	}
}
