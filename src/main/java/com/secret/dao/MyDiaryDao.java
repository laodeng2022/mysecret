package com.secret.dao;

import com.secret.entity.MyDiary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (MyDiary)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-15 19:17:21
 */
public interface MyDiaryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MyDiary queryById(Long id);

    /**
     * 统计总行数
     *
     * @param myDiary 查询条件
     * @return 总行数
     */
    long count(MyDiary myDiary);

    /**
     * 新增数据
     *
     * @param myDiary 实例对象
     * @return 影响行数
     */
    int insert(MyDiary myDiary);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MyDiary> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MyDiary> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MyDiary> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MyDiary> entities);

    /**
     * 修改数据
     *
     * @param myDiary 实例对象
     * @return 影响行数
     */
    int update(MyDiary myDiary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

