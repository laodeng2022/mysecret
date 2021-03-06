package com.secret.controller;

import com.secret.config.JwtConfig;
import com.secret.entity.*;
import com.secret.service.*;
import com.secret.util.Md5Util;
import io.jsonwebtoken.Claims;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
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

//import com.secret.util.RedisUtil;

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
    @Resource
    private MySettingService mySettingService;
    /**
     * 服务对象
     */
    @Resource
    private MyFilesManageService myFilesManageService;

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

    @PostMapping("/user/signup")
    public ApiResponse<MyBaseInfo> userSignUp(@RequestBody MyBaseInfo mybaseInfo) {
        try {
            MyBaseInfo checkUser = new MyBaseInfo();
            checkUser.setEmail(mybaseInfo.getEmail());
            List<MyBaseInfo> baseInfoList = this.myBaseInfoService.queryList(checkUser);
            if (!CollectionUtils.isEmpty(baseInfoList)) {
                return ApiResponse.error("账号已存在", mybaseInfo);
            }
            mybaseInfo.setPassword(Md5Util.encodeByMd5(mybaseInfo.getPassword()));
            this.myBaseInfoService.insert(mybaseInfo);
            return ApiResponse.success(mybaseInfo);
        } catch (Exception e) {
            return ApiResponse.error("注册失败", mybaseInfo);
        }
    }

    @PostMapping("/user/signin")
    public ApiResponse<MyBaseInfo> userSignin(@RequestBody MyBaseInfo mybaseInfo) {
        try {
            MyBaseInfo checkUser = new MyBaseInfo();
            checkUser.setEmail(mybaseInfo.getEmail());
            checkUser.setPassword(Md5Util.encodeByMd5(mybaseInfo.getPassword()));
            List<MyBaseInfo> baseInfoList = this.myBaseInfoService.queryList(checkUser);
            if (!CollectionUtils.isEmpty(baseInfoList)) {
                mybaseInfo.setToken(jwtConfig.getToken(mybaseInfo.getEmail() + "##" + mybaseInfo.getPassword()));
                return ApiResponse.success(mybaseInfo);
            }
            return ApiResponse.error("用户未注册或密码不正确", mybaseInfo);
        } catch (Exception e) {
            return ApiResponse.error("注册失败", mybaseInfo);
        }
    }

    private Long getCurrentUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("authorization");
        Claims claim = jwtConfig.getTokenClaim(token);
        String subject = claim.getSubject();
        String userAccount = subject.split("##")[0];
        MyBaseInfo mybaseInfo = new MyBaseInfo();
        mybaseInfo.setEmail(userAccount);
        List<MyBaseInfo> userInfoList = myBaseInfoService.queryList(mybaseInfo);
        return userInfoList.get(0).getId();
    }

    private MyBaseInfo getLoginUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("authorization");
        Claims claim = jwtConfig.getTokenClaim(token);
        if (claim == null) {
            return null;
        }
        String subject = jwtConfig.getTokenClaim(token).getSubject();
        String email = subject.split("##")[0];
        MyBaseInfo mybaseInfo = new MyBaseInfo();
        mybaseInfo.setEmail(email);
        mybaseInfo.setPassword(subject.split("##")[1]);
        return mybaseInfo;
    }

    @PostMapping("/user/profile")
    public ApiResponse<MyBaseInfo> addUserProfile(@RequestBody MyBaseInfo mybaseInfo) {

        try {
            MyBaseInfo loginUser = this.getLoginUser();
            MyBaseInfo checkBaseInfo = new MyBaseInfo();
            checkBaseInfo.setEmail(loginUser.getEmail());
            List<MyBaseInfo> userInfoList = myBaseInfoService.queryList(checkBaseInfo);
            if (CollectionUtils.isEmpty(userInfoList)) {
                return ApiResponse.error("用户不存在", mybaseInfo);
            }
            mybaseInfo.setId(userInfoList.get(0).getId());
            this.myBaseInfoService.update(mybaseInfo);
        } catch (Exception e) {
            return ApiResponse.error("保存个人资料失败", mybaseInfo);
        }
        return ApiResponse.success(mybaseInfo);
    }

    @GetMapping("/user/profile")
    public ApiResponse<MyBaseInfo> getUsrProfile() {
        try {
            MyBaseInfo loginUser = this.getLoginUser();
            MyBaseInfo userProfileCond = new MyBaseInfo();
            userProfileCond.setEmail(loginUser.getEmail());
            List<MyBaseInfo> userInfoList = myBaseInfoService.queryList(userProfileCond);
            if (CollectionUtils.isEmpty(userInfoList)) {
                return ApiResponse.error("用户不存在", null);
            }
            return ApiResponse.success(userInfoList.get(0));
        } catch (Exception e) {
            return ApiResponse.error("读取个人资料失败", null);
        }
    }

    @PostMapping("/validLockPassowrd")
    public ApiResponse<MyBaseInfo> validLockPassowrd(@RequestBody MyBaseInfo mybaseInfo) {
        try {
            MyBaseInfo loginUser = this.getLoginUser();
            MyBaseInfo checkUserProfile = new MyBaseInfo();
            checkUserProfile.setEmail(loginUser.getEmail());
            checkUserProfile.setLockpassword(Md5Util.encodeByMd5(mybaseInfo.getPassword()));
            List<MyBaseInfo> userInfoList = myBaseInfoService.queryList(checkUserProfile);
            if (CollectionUtils.isEmpty(userInfoList)) {
                return ApiResponse.error("密码不正确", null);
            }
            return ApiResponse.success(mybaseInfo);
        } catch (Exception e) {
            return ApiResponse.error("验证启动密码失败", null);
        }
    }

    @PostMapping("/updatePassword")
    public ApiResponse<MyBaseInfo> updatePassword(@RequestBody MyBaseInfo mybaseInfo) {
        try {
            MyBaseInfo curBaseInfo = this.myBaseInfoService.queryById(this.getCurrentUserId());
            if (StringUtils.isEmpty(mybaseInfo.getNewpassword())) {
                return ApiResponse.error("请设置新密码", null);
            }
            String oldPassword = Md5Util.encodeByMd5(mybaseInfo.getOldpassword());
            if (!oldPassword.equalsIgnoreCase(mybaseInfo.getPassword())) {
                return ApiResponse.error("旧密码不正确", null);
            }
            curBaseInfo.setPassword(Md5Util.encodeByMd5(mybaseInfo.getNewpassword()));
            curBaseInfo.setLastUpdatedDate(new Date());
            this.myBaseInfoService.update(curBaseInfo);
            return ApiResponse.success(mybaseInfo);
        } catch (Exception e) {
            return ApiResponse.error("设置启动密码失败", null);
        }
    }

    @PostMapping("/setMaskPassowrd")
    public ApiResponse<MyBaseInfo> setMaskPassowrd(@RequestBody MyBaseInfo mybaseInfo) {
        try {
            MyBaseInfo curBaseInfo = this.myBaseInfoService.queryById(this.getCurrentUserId());
            if (StringUtils.isEmpty(mybaseInfo.getMaskpassword())) {
                return ApiResponse.error("请设置伪装密码", null);
            }
            curBaseInfo.setMaskpassword(Md5Util.encodeByMd5(mybaseInfo.getMaskpassword()));
            curBaseInfo.setLastUpdatedDate(new Date());
            this.myBaseInfoService.update(curBaseInfo);
            return ApiResponse.success(mybaseInfo);
        } catch (Exception e) {
            return ApiResponse.error("设置伪装密码失败", null);
        }
    }

    @PostMapping("/validMaskPassowrd")
    public ApiResponse<MyBaseInfo> validMaskPassowrd(@RequestBody MyBaseInfo mybaseInfo) {
        try {
            MyBaseInfo loginUser = this.getLoginUser();
            MyBaseInfo checkMaskPassword = new MyBaseInfo();
            checkMaskPassword.setEmail(loginUser.getEmail());
            checkMaskPassword.setMaskpassword(Md5Util.encodeByMd5(mybaseInfo.getPassword()));
            List<MyBaseInfo> userInfoList = myBaseInfoService.queryList(checkMaskPassword);
            if (CollectionUtils.isEmpty(userInfoList)) {
                return ApiResponse.error("密码不正确", null);
            }
            return ApiResponse.success(mybaseInfo);
        } catch (Exception e) {
            return ApiResponse.error("设置伪装密码失败", null);
        }
    }

    @PostMapping("/setLockPassowrd")
    public ApiResponse<MyBaseInfo> setLockPassowrd(@RequestBody MyBaseInfo mybaseInfo) {
        try {
            MyBaseInfo curBaseInfo = this.myBaseInfoService.queryById(this.getCurrentUserId());
            if (StringUtils.isEmpty(mybaseInfo.getPassword())) {
                return ApiResponse.error("请设置密码", null);
            }
            if (StringUtils.isEmpty(mybaseInfo.getLockpassword())) {
                return ApiResponse.error("请设置启动密码", null);
            }
            curBaseInfo.setPassword(Md5Util.encodeByMd5(mybaseInfo.getPassword()));
            curBaseInfo.setLockpassword(Md5Util.encodeByMd5(mybaseInfo.getLockpassword()));
            curBaseInfo.setLastUpdatedDate(new Date());
            this.myBaseInfoService.update(curBaseInfo);
            return ApiResponse.success(mybaseInfo);
        } catch (Exception e) {
            return ApiResponse.error("更新密码失败", null);
        }
    }

    @GetMapping("/public/lostpwd")
    public ApiResponse<Map> lostPassword() {
        Map<String, Long> result = new HashMap<>();
        result.put("ky", Math.round((Math.random() + 1) * 1000));
        // RedisUtil.redisPut("superbing09", "你好！是、啊");
        // String s= RedisUtil.redisGet("superbing09");
        return ApiResponse.success(result);
    }


    @PostMapping("/user/setting")
    public ApiResponse<MySetting> addUserSetting(@RequestBody MySetting mysetting) {
        mysetting.setUserId(this.getCurrentUserId());
        mysetting.setCreatedBy(mysetting.getUserId());
        return ApiResponse.success(this.mySettingService.insert(mysetting));
    }

    @GetMapping("/user/setting")
    public ApiResponse<MySetting> addUserSetting() {
        MySetting mySetting = new MySetting();
        mySetting.setUserId(this.getCurrentUserId());
        List<MySetting> mySettingList = this.mySettingService.queryList(mySetting);
        if (CollectionUtils.isEmpty(mySettingList)) {
            return ApiResponse.error("找不到用户设置", null);
        }
        return ApiResponse.success(mySettingList.get(0));
    }

    /**
     * 新增数据
     *
     * @param myDiary 实体
     * @return 新增结果
     */
    @PostMapping("/diary")
    public ApiResponse<MyDiary> addDiary(@RequestBody MyDiary myDiary) {
        Long curUserId = this.getCurrentUserId();
        myDiary.setCreatedBy(curUserId);
        return ApiResponse.success(this.myDiaryService.insert(myDiary));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/diary/{id}")
    public ApiResponse<Boolean> deleteDiaryById(@PathVariable("id") Long id) {
        return ApiResponse.success(this.myDiaryService.deleteById(id));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/diary/{id}")
    public ApiResponse<MyDiary> queryDiaryById(@PathVariable("id") Long id) {
        return ApiResponse.success(this.myDiaryService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param myAlbum 实体
     * @return 新增结果
     */
    @PostMapping("/album")
    public ApiResponse<MyAlbum> addAlbum(@RequestBody MyAlbum myAlbum) {
        myAlbum.setCreatedBy(this.getCurrentUserId());
        return ApiResponse.success(this.myAlbumService.insert(myAlbum));
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

    @GetMapping("/album/{id}")
    public ApiResponse<Page<MyFilesManage>> selectAllPageQuery(@PathVariable("id") Long id, @RequestParam("page") int pageNum,
                                                               @RequestParam("limit") int pageSize) {
        MyFilesManage filesManage = new MyFilesManage();
        filesManage.setSourceId(id);
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNum - 1, pageSize, sort);
        Page<MyFilesManage> list = myFilesManageService.queryByPage(filesManage, pageable);

        return ApiResponse.success(list);
    }

    @GetMapping("/album/list")
    public ApiResponse<Page<MyAlbum>> selectAlbumList(@RequestParam("page") int pageNum,
                                                      @RequestParam("limit") int pageSize) {
        MyAlbum album = new MyAlbum();
        album.setCreatedBy(this.getCurrentUserId());
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNum - 1, pageSize, sort);
        Page<MyAlbum> list = myAlbumService.queryByPage(album, pageable);
        return ApiResponse.success(list);
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

    @PostMapping("/file")
    public ApiResponse<MyFilesManage> uploadPic(@RequestBody MyFilesManage myFilesManage) {
        Long userId = this.getCurrentUserId();
        if (!CollectionUtils.isEmpty(myFilesManage.getUrl())) {
            for (String url : myFilesManage.getUrl()) {
                myFilesManage.setCreatedBy(userId);
                myFilesManage.setSourceId(myFilesManage.getAlbum());
                myFilesManage.setSourceType("album");
                myFilesManage.setFileUrl(url);
                this.myFilesManageService.insert(myFilesManage);
            }

        }
        return ApiResponse.success(myFilesManage);
    }

    @PostMapping("/snaphost")
    public ApiResponse<MyFilesManage> snaphost(@RequestBody MyFilesManage myFilesManage) {
        Long userId = this.getCurrentUserId();
        myFilesManage.setSourceType("snaphost");
        myFilesManage.setCreatedBy(userId);
        this.myFilesManageService.insert(myFilesManage);
        return ApiResponse.success(myFilesManage);
    }

    @PostMapping("/album/download/{album}")
    public ApiResponse<Object> downloadFile(@PathVariable("album") Long album, @RequestBody MyFilesManage myFilesManage) {
        myFilesManage.setSourceId(album);
        return ApiResponse.success(this.myFilesManageService.queryList(myFilesManage));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/album/{album}/file/{id}")
    public ApiResponse<Boolean> deleteAlbumById(@PathVariable("album") Long album, @PathVariable("id") Long id) {
        MyFilesManage deleteCond = new MyFilesManage();
        deleteCond.setSourceId(album);
        deleteCond.setId(id);
        this.myFilesManageService.deleteByCond(deleteCond);
        return ApiResponse.success(null);
    }

    /**
     * 移动相册
     *
     * @return
     */
    @PostMapping("/album/move")
    public ApiResponse<List<VipRights>> albumMove(@RequestBody MyFilesManage myFilesManage) {
        myFilesManage.setLastUpdatedBy(this.getCurrentUserId());
        myFilesManage.setLastUpdatedDate(new Date());
        this.myFilesManageService.albumMove(myFilesManage);
        return ApiResponse.success(null);
    }
}

