package com.hualr.dao;

import com.hualr.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ：zq
 * @description：TODO
 * @date ：2020/10/18 22:07
 */
public interface SchoolDao extends JpaRepository<School,Long>, JpaSpecificationExecutor<School> {

}
