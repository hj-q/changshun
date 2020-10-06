package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TZhiliangjiangliMapper;
import com.changshun.countsystem.pojo.TZhiliangjiangli;
import com.changshun.countsystem.service.TZhiliangjiangliService;
import com.changshun.countsystem.util.TZhiliangjiangliExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TZhiliangjiangliServiceImpl extends CoreServiceImpl<TZhiliangjiangli> implements TZhiliangjiangliService {
	@Autowired
    private TZhiliangjiangliMapper tZhiliangjiangliMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TZhiliangjiangliExcelUtil tZhiliangjiangliExcelUtil = new TZhiliangjiangliExcelUtil();
        List<TZhiliangjiangli> ilist = tZhiliangjiangliExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TZhiliangjiangli tZhiliangjiangli : ilist) {
                tZhiliangjiangliMapper.insert(tZhiliangjiangli);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TZhiliangjiangli tZhiliangjiangli){
	List<TZhiliangjiangli> tZhiliangjianglis = tZhiliangjiangliMapper.getList(tZhiliangjiangli);
        for (int i=0;i<tZhiliangjianglis.size();i++){
            caffeineCache.put(tZhiliangjianglis.get(i).getWorkerid(),tZhiliangjianglis.get(i));
        }
    }

    @Override
    public TZhiliangjiangli getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TZhiliangjiangli tZhiliangjiangli = (TZhiliangjiangli) caffeineCache.asMap().get(workerid);
        return tZhiliangjiangli;
    }

}
