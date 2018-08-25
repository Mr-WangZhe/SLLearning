package cn.net.realloyal.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.net.realloyal.core.util.BackJsonUtil;
import cn.net.realloyal.core.util.CalculateStartIndex;
import cn.net.realloyal.mapper.CourseMapper;
import cn.net.realloyal.model.ListeningCourse;
import cn.net.realloyal.model.OralCourse;
import cn.net.realloyal.model.ReadingCourse;
import cn.net.realloyal.service.CourseService;
import cn.net.realloyal.vo.ListeningCourseForSQL;
import cn.net.realloyal.vo.OralCourseForSQL;
import cn.net.realloyal.vo.ReadingCourseForSQL;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseMapper courseMapper;

	@Override
	public void addListeningCourse(Integer languageTypeId, Integer rateTypeId, String courseName,
			String courseChineseContent, String courseEnglishContent, CommonsMultipartFile courseResource,
			CommonsMultipartFile instructionImg,HttpServletRequest request) {
		// 源听力资源文件名
		String originalCourseResourceName = courseResource.getOriginalFilename();
		// 新听力资源文件名  
        String newCourseResourceName = UUID.randomUUID() + originalCourseResourceName;
        // 源介绍图片资源文件名
 		String originalInstructionImgName = instructionImg.getOriginalFilename();
 		// 新介绍图片文件名  
         String newInstructionImgName = UUID.randomUUID() + originalInstructionImgName;
        // 获得项目的路径  
        ServletContext sc = request.getSession().getServletContext();  
        // 上传位置  
        String path = sc.getRealPath("/ListeningCourseResource") + "/"; // 设定文件保存的目录  
        File f = new File(path);  
        if (!f.exists())  
            f.mkdirs();  
        //将资源文件写入磁盘
        if (!courseResource.isEmpty()) {  
            try {  
                FileOutputStream fos = new FileOutputStream(path + newCourseResourceName);  
                InputStream in = courseResource.getInputStream();  
                int b = 0;  
                while ((b = in.read()) != -1) {  
                    fos.write(b);  
                }  
                fos.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
        //将介绍图片文件写入磁盘
        if (!instructionImg.isEmpty()) {  
            try {  
                FileOutputStream fos = new FileOutputStream(path + newInstructionImgName);  
                InputStream in = instructionImg.getInputStream();  
                int b = 0;  
                while ((b = in.read()) != -1) {  
                    fos.write(b);  
                }  
                fos.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
        String sourcePath = sc.getContextPath()+"/ListeningCourseResource/";
        String courseResourceUrl=sourcePath+newCourseResourceName;
        String instructionImgUrl=sourcePath+newInstructionImgName;
		ListeningCourseForSQL listeningCourse = new ListeningCourseForSQL(0,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,courseResourceUrl,0,instructionImgUrl);
		courseMapper.addListeningCourse(listeningCourse);
	}

	@Override
	public void addOralCourse(Integer languageTypeId, Integer rateTypeId, String courseName,
			String courseChineseContent, String courseEnglishContent, CommonsMultipartFile instructionImg,HttpServletRequest request) {
		// 源介绍图片资源文件名
 		String originalInstructionImgName = instructionImg.getOriginalFilename();
 		// 新介绍图片文件名  
         String newInstructionImgName = UUID.randomUUID() + originalInstructionImgName;
        // 获得项目的路径  
        ServletContext sc = request.getSession().getServletContext();  
        // 上传位置  
        String path = sc.getRealPath("/OralCourseResource") + "/"; // 设定文件保存的目录  
        File f = new File(path);  
        if (!f.exists())  
            f.mkdirs();  
        //将介绍图片文件写入磁盘
        if (!instructionImg.isEmpty()) {  
            try {  
                FileOutputStream fos = new FileOutputStream(path + newInstructionImgName);  
                InputStream in = instructionImg.getInputStream();  
                int b = 0;  
                while ((b = in.read()) != -1) {  
                    fos.write(b);  
                }  
                fos.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
        String sourcePath = sc.getContextPath()+"/OralCourseResource/";
        String instructionImgUrl=sourcePath+newInstructionImgName;
		OralCourseForSQL oralCourse = new OralCourseForSQL(0,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,0,instructionImgUrl);
		courseMapper.addOralCourse(oralCourse);
	}

	@Override
	public void addReadingCourse(Integer languageTypeId, Integer rateTypeId, String courseName,
			String courseChineseContent, String courseEnglishContent, CommonsMultipartFile instructionImg,HttpServletRequest request) {
		// 源介绍图片资源文件名
 		String originalInstructionImgName = instructionImg.getOriginalFilename();
 		// 新介绍图片文件名  
         String newInstructionImgName = UUID.randomUUID() + originalInstructionImgName;
        // 获得项目的路径  
        ServletContext sc = request.getSession().getServletContext();  
        // 上传位置  
        String path = sc.getRealPath("/ReadingCourseResource") + "/"; // 设定文件保存的目录  
        File f = new File(path);  
        if (!f.exists())  
            f.mkdirs();  
        //将介绍图片文件写入磁盘
        if (!instructionImg.isEmpty()) {  
            try {  
                FileOutputStream fos = new FileOutputStream(path + newInstructionImgName);  
                InputStream in = instructionImg.getInputStream();  
                int b = 0;  
                while ((b = in.read()) != -1) {  
                    fos.write(b);  
                }  
                fos.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
        String sourcePath = sc.getContextPath()+"/ReadingCourseResource/";
        String instructionImgUrl=sourcePath+newInstructionImgName;
		ReadingCourseForSQL readingCourse = new ReadingCourseForSQL(0,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,0,instructionImgUrl);
		courseMapper.addReadingCourse(readingCourse);
	}

	//删除听力课程
	@Override
	public BackJsonUtil deleteListeningCourse(Integer courseId) {
		int status = courseMapper.deleteListeningCourse(courseId);
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		if(status!=0) {
			backJsonUtil.setStatus(true);
			backJsonUtil.setInfo("删除成功");
		}else {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("删除失败");
		}
		return backJsonUtil;
	}

	//删除口语课程
	@Override
	public BackJsonUtil deleteOralCourse(Integer courseId) {
		int status = courseMapper.deleteOralCourse(courseId);
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		if(status!=0) {
			backJsonUtil.setStatus(true);
			backJsonUtil.setInfo("删除成功");
		}else {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("删除失败");
		}
		return backJsonUtil;
	}

	//删除阅读课程
	@Override
	public BackJsonUtil deleteReadingCourse(Integer courseId) {
		int status = courseMapper.deleteReadingCourse(courseId);
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		if(status!=0) {
			backJsonUtil.setStatus(true);
			backJsonUtil.setInfo("删除成功");
		}else {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("删除失败");
		}
		return backJsonUtil;
	}

	@Override
	public ListeningCourse getListeningCourseInfo(Integer courseId) {
		return courseMapper.getListeningCourseInfo(courseId);
	}

	@Override
	public OralCourse getOralCourseInfo(Integer courseId) {
		return courseMapper.getOralCourseInfo(courseId);
	}

	@Override
	public ReadingCourse getReadingCourseInfo(Integer courseId) {
		return courseMapper.getReadingCourseInfo(courseId);
	}

	//更新听力课程
	@Override
	public void updateListeningCourse(Integer courseId,Integer languageTypeId, Integer rateTypeId, String courseName,
			String courseChineseContent, String courseEnglishContent, CommonsMultipartFile courseResource,@RequestParam("downloadNum")Integer downloadNum,
			CommonsMultipartFile instructionImg, HttpServletRequest request) {
		ListeningCourseForSQL listeningCourse=null;
		// 获得项目的路径  
        ServletContext sc = request.getSession().getServletContext();  
        // 上传位置  
        String path = sc.getRealPath("/ListeningCourseResource") + "/"; // 设定文件保存的目录  
        File f = new File(path);  
        if (!f.exists())  
            f.mkdirs();  
        //资源基本路径
        String sourcePath = sc.getContextPath()+"/ListeningCourseResource/";
		if(!courseResource.isEmpty()) {
			// 源听力资源文件名
			String originalCourseResourceName = courseResource.getOriginalFilename();
			// 新听力资源文件名  
	        String newCourseResourceName = UUID.randomUUID() + originalCourseResourceName;
	        //将资源文件写入磁盘
            try {  
                FileOutputStream fos = new FileOutputStream(path + newCourseResourceName);  
                InputStream in = courseResource.getInputStream();  
                int b = 0;  
                while ((b = in.read()) != -1) {  
                    fos.write(b);  
                }  
                fos.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
	        String courseResourceUrl=sourcePath+newCourseResourceName;
			if(!instructionImg.isEmpty()) {
				// 源介绍图片资源文件名
		 		String originalInstructionImgName = instructionImg.getOriginalFilename();
		 		// 新介绍图片文件名  
		         String newInstructionImgName = UUID.randomUUID() + originalInstructionImgName;
		        //将介绍图片文件写入磁盘
	            try {  
	                FileOutputStream fos = new FileOutputStream(path + newInstructionImgName);  
	                InputStream in = instructionImg.getInputStream();  
	                int b = 0;  
	                while ((b = in.read()) != -1) {  
	                    fos.write(b);  
	                }  
	                fos.close();  
	                in.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
		        String instructionImgUrl=sourcePath+newInstructionImgName;
		        listeningCourse = new ListeningCourseForSQL(courseId,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,courseResourceUrl,downloadNum,instructionImgUrl);
		        courseMapper.updateListeningCourseWithRousourceWithImg(listeningCourse);
			}else {
				listeningCourse = new ListeningCourseForSQL(courseId,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,courseResourceUrl,downloadNum,"");
				courseMapper.updateListeningCourseWithRousourceWithoutImg(listeningCourse);
			}
		}else {
			if(!instructionImg.isEmpty()) {
				// 源介绍图片资源文件名
		 		String originalInstructionImgName = instructionImg.getOriginalFilename();
		 		// 新介绍图片文件名  
		         String newInstructionImgName = UUID.randomUUID() + originalInstructionImgName;
		        //将介绍图片文件写入磁盘
	            try {  
	                FileOutputStream fos = new FileOutputStream(path + newInstructionImgName);  
	                InputStream in = instructionImg.getInputStream();  
	                int b = 0;  
	                while ((b = in.read()) != -1) {  
	                    fos.write(b);  
	                }  
	                fos.close();  
	                in.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
		        String instructionImgUrl=sourcePath+newInstructionImgName;
		        listeningCourse = new ListeningCourseForSQL(courseId,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,"",downloadNum,instructionImgUrl);
		        courseMapper.updateListeningCourseWithoutRousourceWithImg(listeningCourse);
			}else {
				listeningCourse = new ListeningCourseForSQL(courseId,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,"",downloadNum,"");
				courseMapper.updateListeningCourseWithoutRousourceWithoutImg(listeningCourse);
			}
		}
	}
	
	//更新口语课程
	@Override
	public void updateOralCourse(Integer courseId, Integer languageTypeId, Integer rateTypeId, String courseName,
			String courseChineseContent, String courseEnglishContent,@RequestParam("downloadNum")Integer downloadNum, CommonsMultipartFile instructionImg,
			HttpServletRequest request) {
		OralCourseForSQL oralCourse=null;
		// 获得项目的路径  
        ServletContext sc = request.getSession().getServletContext();  
        // 上传位置  
        String path = sc.getRealPath("/OralCourseResource") + "/"; // 设定文件保存的目录  
        File f = new File(path);  
        if (!f.exists())  
            f.mkdirs();  
        //资源基本路径
        String sourcePath = sc.getContextPath()+"/OralCourseResource/";
		if(!instructionImg.isEmpty()) {
			// 源介绍图片资源文件名
	 		String originalInstructionImgName = instructionImg.getOriginalFilename();
	 		// 新介绍图片文件名  
	         String newInstructionImgName = UUID.randomUUID() + originalInstructionImgName;
	        //将介绍图片文件写入磁盘
            try {  
                FileOutputStream fos = new FileOutputStream(path + newInstructionImgName);  
                InputStream in = instructionImg.getInputStream();  
                int b = 0;  
                while ((b = in.read()) != -1) {  
                    fos.write(b);  
                }  
                fos.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
	        String instructionImgUrl=sourcePath+newInstructionImgName;
	        oralCourse = new OralCourseForSQL(courseId,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,downloadNum,instructionImgUrl);
	        courseMapper.updateOralCourseWithImg(oralCourse);
		}else {
			oralCourse = new OralCourseForSQL(courseId,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,downloadNum,"");
			courseMapper.updateOralCourseWithoutImg(oralCourse);
		}
	}

	//更新阅读课程
	@Override
	public void updateReadingCourse(Integer courseId, Integer languageTypeId, Integer rateTypeId, String courseName,
			String courseChineseContent, String courseEnglishContent,@RequestParam("downloadNum")Integer downloadNum, CommonsMultipartFile instructionImg,
			HttpServletRequest request) {
		ReadingCourseForSQL readingCourse=null;
		// 获得项目的路径  
        ServletContext sc = request.getSession().getServletContext();  
        // 上传位置  
        String path = sc.getRealPath("/ReadingCourseResource") + "/"; // 设定文件保存的目录  
        File f = new File(path);  
        if (!f.exists())  
            f.mkdirs();  
        //资源基本路径
        String sourcePath = sc.getContextPath()+"/ReadingCourseResource/";
		if(!instructionImg.isEmpty()) {
			// 源介绍图片资源文件名
	 		String originalInstructionImgName = instructionImg.getOriginalFilename();
	 		// 新介绍图片文件名  
	         String newInstructionImgName = UUID.randomUUID() + originalInstructionImgName;
	        //将介绍图片文件写入磁盘
            try {  
                FileOutputStream fos = new FileOutputStream(path + newInstructionImgName);  
                InputStream in = instructionImg.getInputStream();  
                int b = 0;  
                while ((b = in.read()) != -1) {  
                    fos.write(b);  
                }  
                fos.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
	        String instructionImgUrl=sourcePath+newInstructionImgName;
	        readingCourse = new ReadingCourseForSQL(courseId,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,downloadNum,instructionImgUrl);
	        courseMapper.updateReadingCourseWithImg(readingCourse);
		}else {
			readingCourse = new ReadingCourseForSQL(courseId,languageTypeId,rateTypeId,courseName,courseEnglishContent,courseChineseContent,downloadNum,"");
			courseMapper.updateReadingCourseWithoutImg(readingCourse);
		}
	}

	//取所有类型的课程：听力先取，取完为止；口语再取，取完为止；阅读再取，取完为止
	@Override
	public Map<String, List> getAllTypeCourses(Integer pageNum) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		if(startIndex<courseMapper.getListeningCoursesNum()) {
			List<ListeningCourse> listeningCourses = courseMapper.getListeningCourses(startIndex,10);
			allTypeCourses.replace("listeningCourses", listeningCourses);
			if(listeningCourses.size()<10) {
				int lastNum = 10-listeningCourses.size();
				List<OralCourse> oralCourses = courseMapper.getOralCourses(0,lastNum);
				allTypeCourses.replace("oralCourses",oralCourses);
				if(oralCourses.size()<lastNum) {
					int finalNum = lastNum - oralCourses.size();
					List<ReadingCourse> readingCourses = courseMapper.getReadingCourses(0,finalNum);
					allTypeCourses.replace("readingCourses", readingCourses);
				}
			}
		}else {
			startIndex = startIndex-courseMapper.getListeningCoursesNum();
			if(startIndex<courseMapper.getOralCoursesNum()) {
				List<OralCourse> oralCourses = courseMapper.getOralCourses(startIndex,10);
				allTypeCourses.replace("oralCourses",oralCourses);
				if(oralCourses.size()<10) {
					int lastNum = 10 - oralCourses.size();
					List<ReadingCourse> readingCourses = courseMapper.getReadingCourses(0,lastNum);
					allTypeCourses.replace("readingCourses", readingCourses);
				}
			}else {
				startIndex = startIndex-courseMapper.getOralCoursesNum();
				List<ReadingCourse> readingCourses = courseMapper.getReadingCourses(startIndex,10);
				allTypeCourses.replace("readingCourses", readingCourses);
			}
		}
		return allTypeCourses;
	}
	
	//所有类型课程的总页数
	@Override
	public int allTypesTotalPages() {
		int listeningCoursesNum=courseMapper.getListeningCoursesNum();
		int oralCoursesNum=courseMapper.getOralCoursesNum();
		int readingCoursesNum=courseMapper.getReadingCoursesNum();
		int totalPages = (int)(listeningCoursesNum+oralCoursesNum+readingCoursesNum)/10+1;
		return totalPages;
	}

	//取所有类型的课程（返回JSON）：听力先取，取完为止；口语再取，取完为止；阅读再取，取完为止
	@Override
	public BackJsonUtil getCoursesManageByUser(Integer pageNum) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int listeningCoursesNum=courseMapper.getListeningCoursesNum();
		int oralCoursesNum=courseMapper.getOralCoursesNum();
		int readingCoursesNum=courseMapper.getReadingCoursesNum();
		int totalPages = (int)(listeningCoursesNum+oralCoursesNum+readingCoursesNum)/10+1;
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
			Map<String,List> allTypeCourses = new HashMap<String,List>();
			allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
			allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
			allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
			if(startIndex<listeningCoursesNum) {
				List<ListeningCourse> listeningCourses = courseMapper.getListeningCourses(startIndex,10);
				allTypeCourses.replace("listeningCourses", listeningCourses);
				if(listeningCourses.size()<10) {
					int lastNum = 10-listeningCourses.size();
					List<OralCourse> oralCourses = courseMapper.getOralCourses(0,lastNum);
					allTypeCourses.replace("oralCourses",oralCourses);
					if(oralCourses.size()<lastNum) {
						int finalNum = lastNum - oralCourses.size();
						List<ReadingCourse> readingCourses = courseMapper.getReadingCourses(0,finalNum);
						allTypeCourses.replace("readingCourses", readingCourses);
					}
				}
			}else {
				startIndex = startIndex-listeningCoursesNum;
				if(startIndex<oralCoursesNum) {
					List<OralCourse> oralCourses = courseMapper.getOralCourses(startIndex,10);
					allTypeCourses.replace("oralCourses",oralCourses);
					if(oralCourses.size()<10) {
						int lastNum = 10 - oralCourses.size();
						List<ReadingCourse> readingCourses = courseMapper.getReadingCourses(0,lastNum);
						allTypeCourses.replace("readingCourses", readingCourses);
					}
				}else {
					startIndex = startIndex-oralCoursesNum;
					List<ReadingCourse> readingCourses = courseMapper.getReadingCourses(startIndex,10);
					allTypeCourses.replace("readingCourses", readingCourses);
				}
			}
			result.put("courses", allTypeCourses);
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}

	//取听力类型课程的内容
	@Override
	public Map<String, List> getListeningCourses(Integer pageNum) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<ListeningCourse> listeningCourses = courseMapper.getListeningCourses(startIndex,10);
		allTypeCourses.replace("listeningCourses", listeningCourses);
		return allTypeCourses;
	}

	//取听力课程的总页数
	@Override
	public int getListeningCoursesTotalPages() {
		int listeningCoursesNum=courseMapper.getListeningCoursesNum();
		return (int)(listeningCoursesNum)/10+1;
	}

	//获得听力课程列表JSON——用户
	@Override
	public BackJsonUtil getListeningCourses_manageByUser(Integer pageNum) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getListeningCoursesTotalPages();
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getListeningCourses(pageNum));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}
	
	//通过下载量排序，取听力类型课程的内容
	@Override
	public Map<String, List> getListeningCoursesByDownload(Integer pageNum) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<ListeningCourse> listeningCourses = courseMapper.getListeningCoursesByDownload(startIndex,10);
		allTypeCourses.replace("listeningCourses", listeningCourses);
		return allTypeCourses;
	}

	//获取按下载量排序JSON：听力课程列表——用户
	@Override
	public BackJsonUtil getListeningCourses_manageByDownloadByUser(Integer pageNum) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getListeningCoursesTotalPages();
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getListeningCoursesByDownload(pageNum));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}

	
	//通过语言类别，取听力类型课程的内容
	@Override
	public Map<String, List> getListeningCoursesByLanguageType(Integer pageNum, Integer languageTypeId) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<ListeningCourse> listeningCoursesByLanguageType = courseMapper.getListeningCoursesByLanguageType(startIndex,10,languageTypeId);
		allTypeCourses.replace("listeningCourses", listeningCoursesByLanguageType);
		return allTypeCourses;
	}
	
	//取听力课程的总页数——通过语言类别筛选

	@Override
	public int getListeningCoursesTotalPagesByLanguageType(Integer languageTypeId) {
		int listeningCoursesNumByLanguageType=courseMapper.getListeningCoursesNumByLanguageType(languageTypeId);
		int totalPages = (int)(listeningCoursesNumByLanguageType)/10+1;
		return totalPages;
	}
	
	//通过种类类别，取听力类型课程的内容

	@Override
	public Map<String, List> getListeningCourses_manageByRateType(Integer pageNum, Integer rateTypeId) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<ListeningCourse> listeningCoursesByRateType = courseMapper.getListeningCoursesByRateType(startIndex,10,rateTypeId);
		allTypeCourses.replace("listeningCourses", listeningCoursesByRateType);
		return allTypeCourses;
	}
	
	//取听力课程的总页数——通过种类类别筛选

	@Override
	public int getListeningCoursesTotalPagesByRateType(Integer rateTypeId) {
		int listeningCoursesNumByRateType=courseMapper.getListeningCoursesNumByRateType(rateTypeId);
		int totalPages = (int)(listeningCoursesNumByRateType)/10+1;
		return totalPages;
	}
	
	//获取筛选指定语言类别的听力课程列表JSON——用户

	@Override
	public BackJsonUtil getListeningCourses_manageByLanguageTypeByUser(Integer pageNum, Integer languageTypeId) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getListeningCoursesTotalPagesByLanguageType(languageTypeId);
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getListeningCoursesByLanguageType(pageNum,languageTypeId));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}
	
	//获取筛选指定种类类别(种类类别已经确定了语言类别)的听力课程列表JSON——用户

	@Override
	public BackJsonUtil getListeningCourses_manageByRateTypeByUser(Integer pageNum, Integer rateTypeId) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getListeningCoursesTotalPagesByRateType(rateTypeId);
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getListeningCourses_manageByRateType(pageNum,rateTypeId));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}

	
	//取口语类型课程的内容
	@Override
	public Map<String, List> getOralCourses(Integer pageNum) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<OralCourse> oralCourses = courseMapper.getOralCourses(startIndex,10);
		allTypeCourses.replace("oralCourses", oralCourses);
		return allTypeCourses;
	}
	
	//取口语课程的总页数

	@Override
	public int getOralCoursesTotalPages() {
		int oralCoursesNum=courseMapper.getOralCoursesNum();
		return (int)(oralCoursesNum)/10+1;
	}
	
	//取阅读类型课程的内容

	@Override
	public Map<String, List> getReadingCourses(Integer pageNum) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<ReadingCourse> readingCourses = courseMapper.getReadingCourses(startIndex,10);
		allTypeCourses.replace("readingCourses", readingCourses);
		return allTypeCourses;
	}
	
	//取阅读课程的总页数

	@Override
	public int getReadingCoursesTotalPages() {
		int readingCoursesNum=courseMapper.getReadingCoursesNum();
		return (int)(readingCoursesNum)/10+1;
	}
	
	//获得口语课程列表JSON——用户

	@Override
	public BackJsonUtil getOralCourses_manageByUser(Integer pageNum) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getOralCoursesTotalPages();
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getOralCourses(pageNum));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}
	
	//获得阅读课程列表JSON——用户

	@Override
	public BackJsonUtil getReadingCourses_manageByUser(Integer pageNum) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getReadingCoursesTotalPages();
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getReadingCourses(pageNum));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}
	
	//通过下载量排序，取口语类型课程的内容

	@Override
	public Map<String, List> getOralCoursesByDownload(Integer pageNum) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<OralCourse> oralCourses = courseMapper.getOralCoursesByDownload(startIndex,10);
		allTypeCourses.replace("oralCourses", oralCourses);
		return allTypeCourses;
	}
	
	//通过下载量排序，取阅读类型课程的内容

	@Override
	public Map<String, List> getReadingCoursesByDownload(Integer pageNum) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<ReadingCourse> readingCourses = courseMapper.getReadingCoursesByDownload(startIndex,10);
		allTypeCourses.replace("readingCourses", readingCourses);
		return allTypeCourses;
	}
	
	//获取按下载量排序JSON：口语课程列表——用户

	@Override
	public BackJsonUtil getOralCourses_manageByDownloadByUser(Integer pageNum) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getOralCoursesTotalPages();
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getOralCoursesByDownload(pageNum));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}
	
	//获取按下载量排序JSON：阅读课程列表——用户

	@Override
	public BackJsonUtil getReadingCourses_manageByDownloadByUser(Integer pageNum) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getReadingCoursesTotalPages();
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getReadingCoursesByDownload(pageNum));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}
	
	//通过语言类别，取口语类型课程的内容

	@Override
	public Map<String, List> getOralCoursesByLanguageType(Integer pageNum, Integer languageTypeId) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<OralCourse> oralCoursesByLanguageType = courseMapper.getOralCoursesByLanguageType(startIndex,10,languageTypeId);
		allTypeCourses.replace("oralCourses", oralCoursesByLanguageType);
		return allTypeCourses;
	}
	
	//取口语课程的总页数——通过语言类别筛选

	@Override
	public int getOralCoursesTotalPagesByLanguageType(Integer languageTypeId) {
		int oralCoursesNumByLanguageType=courseMapper.getOralCoursesNumByLanguageType(languageTypeId);
		int totalPages = (int)(oralCoursesNumByLanguageType)/10+1;
		return totalPages;
	}
	
	//通过语言类别，取阅读类型课程的内容

	@Override
	public Map<String, List> getReadingCoursesByLanguageType(Integer pageNum, Integer languageTypeId) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<ReadingCourse> readingCoursesByLanguageType = courseMapper.getReadingCoursesByLanguageType(startIndex,10,languageTypeId);
		allTypeCourses.replace("readingCourses", readingCoursesByLanguageType);
		return allTypeCourses;
	}
	
	//取阅读课程的总页数——通过语言类别筛选

	@Override
	public int getReadingCoursesTotalPagesByLanguageType(Integer languageTypeId) {
		int readingCoursesNumByLanguageType=courseMapper.getReadingCoursesNumByLanguageType(languageTypeId);
		int totalPages = (int)(readingCoursesNumByLanguageType)/10+1;
		return totalPages;
	}
	
	//获取筛选指定语言类别的口语课程列表JSON——用户

	@Override
	public BackJsonUtil getOralCourses_manageByLanguageTypeByUser(Integer pageNum, Integer languageTypeId) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getOralCoursesTotalPagesByLanguageType(languageTypeId);
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getOralCoursesByLanguageType(pageNum,languageTypeId));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}
	
	//获取筛选指定语言类别的阅读课程列表JSON——用户

	@Override
	public BackJsonUtil getReadingCourses_manageByLanguageTypeByUser(Integer pageNum, Integer languageTypeId) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getReadingCoursesTotalPagesByLanguageType(languageTypeId);
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getReadingCoursesByLanguageType(pageNum,languageTypeId));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}
	
	//通过种类类别，取口语类型课程的内容

	@Override
	public Map<String, List> getOralCourses_manageByRateType(Integer pageNum, Integer rateTypeId) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<OralCourse> oralCoursesByRateType = courseMapper.getOralCoursesByRateType(startIndex,10,rateTypeId);
		allTypeCourses.replace("oralCourses", oralCoursesByRateType);
		return allTypeCourses;
	}
	
	//取口语课程的总页数——通过种类类别筛选

	@Override
	public int getOralCoursesTotalPagesByRateType(Integer rateTypeId) {
		int oralCoursesNumByRateType=courseMapper.getOralCoursesNumByRateType(rateTypeId);
		int totalPages = (int)(oralCoursesNumByRateType)/10+1;
		return totalPages;
	}
	
	//通过种类类别，取阅读类型课程的内容

	@Override
	public Map<String, List> getReadingCourses_manageByRateType(Integer pageNum, Integer rateTypeId) {
		int startIndex = CalculateStartIndex.getStartIndex(pageNum, 10);
		Map<String,List> allTypeCourses = new HashMap<String,List>();
		allTypeCourses.put("listeningCourses", new ArrayList<ListeningCourse>());
		allTypeCourses.put("oralCourses", new ArrayList<OralCourse>());
		allTypeCourses.put("readingCourses", new ArrayList<ReadingCourse>());
		List<ReadingCourse> readingCoursesByRateType = courseMapper.getReadingCoursesByRateType(startIndex,10,rateTypeId);
		allTypeCourses.replace("readingCourses", readingCoursesByRateType);
		return allTypeCourses;
	}
	
	//取阅读课程的总页数——通过种类类别筛选

	@Override
	public int getReadingCoursesTotalPagesByRateType(Integer rateTypeId) {
		int readingCoursesNumByRateType=courseMapper.getReadingCoursesNumByRateType(rateTypeId);
		int totalPages = (int)(readingCoursesNumByRateType)/10+1;
		return totalPages;
	}
	
	//获取筛选指定种类类别(种类类别已经确定了语言类别)的口语课程列表JSON——用户

	@Override
	public BackJsonUtil getOralCourses_manageByRateTypeByUser(Integer pageNum, Integer rateTypeId) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getOralCoursesTotalPagesByRateType(rateTypeId);
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getOralCourses_manageByRateType(pageNum,rateTypeId));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}
	
	//获取筛选指定种类类别(种类类别已经确定了语言类别)的阅读课程列表JSON——用户

	@Override
	public BackJsonUtil getReadingCourses_manageByRateTypeByUser(Integer pageNum, Integer rateTypeId) {
		BackJsonUtil backJsonUtil = new BackJsonUtil();
		int totalPages=getReadingCoursesTotalPagesByRateType(rateTypeId);
		if(pageNum>totalPages) {
			backJsonUtil.setStatus(false);
			backJsonUtil.setInfo("您的访问页数有误，请重新选择!");
		}else {
			backJsonUtil.setStatus(true);
			Map<String,Object>result = new HashMap<String,Object>();
			result.put("totalPages", totalPages);
			result.put("currentPage", pageNum);
			result.put("courses", getReadingCourses_manageByRateType(pageNum,rateTypeId));
			backJsonUtil.setInfo(result);
		}
		return backJsonUtil;
	}

}
