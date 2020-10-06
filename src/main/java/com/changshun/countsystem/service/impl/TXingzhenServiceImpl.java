package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TXingzhenMapper;
import com.changshun.countsystem.pojo.TXingzhen;
import com.changshun.countsystem.service.TXingzhenService;
import com.changshun.countsystem.util.TXingzhenExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TXingzhenServiceImpl extends CoreServiceImpl<TXingzhen> implements TXingzhenService{
	@Autowired
    private TXingzhenMapper tXingzhenMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TXingzhenExcelUtil tXingzhenExcelUtil = new TXingzhenExcelUtil();
        List<TXingzhen> ilist = tXingzhenExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TXingzhen tXingzhen : ilist) {
                tXingzhenMapper.insert(tXingzhen);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TXingzhen tXingzhen){
	List<TXingzhen> tXingzhens = tXingzhenMapper.getList(tXingzhen);
        for (int i=0;i<tXingzhens.size();i++){
            caffeineCache.put(tXingzhens.get(i).getWorkerid(),tXingzhens.get(i));
        }
    }

    @Override
    public TXingzhen getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TXingzhen tXingzhen = (TXingzhen) caffeineCache.asMap().get(workerid);
        return tXingzhen;
    }

}
