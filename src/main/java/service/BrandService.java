package service;

import pojo.Brand;
import pojo.PageBean;

import java.util.List;

public interface BrandService {
    List<Brand> SelectAll();
    void add(Brand brand);
    void delete(int id);
    public void update(Brand Brand);
    PageBean<Brand> selectByPage(int currentPage, int pageSize);
    void deleteByIds(int[]ids);
    PageBean<Brand> selectConditions(int currentPage, int pageSize,Brand brand);
}
