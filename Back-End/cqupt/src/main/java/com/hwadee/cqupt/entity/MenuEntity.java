package com.hwadee.cqupt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 菜单实体类
 * @author: lys
 * @date: 2023/8/11
 */
@TableName(value = "menu")
public class MenuEntity {

    @TableId(value = "menu_id",type = IdType.AUTO)
    private Integer menuId;
    private String menuName;
    private String menuUrl;
    private String menuPath;
    private String menuIcon;
    private Integer menuOrder;
    private Integer pid;
    private LocalDateTime createdate;
    private LocalDateTime updatedate;

    public MenuEntity() {
    }

    public MenuEntity(Integer menuId, String menuName, String menuUrl, String menuPath, String menuIcon, Integer menuOrder, Integer pid, LocalDateTime createdate, LocalDateTime updatedate) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuPath = menuPath;
        this.menuIcon = menuIcon;
        this.menuOrder = menuOrder;
        this.pid = pid;
        this.createdate = createdate;
        this.updatedate = updatedate;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public LocalDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(LocalDateTime createdate) {
        this.createdate = createdate;
    }

    public LocalDateTime getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(LocalDateTime updatedate) {
        this.updatedate = updatedate;
    }
}
