package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TJiechajianbanzuzhangMapper;
import com.changshun.countsystem.pojo.TJiechajianbanzuzhang;
import com.changshun.countsystem.service.TJiechajianbanzuzhangService;
import com.changshun.countsystem.util.TJiechajianbanzuzhangExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TJiechajianbanzuzhangServiceImpl extends CoreServiceImpl<TJiechajianbanzuzhang> implements TJiechajianbanzuzhangService {
	@Autowired
    private TJiechajianbanzuzhangMapper tJiechajianbanzuzhangMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TJiechajianbanzuzhangExcelUtil tJiechajianbanzuzhangExcelUtil = new TJiechajianbanzuzhangExcelUtil();
        List<TJiechajianbanzuzhang> ilist = tJiechajianbanzuzhangExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TJiechajianbanzuzhang tJiechajianbanzuzhang : ilist) {
                tJiechajianbanzuzhangMapper.insert(tJiechajianbanzuzhang);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TJiechajianbanzuzhang tJiechajianbanzuzhang){
	List<TJiechajianbanzuzhang> tJiechajianbanzuzhangs = tJiechajianbanzuzhangMapper.getList(tJiechajianbanzuzhang);
        for (int i=0;i<tJiechajianbanzuzhangs.size();i++){
            caffeineCache.put(tJiechajianbanzuzhangs.get(i).getWorkerid(),tJiechajianbanzuzhangs.get(i));
        }
    }

    @Override
    public TJiechajianbanzuzhang getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TJiechajianbanzuzhang tJiechajianbanzuzhang = (TJiechajianbanzuzhang) caffeineCache.asMap().get(workerid);
        return tJiechajianbanzuzhang;
    }

}
