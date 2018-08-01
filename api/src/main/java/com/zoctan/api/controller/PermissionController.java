package com.zoctan.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.Permission;
import com.zoctan.api.service.PermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/06/09
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @PreAuthorize("hasAuthority('role:list')")
    @GetMapping
    public Result listResourcePermission(@RequestParam(defaultValue = "0") final Integer page,
                                         @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<JSONObject> list = this.permissionService.findAllResourcePermission();
        //noinspection unchecked
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PreAuthorize("hasAuthority('permission:list')")
    @GetMapping("listPermission")
    public Result listPermission(@RequestParam(defaultValue = "0") final Integer page,
                                         @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Permission> list = this.permissionService.findAll();
        //noinspection unchecked
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PreAuthorize("hasAuthority('permission:add')")
    @PostMapping
    public Result add(@RequestBody final Permission permission) {
        this.permissionService.save(permission);
        return ResultGenerator.genOkResult();
    }

    @PreAuthorize("hasAuthority('permission:delete')")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.permissionService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PreAuthorize("hasAuthority('permission:update')")
    @PutMapping
    public Result update(@RequestBody final Permission permission) {
        this.permissionService.update(permission);
        return ResultGenerator.genOkResult();
    }

}
