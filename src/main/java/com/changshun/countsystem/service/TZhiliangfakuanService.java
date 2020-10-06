package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TZhiliangfakuan;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TZhiliangfakuanService extends CoreService<TZhiliangfakuan> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TZhiliangfakuan tZhiliangfakuan);
    //从缓存中获取信息
    TZhiliangfakuan getInfo(String workerid);
}
