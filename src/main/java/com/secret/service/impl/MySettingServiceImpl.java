package com.secret.service.impl;

import com.secret.entity.MySetting;
import com.secret.dao.MySettingDao;
import com.secret.service.MySettingService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (MySetting)表服务实现类
 *
 * @author makejava
 * @since 2022-04-18 21:00:28
 */
@Service("mySettingService")
public class MySettingServiceImpl implements MySettingService {
    @Resource
    private MySettingDao mySettingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MySetting queryById(Long id) {
        return this.mySettingDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param mySetting 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<MySetting> queryByPage(MySetting mySetting, PageRequest pageRequest) {
        long total = this.mySettingDao.count(mySetting);
        return new PageImpl<>(this.mySettingDao.queryAllByLimit(mySetting, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param mySetting 实例对象
     * @return 实例对象
     */
    @Override
    public MySetting insert(MySetting mySetting) {
        this.mySettingDao.insert(mySetting);
        return mySetting;
    }

    /**
     * 修改数据
     *
     * @param mySetting 实例对象
     * @return 实例对象
     */
    @Override
    public MySetting update(MySetting mySetting) {
        this.mySettingDao.update(mySetting);
        return this.queryById(mySetting.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.mySettingDao.deleteById(id) > 0;
    }
}
