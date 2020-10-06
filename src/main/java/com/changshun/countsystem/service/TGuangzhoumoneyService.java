package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TGuangzhoumoney;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TGuangzhoumoneyService extends CoreService<TGuangzhoumoney> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TGuangzhoumoney tGuangzhoumoney);
    //从缓存中获取信息
    TGuangzhoumoney getInfo(String workerid);
}
