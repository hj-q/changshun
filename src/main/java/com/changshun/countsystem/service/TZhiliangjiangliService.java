package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TZhiliangjiangli;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TZhiliangjiangliService extends CoreService<TZhiliangjiangli> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TZhiliangjiangli tZhiliangjiangli);
    //从缓存中获取信息
    TZhiliangjiangli getInfo(String workerid);
}
