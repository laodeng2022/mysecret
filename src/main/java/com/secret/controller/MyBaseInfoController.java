package com.secret.controller;

import com.secret.entity.MyBaseInfo;
import com.secret.service.MyBaseInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MyBaseInfo)表控制层
 *
 * @author makejava
 * @since 2022-04-18 21:00:27
 */
@RestController
@RequestMapping("myBaseInfo")
public class MyBaseInfoController {
    /**
     * 服务对象
     */
    @Resource
    private MyBaseInfoService myBaseInfoService;

    /**
     * 分页查询
     *
     * @param myBaseInfo  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MyBaseInfo>> queryByPage(MyBaseInfo myBaseInfo, PageRequest pageRequest) {
        return ResponseEntity.ok(this.myBaseInfoService.queryByPage(myBaseInfo, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MyBaseInfo> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.myBaseInfoService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param myBaseInfo 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MyBaseInfo> add(MyBaseInfo myBaseInfo) {
        return ResponseEntity.ok(this.myBaseInfoService.insert(myBaseInfo));
    }

    /**
     * 编辑数据
     *
     * @param myBaseInfo 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MyBaseInfo> edit(MyBaseInfo myBaseInfo) {
        return ResponseEntity.ok(this.myBaseInfoService.update(myBaseInfo));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.myBaseInfoService.deleteById(id));
    }

}

