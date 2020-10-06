package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TGuangzhoumoneyMapper;
import com.changshun.countsystem.pojo.TGuangzhoumoney;
import com.changshun.countsystem.service.TGuangzhoumoneyService;
import com.changshun.countsystem.util.TGuangzhoumoneyExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TGuangzhoumoneyServiceImpl extends CoreServiceImpl<TGuangzhoumoney> implements TGuangzhoumoneyService {
	@Autowired
    private TGuangzhoumoneyMapper tGuangzhoumoneyMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TGuangzhoumoneyExcelUtil tGuangzhoumoneyExcelUtil = new TGuangzhoumoneyExcelUtil();
        List<TGuangzhoumoney> ilist = tGuangzhoumoneyExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TGuangzhoumoney tGuangzhoumoney : ilist) {
                tGuangzhoumoneyMapper.insert(tGuangzhoumoney);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TGuangzhoumoney tGuangzhoumoney){
	List<TGuangzhoumoney> tGuangzhoumoneys = tGuangzhoumoneyMapper.getList(tGuangzhoumoney);
        for (int i=0;i<tGuangzhoumoneys.size();i++){
            caffeineCache.put(tGuangzhoumoneys.get(i).getWorkerid(),tGuangzhoumoneys.get(i));
        }
    }

    @Override
    public TGuangzhoumoney getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TGuangzhoumoney tGuangzhoumoney = (TGuangzhoumoney) caffeineCache.asMap().get(workerid);
        return tGuangzhoumoney;
    }

}
