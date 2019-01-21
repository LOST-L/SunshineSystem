package com.heli.oa.plan.service.impl;

import com.heli.oa.common.entity.CountUser;
import com.heli.oa.plan.entity.MyShop;
import com.heli.oa.plan.dao.MyShopMapper;
import com.heli.oa.plan.service.MyShopService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MyShopServiceImpl implements MyShopService {
    @Autowired
    private MyShopMapper msm;

    @Override
    public int deleteByPrimaryKey(Integer shopId) {
        return msm.deleteByPrimaryKey(shopId);
    }

    @Override
    public int insert(MyShop record) {
        return msm.insert(record);
    }

    @Override
    public int insertSelective(MyShop record) {
        return msm.insertSelective(record);
    }

    @Override
    public MyShop selectByPrimaryKey(Integer shopId) {
        return msm.selectByPrimaryKey(shopId);
    }

    @Override
    public int updateByPrimaryKeySelective(MyShop record) {
        return msm.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MyShop record) {
        return msm.updateByPrimaryKey(record);
    }

    @Override
    public List<MyShop> selectByUserId(Integer userId) {
        return msm.selectByUserId(userId);
    }

    @Override
    public boolean bindingShop(String url, Integer userId, String linkType) throws Exception {
        MyShop myShop = new MyShop();
        myShop.setShopTitleCode(UUID.randomUUID().toString().replaceAll("-","").substring(0,16));
        myShop.setShopUserId(userId);

        int edit = 0;

        if(linkType.equals("0")){
            Document docShopHref = Jsoup.connect(url).maxBodySize(0).timeout(80000).get();
            String shop_id = docShopHref.getElementById("shop_id").val();
            Document showLicence = Jsoup.connect("https://mall.jd.com/showLicence-" + shop_id + ".html").maxBodySize(0).timeout(80000).get();
            Element logoEle = showLicence.getElementsByClass("jHeader").get(0).getElementsByClass("jLogo").get(0);


            myShop.setShopName(logoEle.getElementsByTag("em").text());
            myShop.setShopJdId(shop_id);
            myShop.setShopLogo(logoEle.getElementsByTag("img").attr("src"));
            myShop.setShopBinding("1");
            edit = msm.insertSelective(myShop);
        } else {
            Document docShopHref = Jsoup.connect(url).maxBodySize(0).timeout(80000).get();
            String shop_id = docShopHref.getElementById("shop_id").val();

            String htmlTitle = docShopHref.title();
            myShop.setShopName(htmlTitle);
            myShop.setShopJdId(shop_id);
            myShop.setShopLogo("default");
            myShop.setShopBinding("1");
            edit = msm.insertSelective(myShop);
        }
        if(edit != 0){
            return true;
        } else {
            return false;
        }
    }
}
