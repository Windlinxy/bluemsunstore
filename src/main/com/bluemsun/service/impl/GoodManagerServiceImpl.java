package com.bluemsun.service.impl;

import com.bluemsun.dao.GoodDao;
import com.bluemsun.entity.Good;
import com.bluemsun.entity.Page;
import com.bluemsun.service.GoodManagerService;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

public class GoodManagerServiceImpl implements GoodManagerService {
    @Override
    public Good insertGood(Good good) {
        Good good1;
        GoodDao goodDao = new GoodDao();
        good1 = goodDao.insertGood(good);
        return good1;
    }

    @Override
    public int delGood(int goodID) {
        GoodDao goodDao = new GoodDao();
        int jud= 0;
        jud = goodDao.deleteGood(goodID);
        return jud;
    }

    @Override
    public int setGood(Good good) {
        GoodDao goodDao = new GoodDao();
        int jud = 0;
        jud = goodDao.updateGood(good);
        return jud;
    }

    @Override
    public String fileStore(Part part, String serverPath) {
        // 获取上传的文件名扩展名
        String disposition = part.getSubmittedFileName();
        String suffix = disposition.substring(disposition.lastIndexOf("."));

        // 随机的生成uuid，作为文件名的一部分。 加上刚才获取到的后缀作为最终文件名。
        String uuid = UUID.randomUUID() + "";
        String filename = uuid.substring(0, 13) + suffix;
        System.out.println("-------------------------------------");
        System.out.println("文件名： " + filename);
        System.out.println("文件绝对路径： " + serverPath);
        System.out.println("______________________________________");
        //不存在文件夹则新建一个
        File fileDisk = new File(serverPath);
        if (!fileDisk.exists()) {
            fileDisk.mkdir();
        }

        String fileParts = serverPath + "/" + filename;

        try {
            part.write(fileParts);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return filename;
    }

}
