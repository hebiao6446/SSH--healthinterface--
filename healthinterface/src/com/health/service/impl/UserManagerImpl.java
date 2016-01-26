package com.health.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.health.common.Commonparam;
import com.health.dao.UserDao;
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




import cn.eavic.framework.orm.Page;
import cn.eavic.framework.orm.PropertyFilter;
import cn.eavic.framework.service.impl.GenericManagerImpl;



@Service
@Transactional
public class UserManagerImpl implements UserManager {
	@Autowired
	UserDao userDao;
	
	public UserManagerImpl() {
	}

	

	@Override
	public List<Bootpage> loadBootpageInfo() {
		// TODO Auto-generated method stub
		return userDao.loadBootpageInfo();
	}



	@Override
	public List<Aboutus> loadAboutusInfo() {
		// TODO Auto-generated method stub
		return userDao.loadAboutusInfo();
	}



	@Override
	public List<Supports> loadSupportsInfo() {
		// TODO Auto-generated method stub
		return userDao.loadSupportsInfo();
	}



	@Override
	public List<Version> loadVersionInfo() {
		// TODO Auto-generated method stub
		return userDao.loadVersionInfo();
	}



	@Override
	public boolean addMessage(Message com) {
		// TODO Auto-generated method stub
		Message msg =  userDao.findMessage(com.getPhoneId());
		msg.setAddress(com.getAddress());
		msg.setName(com.getName());
		msg.setContent(com.getContent());
		msg.setEmail(com.getEmail());
		msg.setPhone(com.getPhone());
		msg.setEndTime(Commonparam.Date2Str(new Date()));
		msg.setCorrect(com.getCorrect());
		msg.setTotal(com.getTotal());
		msg.setTimeSpace(Commonparam.timeSpace(msg.getCreatTime(),msg.getEndTime()));
		userDao.saveMessage(msg);
		
		
		return true;
	}



	@Override
	public List<Knowledge> loadKnowledgeInfo(int rows, int page) {
		// TODO Auto-generated method stub
		return userDao.loadKnowledgeInfo(rows,page);
	}



	@Override
	public Object loadKnowledgeInfoCount() {
		// TODO Auto-generated method stub
		return userDao.loadKnowledgeInfoCount();
	}



	@Override
	public List<Firstpage> loadFirstpageInfo(int rows, int page, List<Firstpage> topNews) {
		// TODO Auto-generated method stub
		return userDao.loadFirstpageInfo(rows,page,topNews);
	}



	@Override
	public Object loadFirstpageInfoCount(List<Firstpage> topNews) {
		// TODO Auto-generated method stub
		return userDao.loadFirstpageInfoCount(topNews);
	}



	@Override
	public List<Firstpage> loadTopFirstpage() {
		// TODO Auto-generated method stub
		List<Firstpage> lsit =  userDao.loadTopFirstpage();
		if(lsit.size()==0){
			lsit =  userDao.loadTopFirstpageByTime();
		}
		return lsit;
	}



	@Override
	public List<Onlinetest> loadOnlinetestInfo(String phoneId) {
		// TODO Auto-generated method stub
		Message msg =  userDao.findMessage(phoneId);
		if(msg==null){
			msg = new Message();
			msg.setPhoneId(phoneId);
			msg.setCreatTime(Commonparam.Date2Str(new Date()));
		}
		else{
			msg.setCreatTime(Commonparam.Date2Str(new Date()));
		}
		userDao.saveMessage(msg);
		return userDao.loadOnlinetestInfo();
	}



	@Override
	public List<Object[]> findFirstpageKeyword(String keyword, int rows,
			int page) {
		// TODO Auto-generated method stub
		return userDao.findFirstpageKeyword(keyword,rows,page);
	}



	@Override
	public Object findFirstpageKeywordCount(String keyword) {
		// TODO Auto-generated method stub
		return userDao.findFirstpageKeywordCount(keyword);
	}



	@Override
	public List<Onlinefirst> loadOnlinefirstInfo() {
		// TODO Auto-generated method stub
		return userDao.loadOnlinefirstInfo();
	}



	@Override
	public List<Recommend> loadRecommendInfo(int rows, int page) {
		// TODO Auto-generated method stub
		return userDao.loadRecommendInfo(rows,page);
	}



	@Override
	public Object loadRecommendInfoCount() {
		// TODO Auto-generated method stub
		return userDao.loadRecommendInfoCount();
	}






	
}
