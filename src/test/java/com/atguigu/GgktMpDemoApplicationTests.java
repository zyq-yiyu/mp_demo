package com.atguigu;

import com.atguigu.entity.User;
import com.atguigu.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class GgktMpDemoApplicationTests {

    //注入Mapper
    @Autowired
    private UserMapper userMapper;

    //6 批量删除
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(8,9,10));
        System.out.println(result);
    }
    //5 id删除
    @Test
    public void deleteId(){
        int result = userMapper.deleteById(9L);
        System.out.println(result);
    }

    //4 分页查询
    @Test
    public void findPage(){
        //创建Page对象，传递两个参数:当前页每页显示记录数
        Page<User> page = new Page<>(1,3);
        //调用mp方法实现分页
        //IPage<User> pageMode1 = userMapper.selectPage(page,null);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    //3 修改操作
    @Test
    public void testUpdateById(){
        //1 根据id查询记录
        User user = userMapper.selectById(1L);
        //2 设置修改的值
        user.setAge(50);
        //3 调用方法修改
        int result = userMapper.updateById(user);
        System.out.println(result);
    }
//2 添加
    @Test
    public void addUser(){
        User user = new User();
        user.setName("maryer");
        user.setAge(18);
        user.setEmail("atguigui@qq.com");
        user.setDeleted(0);
        int rows = userMapper.insert(user);
        System.out.println("rows:"+rows);
        //添加成功之后，把添加之后生成id值，回填到user对象里面
        System.out.println(user);

    }

    //1 查询所以数据
    @Test
    public void findAll() {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }

    }

}
