package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TDianlanbanzu;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TDianlanbanzuService extends CoreService<TDianlanbanzu> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TDianlanbanzu tDianlanbanzu);
    //从缓存中获取信息
    TDianlanbanzu getInfo(String workerid);
}
