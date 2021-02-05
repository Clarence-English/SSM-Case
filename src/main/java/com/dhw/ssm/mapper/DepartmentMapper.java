package com.dhw.ssm.mapper;

import com.dhw.ssm.entity.Department;
import com.dhw.ssm.entity.DepartmentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门数据操作层
 *
 * @author dhw
 */
@Mapper
@Repository
public interface DepartmentMapper {
    /**
     * 按照条件进行统计
     *
     * @param example 条件
     * @return 总数
     */
    int countByExample(DepartmentExample example);

    /**
     * 按照条件删除
     *
     * @param example 条件
     * @return 执行行数
     */
    int deleteByExample(DepartmentExample example);

    /**
     * 按照主键删除
     *
     * @param deptId 主键
     * @return 执行行数
     */
    int deleteByPrimaryKey(Integer deptId);

    /**
     * 插入数据
     *
     * @param record 部门对象·
     * @return 执行行数
     */
    int insert(Department record);

    /**
     * 按照有选择的进行插入
     *
     * @param record 部门对象
     * @return 执行行数
     */
    int insertSelective(Department record);

    /**
     * 按照条件的查询
     *
     * @param example 条件
     * @return 执行结果
     */
    List<Department> selectByExample(DepartmentExample example);

    /**
     * 按照主键的查询
     *
     * @param deptId 主键
     * @return 执行结果
     */
    Department selectByPrimaryKey(Integer deptId);

    /**
     * 按照条件、有选择的更新
     *
     * @param record  部门对象
     * @param example 条件
     * @return 执行行数
     */
    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    /**
     * 按照条件更新，全字段的
     *
     * @param record  部门对象
     * @param example 条件
     * @return 执行行数
     */
    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    /**
     * 按照主键、有选择的更新
     *
     * @param record 部门对象
     * @return 执行行数
     */
    int updateByPrimaryKeySelective(Department record);

    /**
     * 按照主键、全字段的更新
     *
     * @param record 部门对象
     * @return 执行行数
     */
    int updateByPrimaryKey(Department record);
}