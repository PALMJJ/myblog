package com.jiaojun.service.impl;

import com.jiaojun.NotFoundException;
import com.jiaojun.dao.BlogDao;
import com.jiaojun.entity.Blog;
import com.jiaojun.queryvo.*;
import com.jiaojun.service.BlogService;
import com.jiaojun.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 博客列表业务层接口实现类
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    /**
     * 保存新增博客
     * @param blog 博客
     * @return 影响条数
     */
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogDao.saveBlog(blog);
    }

    /**
     * 查询文章列表管理
     * @return 文章列表
     */
    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }

    /**
     * 删除博客
     * @param id id值
     */
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    /**
     * 编辑博客
     * @param showBlog 展示博客类
     * @return 影响条数
     */
    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogDao.updateBlog(showBlog);
    }

    /**
     * 查询编辑修改的文章
     * @param id id值
     * @return 展示博客类
     */
    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    /**
     * 搜索博客管理列表
     * @param searchBlog 搜索博客实体类
     * @return 博客查询列表
     */
    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleAndType(searchBlog);
    }

    /**
     * 查询首页最新博客列表信息
     * @return 最新博客列表
     */
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }

    /**
     * 查询首页最新推荐信息
     * @return 推荐博客列表
     */
    @Override
    public List<RecommendBlog> getRecommendBlog() {
        List<RecommendBlog> allRecommendBlog = blogDao.getAllRecommendBlog();
        return allRecommendBlog;
    }

    /**
     * 搜索博客列表
     * @param query 搜索关键词
     * @return 搜索博客列表
     */
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    /**
     * 统计博客总数
     * @return 博客总数
     */
    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }

    /**
     * 统计访问总数
     * @return 访问总数
     */
    @Override
    public Integer getBlogViewTotal() {
        return blogDao.getBlogViewTotal();
    }

    /**
     * 统计评论总数
     * @return 评论总数
     */
    @Override
    public Integer getBlogCommendTotal() {
        return blogDao.getBlogCommentTotal();
    }

    /**
     * 统计留言总数
     * @return 留言总数
     */
    @Override
    public Integer getBlogMessageTotal() {
        return blogDao.getBlogMessageTotal();
    }

    /**
     * 博客详情
     * @param id id值
     * @return 博客详情
     */
    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        // 文章访问数量自增
        blogDao.updateViews(id);
        // 文章评论数量更新
        blogDao.getCommentCountById(id);
        return detailedBlog;
    }

    /**
     * 根据typeId查询博客列表，显示在分类页面
     * @param typeId 类型id
     * @return 博客页列表
     */
    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }
}
