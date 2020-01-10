package com.pr.sepp.work.gantt.service.impl;

import com.pr.sepp.work.gantt.dao.GanttDAO;
import com.pr.sepp.work.gantt.service.GanttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service("ganttService")
public class GanttServiceImpl implements GanttService {

	@Autowired
	private GanttDAO ganttDAO;

	@Override
	public List<Map<String, Object>> ganttMissionQuery(Map<String, Object> dataMap) {
		return ganttDAO.ganttMissionQuery(dataMap);
	}
}
