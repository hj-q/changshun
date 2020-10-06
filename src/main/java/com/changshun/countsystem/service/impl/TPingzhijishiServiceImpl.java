package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TPingzhijishiMapper;
import com.changshun.countsystem.pojo.TPingzhijishi;
import com.changshun.countsystem.service.TPingzhijishiService;
import com.changshun.countsystem.util.TPingzhijishiExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TPingzhijishiServiceImpl extends CoreServiceImpl<TPingzhijishi> implements TPingzhijishiService {
	@Autowired
    private TPingzhijishiMapper tPingzhijishiMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TPingzhijishiExcelUtil tPingzhijishiExcelUtil = new TPingzhijishiExcelUtil();
        List<TPingzhijishi> ilist = tPingzhijishiExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TPingzhijishi tPingzhijishi : ilist) {
                tPingzhijishiMapper.insert(tPingzhijishi);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TPingzhijishi tPingzhijishi){
	List<TPingzhijishi> tPingzhijishis = tPingzhijishiMapper.getList(tPingzhijishi);
        for (int i=0;i<tPingzhijishis.size();i++){
            caffeineCache.put(tPingzhijishis.get(i).getWorkerid(),tPingzhijishis.get(i));
        }
    }

    @Override
    public TPingzhijishi getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TPingzhijishi tPingzhijishi = (TPingzhijishi) caffeineCache.asMap().get(workerid);
        return tPingzhijishi;
    }

}
