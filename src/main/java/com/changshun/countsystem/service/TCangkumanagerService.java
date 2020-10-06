package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TCangkumanager;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TCangkumanagerService extends CoreService<TCangkumanager> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TCangkumanager tCangkumanager);
    //从缓存中获取信息
    TCangkumanager getInfo(String workerid);
}
