package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TJiaqiMapper;
import com.changshun.countsystem.pojo.TJiaqi;
import com.changshun.countsystem.service.TJiaqiService;
import com.changshun.countsystem.util.TJiaqiExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TJiaqiServiceImpl extends CoreServiceImpl<TJiaqi> implements TJiaqiService {
	@Autowired
    private TJiaqiMapper tJiaqiMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TJiaqiExcelUtil tJiaqiExcelUtil = new TJiaqiExcelUtil();
        List<TJiaqi> ilist = tJiaqiExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TJiaqi tJiaqi : ilist) {
                tJiaqiMapper.insert(tJiaqi);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TJiaqi tJiaqi){
	List<TJiaqi> tJiaqis = tJiaqiMapper.getList(tJiaqi);
        for (int i=0;i<tJiaqis.size();i++){
            caffeineCache.put(tJiaqis.get(i).getWorkerid(),tJiaqis.get(i));
        }
    }

    @Override
    public TJiaqi getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TJiaqi tJiaqi = (TJiaqi) caffeineCache.asMap().get(workerid);
        return tJiaqi;
    }

}
