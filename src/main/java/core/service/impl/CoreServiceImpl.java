package core.service.impl;

import com.changshun.countsystem.util.PageBean;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.pagehelper.PageHelper;
import core.dao.CoreDao;
import core.service.CoreService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CoreServiceImpl<T> implements CoreService<T> {

    @Autowired(required = false)
    private CoreDao<T> coreDao;
    @Autowired
    Cache<String, Object> caffeineCache;


    @Override
    public int insert(T t) {
        return coreDao.insert(t);
    }

    @Override
    public int update(T t) {
        return coreDao.update(t);
    }

    @Override
    public int delete(T t) {
        return coreDao.delete(t);
    }

    @Override
    public T getOne(T t) {
        return coreDao.getOne(t);
    }

    @Override
    public List<T> getList(T t) {
        return coreDao.getList(t);
    }

    @Override
    public int getCount(T t) {
        return coreDao.getCount(t);
    }

    @Override
    public List<T> getLike(T t) {
        return coreDao.getLike(t);
    }


    @Override
    public List<T> findByPage(int currentPage, int pageSize, T t) {
        PageHelper.startPage(currentPage, pageSize);
        List<T> managers = coreDao.getList(t); //仓库员工全部人员
        int count = coreDao.getCount(t);//总记录数
        PageBean<T> pageData = new PageBean<>(currentPage, pageSize, count);
        pageData.setItems(managers);
        return pageData.getItems();
    }

    @Override
    public int deleteAll(@Param("ids") List<Long> ids) {

        return coreDao.deleteAll(ids);
    }

    @Override
    public int updateAll(@Param("T") List<T> t) {
        return coreDao.updateAll(t);
    }
}



