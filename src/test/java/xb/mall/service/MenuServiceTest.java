package xb.mall.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xb.mall.MallApplicationTests;
import xb.mall.bean.Menu;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class MenuServiceTest extends MallApplicationTests {
    @Autowired
    MenuService menuService;
    @Test
    public void selectAllFMenu() {
        List<Menu> list = menuService.selectAllFMenu();
        Assert.assertTrue(list.size()>0);
    }
}