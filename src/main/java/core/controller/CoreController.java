package core.controller;

import core.service.CoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController



public class CoreController<T> {
  @Resource
    private CoreService<T> coreService;

   

    /**
     * 获取所有
     * @return
     */
    @GetMapping
    public List<T> getList (T t) {
        List<T> list = coreService.getList(t);
        return list;
    }

    /**
     * 根据id获取一个
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public T CoreController(@PathVariable Long id, T t) {
        T t1 = coreService.getOne(t);
             return  t1;
    }

    /**
     * 获取分页数据
     * @param pageNum
     * @param pageSize
     * @return
     */

    /**
     * 添加数据
     * @param
     * @return
     */
    @PostMapping
    public Object add(T t) {
        int result = coreService.insert(t);
        if (result == 1) {
            return "success";
        }
        return "failure";
    }

    /**
     * 修改数据
     * @param T
     * @return
     * 提供全部资源
     */
    @PutMapping
    public Object update(T T) {
        int result = coreService.update(T);
        if (result == 1) {
            return "success";

        }
        return "failure";
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping
    public Object delete(Long id, T t) {
        int result = coreService.delete(t);
        if (result == 1) {
            return "success";
        }
        return "failure";
    }

}
