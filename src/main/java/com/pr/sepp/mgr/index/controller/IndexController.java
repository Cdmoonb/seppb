package com.pr.sepp.mgr.index.controller;

import com.pr.sepp.common.constants.CommonParameter;
import com.pr.sepp.mgr.index.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class IndexController {
	@Autowired
	private IndexService indexService;

	@RequestMapping(value = "/index/rel_req_query", method =  RequestMethod.POST)
	public List<Map<String, Object>> relRequestQuery(HttpServletRequest request) {
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put(CommonParameter.REL_ID, request.getParameter(CommonParameter.REL_ID));

		return indexService.relRequestQuery(dataMap);
	}

	@RequestMapping(value = "/index/rel_cms_query", method =  RequestMethod.POST)
	public List<Map<String, Object>> relMissionQuery(HttpServletRequest request) {
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put(CommonParameter.REL_ID, request.getParameter(CommonParameter.REL_ID));

		return indexService.relMissionQuery(dataMap);
	}

	@RequestMapping(value = "/index/rel_bug_query", method =  RequestMethod.POST)
	public List<Map<String, Object>> relDefectsQuery(HttpServletRequest request) {
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put(CommonParameter.REL_ID, request.getParameter(CommonParameter.REL_ID));

		return indexService.relDefectsQuery(dataMap);
	}

	@RequestMapping(value = "/index/rel_test_query", method =  RequestMethod.POST)
	public Map<String, Integer> relTestQuery(HttpServletRequest request) {
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put(CommonParameter.REL_ID, request.getParameter(CommonParameter.REL_ID));

		return indexService.relTestQuery(dataMap);
	}

}
