package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TGuangzhouxuexi;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TGuangzhouxuexiService extends CoreService<TGuangzhouxuexi> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TGuangzhouxuexi tGuangzhouxuexi);
    //从缓存中获取信息
    TGuangzhouxuexi getInfo(String workerid);
}
