package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TJiechajiankaixianchuqinMapper;
import com.changshun.countsystem.pojo.TJiechajiankaixianchuqin;
import com.changshun.countsystem.service.TJiechajiankaixianchuqinService;
import com.changshun.countsystem.util.TJiechajiankaixianchuqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TJiechajiankaixianchuqinServiceImpl extends CoreServiceImpl<TJiechajiankaixianchuqin> implements TJiechajiankaixianchuqinService{
	@Autowired
    private TJiechajiankaixianchuqinMapper tJiechajiankaixianchuqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TJiechajiankaixianchuqinExcelUtil tJiechajiankaixianchuqinExcelUtil = new TJiechajiankaixianchuqinExcelUtil();
        List<TJiechajiankaixianchuqin> ilist = tJiechajiankaixianchuqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TJiechajiankaixianchuqin tJiechajiankaixianchuqin : ilist) {
                tJiechajiankaixianchuqinMapper.insert(tJiechajiankaixianchuqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TJiechajiankaixianchuqin tJiechajiankaixianchuqin){
	List<TJiechajiankaixianchuqin> tJiechajiankaixianchuqins = tJiechajiankaixianchuqinMapper.getList(tJiechajiankaixianchuqin);
        for (int i=0;i<tJiechajiankaixianchuqins.size();i++){
            caffeineCache.put(tJiechajiankaixianchuqins.get(i).getWorkerid(),tJiechajiankaixianchuqins.get(i));
        }
    }

    @Override
    public TJiechajiankaixianchuqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TJiechajiankaixianchuqin tJiechajiankaixianchuqin = (TJiechajiankaixianchuqin) caffeineCache.asMap().get(workerid);
        return tJiechajiankaixianchuqin;
    }

}
