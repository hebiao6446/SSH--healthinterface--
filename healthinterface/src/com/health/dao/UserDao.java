package com.health.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Exchanger;

import org.apache.catalina.startup.UserConfig;
import org.apache.poi.hssf.record.formula.functions.Trimmean;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import cn.eavic.framework.orm.hibernate.GenericDao;

import com.health.entity.Aboutus;
import com.health.entity.Bootpage;
import com.health.entity.Firstpage;
import com.health.entity.Knowledge;
import com.health.entity.Message;
import com.health.entity.Onlinefirst;
import com.health.entity.Onlinetest;
import com.health.entity.Recommend;
import com.health.entity.Supports;
import com.health.entity.User;
import com.health.entity.Version;
import com.health.common.Commonparam;






@Repository
public class UserDao extends GenericDao<User, Long> {

	
	
	/**
	 * 引导页面图片
	 */

	public List<Bootpage> loadBootpageInfo() {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("from Bootpage");
			return q.list();
		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}
	
	/**
	 * 关于我们
	 */


	public List<Aboutus> loadAboutusInfo() {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("from Aboutus");
			return q.list();
		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}

	
	/**
	 * 关于我们
	 */


	
	public List<Supports> loadSupportsInfo() {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("from Supports");
			return q.list();
		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}

	/**
	 *版本更新
	 */
	
	public List<Version> loadVersionInfo() {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("from Version");
			return q.list();
		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}
	
	
	
	/**
	 *   
	 *   提交反馈
	 */
	
	public boolean addMessage(Message com) {
		// TODO Auto-generated method stub
		try {
			com.setCreatTime(Commonparam.Date2Str(new Date()));
			this.getSession().save(com);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * 接口——知识
	 * 
	 */
	
	public List<Knowledge> loadKnowledgeInfo(int rows, int page) {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("from Knowledge order by id desc");
			q.setFirstResult((page - 1) * rows);
			q.setMaxResults(rows);
			return q.list();
		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * 接口——知识总数
	 * 
	 */

	public Object loadKnowledgeInfoCount() {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("select count(*) from Knowledge");
			return q.list().get(0);

		} catch (Exception ee) {

		}
		return 0;
	}

	
	

	/**
	 * 接口——首页
	 * @param topNews 
	 * 
	 */

	
	
	public List<Firstpage> loadFirstpageInfo(int rows, int page, List<Firstpage> topNews) {
		// TODO Auto-generated method stub
		try {
			List<Long> ids = new ArrayList<Long>();
			for(Firstpage news : topNews){
				ids.add(news.getId());
			}
			Criteria q = this.getSession().createCriteria(Firstpage.class);
			q.addOrder(Order.desc("id"));
			if(ids.size()>0){
				q.add(Restrictions.not(Restrictions.in("id", ids)));
			}
			q.setFirstResult((page-1)*rows);
			q.setMaxResults(rows);
			return q.list();
			
		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}

	

	/**
	 * 接口——首页  总数
	 * 
	 */

	
	public Object loadFirstpageInfoCount(List<Firstpage> topNews) {
		// TODO Auto-generated method stub
		try {
			List<Long> ids = new ArrayList<Long>();
			for(Firstpage news : topNews){
				ids.add(news.getId());
			}
			Criteria q = this.getSession().createCriteria(Firstpage.class);
			q.setProjection(Projections.projectionList().add(Projections.rowCount()));
			if(ids.size()>0){
				q.add(Restrictions.not(Restrictions.in("id", ids)));
			}
			return q.list().get(0);
			
		} catch (Exception ee) {

		}
		return 0;
	}

	
	
	public List<Firstpage> loadTopFirstpage() {
		try {
			Query q = this.getSession().createQuery("from Firstpage t  WHERE t.imgTop=?");
			q.setString(0, "1");
			q.setMaxResults(3);
			return q.list();

		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}

	public List<Firstpage> loadTopFirstpageByTime() {
		try {
			Query q = this.getSession().createQuery("from Firstpage order by id desc");
			q.setMaxResults(3);
			return q.list();

		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}
	
	

	/**
	 * 接口——在线测试
	 * 
	 */

	public List<Onlinetest> loadOnlinetestInfo() {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("from Onlinetest");
			return q.list();
		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}

	
	

	/**
	 *   首页关键字模糊搜索
	 */


	public List<Object[]> findFirstpageKeyword(String keyword, int rows,
			int page) {
		SQLQuery q = this.getSession().createSQLQuery(
		"SELECT id,title,introduction,creat_time,img_detail,source,content,img_info,img_video,video_url,img_top  from tb_firstpage where title like ?");
          q.setString(0, "%" + keyword + "%");
          q.setFirstResult((page - 1) * rows);
          q.setMaxResults(rows);
        return q.list();

	  }
	
	

	/**
	 *  首页关键字模糊搜索
	 */


	public Object findFirstpageKeywordCount(String keyword) {
		// TODO Auto-generated method stub
		Criteria c = this.getSession().createCriteria(Firstpage.class);
		c.setProjection(Projections.projectionList().add(Projections.rowCount()));
		c.add(Restrictions.like("title", "%" + keyword + "%"));
		return c.uniqueResult();
	
	}
	
	

	/**
	 * 接口——在线测试 首页
	 * 
	 */
	public List<Onlinefirst> loadOnlinefirstInfo() {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("from Onlinefirst");
			return q.list();
		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}

	
	
	/**
	 *   实用推荐
	 * 
	 */
	
	public List<Recommend> loadRecommendInfo(int rows, int page) {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("from Recommend order by id desc");
			q.setFirstResult((page - 1) * rows);
			q.setMaxResults(rows);
			return q.list();
		} catch (Exception ee) {

		}
		return Collections.EMPTY_LIST;
	}

	
	
	/**
	 *   实用推荐
	 * 
	 */
	
	public Object loadRecommendInfoCount() {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("select count(*) from Recommend");
			return q.list().get(0);

		} catch (Exception ee) {

		}
		return 0;
	}

	public Message findMessage(String phoneId) {
		// TODO Auto-generated method stub
		try {
			Query q = this.getSession().createQuery("from Message t where t.phoneId=? and t.endTime is null");
			q.setString(0, phoneId);
			if(q.list().size()>0)
			return (Message) q.list().get(0);

		} catch (Exception ee) {

		}
		return null;
	}

	public void saveMessage(Message msg) {
		// TODO Auto-generated method stub
		this.getSession().saveOrUpdate(msg);
	}
	

	
}
