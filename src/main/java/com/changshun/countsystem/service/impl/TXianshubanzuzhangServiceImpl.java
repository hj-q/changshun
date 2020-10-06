package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TXianshubanzuzhangMapper;
import com.changshun.countsystem.pojo.TXianshubanzuzhang;
import com.changshun.countsystem.service.TXianshubanzuzhangService;
import com.changshun.countsystem.util.TXianshubanzuzhangExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TXianshubanzuzhangServiceImpl extends CoreServiceImpl<TXianshubanzuzhang> implements TXianshubanzuzhangService{
	@Autowired
    private TXianshubanzuzhangMapper tXianshubanzuzhangMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TXianshubanzuzhangExcelUtil tXianshubanzuzhangExcelUtil = new TXianshubanzuzhangExcelUtil();
        List<TXianshubanzuzhang> ilist = tXianshubanzuzhangExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TXianshubanzuzhang tXianshubanzuzhang : ilist) {
                tXianshubanzuzhangMapper.insert(tXianshubanzuzhang);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TXianshubanzuzhang tXianshubanzuzhang){
	List<TXianshubanzuzhang> tXianshubanzuzhangs = tXianshubanzuzhangMapper.getList(tXianshubanzuzhang);
        for (int i=0;i<tXianshubanzuzhangs.size();i++){
            caffeineCache.put(tXianshubanzuzhangs.get(i).getWorkerid(),tXianshubanzuzhangs.get(i));
        }
    }

    @Override
    public TXianshubanzuzhang getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TXianshubanzuzhang tXianshubanzuzhang = (TXianshubanzuzhang) caffeineCache.asMap().get(workerid);
        return tXianshubanzuzhang;
    }

}
