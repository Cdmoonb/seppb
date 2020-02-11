package com.pr.sepp.sep.build.controller;

import com.pr.sepp.common.constants.CommonParameter;
import com.pr.sepp.common.threadlocal.ParameterThreadLocal;
import com.pr.sepp.sep.build.model.BuildInstance;
import com.pr.sepp.sep.build.model.GitProperties;
import com.pr.sepp.sep.build.model.constants.InstanceType;
import com.pr.sepp.sep.build.model.req.InstanceEnvReq;
import com.pr.sepp.sep.build.model.req.InstanceReq;
import com.pr.sepp.sep.build.model.resp.InstanceEnvResp;
import com.pr.sepp.sep.build.model.sonar.SonarReqInstance;
import com.pr.sepp.sep.build.service.BuildInstanceService;
import com.pr.sepp.sep.build.service.SonarScanService;
import org.gitlab.api.models.GitlabBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class BuildInstanceController {

	@Autowired
	private BuildInstanceService buildInstanceService;

	@Autowired
	private SonarScanService sonarScanService;

	@PostMapping(value = "/instances/create")
	public void createInstance(@RequestBody InstanceReq instanceReq) {
		buildInstanceService.createInstance(instanceReq.copy());
	}

	@GetMapping(value = "/instances")
	public List<BuildInstance> listBuildInstances() {

		return buildInstanceService.listBuildInstances(ParameterThreadLocal.getProductId());
	}

	@GetMapping(value = "/gitInfo")
	public List<GitProperties.GitConfig> listGitInfo() throws IOException {
		return buildInstanceService.listGitInfo();
	}

	@GetMapping(value = "/listInstance")
	public List<SonarReqInstance> listInstance(@RequestParam("productId") Integer productId) {
		return sonarScanService.listInstance(productId);
	}

	@PostMapping(value = "/listBranch")
	public List<GitlabBranch> listBranch(@RequestBody BuildInstance buildInstance) throws IOException {
		return sonarScanService.listBranch(buildInstance);
	}

	@GetMapping(value = "/instances/types")
	public List<InstanceType.Tag> listInstanceTypes() {
		return InstanceType.getTags();
	}

	@GetMapping(value = "/instances/validator/{instance}")
	public boolean checkInstanceRepeat(@PathVariable("instance") String instance) {
		return buildInstanceService.isInstanceRepeat(instance);
	}

	@DeleteMapping(value = "/instances/{instance}")
	public void deleteInstance(@PathVariable("instance") String instance) {
		buildInstanceService.deleteInstance(instance);
	}

	@PostMapping(value = "/instances/update")
	public void updateInstance(@RequestBody InstanceReq instanceReq) {
		buildInstanceService.updateInstance(instanceReq.copy());
	}

	@PostMapping(value = "/instances/envs")
	public void addInstanceEnv(@RequestBody @Valid InstanceEnvReq instanceEnvReq) {
		instanceEnvReq.setProductId(ParameterThreadLocal.getProductId());
		buildInstanceService.saveOrUpdateInstanceEnv(instanceEnvReq);
	}

	@GetMapping(value = "/instances/envs/{instance}")
	public List<InstanceEnvResp> instanceEnvsByInstance(@PathVariable("instance") String instance) {
		return buildInstanceService.listInstanceEnvs(instance, ParameterThreadLocal.getProductId());
	}

	@GetMapping(value = "/instances/branch/validator")
	public boolean checkBranchRepeat(@RequestParam(CommonParameter.BRANCH_ID) Integer branchId,
									 @RequestParam("envType") Integer envType,
									 @RequestParam("instance") String instance) {
		return buildInstanceService.checkBranchRepeat(branchId, envType, ParameterThreadLocal.getProductId(), instance);
	}

	@DeleteMapping(value = "/instances/envs/{id}")
	public void checkBranchRepeat(@PathVariable(CommonParameter.ID) Integer id) {
		buildInstanceService.deleteEnv(id);
	}
}
