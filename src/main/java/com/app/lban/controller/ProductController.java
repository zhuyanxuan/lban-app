package com.app.lban.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.lban.model.Product;
import com.app.lban.service.impl.ProductService;
import com.app.lban.utils.ResultJSON;
import com.app.lban.utils.SharedJedisUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Blob;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private Logger log = Logger.getLogger(ProductController.class);

    @Autowired
    private SharedJedisUtils sharedJedisUtils;

    @Autowired
    private ProductService productService;

    @RequestMapping("productList")
    public ModelAndView productList(HttpServletRequest request, ModelAndView mv){
        String section = request.getParameter("section");
        mv.setViewName("productList");
        return mv;
    }

    @RequestMapping("productAdd")
    public ModelAndView productAdd(HttpServletRequest request, ModelAndView mv){

        mv.setViewName("productAdd");
        return mv;
    }

    @RequestMapping("doListProduct")
    @ResponseBody
    public JSONObject doListProduct(@ModelAttribute("product") Product product){
        JSONArray data = new JSONArray();
        List<Product> productList = productService.selectAllProduct(product);
        data = JSONArray.parseArray(productList.toString());
        return new ResultJSON(0, "请求成功", data);
    }

    @RequestMapping("doAddProduct")
    @ResponseBody
    public JSONObject doAddProduct(@ModelAttribute("product") Product product, ModelAndView mv){
        //TODO 获取当前操作员
        product.setCreateBy("admin");
        product.setCreateDate(new Date());
        product.setUpdateBy("admin");
        product.setUpdateDate(new Date());
        product.setValid("Y");
        productService.addProduct(product);
        return new ResultJSON(0, "请求成功");
    }

    @RequestMapping("doUpdateProduct")
    @ResponseBody
    public JSONObject doUpdateProduct(@ModelAttribute("product") Product product, ModelAndView mv){
        product.setCreateBy("admin");
        product.setCreateDate(new Date());
        product.setUpdateBy("admin");
        product.setUpdateDate(new Date());
        product.setValid("Y");
        productService.updateProduct(product);
        return new ResultJSON(0, "请求成功");
    }

    @RequestMapping("doDeleteProduct")
    @ResponseBody
    public JSONObject doDeleteProduct(@ModelAttribute("product") Product product, ModelAndView mv){
        product.setUpdateBy("admin");
        product.setUpdateDate(new Date());
        product.setValid("N");
        productService.deleteProduct(product);
        return new ResultJSON(0, "请求成功");
    }
}
