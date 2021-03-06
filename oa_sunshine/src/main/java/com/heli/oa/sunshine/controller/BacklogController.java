package com.heli.oa.sunshine.controller;

import com.heli.oa.common.util.CodeUtils;
import com.heli.oa.sunshine.entity.Backlog;
import com.heli.oa.sunshine.service.BacklogService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

/**
 *@Author: 白驹
 *@Date: 2019/1/10 14:08
 *@Description:
 */
@Slf4j
@Data
@Controller
public class BacklogController {

    @Autowired
    private BacklogService backService;

    /**
     *@Author: 白驹 on 2019/1/10 14:10
     *@param:
     *@return:
     *@Description:
     */
    @ResponseBody
    @RequestMapping(value = "/addBacklog")
    public CodeUtils addBacklog(Backlog backlog) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        backService.addBacklog(backlog);
        codeUtils.setCode(200);
        
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:10
     *@param:
     *@return:
     *@Description:
     */
    @ResponseBody
    @RequestMapping(value = "/deleteBacklog")
    public CodeUtils deleteBacklog(Backlog backlog) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        backService.deleteBacklog(backlog);
        codeUtils.setCode(200);
        
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:09
     *@param:
     *@return:
     *@Description:
     */
    @ResponseBody
    @RequestMapping(value = "/insertBacklog")
    public CodeUtils insertBacklog(Backlog backlog) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        backService.insertBacklog(backlog);
        codeUtils.setCode(200);    
   
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:09
     *@param:
     *@return:
     *@Description:
     */
    @ResponseBody
    @RequestMapping(value = "/listBacklogByPriority")
    public CodeUtils listBacklogByPriority(Backlog backlog) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        List<Backlog> list = backService.listBacklogByPriority(backlog);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);

        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:09
     *@param:
     *@return:
     *@Description:
     */
    @ResponseBody
    @RequestMapping(value = "/listBacklogByStatus")
    public CodeUtils listBacklogByStatus(Backlog backlog) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        List<Backlog> list = backService.listByStatus(backlog);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:09
     *@param:
     *@return:
     *@Description:
     */
    @ResponseBody
    @RequestMapping(value = "/listSetTimeBacklogByUserId")
    public CodeUtils listSetTimeBacklogByUserId(Backlog backlog) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        List<Backlog> list = backService.listSetTimeBacklogByUserId(backlog);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:09
     *@param:
     *@return:
     *@Description:
     */
    @ResponseBody
    @RequestMapping(value = "/searchDoneBacklog")
    public CodeUtils searchDoneBacklog(Backlog backlog) throws ParseException {
        CodeUtils codeUtils = new CodeUtils();

        List<Backlog> list = backService.searchDone(backlog);
        codeUtils.setCode(200);
        codeUtils.setParamList(list);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:08
     *@param:
     *@return:
     *@Description:
     */

    @ResponseBody
    @RequestMapping(value = "/setBacklogTime")
    public CodeUtils setBacklogTime(Backlog backlog) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        String result = backService.setTime(backlog);
        if("定时成功".equals(result)){
            codeUtils.setCode(200);
        }else if("时间冲突".equals(result)){
            codeUtils.setCode(500);
        }

        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:08
     *@param:
     *@return:
     *@Description:
     */
    @ResponseBody
    @RequestMapping(value = "/cancelBacklogTime")
    public CodeUtils cancelBacklogTime(Backlog backlog) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        backService.cancelTime(backlog);
        codeUtils.setCode(200);
         
        return codeUtils;
    }

    /**
     *@Author: 白驹 on 2019/1/10 14:08
     *@param:
     *@return:
     *@Description:
     */
    @ResponseBody
    @RequestMapping(value = "/overBacklog")
    public CodeUtils overBacklog(Backlog backlog) throws Exception{
        CodeUtils codeUtils = new CodeUtils();

        backService.overTime(backlog);
        codeUtils.setCode(200);
         
        return codeUtils;
    }

}
