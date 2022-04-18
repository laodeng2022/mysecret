package com.secret.service;


import com.secret.entity.MyAlbum;

/**
 * (MyAlbum)表服务接口
 *
 * @author makejava
 * @since 2022-04-15 23:16:50
 */
public interface MyAlbumService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MyAlbum queryById(Long id);

    /**
     * 新增数据
     *
     * @param myAlbum 实例对象
     * @return 实例对象
     */
    MyAlbum insert(MyAlbum myAlbum);

    /**
     * 修改数据
     *
     * @param myAlbum 实例对象
     * @return 实例对象
     */
    MyAlbum update(MyAlbum myAlbum);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
