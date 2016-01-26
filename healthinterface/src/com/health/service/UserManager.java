package com.health.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
import com.json.JsonAuthority;


import cn.eavic.framework.service.GenericManager;


public interface UserManager {
	



	public List<Bootpage> loadBootpageInfo();

	public List<Aboutus> loadAboutusInfo();

	public List<Supports> loadSupportsInfo();

	public List<Version> loadVersionInfo();

	public boolean addMessage(Message com);

	public List<Knowledge> loadKnowledgeInfo(int rows, int page);

	public Object loadKnowledgeInfoCount();

	public List<Firstpage> loadFirstpageInfo(int rows, int page, List<Firstpage> topNews);

	public Object loadFirstpageInfoCount( List<Firstpage> topNews);

	public List<Firstpage> loadTopFirstpage();

	public List<Onlinetest> loadOnlinetestInfo(String phoneId);

	public List<Object[]> findFirstpageKeyword(String keyword, int rows, int page);

	public Object findFirstpageKeywordCount(String keyword);

	public List<Onlinefirst> loadOnlinefirstInfo();

	public List<Recommend> loadRecommendInfo(int rows, int page);

	public Object loadRecommendInfoCount();

	
	


}
