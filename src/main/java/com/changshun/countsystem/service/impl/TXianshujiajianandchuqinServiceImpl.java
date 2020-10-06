package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TXianshujiajianandchuqinMapper;
import com.changshun.countsystem.pojo.TXianshujiajianandchuqin;
import com.changshun.countsystem.service.TXianshujiajianandchuqinService;
import com.changshun.countsystem.util.TXianshujiajianandchuqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TXianshujiajianandchuqinServiceImpl extends CoreServiceImpl<TXianshujiajianandchuqin> implements TXianshujiajianandchuqinService {
	@Autowired
    private TXianshujiajianandchuqinMapper tXianshujiajianandchuqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TXianshujiajianandchuqinExcelUtil tXianshujiajianandchuqinExcelUtil = new TXianshujiajianandchuqinExcelUtil();
        List<TXianshujiajianandchuqin> ilist = tXianshujiajianandchuqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TXianshujiajianandchuqin tXianshujiajianandchuqin : ilist) {
                tXianshujiajianandchuqinMapper.insert(tXianshujiajianandchuqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TXianshujiajianandchuqin tXianshujiajianandchuqin){
	List<TXianshujiajianandchuqin> tXianshujiajianandchuqins = tXianshujiajianandchuqinMapper.getList(tXianshujiajianandchuqin);
        for (int i=0;i<tXianshujiajianandchuqins.size();i++){
            caffeineCache.put(tXianshujiajianandchuqins.get(i).getWorkerid(),tXianshujiajianandchuqins.get(i));
        }
    }

    @Override
    public TXianshujiajianandchuqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TXianshujiajianandchuqin tXianshujiajianandchuqin = (TXianshujiajianandchuqin) caffeineCache.asMap().get(workerid);
        return tXianshujiajianandchuqin;
    }

}
