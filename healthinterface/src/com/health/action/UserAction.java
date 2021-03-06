package com.health.action;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.xml.ws.Action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.catalina.startup.UserConfig;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.eavic.framework.web.BaseAction;

import com.health.common.Commonparam;
import com.health.common.MatrixToImageWriter;
import com.health.common.PingYin;
import com.health.common.PushThread;
import com.health.entity.Aboutus;
import com.health.entity.Bootpage;
import com.health.entity.Firstpage;
import com.health.entity.Knowledge;
import com.health.entity.Message;
import com.health.entity.Onlinefirst;
import com.health.entity.Onlinetest;
import com.health.entity.Recommend;
import com.health.entity.Supports;
import com.health.entity.Version;
import com.health.service.UserManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.json.BaseBean;
import com.json.JsonAuthority;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@Results({ @Result(name = BaseAction.NONE, location = "userAction.action", type = "redirect") })
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserManager userManager;
	private String rows, page;
	private String ids;
	private Long userId;
	private Long id;
	private String table;
	private String title;
	private String content;
	private String creatTime;
	private String type;
	private String name;

	private String phone;
	private String address;
	private String keyword;
	private String email;// 反馈邮箱

	/**
	 * 引导页图片
	 */

	public void loadBootpageInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Bootpage> list = userManager.loadBootpageInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 关于我们
	 * 
	 */

	public void loadAboutusInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Aboutus> list = userManager.loadAboutusInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 实用推荐
	 * 
	 */

	public void loadRecommendInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Recommend> list = userManager.loadRecommendInfo(
					Integer.valueOf(rows), Integer.valueOf(page));
			bean.setStatus(200);
			bean.setRows(list);
			bean.setTotal(userManager.loadRecommendInfoCount());
		} catch (Exception e) {
			bean.setStatus(400);
			bean.setMsg(e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	public void createTextAction() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		bean.setStatus(200);
		bean.setRows("ok");
		bean.setMsg("hebiao");
		
		  Process process;  
	        process = Runtime.getRuntime().exec(new String[]{"ls","-al"},null,null);  
	        InputStreamReader ir = new InputStreamReader(process  
	                .getInputStream());  
	        LineNumberReader input = new LineNumberReader(ir);  
	        String line;  
	        process.waitFor();  
	        while ((line = input.readLine()) != null){  
	            System.out.println("--------------------"+line); 
	        }  
	          
		
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));

	}

	/**
	 * 技术支持
	 * 
	 */

	public void loadSupportsInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Supports> list = userManager.loadSupportsInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 版本更新
	 * 
	 */

	public void loadVersionInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Version> list = userManager.loadVersionInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 接口——知识
	 * 
	 */

	public void loadKnowledgeInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Knowledge> list = userManager.loadKnowledgeInfo(
					Integer.valueOf(rows), Integer.valueOf(page));
			bean.setStatus(200);
			bean.setRows(list);
			bean.setTotal(userManager.loadKnowledgeInfoCount());
		} catch (Exception e) {
			bean.setStatus(400);
			bean.setMsg(e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 接口——首页
	 * 
	 */

	public void loadFirstpageInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Firstpage> topNews = userManager.loadTopFirstpage();

			List<Firstpage> list = userManager.loadFirstpageInfo(
					Integer.valueOf(rows), Integer.valueOf(page), topNews);
			bean.setStatus(200);
			bean.setRows(list);
			if (page != null && page.equals("1")) {
				bean.setTopData(topNews);
			}

			bean.setTotal(userManager.loadFirstpageInfoCount(topNews));
		} catch (Exception e) {
			bean.setStatus(400);
			bean.setMsg(e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 在线测试
	 * 
	 */
	private String phoneId;

	public void loadOnlinetestInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			if (phoneId == null || phoneId.trim().length() == 0) {
				bean.setMsg("参数错误!");
			} else {
				List<Onlinetest> list = userManager.loadOnlinetestInfo(phoneId);
				bean.setStatus(200);
				bean.setRows(list);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 添加反馈意见
	 * 
	 * @param com
	 * @return
	 */
	private Integer correct, total;

	public void addMessage() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean base = new BaseBean();
		try {
			if (phoneId == null || phone == null || name == null
					|| correct == null || total == null) {
				base.setMsg("参数错误!");
			} else {
				Message com = new Message();
				com.setPhoneId(phoneId);
				com.setName(name);
				com.setPhone(phone);
				com.setAddress(address);
				com.setContent(content);
				com.setEmail(email);
				com.setEndTime(Commonparam.Date2Str(new Date()));
				com.setCorrect(correct);
				com.setTotal(total);

				boolean rt = userManager.addMessage(com);
				if (rt) {
					base.setMsg("提交成功");
					base.setStatus(200);
				} else {
					base.setMsg("提交失败");
					base.setStatus(400);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = JSONObject.fromObject(base).toString();
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

	/**
	 * 在线测试 首页
	 * 
	 */

	public void loadOnlinefirstInfo() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			List<Onlinefirst> list = userManager.loadOnlinefirstInfo();
			bean.setStatus(200);
			bean.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 
	 * * 首页 关键字模糊搜索
	 */

	public void findFirstpageKeyword() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();
		try {
			if (keyword == null || rows == null || page == null) {
				bean.setMsg(Commonparam.error_param);
			} else {
				// id,name,img_list,introduction
				List<Object[]> list = userManager.findFirstpageKeyword(keyword,
						Integer.valueOf(rows), Integer.valueOf(page));
				List<Firstpage> rt = new ArrayList<Firstpage>();
				for (Object[] obj : list) {
					Firstpage firstp = new Firstpage();
					firstp.setId(Long.valueOf(obj[0].toString()));
					firstp.setTitle(obj[1].toString());
					firstp.setIntroduction(obj[2].toString());
					firstp.setCreatTime(obj[3].toString());
					firstp.setImgDetail(obj[4].toString());
					firstp.setSource(obj[5].toString());
					firstp.setContent(obj[6].toString());
					firstp.setImgInfo(obj[7].toString());
					firstp.setImgVideo(obj[8] == null ? "" : obj[8].toString());
					firstp.setVideoUrl(obj[9] == null ? "" : obj[9].toString());
					firstp.setImgTop(obj[10] == null ? "" : obj[10].toString());
					rt.add(firstp);
				}
				bean.setStatus(200);
				bean.setRows(rt);
				bean.setTotal(userManager.findFirstpageKeywordCount(keyword));
			}

		} catch (Exception e) {
			// TODO: handle exception
			bean.setMsg(e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCorrect() {
		return correct;
	}

	public void setCorrect(Integer correct) {
		this.correct = correct;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneId() {
		return phoneId;
	}

}
