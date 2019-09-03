package com.pinyougou.mapper.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.TbBrand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-dao.xml")
public class BrandMapperTest {

        @Autowired
        private BrandMapper brandMapper;

        //1.根据主键查询
        @org.junit.Test
        public void selectByPrimaryKey() {
            Object brand = brandMapper.selectByPrimaryKey(1L);
            System.out.println(brand);
        }

        //2.选择性新增
        @org.junit.Test
        public void insertSelective() {
            //选择性新增；如果对象中的属性是有值的才会出现在insert 语句中 brand(name) -- > insert into tb_brand(id, name) values(null, ?)
            TbBrand brand = new TbBrand();
            brand.setFirstChar("z");
            brand.setName("鹏鹏");
            brandMapper.insertSelective(brand);
        }

        //3. 查询全部
        @org.junit.Test
        public void selectAll() {
            List<TbBrand> list = brandMapper.selectAll();
            for (TbBrand tbBrand : list) {
                System.out.println(tbBrand);
            }
        }

         //4.根据条件查询
        @org.junit.Test
        public void select() {
            TbBrand param = new TbBrand();
            param.setFirstChar("c");
            List<TbBrand> list = brandMapper.select(param);
            for (TbBrand tbBrand : list) {
                System.out.println(tbBrand);
            }
        }

          //5.选择性更新
        @org.junit.Test
        public void updateByPrimaryKeySelective() {
            //选择性更新；如果对象中的属性是有值的才会出现在update 语句中 brand(name) -- > update tb_brand set name=? where id=?
            TbBrand brand = new TbBrand();
            brand.setId(23L);
            brand.setName("小文");
            brandMapper.updateByPrimaryKeySelective(brand);
        }

        //6.根据主键删除
        @org.junit.Test
        public void deleteByPrimaryKey() {
            brandMapper.deleteByPrimaryKey(23L);
        }

        // 7.查询全部
        @org.junit.Test
        public void queryAll() {
            List<TbBrand> list = brandMapper.queryAll();
            for (TbBrand tbBrand : list) {
                System.out.println(tbBrand);
            }
        }

        /**
         * 8.查询首字母为c并查第2页数据每页2条；根据id降序排
         */
        @org.junit.Test
        public void selectByExample() {
            //设置分页助手；参数1：页号（从1开始），参数2：页大小
            //只针对紧接着执行的查询生效
            PageHelper.startPage(2, 2);

            //创建查询对象
            Example example = new Example(TbBrand.class);

            //查询条件对象
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("firstChar", "c");

            //根据id降序排序
            example.orderBy("id").desc();
            //sql-->select * from tb_brand where first_char ='c' order by desc limit 2,2
            List<TbBrand> list = brandMapper.selectByExample(example);

            PageInfo<TbBrand> pageInfo = new PageInfo<>(list);
            System.out.println("总记录数为：" + pageInfo.getTotal());
            System.out.println("总页数为：" + pageInfo.getPages());

            for (TbBrand tbBrand : list) {
                System.out.println(tbBrand);
            }
        }
// ----------------------------------------------------------------------------
     // 9.抽取BaseMapper --- 批量插入，批量删除
         @Test
         public void insertList(){
            List<TbBrand>list = new ArrayList<>();
             TbBrand tbBrand = new TbBrand();
             tbBrand.setFirstChar("x");
             tbBrand.setName("鹏鹏");
             list.add(tbBrand);

          TbBrand tbBrand1 = new TbBrand();
          tbBrand1.setFirstChar("y");
          tbBrand1.setName("成");
          list.add(tbBrand1);
          brandMapper.insertList(list);


         }

         @Test
         public void deleteByIds(){
            brandMapper.deleteByIds("25,26");

         }


 }