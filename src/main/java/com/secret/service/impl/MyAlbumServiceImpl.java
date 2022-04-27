package com.secret.service.impl;

import com.secret.dao.MyAlbumDao;
import com.secret.entity.MyAlbum;
import com.secret.service.MyAlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MyAlbum)表服务实现类
 *
 * @author makejava
 * @since 2022-04-15 23:16:50
 */
@Service("myAlbumService")
public class MyAlbumServiceImpl implements MyAlbumService {
    @Resource
    private MyAlbumDao myAlbumDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MyAlbum queryById(Long id) {
        return this.myAlbumDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param myAlbum 实例对象
     * @return 实例对象
     */
    @Override
    public MyAlbum insert(MyAlbum myAlbum) {
        this.myAlbumDao.insert(myAlbum);
        return myAlbum;
    }

    /**
     * 修改数据
     *
     * @param myAlbum 实例对象
     * @return 实例对象
     */
    @Override
    public MyAlbum update(MyAlbum myAlbum) {
        this.myAlbumDao.update(myAlbum);
        return this.queryById(myAlbum.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.myAlbumDao.deleteById(id) > 0;
    }

    @Override
    public Page<MyAlbum> queryByPage(MyAlbum album, Pageable pageable) {
        long total = this.myAlbumDao.count(album);
        return new PageImpl<>(this.myAlbumDao.queryAllByLimit(album, pageable), pageable, total);
    }
}
