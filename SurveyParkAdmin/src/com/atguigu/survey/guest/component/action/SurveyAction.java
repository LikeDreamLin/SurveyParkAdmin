package com.atguigu.survey.guest.component.action;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.i.UserAware;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.e.CascadDelSurveyException;
import com.atguigu.survey.guest.component.service.i.BagService;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalNames;
import com.atguigu.survey.utils.ValidateUtils;

@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware{

	//*******************************成员变量区*************************************
	private static final long serialVersionUID = -2018295513477578782L;
	
	private File logo;
	private String logoContentType;
	private String logoFileName;
	
	private String pageNoStr;
	
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private BagService bagService;
	
	private List<Bag> bagList;

	private User user;

	//*******************************Action方法区*************************************
	public String myEngagedSurvey() {
		
		Page<Survey> page = surveyService.getMyEngagedSurvey(user,pageNoStr,10);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toMyEngagedSurvey";
	}
	
	
	public String findAllAvailableSurvey() {
		
		Page<Survey> page = surveyService.findAllAvailableSurvey(pageNoStr, 30);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toAllAvailableSurveyPage";
	}
	
	public String top10() {
		
		List<Survey> newestList = surveyService.findNewestTenSurvey();
		List<Survey> hotestList = surveyService.findHotestTenSurvey();
		
		reqMap.put(GlobalNames.NEW_TEN_SURVEY, newestList);
		reqMap.put(GlobalNames.HOT_TEN_SURVEY, hotestList);
		
		return "toTop10Page";
	}
	
	public void prepareUpdateBagOrder() {
		this.t = surveyService.getEntityById(surveyId);
	}
	
	public String updateBagOrder() {
		
		if(!ValidateUtils.bagListValidate(bagList)) {
			addActionError("包裹序号请不要重复！");
			return "toAdjustBagOrderPage";
		}
		
		bagService.batchUpdateBagOrder(bagList);
		
		return "toSurveyDesignAction";
	}
	
	public void prepareAdjustBagOrder() {
		this.t = surveyService.getEntityById(surveyId);
	}
	
	public String adjustBagOrder() {
		
		return "toAdjustBagOrderPage";
	}
	
	public String complete() {
		
		boolean completeResult = surveyService.complete(surveyId);
		
		if(!completeResult) {
			addActionError("调查还不完整！");
		}
		
		return "toSurveyMessagePage";
	}
	
	public void prepareDesign() {
		//将Survey对象从数据库中查询出来并放到栈顶，便于设计调查页面显示
		this.t = surveyService.getEntityById(surveyId);
	}
	
	public String design() {
		return "toDesignPage";
	}
	
	public void prepareUpdate() {
		this.inputPath = "/guestPages/survey_edit.jsp";
		//使用从数据库中取出的Survey对象来接收请求参数，从而保持userId不变
		if(surveyId != null)
			this.t = surveyService.getEntityById(surveyId);
	}
	
	public String update() {
		
		this.inputPath = "/guestPages/survey_edit.jsp";
		
		//声明图片上传后的虚拟路径
		String virtualPath = "/surveyLogos";
		
		//将虚拟路径转换为真实路径
		String realPath = this.servletContext.getRealPath(virtualPath);
		
		//实现文件复制，并返回一个可用的logoPath
		String logoPath = DataProcessUtils.resizeImages(logo, realPath);

		//仅在logoPath有效的前提下才进行设置，否则保持默认值
		if(ValidateUtils.stringValidate(logoPath)) {
			this.t.setLogoPath(logoPath);
		}
		
		//此时栈顶对象是：this.t，实际上就是Survey对象，而且是已经注入了请求参数的Survey对象
		surveyService.updateEntity(t);
		
		return "toMyUncompletedAction";
	}
	
	public void prepareEditSurvey() {
		//此时surveyId值的注入要求在prepare拦截器前面有params拦截器，所以要使用paramsPrepareParamsStack拦截器栈
		this.t = surveyService.getEntityById(surveyId);
	}
	
	public String editSurvey() {
		return "toEditSurveyPage";
	}
	
	public String remove() {
		
		try {
			//1.为了避免级联删除是出现问题，要尝试捕获异常
			surveyService.deleteEntity(t);
		} catch (Exception e) {
			
			throw new CascadDelSurveyException();
			
		}
		
		return "toMyUncompletedAction";
	}
	
	public String myCompletedSurveyList() {
		
		Page<Survey> page = surveyService.getCompletedPage(pageNoStr, user, 5);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toCompletedSurveyList";
	}
	
	public String myUncompleted() {
		
		Page<Survey> page = surveyService.getUncompletedPage(pageNoStr, user, 5);
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toUncompletedListPage";
	}
	
	public void prepareSave() {
		this.inputPath = "/guestPages/survey_create.jsp";
	}
	
	public String save() {
		
		//在Action方法中执行这个操作并不能生效，原因是：真正检测到上传错误时，不执行目标Action的目标方法
		//this.inputPath = "/guestPages/survey_create.jsp";
		
		//遗留问题1：没有关联到User
		//关联的方式：survey.setUser(user);
		//User的来源：Session域中——在实现UserAware接口后可以由LoginInterceptor拦截器主动注入
		//User user = (User) session.getAttribute(GlobalNames.LOGIN_USER);
		this.t.setUser(user);
		
		//1.收集创建调查时需要提供的数据
		//①调查的标题：已经被Struts2的模型驱动机制注入到栈顶的t对象值中
		
		//②将来页面上显示图片时需要用到的图片路径：需要将原始的图片压缩后获取其可保存的路径值
		String virtualPath = "/surveyLogos";
		
		String realPath = this.servletContext.getRealPath(virtualPath);
		
		String logoPath = DataProcessUtils.resizeImages(logo, realPath);

		//仅在logoPath有效的前提下才进行设置，否则保持默认值
		if(ValidateUtils.stringValidate(logoPath)) {
			this.t.setLogoPath(logoPath);
		}
		
		//2.将收集了必要数据的Survey对象保存到数据库中
		this.surveyService.saveEntity(t);
		
		return "toMyUncompletedAction";
	}
	
	//*******************************getXxx()、setXxx()方法区*************************************
	public File getLogo() {
		return logo;
	}
	
	public void setLogo(File logo) {
		this.logo = logo;
	}
	
	public String getLogoContentType() {
		return logoContentType;
	}
	
	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}
	
	public String getLogoFileName() {
		return logoFileName;
	}
	
	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Bag> getBagList() {
		return bagList;
	}
	
	public void setBagList(List<Bag> bagList) {
		this.bagList = bagList;
	}
	
	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}
}
