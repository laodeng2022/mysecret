package com.secret.service.impl;

import com.secret.entity.MyBaseInfo;
import com.secret.dao.MyBaseInfoDao;
import com.secret.service.MyBaseInfoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (MyBaseInfo)表服务实现类
 *
 * @author makejava
 * @since 2022-04-18 21:00:27
 */
@Service("myBaseInfoService")
public class MyBaseInfoServiceImpl implements MyBaseInfoService {
    @Resource
    private MyBaseInfoDao myBaseInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MyBaseInfo queryById(Long id) {
        return this.myBaseInfoDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param myBaseInfo 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<MyBaseInfo> queryByPage(MyBaseInfo myBaseInfo, PageRequest pageRequest) {
        long total = this.myBaseInfoDao.count(myBaseInfo);
        return new PageImpl<>(this.myBaseInfoDao.queryAllByLimit(myBaseInfo, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param myBaseInfo 实例对象
     * @return 实例对象
     */
    @Override
    public MyBaseInfo insert(MyBaseInfo myBaseInfo) {
        this.myBaseInfoDao.insert(myBaseInfo);
        return myBaseInfo;
    }

    /**
     * 修改数据
     *
     * @param myBaseInfo 实例对象
     * @return 实例对象
     */
    @Override
    public MyBaseInfo update(MyBaseInfo myBaseInfo) {
        this.myBaseInfoDao.update(myBaseInfo);
        return this.queryById(myBaseInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.myBaseInfoDao.deleteById(id) > 0;
    }
}
