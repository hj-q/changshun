package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TDianlanjiangli;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TDianlanjiangliService extends CoreService<TDianlanjiangli> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TDianlanjiangli tDianlanjiangli);
    //从缓存中获取信息
    TDianlanjiangli getInfo(String workerid);
}
