package com.secret.controller;

import com.secret.entity.MyFilesManage;
import com.secret.service.MyFilesManageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MyFilesManage)表控制层
 *
 * @author makejava
 * @since 2022-04-18 21:00:27
 */
@RestController
@RequestMapping("myFilesManage")
public class MyFilesManageController {
    /**
     * 服务对象
     */
    @Resource
    private MyFilesManageService myFilesManageService;

    /**
     * 分页查询
     *
     * @param myFilesManage 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MyFilesManage>> queryByPage(MyFilesManage myFilesManage, PageRequest pageRequest) {
        return ResponseEntity.ok(this.myFilesManageService.queryByPage(myFilesManage, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MyFilesManage> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.myFilesManageService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param myFilesManage 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MyFilesManage> add(MyFilesManage myFilesManage) {
        return ResponseEntity.ok(this.myFilesManageService.insert(myFilesManage));
    }

    /**
     * 编辑数据
     *
     * @param myFilesManage 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MyFilesManage> edit(MyFilesManage myFilesManage) {
        return ResponseEntity.ok(this.myFilesManageService.update(myFilesManage));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.myFilesManageService.deleteById(id));
    }

}

