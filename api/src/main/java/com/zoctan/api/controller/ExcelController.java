package com.zoctan.api.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.Article;
import com.zoctan.api.util.DateUtilsZXW;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by zxw on 2018/7/26 0026.
 */
@RestController
@RequestMapping("/article")
public class ExcelController {


    @PreAuthorize("hasAuthority('article:list')")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final ArrayList<Article> list = new ArrayList<>();
        Article article1 = new Article(0, DateUtilsZXW.getCurrentTime(), "author0", "reviewer0",  "title0",  "content_short0",  "content0",  0.0f, DateUtilsZXW.getCurrentTime(), false, 0,  "image_uri");
        Article article2 = new Article(0, DateUtilsZXW.getCurrentTime(), "author0", "reviewer0",  "title0",  "content_short0",  "content0",  0.0f, DateUtilsZXW.getCurrentTime(), false, 0,  "image_uri");
        Article article3 = new Article(0, DateUtilsZXW.getCurrentTime(), "author0", "reviewer0",  "title0",  "content_short0",  "content0",  0.0f, DateUtilsZXW.getCurrentTime(), false, 0,  "image_uri");
        Article article4 = new Article(0, DateUtilsZXW.getCurrentTime(), "author0", "reviewer0",  "title0",  "content_short0",  "content0",  0.0f, DateUtilsZXW.getCurrentTime(), false, 0,  "image_uri");

        list.add(article1);
        list.add(article2);
        list.add(article3);
        list.add(article4);
        //noinspection unchecked
        final PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genOkResult(pageInfo);
    }
}
