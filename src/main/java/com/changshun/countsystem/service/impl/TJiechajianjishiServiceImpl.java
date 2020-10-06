package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TJiechajianjishiMapper;
import com.changshun.countsystem.pojo.TJiechajianjishi;
import com.changshun.countsystem.service.TJiechajianjishiService;
import com.changshun.countsystem.util.TJiechajianjishiExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TJiechajianjishiServiceImpl extends CoreServiceImpl<TJiechajianjishi> implements TJiechajianjishiService{
	@Autowired
    private TJiechajianjishiMapper tJiechajianjishiMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TJiechajianjishiExcelUtil tJiechajianjishiExcelUtil = new TJiechajianjishiExcelUtil();
        List<TJiechajianjishi> ilist = tJiechajianjishiExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TJiechajianjishi tJiechajianjishi : ilist) {
                tJiechajianjishiMapper.insert(tJiechajianjishi);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TJiechajianjishi tJiechajianjishi){
	List<TJiechajianjishi> tJiechajianjishis = tJiechajianjishiMapper.getList(tJiechajianjishi);
        for (int i=0;i<tJiechajianjishis.size();i++){
            caffeineCache.put(tJiechajianjishis.get(i).getWorkerid(),tJiechajianjishis.get(i));
        }
    }

    @Override
    public TJiechajianjishi getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TJiechajianjishi tJiechajianjishi = (TJiechajianjishi) caffeineCache.asMap().get(workerid);
        return tJiechajianjishi;
    }

}
