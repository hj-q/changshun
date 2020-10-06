package core.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoreDao<T> {

    int insert(T t);
    int update(T t);
    int delete(T t);
    T getOne(T t);
    List<T > getList(T t);
    public int getCount(T t);
    public List<T> getPage(T t);
    public List<T> getLike(T t);
    public List<T > getInterval(T t);
    int deleteAll(@Param("ids") List<Long> ids);
    int updateAll(@Param("T") List<T> t);

}
