package com.secret.service;


import com.secret.entity.MyDiary;

/**
 * (MyDiary)表服务接口
 *
 * @author makejava
 * @since 2022-04-15 19:17:25
 */
public interface MyDiaryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MyDiary queryById(Long id);


    /**
     * 新增数据
     *
     * @param myDiary 实例对象
     * @return 实例对象
     */
    MyDiary insert(MyDiary myDiary);

    /**
     * 修改数据
     *
     * @param myDiary 实例对象
     * @return 实例对象
     */
    MyDiary update(MyDiary myDiary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
