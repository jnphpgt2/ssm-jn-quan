package org.jnan.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.jnan.po.City;
import org.jnan.po.Proportion;
import org.jnan.po.User;
import org.jnan.service.CityService;
import org.jnan.util.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CityController {
	@Autowired
	private CityService cityService;
	
	@RequestMapping("/tologin")
	public String tologin(ModelAndView modelAndView,@Validated User user,BindingResult bindingResult,HttpSession session){
		user=cityService.tologin(user);
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		for (ObjectError obj : allErrors) {
			System.out.println(obj.getDefaultMessage());
		}
		modelAndView.addObject("message",allErrors);
		session.setAttribute("user",user);
		return "redirect:getcity.action";
	}
	
	@RequestMapping("/zhu")
	public String zhu(HttpSession session){
		session.invalidate();
		return "redirect:index.jsp";
	}
	@RequestMapping("/getcity")
	public ModelAndView getCity(ModelAndView modelAndView,@RequestParam(defaultValue="huanjing") String name){
		List<City> list = cityService.findCity(name);
		Proportion proportion=cityService.findProportion();
		modelAndView.addObject("list",list);
		modelAndView.addObject("plist",proportion);
		modelAndView.setViewName("city_list");
		return modelAndView;
	}
	@RequestMapping("/quanzhi")
	public ModelAndView quanzhi(ModelAndView mv){
		Proportion proportion=cityService.findProportion();
		mv.addObject("plist",proportion);
		mv.setViewName("city_quan");
		return mv;
	}
	@RequestMapping("/toupdate")
	public void toupdate(ModelAndView mv,Proportion pro,HttpServletResponse response) throws IOException{
		boolean a=cityService.toupdate(pro);
		response.getWriter().print(a);
	}
	@RequestMapping("/gengxin")
	public void gengxin(ModelAndView mv,City ci,HttpServletResponse response) throws IOException{
		boolean a=cityService.gengxin(ci);
		response.getWriter().print(a);
	}
	@RequestMapping("/xiugai")
	public ModelAndView xiugai(ModelAndView mv,City city,HttpServletResponse response) throws IOException{
		City city1=cityService.getDanCity(city);
		mv.addObject("plist",city1);
		mv.setViewName("city_update");
		return mv;
	}
	
	//讲师的上传
	/*@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request,
			@RequestParam(value = "myFile", required = false) MultipartFile[] files) {
		List<String> filenames = new ArrayList<String>();
		try {
			for (int i = 0; i < files.length; i++) {
				if(files[i]==null || files[i].getSize()==0){continue;}//防止空文件时报错
				FileUploadUtils.upload(request, files[i]);
				filenames.add(files[i].getOriginalFilename());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("filenames", filenames);
		return "success";
	}*/
	
	@RequestMapping("/unload")
	public String unload(MultipartFile[] user_file,HttpServletResponse rep) throws Exception{
		if(user_file!=null){
			for (MultipartFile file : user_file) {
				String name = file.getOriginalFilename();
				if(name!=""){
					String newName=UUID.randomUUID()+name.substring(name.lastIndexOf("."), name.length());
					File newFile = new File("e:\\unload\\"+newName);
					file.transferTo(newFile);
				}
			}
		}
		
		return "redirect:getcity.action";
	}
	//讲师的下载
	/*@RequestMapping(value = "download", method = RequestMethod.GET)
	public static void download(HttpServletRequest request, HttpServletResponse response, String fileName)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		// String newFileName = new String(fileName.getBytes("ISO8859-1"),
		// "UTF-8");
		String newFileName = fileName;
		// String ctxPath =
		// request.getSession().getServletContext().getRealPath("/") +
		// FileUploadUtils.defaultBaseDir;
		String ctxPath = FileUploadUtils.absoluteBaseDir + FileUploadUtils.defaultBaseDir;
		String downLoadPath = ctxPath + "/" + newFileName;
		long fileLength = new File(downLoadPath).length();
		//下面两个设置，必须
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(newFileName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));
		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}*/
	@RequestMapping("/xiazai")
	public void xiazai(HttpServletResponse response) throws Exception{
		String path="E:\\风景\\美景1.jpg";
		response.setHeader("Content-Disposition", "attachment;fileName="+new String("美女".getBytes("utf-8"),"iso-8859-1"));
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path)); 
		OutputStream out = response.getOutputStream();    
	    byte[] buf = new byte[1024];    
	        BufferedInputStream br = bis;    
	        int len = 0;    
	        while ((len = br.read(buf)) > 0){    
	            out.write(buf, 0, len);    
	        }                   
	        br.close();    
	    out.flush();    
	    out.close();
	}
}
