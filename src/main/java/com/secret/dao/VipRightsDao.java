package com.secret.dao;

import com.secret.entity.VipRights;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (VipRights)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-18 21:08:16
 */
public interface VipRightsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VipRights queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param vipRights 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<VipRights> queryAllByLimit(VipRights vipRights, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param vipRights 查询条件
     * @return 总行数
     */
    long count(VipRights vipRights);

    /**
     * 新增数据
     *
     * @param vipRights 实例对象
     * @return 影响行数
     */
    int insert(VipRights vipRights);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VipRights> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VipRights> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VipRights> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VipRights> entities);

    /**
     * 修改数据
     *
     * @param vipRights 实例对象
     * @return 影响行数
     */
    int update(VipRights vipRights);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<VipRights> queryList(VipRights vipRights);
}

