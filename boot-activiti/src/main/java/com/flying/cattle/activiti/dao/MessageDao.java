package com.flying.cattle.activiti.dao;

import com.flying.cattle.activiti.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Message)表数据库访问层
 *
 * @author makempy
 * @since 2021-05-21 15:55:41
 */
@Mapper
public interface MessageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Message queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Message> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param message 实例对象
     * @return 对象列表
     */
    List<Message> queryAll(Message message);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int insert(Message message);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Message> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Message> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Message> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Message> entities);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

