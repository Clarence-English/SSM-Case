package com.dhw.ssm.entity;

import lombok.Data;

/**
 * @author
 */
@Data
public class Employee {
    private Integer empId;
    private String lastName;
    private String email;
    private String gender;
    private Integer deptId;
    private Department department;
    public Employee() {
    }

    public Employee(String lastName, String email, String gender, Integer deptId) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.deptId = deptId;
    }

    public Employee(Integer empId, String lastName, String email, String gender, Integer deptId) {
        this(lastName, email, gender, deptId);
        this.empId = empId;
    }
}