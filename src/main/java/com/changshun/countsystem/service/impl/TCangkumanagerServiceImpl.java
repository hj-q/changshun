package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TCangkumanagerMapper;
import com.changshun.countsystem.pojo.TCangkumanager;
import com.changshun.countsystem.service.TCangkumanagerService;
import com.changshun.countsystem.util.TCangkumanagerExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TCangkumanagerServiceImpl extends CoreServiceImpl<TCangkumanager> implements TCangkumanagerService{
	@Autowired
    private TCangkumanagerMapper tCangkumanagerMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TCangkumanagerExcelUtil tCangkumanagerExcelUtil = new TCangkumanagerExcelUtil();
        List<TCangkumanager> ilist = tCangkumanagerExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TCangkumanager tCangkumanager : ilist) {
                tCangkumanagerMapper.insert(tCangkumanager);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TCangkumanager tCangkumanager){
	List<TCangkumanager> tCangkumanagers = tCangkumanagerMapper.getList(tCangkumanager);
        for (int i=0;i<tCangkumanagers.size();i++){
            caffeineCache.put(tCangkumanagers.get(i).getWorkerid(),tCangkumanagers.get(i));
        }
    }

    @Override
    public TCangkumanager getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TCangkumanager tCangkumanager = (TCangkumanager) caffeineCache.asMap().get(workerid);
        return tCangkumanager;
    }

}
