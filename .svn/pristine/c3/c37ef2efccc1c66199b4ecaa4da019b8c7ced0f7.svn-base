package com.heli.oa.plan.controller;

import com.heli.oa.common.entity.CountUser;
import com.heli.oa.common.util.CodeUtils;
import com.heli.oa.common.util.ReturnUtils;
import com.heli.oa.plan.entity.MyShop;
import com.heli.oa.plan.service.MyShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "plan/shop")
public class MyShopController {

    @Autowired
    private MyShopService mssi;

    @RequestMapping(value = "/getUserShop")
    private void getUserShop(Integer userId, HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            List<MyShop> myShops = mssi.selectByUserId(userId);
            code.setCode(200);
            code.setParamObject(myShops.size());
        } catch (Exception e) {
            code.setCode(505);
            code.setParamObject("系统发生错误，请联系管理员！");
        } finally {
            ReturnUtils.reutnrJSON(response,ReturnUtils.stringToJson(code));
        }
    }

    @RequestMapping(value = "/bindingShop", method = RequestMethod.POST)
    private void bindingShop(String url, Integer userId, String linkType, HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            List<MyShop> myShops = mssi.selectByUserId(userId);
            if(myShops.size() < 12){
                boolean bindingShopFlag = mssi.bindingShop(url, userId, linkType);
                if(bindingShopFlag){
                    code.setCode(200);
                    code.setParamObject("店铺绑定成功！");
                } else {
                    code.setCode(501);
                    code.setParamObject("店铺绑定失败，请检查标题码是否已加入对应链接商品中！");
                }
            } else {
                code.setCode(501);
                code.setParamObject("店铺绑定数量超出限制，一个账号最多可绑定12个店铺！");
            }
        } catch (Exception e) {
            code.setCode(505);
            code.setParamObject("系统发生错误，请联系管理员！");
        } finally {
            ReturnUtils.reutnrJSON(response,ReturnUtils.stringToJson(code));
        }
    }

}
