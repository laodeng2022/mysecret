package com.secret.dao;

import com.secret.entity.MyAlbum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (MyAlbum)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-15 23:16:50
 */
public interface MyAlbumDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MyAlbum queryById(Long id);

    /**
     * 统计总行数
     *
     * @param myAlbum 查询条件
     * @return 总行数
     */
    long count(MyAlbum myAlbum);

    /**
     * 新增数据
     *
     * @param myAlbum 实例对象
     * @return 影响行数
     */
    int insert(MyAlbum myAlbum);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MyAlbum> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MyAlbum> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MyAlbum> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MyAlbum> entities);

    /**
     * 修改数据
     *
     * @param myAlbum 实例对象
     * @return 影响行数
     */
    int update(MyAlbum myAlbum);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

