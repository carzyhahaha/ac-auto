package com.sy.acauto.controller;

import com.sy.acauto.common.ApiResponse;
import com.sy.acauto.vo.AddWordSetBodyVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wordSet")
public class ACAutoController {

    @PostMapping("/add")
    public ApiResponse addWordSet(AddWordSetBodyVO body) {


        return ApiResponse.success();
    }

}
