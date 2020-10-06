package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TCompanysMapper;
import com.changshun.countsystem.pojo.TCompanys;
import com.changshun.countsystem.service.TCompanysService;
import com.changshun.countsystem.util.TCompanysExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TCompanysServiceImpl extends CoreServiceImpl<TCompanys> implements TCompanysService{
	@Autowired
    private TCompanysMapper tCompanysMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TCompanysExcelUtil tCompanysExcelUtil = new TCompanysExcelUtil();
        List<TCompanys> ilist = tCompanysExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TCompanys tCompanys : ilist) {
                tCompanysMapper.insert(tCompanys);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
