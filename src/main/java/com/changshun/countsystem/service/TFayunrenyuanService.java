package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TFayunrenyuan;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TFayunrenyuanService extends CoreService<TFayunrenyuan> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TFayunrenyuan tFayunrenyuan);
    //从缓存中获取信息
    TFayunrenyuan getInfo(String workerid);
}
