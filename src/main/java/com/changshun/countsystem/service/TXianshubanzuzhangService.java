package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TXianshubanzuzhang;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TXianshubanzuzhangService extends CoreService<TXianshubanzuzhang> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TXianshubanzuzhang tXianshubanzuzhang);
    //从缓存中获取信息
    TXianshubanzuzhang getInfo(String workerid);
}
