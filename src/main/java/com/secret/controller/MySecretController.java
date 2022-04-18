package com.secret.controller;

import com.secret.config.JwtConfig;
import com.secret.entity.MyAlbum;
import com.secret.entity.MyBaseInfo;
import com.secret.entity.MyDiary;
import com.secret.entity.VipRights;
import com.secret.service.MyAlbumService;
import com.secret.service.MyBaseInfoService;
import com.secret.service.MyDiaryService;
import com.secret.service.VipRightsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (MyDiary)表控制层
 *
 * @author makejava
 * @since 2022-04-15 19:17:21
 */
@RestController
@RequestMapping("")
public class MySecretController {
    @Resource
    private JwtConfig jwtConfig;

    /**
     * 服务对象
     */
    @Resource
    private MyDiaryService myDiaryService;

    /**
     * 服务对象
     */
    @Resource
    private MyAlbumService myAlbumService;
    @Resource
    private VipRightsService vipRightsService;
    @Resource
    private MyBaseInfoService myBaseInfoService;

    /**
     * 测试成成Token解析
     *
     * @param userName
     * @param passWord
     * @return
     */
    @PostMapping("/login")
    public Map<String, String> login(@RequestParam("userName") String userName,
                                     @RequestParam("passWord") String passWord) {
        Map<String, String> result = new HashMap<>();
        String token = jwtConfig.getToken(userName + "##" + passWord);
        if (!StringUtils.isEmpty(token)) {
            result.put("token", token);
        }
        result.put("userName", userName);
        return result;
    }

    private Long getCurrentUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String token = request.getHeader("token");
        String subject = jwtConfig.getTokenClaim(token).getSubject();
        return Long.valueOf(subject.split("##")[0]);
    }

    /**
     * 新增数据
     *
     * @param myDiary 实体
     * @return 新增结果
     */
    @PostMapping("diary")
    public ApiResponse<MyDiary> addDiary(@RequestBody MyDiary myDiary) {
        Long curUserId = this.getCurrentUserId();
        myDiary.setCreatedBy(curUserId);
        myDiary.setCreatedDate(new Date());
        return ApiResponse.success(this.myDiaryService.insert(myDiary));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("diary/{id}")
    public ApiResponse<Boolean> deleteDiaryById(@PathVariable("id") Long id) {
        return ApiResponse.success(this.myDiaryService.deleteById(id));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("diary/{id}")
    public ApiResponse<MyDiary> queryDiaryById(@PathVariable("id") Long id) {
        return ApiResponse.success(this.myDiaryService.queryById(id));
    }


    /**
     * 编辑数据
     *
     * @param myDiary 实体
     * @return 编辑结果
     */
    @PutMapping
    public ApiResponse<MyDiary> editDiary(MyDiary myDiary) {
        myDiary.setLastUpdatedBy(this.getCurrentUserId());
        myDiary.setLastUpdatedDate(new Date());
        return ApiResponse.success(this.myDiaryService.update(myDiary));
    }


    /**
     * 新增数据
     *
     * @param myAlbum 实体
     * @return 新增结果
     */
    @PostMapping("album")
    public ApiResponse<MyAlbum> addAlbum(@RequestBody MyAlbum myAlbum) {
        myAlbum.setCreatedBy(this.getCurrentUserId());
        return ApiResponse.success(this.myAlbumService.insert(myAlbum));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/album/{id}")
    public ApiResponse<MyAlbum> queryAlbumById(@PathVariable("id") Long id) {
        return ApiResponse.success(this.myAlbumService.queryById(id));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/album/{id}")
    public ApiResponse<Boolean> deleteAlbumById(@PathVariable("id") Long id) {
        return ApiResponse.success(this.myAlbumService.deleteById(id));
    }

    /**
     * 编辑数据
     *
     * @param myAlbum 实体
     * @return 编辑结果
     */
    @PostMapping("/album/{id}")
    public ApiResponse<MyAlbum> edit(@PathVariable("id") Long id, @RequestBody MyAlbum myAlbum) {
        myAlbum.setId(id);
        return ApiResponse.success(this.myAlbumService.update(myAlbum));
    }

    /**
     * 获取权益列表
     *
     * @return
     */
    @GetMapping("/vip/rights")
    public ApiResponse<List<VipRights>> queryVipRight() {
        return ApiResponse.success(this.vipRightsService.queryList(new VipRights()));
    }

    /**
     * 购买会员
     *
     * @param mybaseInfo
     * @return
     */
    @PostMapping("/vip/buy")
    public ApiResponse<MyBaseInfo> buyVip(@RequestBody MyBaseInfo mybaseInfo) {
        Long userId = this.getCurrentUserId();
        MyBaseInfo userBaseInfo = myBaseInfoService.queryById(userId);
        userBaseInfo.setVipLevel("1");
        myBaseInfoService.update(userBaseInfo);
        return ApiResponse.success(userBaseInfo);
    }

}

