package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TXingzhen;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TXingzhenService extends CoreService<TXingzhen> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TXingzhen tXingzhen);
    //从缓存中获取信息
    TXingzhen getInfo(String workerid);
}
