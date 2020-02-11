package com.pr.sepp.sep.coding.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pr.sepp.common.constants.CommonParameter;
import com.pr.sepp.common.threadlocal.ParameterThreadLocal;
import com.pr.sepp.sep.coding.model.CodeMission;
import com.pr.sepp.sep.coding.service.CodeMissionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class CodeMissionController {

	@Autowired
	private CodeMissionService codeMissionService;

	@RequestMapping(value = "/cms/query", method = RequestMethod.POST)
	public PageInfo<CodeMission> cmsQuery(HttpServletRequest request) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put(CommonParameter.PRODUCT_ID, ParameterThreadLocal.getProductId());
		dataMap.put(CommonParameter.REQ_ID, request.getParameter(CommonParameter.REQ_ID));
		dataMap.put(CommonParameter.REL_ID, request.getParameter(CommonParameter.REL_ID));
		dataMap.put(CommonParameter.ID, request.getParameter(CommonParameter.ID));
		dataMap.put(CommonParameter.SPLITER, request.getParameter(CommonParameter.SPLITER));
		dataMap.put(CommonParameter.MODULE_ID, request.getParameter(CommonParameter.MODULE_ID));
		dataMap.put(CommonParameter.RESPONSER, request.getParameter(CommonParameter.RESPONSER));
		String status = request.getParameter(CommonParameter.STATUS);
		if (!StringUtils.isEmpty(status)) {
			dataMap.put("sts", Arrays.asList(status.split(",")));
		}
		if (!StringUtils.isEmpty(request.getParameter("splitDateBegin"))) {
			dataMap.put("splitDateBegin", request.getParameter("splitDateBegin") + " 00:00:00");
		}
		if (!StringUtils.isEmpty(request.getParameter("splitDateEnd"))) {
			dataMap.put("splitDateEnd", request.getParameter("splitDateEnd") + " 23:59:59");
		}
		if (!StringUtils.isEmpty(request.getParameter("planBeginBegin"))) {
			dataMap.put("planBeginBegin", request.getParameter("planBeginBegin") + " 00:00:00");
		}
		if (!StringUtils.isEmpty(request.getParameter("planBeginEnd"))) {
			dataMap.put("planBeginEnd", request.getParameter("planBeginEnd") + " 23:59:59");
		}
		if (!StringUtils.isEmpty(request.getParameter("planToBegin"))) {
			dataMap.put("planToBegin", request.getParameter("planToBegin") + " 00:00:00");
		}
		if (!StringUtils.isEmpty(request.getParameter("planToEnd"))) {
			dataMap.put("planToEnd", request.getParameter("planToEnd") + " 23:59:59");
		}

		PageHelper.startPage(ParameterThreadLocal.getPageNum(), ParameterThreadLocal.getPageSize());

		List<CodeMission> list = codeMissionService.cmsQuery(dataMap);

		PageInfo<CodeMission> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@RequestMapping(value = "/cms/create", method = RequestMethod.POST)
	public int cmsCreate(@RequestBody CodeMission codeMission) {
		return codeMissionService.cmsCreate(codeMission);
	}

	@RequestMapping(value = "/cms/update", method = RequestMethod.POST)
	public int cmsUpdate(@RequestBody CodeMission codeMission) throws IllegalAccessException {
		return codeMissionService.cmsUpdate(codeMission);
	}

	@RequestMapping(value = "/cms/status_update/{id}/{status}", method = RequestMethod.POST)
	public int cmsStatusUpdate(@PathVariable(CommonParameter.STATUS) Integer status, @PathVariable(CommonParameter.ID) Integer id) {
		return codeMissionService.cmsStatusUpdate(status, id);
	}

	@RequestMapping(value = "/cms/req_cms_sync/{reqId}/{status}", method = RequestMethod.POST)
	public int reqCmsStatusSync(@PathVariable(CommonParameter.REQ_ID) Integer reqId, @PathVariable(CommonParameter.STATUS) Integer status) {
		return codeMissionService.reqCmsStatusSync(reqId, status);
	}
}
