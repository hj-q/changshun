package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TPingzhijishi;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TPingzhijishiService extends CoreService<TPingzhijishi> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TPingzhijishi tPingzhijishi);
    //从缓存中获取信息
    TPingzhijishi getInfo(String workerid);
}
