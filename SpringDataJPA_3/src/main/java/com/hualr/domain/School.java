package com.hualr.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author ：zq
 * @description：TODO
 * @date ：2020/10/18 21:56
 */
@Entity
@Table
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer schoolId;
    @Column
    private String schoolName;

    /**
     * @OneToMany: 一个学校有许多学生.而一般而言一个学生只属于一个学校
     *              targetEntity:所对应的对象的字节码
     * @JoinColumn: 用于配置外键
     *              name: 该类对应的表的外键
     *              referencedColumnName: 对应的主表的主键字段名称
     */
    @OneToMany(targetEntity = Student.class)
    //第一个值会在从表 也就是student类中被体现 第二个值表示的是主表的主健 也就是说这行对于两个关系表是一样的
    @JoinColumn(name = "school_id",referencedColumnName = "schoolId")
    //@OneToMany(mappedBy = "school",cascade = CascadeType.ALL)
    /**
     * 这里的mappedBy时参照对方类该行的属性描述的 也就是学生类下在描述school的哪一行
     */
    private Set<Student> students=new HashSet<>();

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", students=" + students +
                '}';
    }
}
