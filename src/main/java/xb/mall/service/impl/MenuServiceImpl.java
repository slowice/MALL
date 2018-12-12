package xb.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.mall.bean.Menu;
import xb.mall.dao.MenuMapper;
import xb.mall.service.MenuService;

import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> selectAllFMenu() {
       return null;
    }

    @Override
    public List<Menu> selectSMenu(String pid) {
      return null;
    }
}
