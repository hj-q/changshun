package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TCangkukaoqinMapper;
import com.changshun.countsystem.pojo.TCangkukaoqin;
import com.changshun.countsystem.service.TCangkukaoqinService;
import com.changshun.countsystem.util.TCangkukaoqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TCangkukaoqinServiceImpl extends CoreServiceImpl<TCangkukaoqin> implements TCangkukaoqinService{
	@Autowired
    private TCangkukaoqinMapper tCangkukaoqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TCangkukaoqinExcelUtil tCangkukaoqinExcelUtil = new TCangkukaoqinExcelUtil();
        List<TCangkukaoqin> ilist = tCangkukaoqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TCangkukaoqin tCangkukaoqin : ilist) {
                tCangkukaoqinMapper.insert(tCangkukaoqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TCangkukaoqin tCangkukaoqin){
	List<TCangkukaoqin> tCangkukaoqins = tCangkukaoqinMapper.getList(tCangkukaoqin);
        for (int i=0;i<tCangkukaoqins.size();i++){
            caffeineCache.put(tCangkukaoqins.get(i).getWorkerid(),tCangkukaoqins.get(i));
        }
    }

    @Override
    public TCangkukaoqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TCangkukaoqin tCangkukaoqin = (TCangkukaoqin) caffeineCache.asMap().get(workerid);
        return tCangkukaoqin;
    }

}
