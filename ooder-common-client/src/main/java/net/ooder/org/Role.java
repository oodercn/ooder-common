/**
 * $RCSfile: Role.java,v $
 * $Revision: 1.0 $
 * $Date: 2025/08/25 $
 * <p>
 * Copyright (c) 2025 ooder.net
 * </p>
 * <p>
 * Company: ooder.net
 * </p>
 * <p>
 * License: MIT License
 * </p>
 */
package net.ooder.org;


import net.ooder.annotation.*;
import net.ooder.esd.annotation.ViewType;

import java.util.List;

/**
 * 人员角色接口。
 * <p>
 * Title: ooder组织机构中间件
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003-2008
 * </p>
 * <p>
 * Company: raddev.cn
 * </p>
 *
 * @author  ooder
 * @version 2.0
 */
@ESDEntity
//@Aggregation(type = AggregationType.ROOT, sourceClass = Role.class, rootClass = Role.class)
public interface Role extends Comparable<Org> {

    /**
     * 取得该人员角色的标识
     *
     * @return 人员角色的标识
     */
    @MethodChinaName(cname = "角色标识")
    @Uid
    public String getRoleId();

    public void setRoleId(String roleId);


    /**
     * 取得该人员角色的名称
     *
     * @return 人员角色的名称
     */
    @MethodChinaName(cname = "名称")
    @Caption
    public String getName();

    public void setName(String name);

    /**
     * 取得该人员角色类型
     *
     * @return 人员角色类型
     */
    @MethodChinaName(cname = "角色类型")
    public RoleType getType();

    public void setType(RoleType type);

    /**
     * 取得该人员职务的级数
     *
     * @return 人员职务的级数
     */
    @MethodChinaName(cname = "级数")
    public String getRoleNum();

    public void setRoleNum(String num);


    /**
     * 取得该人员角色的标识
     *
     * @return 人员角色的标识
     */
    @MethodChinaName(cname = "系统ID")
    @Pid
    public String getSysId();

    public void setSysId(String sysId);


    @MethodChinaName(cname = "人员")
    @Ref(ref = RefType.M2M, view = ViewType.GRID)
    public List<Person> getPersonList();

    @MethodChinaName(cname = "部门")
    @Ref(ref = RefType.M2M, view = ViewType.GRID)
    public List<Org> getOrgList();

    public List<String> getOrgIdList();

    public void setOrgIdList(List<String> orgIds);

    public List<String> getPersonIdList();

    public void setPersonIdList(List<String> personIds);
    //

}
