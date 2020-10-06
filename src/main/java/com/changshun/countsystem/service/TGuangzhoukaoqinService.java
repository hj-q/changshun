package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TGuangzhoukaoqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TGuangzhoukaoqinService extends CoreService<TGuangzhoukaoqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TGuangzhoukaoqin tGuangzhoukaoqin);
    //从缓存中获取信息
    TGuangzhoukaoqin getInfo(String workerid);
}
