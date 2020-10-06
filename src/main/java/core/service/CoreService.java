package core.service;


import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CoreService<T> {


	int insert(T t);
	int update(T t);
	int delete(T t);
	T getOne(T t);
	List<T > getList(T t);
	public int getCount(T t);
	public List<T> getLike(T t);
	int deleteAll(@Param("ids") List<Long> ids);
	int updateAll(@Param("T") List<T> t);

	//根据页数显示
	List<T> findByPage(int currentPage, int pageSize, T t);

}
