package com.secret.controller;

import com.secret.entity.MySetting;
import com.secret.service.MySettingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MySetting)表控制层
 *
 * @author makejava
 * @since 2022-04-18 21:00:28
 */
@RestController
@RequestMapping("mySetting")
public class MySettingController {
    /**
     * 服务对象
     */
    @Resource
    private MySettingService mySettingService;

    /**
     * 分页查询
     *
     * @param mySetting 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MySetting>> queryByPage(MySetting mySetting, PageRequest pageRequest) {
        return ResponseEntity.ok(this.mySettingService.queryByPage(mySetting, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MySetting> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.mySettingService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param mySetting 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MySetting> add(MySetting mySetting) {
        return ResponseEntity.ok(this.mySettingService.insert(mySetting));
    }

    /**
     * 编辑数据
     *
     * @param mySetting 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MySetting> edit(MySetting mySetting) {
        return ResponseEntity.ok(this.mySettingService.update(mySetting));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.mySettingService.deleteById(id));
    }

}

