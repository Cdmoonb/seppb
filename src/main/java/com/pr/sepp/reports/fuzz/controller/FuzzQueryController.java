package com.pr.sepp.reports.fuzz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pr.sepp.common.constants.CommonParameter;
import com.pr.sepp.common.threadlocal.ParameterThreadLocal;
import com.pr.sepp.reports.fuzz.service.FuzzQueryService;
import com.pr.sepp.sep.coding.model.CodeMission;
import com.pr.sepp.sep.defect.model.Defect;
import com.pr.sepp.sep.problem.model.Problem;
import com.pr.sepp.sep.release.model.Release;
import com.pr.sepp.sep.requirement.model.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class FuzzQueryController {

	@Autowired
	public FuzzQueryService fuzzQueryService;

	@RequestMapping(value = "/fuzz/release_query", method =  RequestMethod.POST)
	public PageInfo<Release> releaseQuery(HttpServletRequest request, @RequestParam(value = "searchText") String searchText) {
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put(CommonParameter.PRODUCT_ID, request.getParameter(CommonParameter.PRODUCT_ID));
		dataMap.put("searchText", searchText);

		int pageNum = ParameterThreadLocal.getPageNum();
		int pageSize = ParameterThreadLocal.getPageSize();
		PageHelper.startPage(pageNum, pageSize);

		List<Release> list = fuzzQueryService.releaseQuery(dataMap);
		PageInfo<Release> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@RequestMapping(value = "/fuzz/req_query", method =  RequestMethod.POST)
	public PageInfo<Requirement> reqQuery(HttpServletRequest request, @RequestParam(value = "searchText") String searchText) {
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put(CommonParameter.PRODUCT_ID, request.getParameter(CommonParameter.PRODUCT_ID));
		dataMap.put("searchText", searchText);

		int pageNum = ParameterThreadLocal.getPageNum();
		int pageSize = ParameterThreadLocal.getPageSize();
		PageHelper.startPage(pageNum, pageSize);

		List<Requirement> list = fuzzQueryService.reqQuery(dataMap);
		PageInfo<Requirement> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@RequestMapping(value = "/fuzz/cms_query", method =  RequestMethod.POST)
	public PageInfo<CodeMission> cmsQuery(HttpServletRequest request, @RequestParam(value = "searchText") String searchText) {
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put(CommonParameter.PRODUCT_ID, request.getParameter(CommonParameter.PRODUCT_ID));
		dataMap.put("searchText", searchText);

		int pageNum = ParameterThreadLocal.getPageNum();
		int pageSize = ParameterThreadLocal.getPageSize();
		PageHelper.startPage(pageNum, pageSize);

		List<CodeMission> list = fuzzQueryService.cmsQuery(dataMap);
		PageInfo<CodeMission> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@RequestMapping(value = "/fuzz/defect_query", method =  RequestMethod.POST)
	public PageInfo<Defect> defectQuery(HttpServletRequest request, @RequestParam(value = "searchText") String searchText) {
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put(CommonParameter.PRODUCT_ID, request.getParameter(CommonParameter.PRODUCT_ID));
		dataMap.put("searchText", searchText);

		int pageNum = ParameterThreadLocal.getPageNum();
		int pageSize = ParameterThreadLocal.getPageSize();
		PageHelper.startPage(pageNum, pageSize);

		List<Defect> list = fuzzQueryService.defectQuery(dataMap);
		PageInfo<Defect> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@RequestMapping(value = "/fuzz/problem_query", method =  RequestMethod.POST)
	public PageInfo<Problem> problemQuery(HttpServletRequest request, @RequestParam(value = "searchText") String searchText) {
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put(CommonParameter.PRODUCT_ID, request.getParameter(CommonParameter.PRODUCT_ID));
		dataMap.put("searchText", searchText);

		int pageNum = ParameterThreadLocal.getPageNum();
		int pageSize = ParameterThreadLocal.getPageSize();
		PageHelper.startPage(pageNum, pageSize);

		List<Problem> list = fuzzQueryService.problemQuery(dataMap);
		PageInfo<Problem> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}
