package com.secret.service;

import com.secret.entity.VipRights;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (VipRights)表服务接口
 *
 * @author makejava
 * @since 2022-04-18 21:08:16
 */
public interface VipRightsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VipRights queryById(Long id);

    /**
     * 分页查询
     *
     * @param vipRights 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VipRights> queryByPage(VipRights vipRights, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param vipRights 实例对象
     * @return 实例对象
     */
    VipRights insert(VipRights vipRights);

    /**
     * 修改数据
     *
     * @param vipRights 实例对象
     * @return 实例对象
     */
    VipRights update(VipRights vipRights);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<VipRights> queryList(VipRights vipRights);
}
