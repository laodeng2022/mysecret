package com.secret.dao;

import com.secret.entity.MySetting;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (MySetting)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-18 21:00:28
 */
public interface MySettingDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MySetting queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param mySetting 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<MySetting> queryAllByLimit(MySetting mySetting, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param mySetting 查询条件
     * @return 总行数
     */
    long count(MySetting mySetting);

    /**
     * 新增数据
     *
     * @param mySetting 实例对象
     * @return 影响行数
     */
    int insert(MySetting mySetting);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MySetting> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MySetting> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MySetting> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MySetting> entities);

    /**
     * 修改数据
     *
     * @param mySetting 实例对象
     * @return 影响行数
     */
    int update(MySetting mySetting);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

