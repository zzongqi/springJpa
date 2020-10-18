package com.hualr.dao;

import com.hualr.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ：zq
 * @description：TODO
 * @date ：2020/10/18 21:51
 */
public interface StudentDao extends JpaRepository<Student, Integer> ,JpaSpecificationExecutor<Student> {
}
