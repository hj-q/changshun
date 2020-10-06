package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TJiechajianchuqinMapper;
import com.changshun.countsystem.pojo.TJiechajianchuqin;
import com.changshun.countsystem.service.TJiechajianchuqinService;
import com.changshun.countsystem.util.TJiechajianchuqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TJiechajianchuqinServiceImpl extends CoreServiceImpl<TJiechajianchuqin> implements TJiechajianchuqinService{
	@Autowired
    private TJiechajianchuqinMapper tJiechajianchuqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TJiechajianchuqinExcelUtil tJiechajianchuqinExcelUtil = new TJiechajianchuqinExcelUtil();
        List<TJiechajianchuqin> ilist = tJiechajianchuqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TJiechajianchuqin tJiechajianchuqin : ilist) {
                tJiechajianchuqinMapper.insert(tJiechajianchuqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TJiechajianchuqin tJiechajianchuqin){
	List<TJiechajianchuqin> tJiechajianchuqins = tJiechajianchuqinMapper.getList(tJiechajianchuqin);
        for (int i=0;i<tJiechajianchuqins.size();i++){
            caffeineCache.put(tJiechajianchuqins.get(i).getWorkerid(),tJiechajianchuqins.get(i));
        }
    }

    @Override
    public TJiechajianchuqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TJiechajianchuqin tJiechajianchuqin = (TJiechajianchuqin) caffeineCache.asMap().get(workerid);
        return tJiechajianchuqin;
    }

}
