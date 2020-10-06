package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TDianlanjianyan;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TDianlanjianyanService extends CoreService<TDianlanjianyan> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TDianlanjianyan tDianlanjianyan);
    //从缓存中获取信息
    TDianlanjianyan getInfo(String workerid);
}
