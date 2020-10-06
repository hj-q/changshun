package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.THouqinMapper;
import com.changshun.countsystem.pojo.THouqin;
import com.changshun.countsystem.service.THouqinService;
import com.changshun.countsystem.util.THouqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class THouqinServiceImpl extends CoreServiceImpl<THouqin> implements THouqinService{
	@Autowired
    private THouqinMapper tHouqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        THouqinExcelUtil tHouqinExcelUtil = new THouqinExcelUtil();
        List<THouqin> ilist = tHouqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (THouqin tHouqin : ilist) {
                tHouqinMapper.insert(tHouqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(THouqin tHouqin){
	List<THouqin> tHouqins = tHouqinMapper.getList(tHouqin);
        for (int i=0;i<tHouqins.size();i++){
            caffeineCache.put(tHouqins.get(i).getWorkerid(),tHouqins.get(i));
        }
    }

    @Override
    public THouqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        THouqin tHouqin = (THouqin) caffeineCache.asMap().get(workerid);
        return tHouqin;
    }

}
