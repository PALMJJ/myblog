package com.jiaojun.service;

import com.jiaojun.entity.Picture;

import java.util.List;

/**
 * 照片墙业务层接口
 */
public interface PictureService {
    /**
     * 查询照片
     * @return 照片集合
     */
    List<Picture> listPicture();

    /**
     * 添加照片
     * @param picture 照片
     * @return 影响条数
     */
    int savePicture(Picture picture);

    /**
     * 根据id查询照片
     * @param id id值
     * @return 照片
     */
    Picture getPicture(Long id);

    /**
     * 编辑修改相册
     * @param picture 照片
     * @return 影响条数
     */
    int updatePicture(Picture picture);

    /**
     * 删除照片
     * @param id id值
     */
    void deletePicture(Long id);
}
