package com.dhw.ssm;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.dhw.ssm.entity.Department;
import com.dhw.ssm.entity.Employee;
import com.dhw.ssm.entity.EmployeeExample;
import com.dhw.ssm.mapper.DepartmentMapper;
import com.dhw.ssm.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * @author dhw
 * @create 2020-11-16 10:17
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//        locations = {"classpath:application.xml"}
//)
public class MapperTest {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SqlSession sqlSession;





    @Test
    public void testBatch() {
        // 批量插入员工
        EmployeeMapper sessionMapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 900; i++) {
            int depId = (int) (Math.random() * 3 + 1);
            String gender = String.valueOf((int) (Math.random() * 2 + 1));
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            sessionMapper.insertSelective(new Employee(uid, uid + "@qq.com", gender, depId));
        }
    }


    @Test
    public void testCRUD() {
        // 1. 插入一个部门
        Department department = new Department(3, "人事部名");
        departmentMapper.insert(department);

        // 2. 插入一个员工
        Employee employee = new Employee("张三", "张三@qq.com", "0", 3);
        employeeMapper.insert(employee);

        // 3. 根据empId查询员工
        Employee employeeKey = employeeMapper.selectByPrimaryKey(1031);
        System.out.println(employeeKey);

        // 4. 根据empId查询员工和部门
        Employee employeeAndDepartment = employeeMapper.selectByPrimaryKeyWithDepartment(1031);
        System.out.println(employeeAndDepartment);

        // 5. 根据empId和empName查询员工
        EmployeeExample example = new EmployeeExample();
        example.or().andEmpIdEqualTo(1031).andLastNameEqualTo("张三");
        List<Employee> employees = employeeMapper.selectByExample(example);
        System.out.println(employees);
    }

    @Test
    public void testPageHelper() {

    }
}
