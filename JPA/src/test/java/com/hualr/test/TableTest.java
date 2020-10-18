package com.hualr.test;

import com.hualr.domain.Customer2;
import com.hualr.utils.JpaUtils;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ：zq
 * @description：TODO
 * @date ：2020/10/18 20:15
 */
public class TableTest {
    EntityManager entityManager;
    EntityTransaction entityTransaction;

    @Before
    public void createResource(){
        //1. 获取实体管理器
        entityManager = JpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();//开启事务

    }

    @After
    public void clearResource(){
        //5.提交事务
        if (entityTransaction.isActive()){
            entityTransaction.commit();
        }
        //6.释放资源
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Test
    public void saveCustomer(){
        Customer2 customer=new Customer2();
        customer.setCustIndustry("牙膏厂");
        customer.setCustName("lian");
        entityManager.persist(customer);
    }
}
