package com.secret.dao;

import com.secret.entity.MyBaseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (MyBaseInfo)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-18 21:00:27
 */
public interface MyBaseInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MyBaseInfo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param myBaseInfo 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<MyBaseInfo> queryAllByLimit(MyBaseInfo myBaseInfo, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param myBaseInfo 查询条件
     * @return 总行数
     */
    long count(MyBaseInfo myBaseInfo);

    /**
     * 新增数据
     *
     * @param myBaseInfo 实例对象
     * @return 影响行数
     */
    int insert(MyBaseInfo myBaseInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MyBaseInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MyBaseInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MyBaseInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MyBaseInfo> entities);

    /**
     * 修改数据
     *
     * @param myBaseInfo 实例对象
     * @return 影响行数
     */
    int update(MyBaseInfo myBaseInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过条件查询列表
     *
     * @param mybaseInfo
     * @return
     */
    List<MyBaseInfo> queryList(MyBaseInfo mybaseInfo);
}

