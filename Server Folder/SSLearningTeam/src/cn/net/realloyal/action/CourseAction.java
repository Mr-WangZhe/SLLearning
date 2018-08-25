package cn.net.realloyal.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.net.realloyal.core.util.BackJsonUtil;
import cn.net.realloyal.model.LanguageType;
import cn.net.realloyal.model.ListeningCourse;
import cn.net.realloyal.model.OralCourse;
import cn.net.realloyal.model.ReadingCourse;
import cn.net.realloyal.service.CourseService;
import cn.net.realloyal.service.RateTypeService;
import cn.net.realloyal.vo.RateTypeForSQL;

@Controller
@RequestMapping("/course")
public class CourseAction {
	@Autowired
	private CourseService courseService;
	@Autowired
	private RateTypeService rateTypeService;
	
	//获取听力课程添加页面——管理员
	@RequestMapping("/admin/toAddListeningCourse")
	public ModelAndView toAddListeningCourse() {
		ModelAndView mv = new ModelAndView("admin/resourceManager/addListeningCourse");
		mv.addObject("pageName","courseManage");
		List<LanguageType>languageTypes = rateTypeService.getLanguages();
		mv.addObject("languageTypes", languageTypes);
		return mv;
	}
	
	//获取口语课程添加页面——管理员
	@RequestMapping("/admin/toAddOralCourse")
	public ModelAndView toAddOralCourse() {
		ModelAndView mv = new ModelAndView("admin/resourceManager/addOralCourse");
		mv.addObject("pageName","courseManage");
		List<LanguageType>languageTypes = rateTypeService.getLanguages();
		mv.addObject("languageTypes", languageTypes);
		return mv;
	}
	
	//获取阅读课程添加页面——管理员
	@RequestMapping("/admin/toAddReadingCourse")
	public ModelAndView toAddReadingCourse() {
		ModelAndView mv = new ModelAndView("admin/resourceManager/addReadingCourse");
		mv.addObject("pageName","courseManage");
		List<LanguageType>languageTypes = rateTypeService.getLanguages();
		mv.addObject("languageTypes", languageTypes);
		return mv;
	}
	
	//添加听力课程接口
	@RequestMapping("/admin/addListeningCourse")
	public String addListeningCourse(@RequestParam("languageTypeId")Integer languageTypeId,@RequestParam("rateTypeId")Integer rateTypeId,@RequestParam("courseName")String courseName,@RequestParam("courseChineseContent")String courseChineseContent,@RequestParam("courseEnglishContent")String courseEnglishContent,@RequestParam("courseResource")CommonsMultipartFile courseResource,@RequestParam("instructionImg")CommonsMultipartFile instructionImg,HttpServletRequest request) {
		courseService.addListeningCourse(languageTypeId,rateTypeId,courseName,courseChineseContent,courseEnglishContent,courseResource,instructionImg,request);
		return "redirect:/course/admin/courses_manage";
	}
	
	//添加口语课程接口
	@RequestMapping("/admin/addOralCourse")
	public String addOralCourse(@RequestParam("languageTypeId")Integer languageTypeId,@RequestParam("rateTypeId")Integer rateTypeId,@RequestParam("courseName")String courseName,@RequestParam("courseChineseContent")String courseChineseContent,@RequestParam("courseEnglishContent")String courseEnglishContent,@RequestParam("instructionImg")CommonsMultipartFile instructionImg,HttpServletRequest request) {
		courseService.addOralCourse(languageTypeId,rateTypeId,courseName,courseChineseContent,courseEnglishContent,instructionImg,request);
		return "redirect:/course/admin/courses_manage";
	}
	
	//添加阅读课程接口
	@RequestMapping("/admin/addReadingCourse")
	public String addReadingCourse(@RequestParam("languageTypeId")Integer languageTypeId,@RequestParam("rateTypeId")Integer rateTypeId,@RequestParam("courseName")String courseName,@RequestParam("courseChineseContent")String courseChineseContent,@RequestParam("courseEnglishContent")String courseEnglishContent,@RequestParam("instructionImg")CommonsMultipartFile instructionImg,HttpServletRequest request) {
		courseService.addReadingCourse(languageTypeId,rateTypeId,courseName,courseChineseContent,courseEnglishContent,instructionImg,request);
		return "redirect:/course/admin/courses_manage";
	}
	
	//删除听力课程接口
	@ResponseBody
	@RequestMapping("/admin/deleteListeningCourse")
	public BackJsonUtil deleteListeningCourse(@RequestParam("courseId")Integer courseId) {
		return courseService.deleteListeningCourse(courseId);
	}
	
	//删除口语课程接口
	@ResponseBody
	@RequestMapping("/admin/deleteOralCourse")
	public BackJsonUtil deleteOralCourse(@RequestParam("courseId")Integer courseId) {
		return courseService.deleteOralCourse(courseId);
	}
	
	//删除阅读课程接口
	@ResponseBody
	@RequestMapping("/admin/deleteReadingCourse")
	public BackJsonUtil deleteReadingCourse(@RequestParam("courseId")Integer courseId) {
		return courseService.deleteReadingCourse(courseId);
	}
	
	//跳转修改听力课程信息页面
	@RequestMapping("/admin/toUpdateListeningCourse")
	public ModelAndView toUpdateListeningCourse(@RequestParam("courseId")Integer courseId) {
		ModelAndView mv = new ModelAndView("admin/resourceManager/updateListeningCourse");
		mv.addObject("pageName","courseManage");
		//查看当前课程的相关信息用于表单回显
		ListeningCourse listeningCourse = courseService.getListeningCourseInfo(courseId);
		mv.addObject("listeningCourse",listeningCourse);
		//返回所有的languageType选项
		List<LanguageType>languageTypes = rateTypeService.getLanguages();
		mv.addObject("languageTypes", languageTypes);
		return mv;
	}
	
	//跳转修改口语课程信息页面
	@RequestMapping("/admin/toUpdateOralCourse")
	public ModelAndView toUpdateOralCourse(@RequestParam("courseId")Integer courseId) {
		ModelAndView mv = new ModelAndView("admin/resourceManager/updateOralCourse");
		mv.addObject("pageName","courseManage");
		//查看当前课程的相关信息用于表单回显
		OralCourse oralCourse = courseService.getOralCourseInfo(courseId);
		mv.addObject("oralCourse",oralCourse);
		//返回所有的languageType选项
		List<LanguageType>languageTypes = rateTypeService.getLanguages();
		mv.addObject("languageTypes", languageTypes);
		return mv;
	}
	
	//跳转修改阅读课程信息页面
	@RequestMapping("/admin/toUpdateReadingCourse")
	public ModelAndView toUpdateReadingCourse(@RequestParam("courseId")Integer courseId) {
		ModelAndView mv = new ModelAndView("admin/resourceManager/updateReadingCourse");
		mv.addObject("pageName","courseManage");
		//查看当前课程的相关信息用于表单回显
		ReadingCourse readingCourse = courseService.getReadingCourseInfo(courseId);
		mv.addObject("readingCourse",readingCourse);
		//返回所有的languageType选项
		List<LanguageType>languageTypes = rateTypeService.getLanguages();
		mv.addObject("languageTypes", languageTypes);
		return mv;
	}
	
	//修改听力课程信息接口
	@RequestMapping("/admin/updateListeningCourse")
	public String updateListeningCourse(@RequestParam("courseId")Integer courseId,@RequestParam("languageTypeId")Integer languageTypeId,@RequestParam("rateTypeId")Integer rateTypeId,@RequestParam("courseName")String courseName,@RequestParam("courseChineseContent")String courseChineseContent,@RequestParam("courseEnglishContent")String courseEnglishContent,@RequestParam(value="courseResource",required=false)CommonsMultipartFile courseResource,@RequestParam("downloadNum")Integer downloadNum,@RequestParam(value="instructionImg",required=false)CommonsMultipartFile instructionImg,HttpServletRequest request) {
		courseService.updateListeningCourse(courseId,languageTypeId,rateTypeId,courseName,courseChineseContent,courseEnglishContent,courseResource,downloadNum,instructionImg,request);
		return "redirect:/course/admin/courses_manage";
	}
	
	//修改口语课程信息接口
	@RequestMapping("/admin/updateOralCourse")
	public String updateOralCourse(@RequestParam("courseId")Integer courseId,@RequestParam("languageTypeId")Integer languageTypeId,@RequestParam("rateTypeId")Integer rateTypeId,@RequestParam("courseName")String courseName,@RequestParam("courseChineseContent")String courseChineseContent,@RequestParam("courseEnglishContent")String courseEnglishContent,@RequestParam("downloadNum")Integer downloadNum,@RequestParam(value="instructionImg",required=false)CommonsMultipartFile instructionImg,HttpServletRequest request) {
		courseService.updateOralCourse(courseId,languageTypeId,rateTypeId,courseName,courseChineseContent,courseEnglishContent,downloadNum,instructionImg,request);
		return "redirect:/course/admin/courses_manage";
	}
	
	//修改阅读课程信息接口
	@RequestMapping("/admin/updateReadingCourse")
	public String updateReadingCourse(@RequestParam("courseId")Integer courseId,@RequestParam("languageTypeId")Integer languageTypeId,@RequestParam("rateTypeId")Integer rateTypeId,@RequestParam("courseName")String courseName,@RequestParam("courseChineseContent")String courseChineseContent,@RequestParam("courseEnglishContent")String courseEnglishContent,@RequestParam("downloadNum")Integer downloadNum,@RequestParam(value="instructionImg",required=false)CommonsMultipartFile instructionImg,HttpServletRequest request) {
		courseService.updateReadingCourse(courseId,languageTypeId,rateTypeId,courseName,courseChineseContent,courseEnglishContent,downloadNum,instructionImg,request);
		return "redirect:/course/admin/courses_manage";
	}
	
	//获取综合课程列表——管理员
	@RequestMapping("/admin/courses_manage/{pageNum}")
	public ModelAndView getCoursesManage(@PathVariable("pageNum")Integer pageNum) {
		ModelAndView mv = new ModelAndView("admin/resourceManager/courses_manage");
		mv.addObject("pageName","courseManage");
		//返回综合课程信息
		Map<String,List>allTypeCourses = courseService.getAllTypeCourses(pageNum);
		mv.addObject("courses", allTypeCourses);
		//返回总页数
		int totalPages = courseService.allTypesTotalPages();
		mv.addObject("totalPages", totalPages);
		//返回当前页面页号
		mv.addObject("pageNum", pageNum);
		return mv;
	}
	
	//获取综合课程列表JSON——用户
	@ResponseBody
	@RequestMapping("/user/courses_manage/{pageNum}")
	public BackJsonUtil getCoursesManageByUser(@PathVariable("pageNum")Integer pageNum) {
		return courseService.getCoursesManageByUser(pageNum);
	}
	
	//获取听力课程列表——管理员
	@RequestMapping("/admin/listeningCourses_manage/{pageNum}")
	public ModelAndView getListeningCourses_manage(@PathVariable("pageNum")Integer pageNum) {
		ModelAndView mv = new ModelAndView("admin/resourceManager/courses_manage");
		mv.addObject("pageName","courseManage");
		//返回听力课程信息
		Map<String,List>listeningCourses = courseService.getListeningCourses(pageNum);
		mv.addObject("courses", listeningCourses);
		//返回听力课程总页数
		int totalPages = courseService.getListeningCoursesTotalPages();
		mv.addObject("totalPages", totalPages);
		//返回当前页面页号
		mv.addObject("pageNum", pageNum);
		return mv;
	}
	
	//获得听力课程列表JSON——用户
	@ResponseBody
	@RequestMapping("/user/listeningCourses_manage/{pageNum}")
	public BackJsonUtil getListeningCourses_manageByUser(@PathVariable("pageNum")Integer pageNum) {
		return courseService.getListeningCourses_manageByUser(pageNum);
	}
	
	//获取按下载量排序：听力课程列表——管理员
	@RequestMapping("/admin/listeningCourses_manageByDownload/{pageNum}")
	public ModelAndView getListeningCourses_manageByDownload(@PathVariable("pageNum")Integer pageNum) {
		ModelAndView mv = new ModelAndView("admin/resourceManager/courses_manage");
		mv.addObject("pageName","courseManage");
		//返回听力课程信息
		Map<String,List>listeningCoursesByDownload = courseService.getListeningCoursesByDownload(pageNum);
		mv.addObject("courses", listeningCoursesByDownload);
		//返回听力课程总页数
		int totalPages = courseService.getListeningCoursesTotalPages();
		mv.addObject("totalPages", totalPages);
		//返回当前页面页号
		mv.addObject("pageNum", pageNum);
		return mv;
	}
	
	//获取按下载量排序JSON：听力课程列表——用户
	@ResponseBody
	@RequestMapping("/user/listeningCourses_manageByDownload/{pageNum}")
	public BackJsonUtil getListeningCourses_manageByDownloadByUser(@PathVariable("pageNum")Integer pageNum) {
		return courseService.getListeningCourses_manageByDownloadByUser(pageNum);
	}
	
	//获取筛选指定语言类别的听力课程列表——管理员
	@RequestMapping("/admin/listeningCourses_manageByLanguageType/{pageNum}")
	public ModelAndView getListeningCourses_manageByLanguageType(@PathVariable("pageNum")Integer pageNum,@RequestParam("languageTypeId")Integer languageTypeId) {
		ModelAndView mv = new ModelAndView("admin/resourceManager/courses_manage");
		mv.addObject("pageName","courseManage");
		//返回指定语言类别的听力课程信息
		Map<String,List>listeningCoursesByLanguageType = courseService.getListeningCoursesByLanguageType(pageNum,languageTypeId);
		mv.addObject("courses", listeningCoursesByLanguageType);
		//返回指定语言类别的听力课程总页数
		int totalPages = courseService.getListeningCoursesTotalPagesByLanguageType(languageTypeId);
		mv.addObject("totalPages", totalPages);
		//返回当前页面页号
		mv.addObject("pageNum", pageNum);
		//System.out.println("Language------pageNum:"+pageNum+"\n\ntotalPages:"+totalPages+"\n\ncourses:"+listeningCoursesByLanguageType);
		return mv;
	}
	
	//获取筛选指定语言类别的听力课程列表JSON——用户
	@ResponseBody
	@RequestMapping("/user/listeningCourses_manageByLanguageType/{pageNum}")
	public BackJsonUtil getListeningCourses_manageByLanguageTypeByUser(@PathVariable("pageNum")Integer pageNum,@RequestParam("languageTypeId")Integer languageTypeId) {
		return courseService.getListeningCourses_manageByLanguageTypeByUser(pageNum,languageTypeId);
	}
	
	//获取筛选指定种类类别(种类类别已经确定了语言类别)的听力课程列表——管理员
	@RequestMapping("/admin/listeningCourses_manageByRateType/{pageNum}")
	public ModelAndView getListeningCourses_manageByRateType(@PathVariable("pageNum")Integer pageNum,@RequestParam("rateTypeId")Integer rateTypeId) {
		ModelAndView mv = new ModelAndView("admin/resourceManager/courses_manage");
		mv.addObject("pageName","courseManage");
		//返回指定种类类别的听力课程信息
		Map<String,List>listeningCoursesByRateType = courseService.getListeningCourses_manageByRateType(pageNum,rateTypeId);
		mv.addObject("courses", listeningCoursesByRateType);
		//返回指定种类类别的听力课程总页数
		int totalPages = courseService.getListeningCoursesTotalPagesByRateType(rateTypeId);
		mv.addObject("totalPages", totalPages);
		//返回当前页面页号
		mv.addObject("pageNum", pageNum);
		//System.out.println("ratetype------pageNum:"+pageNum+"\n\ntotalPages:"+totalPages+"\n\ncourses:"+listeningCoursesByRateType);
		return mv;
	}
	
	//获取筛选指定种类类别(种类类别已经确定了语言类别)的听力课程列表JSON——用户
	@ResponseBody
	@RequestMapping("/user/listeningCourses_manageByRateType/{pageNum}")
	public BackJsonUtil getListeningCourses_manageByRateTypeByUser(@PathVariable("pageNum")Integer pageNum,@RequestParam("rateTypeId")Integer rateTypeId) {
		return courseService.getListeningCourses_manageByRateTypeByUser(pageNum,rateTypeId);
	}
	
	//获得口语课程列表——管理员
	
	//获得口语课程列表JSON——用户
	
	//获取按下载量排序：口语课程列表——管理员
	
	//获得阅读课程列表——管理员
	
	//获取按下载量排序：阅读课程列表——管理员
	
	//获得阅读课程列表JSON——用户
	
	//根据课程名称搜索得到综合课程列表——管理员
	
	//根据课程名称搜索得到综合课程列表JSON——用户
	
	
}
