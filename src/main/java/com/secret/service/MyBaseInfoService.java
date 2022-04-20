package com.secret.service;

import com.secret.entity.MyBaseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (MyBaseInfo)表服务接口
 *
 * @author makejava
 * @since 2022-04-18 21:00:27
 */
public interface MyBaseInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MyBaseInfo queryById(Long id);

    /**
     * 分页查询
     *
     * @param myBaseInfo  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<MyBaseInfo> queryByPage(MyBaseInfo myBaseInfo, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param myBaseInfo 实例对象
     * @return 实例对象
     */
    MyBaseInfo insert(MyBaseInfo myBaseInfo);

    /**
     * 修改数据
     *
     * @param myBaseInfo 实例对象
     * @return 实例对象
     */
    MyBaseInfo update(MyBaseInfo myBaseInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 获取用户列表
     *
     * @param mybaseInfo
     * @return
     */
    List<MyBaseInfo> queryList(MyBaseInfo mybaseInfo);
}
