package com.hualr.test;

import com.hualr.dao.SchoolDao;
import com.hualr.dao.StudentDao;
import com.hualr.domain.School;
import com.hualr.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：zq
 * @description：TODO
 * @date ：2020/10/18 22:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest2 {
    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private StudentDao studentDao;
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd_A() {
        School school=new School();
        school.setSchoolName("光meiguiee中学");

        Student student1=new Student();
        student1.setName("小红");
        //主表去维护关系
        school.getStudents().add(student1);

        Student student2=new Student();
        student2.setName("小绿");
        //从表去维护关系
        student2.setSchool(school);
        //主表或者从表在双向关系中,都是具备维护表的功能特性的

        //在没有设置级联的情况下 一定是所有表都需要手动保存的
        schoolDao.save(school);
        studentDao.save(student1);
        studentDao.save(student2);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd_B() {
        School school=new School();
        school.setSchoolName("黑暗中学");

        Student student1=new Student();
        student1.setName("小红");
        //主表去维护关系
        /**
         * 此时,我们让school表放弃维护,这样下来,接下来小红就没有学校了.因为放弃了维护权所以下面的代码时没有意义的
         */
        school.getStudents().add(student1);

        Student student2=new Student();
        student2.setName("小绿");
        //从表去维护关系
        student2.setSchool(school);
        //放弃维护之后,只能从表维护

        //在没有设置级联的情况下 一定是所有表都需要手动保存的
        schoolDao.save(school);
        studentDao.save(student1);
        studentDao.save(student2);
    }

}
