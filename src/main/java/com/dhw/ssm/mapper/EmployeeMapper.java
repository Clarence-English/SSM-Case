package com.dhw.ssm.mapper;

import com.dhw.ssm.entity.Employee;
import com.dhw.ssm.entity.EmployeeExample;
import com.dhw.ssm.helper.PageDataHelper;
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
public interface EmployeeMapper {
    /**
     * 按照条件进行统计
     *
     * @param example 条件
     * @return 总数
     */
    int countByExample(EmployeeExample example);

    /**
     * 按照条件删除
     *
     * @param example 条件
     * @return 执行行数
     */
    int deleteByExample(EmployeeExample example);

    /**
     * 按照主键删除
     *
     * @param empId 主键
     * @return 执行行数
     */
    int deleteByPrimaryKey(Integer empId);

    /**
     * 插入数据
     *
     * @param record 员工对象
     * @return 执行行数
     */
    int insert(Employee record);

    /**
     * 按照有选择的进行插入
     *
     * @param record 员工对象
     * @return 执行行数
     */
    int insertSelective(Employee record);

    /**
     * 按照条件的查询
     *
     * @param example 条件
     * @return 执行结果
     */
    List<Employee> selectByExample(EmployeeExample example);

    /**
     * 按照主键的查询
     *
     * @param empId 主键
     * @return 执行结果
     */
    Employee selectByPrimaryKey(Integer empId);


    /**
     * 按照条件的查询员工和部门
     *
     * @param example 条件
     * @return 执行结果
     */
    List<Employee> selectByExampleWithDepartment(EmployeeExample example);

    /**
     * 按照主键的查询员工和部门
     *
     * @param empId 主键
     * @return 执行结果
     */
    Employee selectByPrimaryKeyWithDepartment(Integer empId);

    /**
     * 分页
     *
     * @param pageDataHelper 分页参数类
     * @return 分页执行结果
     */
    List<Employee> selectByExamplePaging(PageDataHelper pageDataHelper);

    /**
     * 按照条件、有选择的更新
     *
     * @param record  员工对象
     * @param example 条件
     * @return 执行行数
     */
    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    /**
     * 按照条件更新，全字段的
     *
     * @param record  员工对象
     * @param example 条件
     * @return 执行行数
     */
    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    /**
     * 按照主键、有选择的更新
     *
     * @param record 员工对象
     * @return 执行行数
     */
    int updateByPrimaryKeySelective(Employee record);

    /**
     * 按照主键、全字段的更新
     *
     * @param record 员工对象
     * @return 执行行数
     */
    int updateByPrimaryKey(Employee record);

    /**
     * 通过主键批量删除
     *
     * @param empIds 多个编号
     * @return 执行行数
     */
    int deleteByPrimaryKeyInBulk(@Param("empIds") List<Integer> empIds);
}