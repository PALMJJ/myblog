package com.jiaojun.dao;

import com.jiaojun.entity.Blog;
import com.jiaojun.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客管理持久层接口
 */
@Mapper
@Repository
public interface BlogDao {
    /**
     * 保存新增博客
     * @param blog 博客
     * @return 影响条数
     */
    int saveBlog(Blog blog);

    /**
     * 查询文章列表管理
     * @return 文章列表
     */
    List<BlogQuery> getAllBlogQuery();

    /**
     * 删除博客
     * @param id id值
     */
    void deleteBlog(Long id);

    /**
     * 编辑博客
     * @param showBlog 展示博客类
     * @return 影响条数
     */
    int updateBlog(ShowBlog showBlog);

    /**
     * 查询编辑修改的文章
     * @param id id值
     * @return 展示博客类
     */
    ShowBlog getBlogById(Long id);

    /**
     * 搜索博客管理列表
     * @param searchBlog 搜索博客实体类
     * @return 博客查询列表
     */
    List<BlogQuery> searchByTitleAndType(SearchBlog searchBlog);

    /**
     * 查询首页最新博客列表信息
     * @return 最新博客列表
     */
    List<FirstPageBlog> getFirstPageBlog();

    /**
     * 查询首页最新推荐信息
     * @return 推荐博客列表
     */
    List<RecommendBlog> getAllRecommendBlog();

    /**
     * 搜索博客列表
     * @param query 搜索关键词
     * @return 搜索博客列表
     */
    List<FirstPageBlog> getSearchBlog(String query);

    /**
     * 统计博客总数
     * @return 博客总数
     */
    Integer getBlogTotal();

    /**
     * 统计访问总数
     * @return 访问总数
     */
    Integer getBlogViewTotal();

    /**
     * 统计评论总数
     * @return 评论总数
     */
    Integer getBlogCommentTotal();

    /**
     * 统计留言总数
     * @return 留言总数
     */
    Integer getBlogMessageTotal();

    /**
     * 查询博客详情
     * @param id id值
     * @return 博客详细
     */
    DetailedBlog getDetailedBlog(Long id);

    /**
     * 文章访问更新
     * @param id id值
     * @return 更新数
     */
    int updateViews(Long id);

    /**
     * 根据博客id查询出评论数量
     * @param id id值
     * @return 评论数
     */
    int getCommentCountById(Long id);

    /**
     * 根据typeId查询博客列表，显示在分类页面
     * @param typeId 类型id
     * @return 博客页列表
     */
    List<FirstPageBlog> getByTypeId(Long typeId);
}
