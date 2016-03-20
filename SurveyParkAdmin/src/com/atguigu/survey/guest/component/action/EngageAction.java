package com.atguigu.survey.guest.component.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.i.UserAware;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.AnswerService;
import com.atguigu.survey.guest.component.service.i.BagService;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class EngageAction extends BaseAction<Survey> implements UserAware{

	private static final long serialVersionUID = 1L;
	
	private Bag currentBag;
	private Integer bagId;
	@Autowired
	private BagService bagService;
	
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private AnswerService answerService;

	private User user;
	
	public String doEngage() {
		
		//0.将allBagMap从Session域中取出备用，在点击“上一个”、“下一个”、“完成”时都会提交当前包裹的答案数据
		//Action中接收到这些数据时应该立即保存到allBagMap中：合并答案
		Map<Integer, Map<String, String[]>> allBagMap = (Map<Integer, Map<String, String[]>>) this.sessionMap.get(GlobalNames.ALL_BAG_MAP);
		
		//1.获取用户在当前请求中点击的提交按钮的name属性值
		String submitName = DataProcessUtils.getSubmitName(parametersMap);
		
		//2.如果submitName是submit_prev则查询上一个包裹
		if("submit_prev".equals(submitName)) {
			
			allBagMap.put(bagId, parametersMap);
			
			//要保证是在同一个调查范围内，所以要传入surveyId
			//要找当前包裹的上一个包裹，所以要传入当前包裹的id
			currentBag = bagService.getPrevBag(surveyId,bagId);
			
		}
		
		//3.如果submitName是submit_next则查询下一个包裹
		if("submit_next".equals(submitName)) {
			
			allBagMap.put(bagId, parametersMap);
			
			currentBag = bagService.getNextBag(surveyId,bagId);
			
		}
		
		//4.如果submitName是submit_quit则清理Session域，回到首页
		if("submit_quit".equals(submitName)) {
			
			this.sessionMap.remove(GlobalNames.CURRENT_SURVEY);
			this.sessionMap.remove(GlobalNames.ALL_BAG_MAP);
			
			return "toTop10Action";
			
		}
		
		//5.如果submitName是submit_done则保存答案
		if("submit_done".equals(submitName)) {
			
			allBagMap.put(bagId, parametersMap);
			
			answerService.parseAndSave(allBagMap,surveyId);
			
			try {
				//如果不能保存，则说明数据已经存在，那么就无需再保存重复数据了
				surveyService.saveEngageMsg(user.getUserId(), surveyId);
			} catch (Exception e) {}
			
			return "toTop10Action";
		}
		
		return "toEngagePage";
	}
	
	public String entry() {
		
		//1.查询Survey对象
		Survey survey = surveyService.getEntityById(surveyId);
		
		//2.将Survey对象保存到Session域中
		this.sessionMap.put(GlobalNames.CURRENT_SURVEY, survey);
		
		//3.创建保持各个包裹答案数据的Map，并保存到Session域中
		Map<Integer, Map<String,String[]>> allBagMap = new HashMap<>();
		this.sessionMap.put(GlobalNames.ALL_BAG_MAP, allBagMap);
		
		//4.查找第一个包裹
		currentBag = bagService.getFirstBag(surveyId);
		
		return "toEngagePage";
	}

	public Bag getCurrentBag() {
		return currentBag;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}
	
	public Integer getBagId() {
		return bagId;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
}
