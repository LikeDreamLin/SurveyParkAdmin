package junit.test;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.guest.component.service.i.BagService;
import com.atguigu.survey.guest.component.service.i.StatisticsService;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.component.service.i.UserService;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.Question;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.OptionStatisticsModel;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.guest.model.QuestionStatisticsModel;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.HTMLTagUtils;

public class CommonTest {

	private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	private UserService userService = ioc.getBean(UserService.class);
	private SurveyService surveyService = ioc.getBean(SurveyService.class);
	private BagService bagService = ioc.getBean(BagService.class);
	private ResourceService resourceService = ioc.getBean(ResourceService.class);
	
	@Test
	public void test19() {
		Integer maxResPos = resourceService.getMaxResPos();
		System.out.println(maxResPos);
		
		Long resCode = resourceService.getCurrentMaxResCode(maxResPos);
		System.out.println(resCode);
	}
	
	@Test
	public void test18() {
		surveyService.generateWorkBook(1);
	}
	
	@Test
	public void test17() {
		StatisticsService statisticsService = ioc.getBean(StatisticsService.class);
		QuestionStatisticsModel qsm = statisticsService.getQsm(1);
		System.out.println(qsm);
		
		List<OptionStatisticsModel> osmList = qsm.getOsmList();
		for (OptionStatisticsModel optionStatisticsModel : osmList) {
			System.out.println(optionStatisticsModel);
		}
	}
	
	@Test
	public void test16() {
		String result = DataProcessUtils.convertArrToStr(new String[]{"a"});
		System.out.println(result);
	}
	
	@Test
	public void test15() {
		String radio = HTMLTagUtils.generateRadio("radio51", "question5", "5", "选项01", "checked='checked'", true);
		System.out.println(radio);
		
		String generateOption = HTMLTagUtils.generateOption("value01", "selected='selected'", "选项01");
		System.out.println(generateOption);
		
		String text = HTMLTagUtils.generateText("text01", "value01", true);
		System.out.println(text);
	}
	
	@Test
	public void test14() {
		Survey survey = surveyService.getEntityById(115);
		System.out.println(survey);
	}
	
	@Test
	public void test13() {
		Bag bag = bagService.getFirstBag(115);
		System.out.println(bag);
		
		bag = bagService.getPrevBag(115, 6);
		System.out.println(bag);
		
		bag = bagService.getNextBag(115, 6);
		System.out.println(bag);
	}
	
	@Test
	public void test12() {
		Bag sourceBag = bagService.getEntityById(4);

		System.out.println("源对象：");
		System.out.println(sourceBag.hashCode());
		System.out.println(sourceBag);
		Set<Question> questions = sourceBag.getQuestions();
		for (Question question : questions) {
			System.out.println(question);
		}
		
		Bag targetBag = (Bag) DataProcessUtils.deeplyCopy(sourceBag);
		
		System.out.println("================================");
		
		System.out.println("目标对象：");
		System.out.println(targetBag.hashCode());
		System.out.println(targetBag);
		questions = targetBag.getQuestions();
		for (Question question : questions) {
			System.out.println(question);
		}
	}
	
	@Test
	public void test11() {
		String source = "AAA\r\nBBB\r\nCCC\r\nDDD";
		System.out.println(DataProcessUtils.processStrForSave(source));
		
		source = "AAA,BBB,CCC,DDD";
		System.out.println(DataProcessUtils.processStrForShow(source));
		
		System.out.println();
		
		String[] arr = DataProcessUtils.convertStrToArr(source);
		for (String string : arr) {
			System.out.println(string);
		}
	}
	
	@Test
	public void test10() {
		for(int i = 0; i < 100; i++) {
			Survey survey = new Survey();
			User user = new User();
			user.setUserId(1);
			
			survey.setUser(user);
			survey.setTitle("title"+i);
			
			surveyService.saveEntity(survey);
		}
	}
	
	@Test
	public void test09() {
		
		User user = new User();
		user.setUserId(5);
		
		Page<Survey> page = surveyService.getUncompletedPage("ABC", user, 2);
		List<Survey> list = page.getList();
		for (Survey survey : list) {
			System.out.println(survey.getTitle());
		}
		
	}
	
	@Test
	public void test08() {
		//测试压缩图片
		File file = new File("Koala.jpg");
		String realPath = "C:\\image";
		String path = DataProcessUtils.resizeImages(file, realPath);
		System.out.println(path);
	}
	
	@Test
	public void test07() {
		Date date = new Date();
		System.out.println(date.getTime());
	}
	
	@Test
	public void test06() throws ParseException {
		//测试剩余天数
		User user = new User();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse("2016-02-03 08:20:30");
		
		user.setPayStatus(true);
		user.setEndTime(date.getTime());
		
		int leftDays = user.getLeftDays();
		System.out.println(leftDays);
		
	}
	
	@Test
	public void test05() {
		User user = new User();
		user.setUserName("Tom2016");
		user.setUserPwd("123ttt456");
		user = userService.login(user);
		System.out.println(user);
	}
	
	@Test
	public void test04() throws NoSuchAlgorithmException {
		//测试MD5加密
		//1.原始字符串
		String src = "goodMorningOOOPPPP";
		
		//2.将原始字符串转换为字节数组
		byte[] bytes = src.getBytes();
		System.out.println("原始字节数组长度："+bytes.length);
		
		//3.创建MD5加密对象
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		//4.给加密对象传入字节数组
		byte[] targetBytes = digest.digest(bytes);
		System.out.println("加密后的字节数组长度："+targetBytes.length);
		
		//提供一个转换16进制数的字符数组
		char [] code = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		//提供一个保存每个字节字符的字符串
		StringBuilder sb = new StringBuilder();
		
		//5.将字节数组转化为字符串
		for (int i = 0; i < targetBytes.length; i++) {
			byte b = targetBytes[i];
			
			//①低四位转换
			int lowNumber = b & 0x0F;//0x0F→00001111
			
			//②高四位转换
			int highNumber = (b >> 4) & 0x0F;
			
			sb.append(code[lowNumber]).append(code[highNumber]);
		}
		
		System.out.println(sb);
	}
	
	@Test
	public void test03() {
		User user = new User();
		user.setUserName("Tom2016");
		user.setUserPwd("123456");
		boolean exists = userService.regist(user);
		System.out.println(exists?"用户名已存在":"用户名不存在");
	}
	
	@Test
	public void test02() {
		//测试SessionFactory
	}
	
	@Test
	public void test01() throws SQLException {
		//测试数据源
		DataSource dataSource = ioc.getBean(DataSource.class);
		Connection connection = dataSource.getConnection();
		
		System.out.println(connection);
		
	}

}
