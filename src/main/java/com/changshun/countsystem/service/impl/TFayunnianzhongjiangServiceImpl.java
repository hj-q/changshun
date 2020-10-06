package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TFayunnianzhongjiangMapper;
import com.changshun.countsystem.pojo.TFayunnianzhongjiang;
import com.changshun.countsystem.service.TFayunnianzhongjiangService;
import com.changshun.countsystem.util.TFayunnianzhongjiangExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TFayunnianzhongjiangServiceImpl extends CoreServiceImpl<TFayunnianzhongjiang> implements TFayunnianzhongjiangService {
	@Autowired
    private TFayunnianzhongjiangMapper tFayunnianzhongjiangMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TFayunnianzhongjiangExcelUtil tFayunnianzhongjiangExcelUtil = new TFayunnianzhongjiangExcelUtil();
        List<TFayunnianzhongjiang> ilist = tFayunnianzhongjiangExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TFayunnianzhongjiang tFayunnianzhongjiang : ilist) {
                tFayunnianzhongjiangMapper.insert(tFayunnianzhongjiang);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TFayunnianzhongjiang tFayunnianzhongjiang){
	List<TFayunnianzhongjiang> tFayunnianzhongjiangs = tFayunnianzhongjiangMapper.getList(tFayunnianzhongjiang);
        for (int i=0;i<tFayunnianzhongjiangs.size();i++){
            caffeineCache.put(tFayunnianzhongjiangs.get(i).getWorkerid(),tFayunnianzhongjiangs.get(i));
        }
    }

    @Override
    public TFayunnianzhongjiang getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TFayunnianzhongjiang tFayunnianzhongjiang = (TFayunnianzhongjiang) caffeineCache.asMap().get(workerid);
        return tFayunnianzhongjiang;
    }

}
