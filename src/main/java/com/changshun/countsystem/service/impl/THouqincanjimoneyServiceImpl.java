package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.THouqincanjimoneyMapper;
import com.changshun.countsystem.pojo.THouqincanjimoney;
import com.changshun.countsystem.service.THouqincanjimoneyService;
import com.changshun.countsystem.util.THouqincanjimoneyExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class THouqincanjimoneyServiceImpl extends CoreServiceImpl<THouqincanjimoney> implements THouqincanjimoneyService{
	@Autowired
    private THouqincanjimoneyMapper tHouqincanjimoneyMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        THouqincanjimoneyExcelUtil tHouqincanjimoneyExcelUtil = new THouqincanjimoneyExcelUtil();
        List<THouqincanjimoney> ilist = tHouqincanjimoneyExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (THouqincanjimoney tHouqincanjimoney : ilist) {
                tHouqincanjimoneyMapper.insert(tHouqincanjimoney);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
