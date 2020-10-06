package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TCangku;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TCangkuService extends CoreService<TCangku> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TCangku tCangku);
    //从缓存中获取信息
    TCangku getInfo(String workerid);
}
