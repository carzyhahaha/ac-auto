package com.sy.acauto.controller;

import com.sy.acauto.common.ApiResponse;
import com.sy.acauto.vo.AddWordSetBodyVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/wordSet")
public class ACAutoController {

    @PostMapping("/add")
    public ApiResponse addWordSet(AddWordSetBodyVO body) {


        return ApiResponse.success();
    }


    @PostMapping("/uploadFiles")
    public ApiResponse upload(List<MultipartFile> files) {


        return ApiResponse.success();
    }

}
