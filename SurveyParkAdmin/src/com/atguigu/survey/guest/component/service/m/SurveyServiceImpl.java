package com.atguigu.survey.guest.component.service.m;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.m.BaseServiceImpl;
import com.atguigu.survey.guest.component.dao.i.AnswerDao;
import com.atguigu.survey.guest.component.dao.i.QuestionDao;
import com.atguigu.survey.guest.component.dao.i.SurveyDao;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.entity.Answer;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.Question;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.ValidateUtils;

@Service
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements SurveyService{

	@Autowired
	private SurveyDao surveyDao;
	@Autowired
	private AnswerDao answerDao;
	@Autowired
	private QuestionDao questionDao;
	
	@Override
	public Page<Survey> getUncompletedPage(String pageNoStr, User user,
			int pageSize) {
		
		//1.查询数据库得到总记录数
		int totalRecordNo = surveyDao.getUncompletedCount(user);
		
		//2.创建Page对象
		Page<Survey> page = new Page<>(pageNoStr, totalRecordNo, pageSize);
		
		//3.从Page对象中获取经过修正的pageNo值
		Integer pageNo = page.getPageNo();
		
		//4.根据经过修正的pageNo值和pageSize查询list
		List<Survey> list = surveyDao.getUncompletedList(pageNo, pageSize, user);
		
		//5.将list设置到page对象中
		page.setList(list);
		
		return page;
	}

	@Override
	public Page<Survey> getCompletedPage(String pageNoStr, User user,
			int pageSize) {
		//1.查询数据库得到总记录数
		int totalRecordNo = surveyDao.getCompletedCount(user);
		
		//2.创建Page对象
		Page<Survey> page = new Page<>(pageNoStr, totalRecordNo, pageSize);
		
		//3.从Page对象中获取经过修正的pageNo值
		Integer pageNo = page.getPageNo();
		
		//4.根据经过修正的pageNo值和pageSize查询list
		List<Survey> list = surveyDao.getCompletedList(pageNo, pageSize, user);
		
		//5.将list设置到page对象中
		page.setList(list);
		
		return page;
	}

	@Override
	public boolean complete(Integer surveyId) {
		
		//1.检查当前调查是否完整
		//完整标准：Survey对象关联的Bag集合不为空，Bag集合中每一个Bag对象关联的Question集合也不为空
		//①根据surveyId查询一个Survey对象
		Survey survey = surveyDao.getEntityById(surveyId);
		
		//②检查关联的Bag集合
		Set<Bag> bagSet = survey.getBagSet();
		if(!ValidateUtils.collectionValidate(bagSet)) return false;
		
		//③遍历Bag集合，获取每一个Question集合
		for (Bag bag : bagSet) {
			//④检查Question集合是否为空
			Set<Question> questions = bag.getQuestions();
			
			if(!ValidateUtils.collectionValidate(questions)) return false;
			
		}
		
		//2.如果完整，则将completed属性设置为true，并保存
		survey.setCompleted(true);
		survey.setCompletedTime(new Date());
		
		//3.如果调查经检测是完整的，那么返回true
		return true;
	}

	@Override
	public List<Survey> findNewestTenSurvey() {
		return surveyDao.findNewestTenSurvey();
	}

	@Override
	public List<Survey> findHotestTenSurvey() {
		return surveyDao.findHotestTenSurvey();
	}

	@Override
	public Page<Survey> findAllAvailableSurvey(String pageNoStr,int pageSize) {
		
		int totalRecordNo = surveyDao.getCompletedCount();
		
		Page<Survey> page = new Page<>(pageNoStr, totalRecordNo, pageSize);
		
		List<Survey> dataList = surveyDao.getCompletedList(page.getPageNo(), pageSize);
		
		page.setList(dataList);
		
		return page;
	}

	@Override
	public void saveEngageMsg(Integer userId, Integer surveyId) {
		surveyDao.saveEngageMsg(userId, surveyId);
	}

	@Override
	public Page<Survey> getMyEngagedSurvey(User user, String pageNoStr,int pageSize) {
		
		int totalRecordNo = surveyDao.getMyEngagedCount(user.getUserId());
		
		Page<Survey> page = new Page<>(pageNoStr, totalRecordNo, pageSize);
		
		List<Survey> list = surveyDao.getMyEngagedList(user.getUserId(),page.getPageNo(), pageSize);
		
		page.setList(list);
		
		return page;
	}

	@Override
	public Page<Survey> getCompletedPage(String pageNoStr, int pageSize) {
		
		int totalRecord = surveyDao.getCompletedCount();
		
		Page<Survey> page = new Page<>(pageNoStr, totalRecord, pageSize);
		
		List<Survey> list = surveyDao.getCompletedList(page.getPageNo(), pageSize);
		
		page.setList(list);
		
		return page;
	}

	@Override
	public HSSFWorkbook generateWorkBook(Integer surveyId) {
		
		//一、准备数据
		//1.Survey对象
		Survey survey = surveyDao.getEntityById(surveyId);
		
		//2.当前调查参与的总数
		//SELECT COUNT(DISTINCT UUID) FROM answers WHERE survey_id=?
		int engageCount = surveyDao.getTotalEngagedCount(surveyId);
		
		//3.当前调查被参与的所有答案
		List<Answer> answerList = answerDao.getEngagedListBySurveyId(surveyId);
		
		//4.当前调查的所有问题
		List<Question> questionList = questionDao.getQuestionListBySurveyId(surveyId);
		
		//二、生成工作表
		//1.创建Excel文件对应的HSSFWorkBook对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//2.创建工作表对象：HSSFSheet
		HSSFSheet sheet = workbook.createSheet(survey.getTitle()+" "+engageCount+"人次参与");
		
		//3.创建行对象：HSSFRow
		//rownum是行的索引从0开始的
		HSSFRow row = sheet.createRow(0);
		
		//4.如果参与调查的总人数等于0
		if(engageCount == 0) {
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("当前调查还没有人参与！");
			return workbook;
		}
		
		//5.如果不为0，则在第一行显示问题标题
		for (int i = 0; i < questionList.size(); i++) {
			
			Question question = questionList.get(i);
			String questionName = question.getQuestionName();
			
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(questionName);
			
		}
		
		Map<String, Map<Integer, String>> bigMap = convertAnswer(answerList);
		
		Set<Entry<String, Map<Integer, String>>> bigEntrySet = bigMap.entrySet();
		ArrayList<Entry<String, Map<Integer, String>>> bigEntryList = new ArrayList<>(bigEntrySet);
		
		for(int i = 0; i < bigEntryList.size(); i++) {
			
			Entry<String, Map<Integer, String>> entry = bigEntryList.get(i);
			String uuid = entry.getKey();
			Map<Integer, String> samllMap = entry.getValue();
			row = sheet.createRow(i + 1);
			
			for(int j = 0; j < questionList.size(); j++) {
				
				Question question = questionList.get(j);
				Integer questionId = question.getQuestionId();
				String answerContent = samllMap.get(questionId);
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(answerContent);
			}
			
		}
		
		/*Set<Entry<String, Map<Integer, String>>> entrySet = bigMap.entrySet();
		for (Entry<String, Map<Integer, String>> entry : entrySet) {
			String uuid = entry.getKey();
			Map<Integer, String> smallMap = entry.getValue();
			Set<Entry<Integer, String>> samllEntrySet = smallMap.entrySet();
			for (Entry<Integer, String> samllEntry : samllEntrySet) {
				Integer qId = samllEntry.getKey();
				String content = samllEntry.getValue();
				System.out.println(uuid+" "+qId+" "+content);
			}
		}*/
		
		for (int i = 0; i < questionList.size(); i++) {
			sheet.autoSizeColumn(i);
		}
		
		return workbook;
	}
	
	public Map<String, Map<Integer, String>> convertAnswer(List<Answer> answers) {
		
		Map<String, Map<Integer, String>> bigMap = new HashMap<String, Map<Integer,String>>();
		
		for (Answer answer : answers) {
			
			String uuid = answer.getUuid();
			
			//key：questionId，value：答案内容
			Map<Integer, String> smallMap = bigMap.get(uuid);
			if(smallMap == null) {
				smallMap = new HashMap<>();
				bigMap.put(uuid, smallMap);
			}
			
			Integer questionId = answer.getQuestionId();
			
			String oldContent = smallMap.get(questionId);
			
			if(ValidateUtils.stringValidate(oldContent)) {
				oldContent = oldContent + ",";
			}else{
				oldContent = "";
			}
			
			String mainAnswers = answer.getMainAnswers();
			if(ValidateUtils.stringValidate(mainAnswers)) {
				oldContent = oldContent + mainAnswers;
			}
			
			String otherAnswers = answer.getOtherAnswers();
			if(ValidateUtils.stringValidate(otherAnswers)) {
				oldContent = oldContent + "[其他项：" + otherAnswers + "],";
			}
			
			oldContent = DataProcessUtils.removeLastComma(oldContent);
			
			smallMap.put(questionId, oldContent);
			
		}
		
		return bigMap;
	}

}















