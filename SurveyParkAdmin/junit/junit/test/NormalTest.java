package junit.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.atguigu.survey.utils.DataProcessUtils;

public class NormalTest {
	
	@Test
	public   void   testMd5(){
		
		
		System.out.println(DataProcessUtils.md5("123"));
		
		
	}
	
	
	
	
	@Test
	public void test04() {
		
		/*
		SELECT * FROM log_2016_3 UNION 
		SELECT * FROM log_2016_4 UNION 
		SELECT * FROM log_2016_5 UNION 
		SELECT * FROM log_2016_6 UNION 
		SELECT * FROM log_2016_7 UNION 
		SELECT * FROM log_2016_8
		 */
		
		List<String> tableNames = getTableNames();
		String subSelect = DataProcessUtils.generateSubSelect(tableNames);
		System.out.println(subSelect);
		
	}
	
	public List<String> getTableNames() {
		List<String> tableNames = new ArrayList<>();
		
		tableNames.add("log_2016_3");
		tableNames.add("log_2016_4");
		tableNames.add("log_2016_5");
		tableNames.add("log_2016_6");
		tableNames.add("log_2016_7");
		tableNames.add("log_2016_8");
		
		return tableNames;
	}
	
	@Test
	public void test03() {
		
		String tableName = DataProcessUtils.generateTableName(-25);
		System.out.println(tableName);
		
		tableName = DataProcessUtils.generateTableName(-5);
		System.out.println(tableName);
		
		tableName = DataProcessUtils.generateTableName(-2);
		System.out.println(tableName);
		
		tableName = DataProcessUtils.generateTableName(0);
		System.out.println(tableName);
		
		tableName = DataProcessUtils.generateTableName(2);
		System.out.println(tableName);
		
		tableName = DataProcessUtils.generateTableName(5);
		System.out.println(tableName);
		
		tableName = DataProcessUtils.generateTableName(25);
		System.out.println(tableName);
		
	}
	
	@Test
	public void test02() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = format.parse("2016-02-29 14:36:00");
		long time = parse.getTime();
		System.out.println(time);
	}

	@Test
	public void test01() {
		
		//int 32
		System.out.println(1 << 60);
		System.out.println(1 << 31);// 1000 0000 0000 0000 0000 0000 0000 0000
		System.out.println(1 << 32);// 000 0000 0000 0000 0000 0000 0000 00001
		
		System.out.println(1L << 58);
		System.out.println(1L << 59);
		System.out.println(1L << 60);
	}

}
