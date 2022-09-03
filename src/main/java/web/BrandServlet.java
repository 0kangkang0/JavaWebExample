package web;

import com.alibaba.fastjson.JSON;
import pojo.Brand;
import pojo.PageBean;
import service.BrandService;
import service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private final BrandService brandService = new BrandServiceImpl();
    public void selectAllServlet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        List<Brand> brands = brandService.SelectAll();
        String s = JSON.toJSONString(brands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
    public void addServlet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.add(brand);
        response.getWriter().write("success");
    }

    public void deleteServlet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        Integer id = brand.getId();
        brandService.delete(id);
        response.getWriter().write("success");
    }
    public void updateServlet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        //System.out.println(brand);
        brandService.update(brand);
        response.getWriter().write("success");
    }
    public void selectByPageServlet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Brand> brandPageBean = brandService.selectByPage(currentPage, pageSize);
        String s = JSON.toJSONString(brandPageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
    public void deleteByIdsServlet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        int[] ints = JSON.parseObject(s, int[].class);

        //System.out.println(brand);
        brandService.deleteByIds(ints);
        response.getWriter().write("success");
    }
    public void selectByConditionsServlet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        Brand brand = JSON.parseObject(params, Brand.class);
        PageBean<Brand> brandPageBean = brandService.selectConditions(currentPage, pageSize, brand);
        String s = JSON.toJSONString(brandPageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
}
