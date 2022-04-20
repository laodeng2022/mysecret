package com.secret.controller;

import com.secret.entity.VipRights;
import com.secret.service.VipRightsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (VipRights)表控制层
 *
 * @author makejava
 * @since 2022-04-18 21:08:15
 */
@RestController
@RequestMapping("vipRights")
public class VipRightsController {
    /**
     * 服务对象
     */
    @Resource
    private VipRightsService vipRightsService;

    /**
     * 分页查询
     *
     * @param vipRights   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<VipRights>> queryByPage(VipRights vipRights, PageRequest pageRequest) {
        return ResponseEntity.ok(this.vipRightsService.queryByPage(vipRights, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<VipRights> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.vipRightsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param vipRights 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<VipRights> add(VipRights vipRights) {
        return ResponseEntity.ok(this.vipRightsService.insert(vipRights));
    }

    /**
     * 编辑数据
     *
     * @param vipRights 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<VipRights> edit(VipRights vipRights) {
        return ResponseEntity.ok(this.vipRightsService.update(vipRights));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.vipRightsService.deleteById(id));
    }

}

