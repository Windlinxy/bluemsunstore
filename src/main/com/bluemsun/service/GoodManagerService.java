package com.bluemsun.service;

import com.bluemsun.entity.Good;

import javax.servlet.http.Part;

public interface GoodManagerService {
    /**
     * 添加商品
     *
     * @param good 商品
     * @return 商品
     */
    Good insertGood(Good good);

    /**
     * 删除商品
     *
     * @param goodID 商品id
     * @return 执行顺利与否
     */
    int delGood(int goodID);

    /**
     * 修改商品
     *
     * @param good 商品
     * @return 执行顺利与否
     */
    int setGood(Good good);

    /**
     * 文件上传
     *
     * @param part       数据源
     * @param serverPath 绝对路径
     * @return 文件名
     */
    String fileStore(Part part, String serverPath);
}
