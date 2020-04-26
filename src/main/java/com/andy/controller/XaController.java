package com.andy.controller;

import com.andy.model.Order;
import com.andy.service.Order0Service;
import com.andy.service.Order1Service;
import com.andy.service.XaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andy
 * @date 2020/4/26 9:20
 */
@RequestMapping("xa")
@RestController
public class XaController {

    @Autowired
    private XaService xaService;

    @GetMapping("test1")
    public Object test1(){
        xaService.testXa();
        return 200;
    }
}
