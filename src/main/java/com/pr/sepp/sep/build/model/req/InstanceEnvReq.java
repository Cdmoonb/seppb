package com.pr.sepp.sep.build.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstanceEnvReq {

	private Integer id;
	private String instance;
	private Integer envType;
	private String repository;
	private String projectName;
	private String repoUrl;
	private String namespace;
	private Integer productId;
	private String jobName;
	private Integer branchId;

}
