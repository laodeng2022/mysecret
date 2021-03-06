package com.secret.service.impl;

import com.secret.entity.MyFilesManage;
import com.secret.dao.MyFilesManageDao;
import com.secret.service.MyFilesManageService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MyFilesManage)表服务实现类
 *
 * @author makejava
 * @since 2022-04-18 21:00:28
 */
@Service("myFilesManageService")
public class MyFilesManageServiceImpl implements MyFilesManageService {
    @Resource
    private MyFilesManageDao myFilesManageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MyFilesManage queryById(Long id) {
        return this.myFilesManageDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param myFilesManage 筛选条件
     * @param pageable   分页对象
     * @return 查询结果
     */
    @Override
    public Page<MyFilesManage> queryByPage(MyFilesManage myFilesManage, Pageable pageable) {
        long total = this.myFilesManageDao.count(myFilesManage);
        return new PageImpl<>(this.myFilesManageDao.queryAllByLimit(myFilesManage, pageable), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param myFilesManage 实例对象
     * @return 实例对象
     */
    @Override
    public MyFilesManage insert(MyFilesManage myFilesManage) {
        this.myFilesManageDao.insert(myFilesManage);
        return myFilesManage;
    }

    /**
     * 修改数据
     *
     * @param myFilesManage 实例对象
     * @return 实例对象
     */
    @Override
    public MyFilesManage update(MyFilesManage myFilesManage) {
        this.myFilesManageDao.update(myFilesManage);
        return this.queryById(myFilesManage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.myFilesManageDao.deleteById(id) > 0;
    }

    @Override
    public List<MyFilesManage> queryList(MyFilesManage myFilesManage) {
        return this.myFilesManageDao.queryList(myFilesManage);
    }

    @Override
    public void deleteByCond(MyFilesManage deleteCond) {
        this.myFilesManageDao.deleteByCond(deleteCond);
    }

    @Override
    public void albumMove(MyFilesManage myFilesManage) {
        this.myFilesManageDao.albumMove(myFilesManage);
    }

    @Override
    public List<MyFilesManage> queryByPage(MyFilesManage filesManage) {
        return myFilesManageDao.selectByParamsPage(filesManage) ;
    }
}
