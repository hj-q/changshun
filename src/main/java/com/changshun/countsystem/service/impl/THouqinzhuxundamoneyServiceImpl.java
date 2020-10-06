package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.THouqinzhuxundamoneyMapper;
import com.changshun.countsystem.pojo.THouqinzhuxundamoney;
import com.changshun.countsystem.service.THouqinzhuxundamoneyService;
import com.changshun.countsystem.util.THouqinzhuxundamoneyExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class THouqinzhuxundamoneyServiceImpl extends CoreServiceImpl<THouqinzhuxundamoney> implements THouqinzhuxundamoneyService{
	@Autowired
    private THouqinzhuxundamoneyMapper tHouqinzhuxundamoneyMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        THouqinzhuxundamoneyExcelUtil tHouqinzhuxundamoneyExcelUtil = new THouqinzhuxundamoneyExcelUtil();
        List<THouqinzhuxundamoney> ilist = tHouqinzhuxundamoneyExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (THouqinzhuxundamoney tHouqinzhuxundamoney : ilist) {
                tHouqinzhuxundamoneyMapper.insert(tHouqinzhuxundamoney);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
