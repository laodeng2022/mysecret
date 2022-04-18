package com.secret.dao;

import com.secret.entity.MyFilesManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (MyFilesManage)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-18 21:00:27
 */
public interface MyFilesManageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MyFilesManage queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param myFilesManage 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<MyFilesManage> queryAllByLimit(MyFilesManage myFilesManage, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param myFilesManage 查询条件
     * @return 总行数
     */
    long count(MyFilesManage myFilesManage);

    /**
     * 新增数据
     *
     * @param myFilesManage 实例对象
     * @return 影响行数
     */
    int insert(MyFilesManage myFilesManage);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MyFilesManage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MyFilesManage> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MyFilesManage> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MyFilesManage> entities);

    /**
     * 修改数据
     *
     * @param myFilesManage 实例对象
     * @return 影响行数
     */
    int update(MyFilesManage myFilesManage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

