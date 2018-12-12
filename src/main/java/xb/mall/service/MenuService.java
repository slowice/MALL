package xb.mall.service;


import xb.mall.bean.Menu;

import java.util.List;

public interface MenuService {
    //查询所有一级菜单
    List<Menu> selectAllFMenu();

    //查询二级菜单 byIndex
    List<Menu> selectSMenu(String id);
}
