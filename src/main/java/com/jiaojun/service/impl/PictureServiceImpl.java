package com.jiaojun.service.impl;

import com.jiaojun.dao.PictureDao;
import com.jiaojun.entity.Picture;
import com.jiaojun.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 照片墙业务层接口实现类
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDao pictureDao;

    /**
     * 查询照片
     * @return 照片集合
     */
    @Override
    public List<Picture> listPicture() {
        return pictureDao.listPicture();
    }

    /**
     * 添加照片
     * @param picture 照片
     * @return 影响条数
     */
    @Override
    public int savePicture(Picture picture) {
        return pictureDao.savePicture(picture);
    }

    /**
     * 根据id查询照片
     * @param id id值
     * @return 照片
     */
    @Override
    public Picture getPicture(Long id) {
        return pictureDao.getPicture(id);
    }

    /**
     * 编辑修改相册
     * @param picture 照片
     * @return 影响条数
     */
    @Override
    public int updatePicture(Picture picture) {
        return pictureDao.updatePicture(picture);
    }

    /**
     * 删除照片
     * @param id id值
     */
    @Override
    public void deletePicture(Long id) {
        pictureDao.deletePicture(id);
    }
}
