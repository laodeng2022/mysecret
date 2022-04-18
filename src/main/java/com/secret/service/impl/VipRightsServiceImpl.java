package com.secret.service.impl;

import com.secret.entity.VipRights;
import com.secret.dao.VipRightsDao;
import com.secret.service.VipRightsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (VipRights)表服务实现类
 *
 * @author makejava
 * @since 2022-04-18 21:08:16
 */
@Service("vipRightsService")
public class VipRightsServiceImpl implements VipRightsService {
    @Resource
    private VipRightsDao vipRightsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VipRights queryById(Long id) {
        return this.vipRightsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param vipRights 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VipRights> queryByPage(VipRights vipRights, PageRequest pageRequest) {
        long total = this.vipRightsDao.count(vipRights);
        return new PageImpl<>(this.vipRightsDao.queryAllByLimit(vipRights, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param vipRights 实例对象
     * @return 实例对象
     */
    @Override
    public VipRights insert(VipRights vipRights) {
        this.vipRightsDao.insert(vipRights);
        return vipRights;
    }

    /**
     * 修改数据
     *
     * @param vipRights 实例对象
     * @return 实例对象
     */
    @Override
    public VipRights update(VipRights vipRights) {
        this.vipRightsDao.update(vipRights);
        return this.queryById(vipRights.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.vipRightsDao.deleteById(id) > 0;
    }

    @Override
    public List<VipRights> queryList(VipRights vipRights) {
        return this.vipRightsDao.queryList(vipRights);
    }
}
