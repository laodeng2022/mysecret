package com.secret.service;

import com.secret.entity.MyFilesManage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (MyFilesManage)表服务接口
 *
 * @author makejava
 * @since 2022-04-18 21:00:28
 */
public interface MyFilesManageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MyFilesManage queryById(Long id);

    /**
     * 分页查询
     *
     * @param myFilesManage 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<MyFilesManage> queryByPage(MyFilesManage myFilesManage, Pageable pageable);

    /**
     * 新增数据
     *
     * @param myFilesManage 实例对象
     * @return 实例对象
     */
    MyFilesManage insert(MyFilesManage myFilesManage);

    /**
     * 修改数据
     *
     * @param myFilesManage 实例对象
     * @return 实例对象
     */
    MyFilesManage update(MyFilesManage myFilesManage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<MyFilesManage> queryList(MyFilesManage myFilesManage);

    void deleteByCond(MyFilesManage deleteCond);

    void albumMove(MyFilesManage myFilesManage);

    List<MyFilesManage> queryByPage(MyFilesManage filesManage);
}
