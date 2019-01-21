package com.heli.oa.plan.controller;

import com.heli.oa.common.util.CodeUtils;
import com.heli.oa.common.util.PageTable;
import com.heli.oa.common.util.ReturnUtils;
import com.heli.oa.plan.entity.Category;
import com.heli.oa.plan.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "plan/category")
public class CategoryController {
    @Autowired
    private CategoryService csi;

    @ResponseBody
    @RequestMapping(value = "getInfoCategory", method = RequestMethod.GET)
    public CodeUtils getInfoCategory(Integer id, HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            Category category = csi.selectByPrimaryKey(id);
            code.setCode(200);
            code.setParamObject(category);
        } catch (Exception e) {
            // e.printStackTrace();
            log.info("查询类目失败，"+e.getMessage());
            code.setCode(505);
            code.setParamObject("系统出现异常，请立即联系管理员！");
        }
        return code;
    }

    @ResponseBody
    @RequestMapping(value = "addCategory", method = RequestMethod.POST)
    public CodeUtils addCategory(Category c, HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            if(c.getCmeta().equals("[]")){
                c.setCmeta("");
            }
            Integer edit = csi.insertSelective(c);
            if(edit > 0){
                code.setCode(200);
                code.setParamObject("新增成功！");
            } else {
                code.setCode(501);
                code.setParamObject("新增失败！");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            log.info("新增类目失败，"+e.getMessage());
            code.setCode(505);
            code.setParamObject("系统出现异常，请立即联系管理员！");
        }
        return code;
    }

    @ResponseBody
    @RequestMapping(value = "editCategory", method = RequestMethod.POST)
    public CodeUtils editCategory(String editInfo, Category c, HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            if(editInfo.equals("[]")){
                editInfo = "";
            }
            c.setCmeta(editInfo);
            Integer edit = csi.updateByPrimaryKeySelective(c);
            if(edit > 0){
                code.setCode(200);
                code.setParamObject("修改成功！");
            } else {
                code.setCode(501);
                code.setParamObject("修改失败！");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            log.info("修改类目失败，"+e.getMessage());
            code.setCode(505);
            code.setParamObject("系统出现异常，请立即联系管理员！");
        }
        return code;
    }

    @ResponseBody
    @RequestMapping(value = "getListCategory", method = RequestMethod.GET)
    public PageTable getListCategory(Category c, HttpServletResponse response){
        PageTable pageTable = new PageTable();
        try {
            Integer total = csi.selectAllCategoryTotal(c);
            pageTable.setCount(total);
            if(total != 0){
                c.setPage((c.getPage() - 1) * c.getLimit());
                List<Category> categories = csi.selectAllCategory(c);
                pageTable.setCode(0);
                pageTable.setData(categories);
            } else {
                pageTable.setCode(0);
                pageTable.setData("暂无数据");
            }
        } catch (Exception e) {
            log.info("加载类目失败，"+e.getMessage());
            pageTable.setCode(505);
            pageTable.setData("系统出现异常，请立即联系管理员！");
        }

        return pageTable;
    }

    @ResponseBody
    @RequestMapping(value = "delCategory", method = RequestMethod.POST)
    public CodeUtils delCategory(Integer id, Integer wid,HttpServletResponse response){
        CodeUtils code = new CodeUtils();
        try {
            List<Category> categories = csi.selectCategoryPidByWid(wid);
            Category c = new Category();
            c.setId(id);
            categories.add(c);
            Integer integer = csi.delListCategory(categories);
            if(integer > 0 ){
                code.setCode(200);
                code.setParamObject("删除成功");
            }else{
                code.setCode(501);
                code.setParamObject("删除失败");
            }
        } catch (Exception e) {
            log.info("删除类目失败，"+e.getMessage());
            code.setCode(505);
            code.setParamObject("系统出现异常，请立即联系管理员！");
        }
        return code;
    }

}
