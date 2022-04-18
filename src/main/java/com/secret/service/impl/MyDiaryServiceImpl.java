package com.secret.service.impl;

import com.secret.dao.MyDiaryDao;
import com.secret.service.MyDiaryService;
import com.secret.entity.MyDiary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (MyDiary)表服务实现类
 *
 * @author makejava
 * @since 2022-04-15 19:17:25
 */
@Service("myDiaryService")
public class MyDiaryServiceImpl implements MyDiaryService {
    @Resource
    private MyDiaryDao myDiaryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MyDiary queryById(Long id) {
        return this.myDiaryDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param myDiary 实例对象
     * @return 实例对象
     */
    @Override
    public MyDiary insert(MyDiary myDiary) {
        this.myDiaryDao.insert(myDiary);
        return myDiary;
    }

    /**
     * 修改数据
     *
     * @param myDiary 实例对象
     * @return 实例对象
     */
    @Override
    public MyDiary update(MyDiary myDiary) {
        this.myDiaryDao.update(myDiary);
        return this.queryById(myDiary.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.myDiaryDao.deleteById(id) > 0;
    }
}
