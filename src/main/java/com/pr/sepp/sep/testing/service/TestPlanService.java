package com.pr.sepp.sep.testing.service;

import com.pr.sepp.sep.testing.model.TestPlan;

import java.util.List;
import java.util.Map;

public interface TestPlanService {

	List<TestPlan> testPlanQuery(Map<String, Object> dataMap);

	int testPlanCreate(TestPlan testPlan);

	int testPlanUpdate(TestPlan testPlan) throws IllegalAccessException;

	int planTmsClose(Integer planId);

	int testPlanDelete(Integer id);

	TestPlan latestPlanQuery(Integer planType);
}
