package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TDianlanjianyanMapper;
import com.changshun.countsystem.pojo.TDianlanjianyan;
import com.changshun.countsystem.service.TDianlanjianyanService;
import com.changshun.countsystem.util.TDianlanjianyanExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TDianlanjianyanServiceImpl extends CoreServiceImpl<TDianlanjianyan> implements TDianlanjianyanService {
	@Autowired
    private TDianlanjianyanMapper tDianlanjianyanMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TDianlanjianyanExcelUtil tDianlanjianyanExcelUtil = new TDianlanjianyanExcelUtil();
        List<TDianlanjianyan> ilist = tDianlanjianyanExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TDianlanjianyan tDianlanjianyan : ilist) {
                tDianlanjianyanMapper.insert(tDianlanjianyan);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TDianlanjianyan tDianlanjianyan){
	List<TDianlanjianyan> tDianlanjianyans = tDianlanjianyanMapper.getList(tDianlanjianyan);
        for (int i=0;i<tDianlanjianyans.size();i++){
            caffeineCache.put(tDianlanjianyans.get(i).getWorkerid(),tDianlanjianyans.get(i));
        }
    }

    @Override
    public TDianlanjianyan getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TDianlanjianyan tDianlanjianyan = (TDianlanjianyan) caffeineCache.asMap().get(workerid);
        return tDianlanjianyan;
    }

}
