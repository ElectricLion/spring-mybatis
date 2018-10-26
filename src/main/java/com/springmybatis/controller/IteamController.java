package com.springmybatis.controller;


import com.springmybatis.config.ReadOnlyConnection;
import com.springmybatis.mapper.IteamMapper;
import com.springmybatis.pojo.Iteam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:tanghui
 * @since 1.0
 */
@Controller
@RequestMapping("/item")
public class IteamController {
    @Autowired
    private IteamMapper iteamMapper;

    @RequestMapping("/all")
    @ReadOnlyConnection
    @ResponseBody
    public Object getIteam() {

        return iteamMapper.select();
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(String id) {
        Iteam iteam = new Iteam();
        iteam.setId(Integer.valueOf(id));
        iteamMapper.add(iteam);
        return iteam;
    }

}
