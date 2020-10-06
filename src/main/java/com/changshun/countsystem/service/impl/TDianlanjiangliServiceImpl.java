package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TDianlanjiangliMapper;
import com.changshun.countsystem.pojo.TDianlanjiangli;
import com.changshun.countsystem.service.TDianlanjiangliService;
import com.changshun.countsystem.util.TDianlanjiangliExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TDianlanjiangliServiceImpl extends CoreServiceImpl<TDianlanjiangli> implements TDianlanjiangliService {
	@Autowired
    private TDianlanjiangliMapper tDianlanjiangliMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TDianlanjiangliExcelUtil tDianlanjiangliExcelUtil = new TDianlanjiangliExcelUtil();
        List<TDianlanjiangli> ilist = tDianlanjiangliExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TDianlanjiangli tDianlanjiangli : ilist) {
                tDianlanjiangliMapper.insert(tDianlanjiangli);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TDianlanjiangli tDianlanjiangli){
	List<TDianlanjiangli> tDianlanjianglis = tDianlanjiangliMapper.getList(tDianlanjiangli);
        for (int i=0;i<tDianlanjianglis.size();i++){
            caffeineCache.put(tDianlanjianglis.get(i).getWorkerid(),tDianlanjianglis.get(i));
        }
    }

    @Override
    public TDianlanjiangli getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TDianlanjiangli tDianlanjiangli = (TDianlanjiangli) caffeineCache.asMap().get(workerid);
        return tDianlanjiangli;
    }

}
