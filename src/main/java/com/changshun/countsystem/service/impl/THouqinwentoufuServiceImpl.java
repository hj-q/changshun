package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.THouqinwentoufuMapper;
import com.changshun.countsystem.pojo.THouqinwentoufu;
import com.changshun.countsystem.service.THouqinwentoufuService;
import com.changshun.countsystem.util.THouqinwentoufuExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class THouqinwentoufuServiceImpl extends CoreServiceImpl<THouqinwentoufu> implements THouqinwentoufuService {
	@Autowired
    private THouqinwentoufuMapper tHouqinwentoufuMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        THouqinwentoufuExcelUtil tHouqinwentoufuExcelUtil = new THouqinwentoufuExcelUtil();
        List<THouqinwentoufu> ilist = tHouqinwentoufuExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (THouqinwentoufu tHouqinwentoufu : ilist) {
                tHouqinwentoufuMapper.insert(tHouqinwentoufu);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(THouqinwentoufu tHouqinwentoufu){
	List<THouqinwentoufu> tHouqinwentoufus = tHouqinwentoufuMapper.getList(tHouqinwentoufu);
        for (int i=0;i<tHouqinwentoufus.size();i++){
            caffeineCache.put(tHouqinwentoufus.get(i).getWorkerid(),tHouqinwentoufus.get(i));
        }
    }

    @Override
    public THouqinwentoufu getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        THouqinwentoufu tHouqinwentoufu = (THouqinwentoufu) caffeineCache.asMap().get(workerid);
        return tHouqinwentoufu;
    }

}
