package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TJiechajiankaixianjijianMapper;
import com.changshun.countsystem.pojo.TJiechajiankaixianjijian;
import com.changshun.countsystem.service.TJiechajiankaixianjijianService;
import com.changshun.countsystem.util.TJiechajiankaixianjijianExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TJiechajiankaixianjijianServiceImpl extends CoreServiceImpl<TJiechajiankaixianjijian> implements TJiechajiankaixianjijianService {
	@Autowired
    private TJiechajiankaixianjijianMapper tJiechajiankaixianjijianMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TJiechajiankaixianjijianExcelUtil tJiechajiankaixianjijianExcelUtil = new TJiechajiankaixianjijianExcelUtil();
        List<TJiechajiankaixianjijian> ilist = tJiechajiankaixianjijianExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TJiechajiankaixianjijian tJiechajiankaixianjijian : ilist) {
                tJiechajiankaixianjijianMapper.insert(tJiechajiankaixianjijian);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TJiechajiankaixianjijian tJiechajiankaixianjijian){
	List<TJiechajiankaixianjijian> tJiechajiankaixianjijians = tJiechajiankaixianjijianMapper.getList(tJiechajiankaixianjijian);
        for (int i=0;i<tJiechajiankaixianjijians.size();i++){
            caffeineCache.put(tJiechajiankaixianjijians.get(i).getWorkerid(),tJiechajiankaixianjijians.get(i));
        }
    }

    @Override
    public TJiechajiankaixianjijian getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TJiechajiankaixianjijian tJiechajiankaixianjijian = (TJiechajiankaixianjijian) caffeineCache.asMap().get(workerid);
        return tJiechajiankaixianjijian;
    }

}
