package com.secret.service;

import com.secret.entity.MySetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (MySetting)表服务接口
 *
 * @author makejava
 * @since 2022-04-18 21:00:28
 */
public interface MySettingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MySetting queryById(Long id);

    /**
     * 分页查询
     *
     * @param mySetting   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<MySetting> queryByPage(MySetting mySetting, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param mySetting 实例对象
     * @return 实例对象
     */
    MySetting insert(MySetting mySetting);

    /**
     * 修改数据
     *
     * @param mySetting 实例对象
     * @return 实例对象
     */
    MySetting update(MySetting mySetting);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<MySetting> queryList(MySetting mySetting);
}
