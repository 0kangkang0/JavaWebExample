package mapper;

import org.apache.ibatis.annotations.*;
import pojo.Brand;
import pojo.PageBean;

import java.util.List;

public interface BrandMapper {
    /*查询所有方法*/
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();
    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);
    @Delete("delete from tb_brand where id=#{id}")
    void delete(int id);
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    @ResultMap("brandResultMap")
    void update(Brand brand);
    @Select("select *from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin")int begin,@Param("size")int size);
    @Select("select count(*) from tb_brand")
    int selectCount();

    void deleteByIds(@Param("ids") int[] ids);

    List<Brand> selectByConditions(@Param("begin")int begin, @Param("size")int size, @Param("brand")Brand brand);

    int selectByConditionsCount(Brand brand);
}
