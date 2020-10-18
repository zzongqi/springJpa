package com.hualr.test;

import com.hualr.domain.Customer;
import com.hualr.utils.JpaUtils;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JpaTest {
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


    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     *  Jpa的操作步骤
     *     1.加载配置文件创建工厂（实体管理器工厂）对象
     *     2.通过实体管理器工厂获取实体管理器
     *     3.获取事务对象，开启事务
     *     4.完成增删改查操作
     *     5.提交事务（回滚事务）
     *     6.释放资源
     */
    @Test
    public void testSave1() {
        //1.加载配置文件创建工厂（实体管理器工厂）对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理器工厂获取实体管理器
        EntityManager em = factory.createEntityManager();
        //3.获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction(); //获取事务对象
        tx.begin();//开启事务
        //4.完成增删改查操作：保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setCustName("宗旗");
        customer.setCustIndustry("烽火公司");
        //保存
        em.persist(customer);
        //5.提交事务
        tx.commit();
        //6.释放资源
        em.close();
        factory.close();

    }


        /**
     * 对基本操作的一重简化
     */
    @Test
    public void testSave2() {
        //1. 获取实体管理器
        entityManager = JpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();//开启事务
        //3.完成增删改查操作：保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setCustName("李华");
        customer.setCustIndustry("黄金公司");
        //4. 保存
        entityManager.persist(customer);
        //5.提交事务
        entityTransaction.commit();
        //6.释放资源
        entityManager.close();

    }
    /**
     * 根据id查询客户
     *  使用find方法查询：
     *      1.查询的对象就是当前客户对象本身
     *      2.在调用find方法的时候，就会发送sql语句查询数据库
     *  立即加载
     */
    @Test
    public  void testFind() {
        //3.增删改查 -- 根据id查询客户
        /**
         * find : 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        Customer customer = entityManager.find(Customer.class, 1L);
        System.out.print(customer);
        //4.提交事务 查询不需要提交事务
    }

    /**
     * 根据id查询客户
     *      getReference方法
     *          1.获取的对象是一个动态代理对象
     *          2.调用getReference方法不会立即发送sql语句查询数据库
     *              * 当调用查询结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询数据库
     *
     * 延迟加载（懒加载）
     *      * 得到的是一个动态代理对象
     *      * 什么时候用，什么使用才会查询
     */
    @Test
    public  void testReference() {
        //3.增删改查 -- 根据id查询客户
        /**
         * getReference : 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        Customer customer = entityManager.getReference(Customer.class, 1l);
        System.out.print(customer);
    }


    /**
     * 更新客户的操作: merge(Object)
     */
    @Test
    public  void testUpdate() {
        Customer customer = entityManager.find(Customer.class, 1L);
        customer.setCustIndustry("白色帝国");
        entityManager.merge(customer);
    }

    /**
     * 删除客户的案例
     */
    @Test
    public  void testRemove() {
        Customer customer = entityManager.find(Customer.class, 1L);
        entityManager.remove(customer);
    }



}
