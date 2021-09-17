package com.bluemsun.controller;

import com.bluemsun.entity.Good;
import com.bluemsun.entity.Page;
import com.bluemsun.service.impl.GoodManagerServiceImpl;
import com.bluemsun.service.impl.PageManagerServiceImpl;
import com.bluemsun.util.JSONUtils;
import com.bluemsun.util.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class GoodController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println("get:" + uri);
        switch (uri) {
            case "/good/test":
                doTest(request, response);
                break;
            case "/good/search": //商品搜索
                doSearch(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println(uri);
        switch (uri) {
            case "/good/addition":  //商品添加
                addGood(request, response);
                break;
            case "/good/upload":    //图片上传
                doUpload(request, response);
                break;
            case "/good/delete":    //商品删除
                deleteGood(request, response);
                break;
            case "/good/update":    //商品更新
                doUpdateGood(request, response);
                break;
        }
    }

    /**
     * 添加商品
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void addGood(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Good good;
        Good good1;
        Map<String, Object> map = new HashMap<>();
        GoodManagerServiceImpl gms = new GoodManagerServiceImpl();
        String jsonString = JSONUtils.getJson(request);
        good = JSONUtils.fromJson(jsonString, Good.class);
        System.out.println(good);
        if ((good1 = gms.insertGood(good)) != null) {
            map.put("msg", "add good successfully");
            map.put("status", Status.SUCCESS.getCode());
            map.put("good", good1);
        } else {
            map.put("msg", "add good failed");
            map.put("status", Status.FAILED.getCode());
        }
        JSONUtils.parseJson(map, response);
    }

    /**
     * 上传商品图片
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        Map<String, Object> map = new HashMap<>();
        String folderString = "images";
        String serverPath = request.getServletContext().getRealPath(folderString);
        GoodManagerServiceImpl uploadService = new GoodManagerServiceImpl();
        String filename = uploadService.fileStore(part, serverPath);
        String projectServerPath = request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath() + "/" + folderString + "/"
                + filename;
        map.put("status", Status.SUCCESS.getCode());
        map.put("imageUrl", projectServerPath);
        JSONUtils.parseJson(map, response);
    }

    /**
     * 删除商品
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void deleteGood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Good good;
        Map<String, Object> map = new HashMap<>();
        GoodManagerServiceImpl gms = new GoodManagerServiceImpl();
        String jsonString = JSONUtils.getJson(request);
        good = JSONUtils.fromJson(jsonString, Good.class);
        System.out.println(good);
        if (gms.delGood(good.getGoodID())==Status.OP_SUCCESS.getCode()) {
            map.put("msg", "delete good successfully");
            map.put("status", Status.SUCCESS.getCode());
        } else {
            map.put("msg", "delete good failed");
            map.put("status", Status.FAILED.getCode());
        }
        JSONUtils.parseJson(map, response);
    }

    /**
     * 测试
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String s = request.getParameter("s");
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "test successfully");
        map.put("status", Status.SUCCESS.getCode());
        JSONUtils.parseJson(map, response);
    }

    /**
     * 搜索商品
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //request.getParameter("keyword");
        int curPage = 1;
        int pageSize = 4;
        String keyword = null;
        Map<String, Object> map = new HashMap<>();
        curPage = Integer.parseInt(request.getParameter("cp"));
        pageSize = Integer.parseInt(request.getParameter("ps"));
        keyword = request.getParameter("k");
        PageManagerServiceImpl pageManagerService = new PageManagerServiceImpl();
        Page<Good> page1 = pageManagerService.getGood(curPage, pageSize, keyword);
        map.put("status", Status.SUCCESS.getCode());
        map.put("page", page1);
        JSONUtils.parseJson(map, response);
    }

    /**
     * 修改商品信息
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException 输入输出异常(Json工具类)
     */
    private void doUpdateGood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        GoodManagerServiceImpl goodManagerService = new GoodManagerServiceImpl();
        String jsonString = JSONUtils.getJson(request);
        Good good = JSONUtils.fromJson(jsonString, Good.class);
        if (goodManagerService.setGood(good)==Status.OP_SUCCESS.getCode()) {
            map.put("msg", "update good successfully");
            map.put("status", Status.SUCCESS.getCode());
        } else {
            map.put("msg", "update good failed");
            map.put("status", Status.FAILED.getCode());
        }
        JSONUtils.parseJson(map, response);
    }
}
