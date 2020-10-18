package com.hualr.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ：zq
 * @description：TODO
 * @date ：2020/10/18 21:34
 */
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    //ZNN ???int??
    private Integer studentId;

    @Column
    private String name;

    @ManyToOne(targetEntity = School.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    /**
     * targetEntity: 对端类
     * fetch:默认查询加载方式为全加载 在未来最好设置为懒加载提高性能
     * cascade 级联方式:其中包含级联删除更新增加等 最好选择ALL来适配全部级联
     *     强调一点: 只有在需要操作的对象对应的类上写级联才有用 如果这里删除而在对端写 是没有任何作用的
     */
    /**
     * 第一个值是外键名称 也就是说 对于学校而言的外键在学生表中 对于学生而言的外键也在学生表中 两者为同一列
     * 第二个值是主表的主键 也就是1方的主键
     */
    @JoinColumn(name="school_id",referencedColumnName = "schoolId" )
    /**
     * name: 表示该类对应的表的属性名称 对应下面的类属性
     * referencedColumnName: 表示对端类的主键
     */
    private School school;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", school=" + school +
                '}';
    }
}
