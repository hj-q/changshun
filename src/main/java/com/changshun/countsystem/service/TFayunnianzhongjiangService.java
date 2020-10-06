package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TFayunnianzhongjiang;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TFayunnianzhongjiangService extends CoreService<TFayunnianzhongjiang> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TFayunnianzhongjiang tFayunnianzhongjiang);
    //从缓存中获取信息
    TFayunnianzhongjiang getInfo(String workerid);
}
