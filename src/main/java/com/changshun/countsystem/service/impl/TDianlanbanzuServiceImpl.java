package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TDianlanbanzuMapper;
import com.changshun.countsystem.pojo.TDianlanbanzu;
import com.changshun.countsystem.service.TDianlanbanzuService;
import com.changshun.countsystem.util.TDianlanbanzuExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TDianlanbanzuServiceImpl extends CoreServiceImpl<TDianlanbanzu> implements TDianlanbanzuService{
	@Autowired
    private TDianlanbanzuMapper tDianlanbanzuMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TDianlanbanzuExcelUtil tDianlanbanzuExcelUtil = new TDianlanbanzuExcelUtil();
        List<TDianlanbanzu> ilist = tDianlanbanzuExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TDianlanbanzu tDianlanbanzu : ilist) {
                tDianlanbanzuMapper.insert(tDianlanbanzu);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TDianlanbanzu tDianlanbanzu){
	List<TDianlanbanzu> tDianlanbanzus = tDianlanbanzuMapper.getList(tDianlanbanzu);
        for (int i=0;i<tDianlanbanzus.size();i++){
            caffeineCache.put(tDianlanbanzus.get(i).getWorkerid(),tDianlanbanzus.get(i));
        }
    }

    @Override
    public TDianlanbanzu getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TDianlanbanzu tDianlanbanzu = (TDianlanbanzu) caffeineCache.asMap().get(workerid);
        return tDianlanbanzu;
    }

}
