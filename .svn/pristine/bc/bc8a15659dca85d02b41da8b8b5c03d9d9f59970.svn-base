package com.heli.oa.sunshine.controller;

import com.heli.oa.common.util.CodeUtils;
import com.heli.oa.sunshine.dao.SunshineDao;
import com.heli.oa.sunshine.entity.Sunshine;
import com.heli.oa.sunshine.entity.SunshineAction;
import com.heli.oa.sunshine.service.SunshineActionService;
import com.heli.oa.sunshine.service.SunshineService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * @Author:核利-白驹
 * @Descriction:
 * @Date:Created in 17:23 2018/11/6
 */
@Slf4j
@Data
@Controller
public class SunshineController {
    @Autowired
    private SunshineDao sunshineDao;
    @Autowired
    private SunshineService sunshineService;
    @Autowired
    private SunshineActionService sunshineActionService;

    @ResponseBody
    @RequestMapping("/addSunshine")
    public CodeUtils addSunshine(Sunshine s) throws IOException {
        CodeUtils codeUtils = new CodeUtils();

        sunshineService.addSunshine(s);
        codeUtils.setCode(200);

        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/minusSunshine")
    public CodeUtils minusSunshine(Sunshine s) throws IOException {
        CodeUtils codeUtils = new CodeUtils();

        sunshineService.minusSunshine(s);
        codeUtils.setCode(200);
         
        return codeUtils;
    }


    @ResponseBody
    @RequestMapping("/minusSunshineMoney")
    public CodeUtils minusSunshineMoney(Sunshine s){
        CodeUtils codeUtils = new CodeUtils();

        //需要判断取现值是否多余当前可取现总额，前端也有校验。
        Double money = s.getSunshineMoney();
        Double sum = sunshineService.sumMoney().get(1);
        if(money > sum){
            codeUtils.setCode(500);
        }else {
            sunshineService.minusMoney(s);
            codeUtils.setCode(200);
        }

        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/listSunshineByUserId")
    public CodeUtils listSunshineByUserId(Integer userId){
        CodeUtils codeUtils = new CodeUtils();

        List<Sunshine> list = sunshineService.listSunshineByUserId(userId);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/listPlusSunshine")
    public CodeUtils listPlusSunshine(){
        CodeUtils codeUtils = new CodeUtils();

        List<Sunshine> list = sunshineService.listPlusSunshine();
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/listMinusSunshine")
    public CodeUtils listMinusSunshine(){
        CodeUtils codeUtils = new CodeUtils();

        List<Sunshine> list = sunshineService.listMinusSunshine();
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/sumSunshineMoney")
    public CodeUtils sumSunshineMoney(){
        CodeUtils codeUtils = new CodeUtils();
         
            List<Double>  sumMoney = sunshineService.sumMoney();
            codeUtils.setCode(200);
            codeUtils.setParamList(sumMoney);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/listSunshineMoney")
    public CodeUtils listSunshineMoney(){
        CodeUtils codeUtils = new CodeUtils();

        List<Sunshine> list = sunshineService.listSunshineMoney();
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/listSunshineAction")
    public CodeUtils listSunshineAction(){
        CodeUtils codeUtils = new CodeUtils();

        List<SunshineAction> list = sunshineActionService.listSunshineAction();
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/deletePlusSunshine")
    public CodeUtils deletePlusSunshine(Sunshine s){
        CodeUtils codeUtils = new CodeUtils();

        sunshineService.delPlusSunshine(s);
        codeUtils.setCode(200);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/deleteMinusSunshine")
    public CodeUtils deleteMinusSunshine(Sunshine s){
        CodeUtils codeUtils = new CodeUtils();

        sunshineService.delMinusSunshine(s);
        codeUtils.setCode(200);
         
        return codeUtils;
    }
    @ResponseBody
    @RequestMapping("/deleteMinusMoneySunshine")
    public CodeUtils deleteMinusMoneySunshine(Sunshine s){
        CodeUtils codeUtils = new CodeUtils();

        sunshineService.delMinusMoneySunshine(s);
        codeUtils.setCode(200);
         
        return codeUtils;
    }

    @ResponseBody
    @RequestMapping("/updateSunshineRecord")
    public CodeUtils updateSunshineRecord(Sunshine s){
        CodeUtils codeUtils = new CodeUtils();

        sunshineService.updateSunshineRecord(s);
        codeUtils.setCode(200);
         
        return codeUtils;
    }
}
