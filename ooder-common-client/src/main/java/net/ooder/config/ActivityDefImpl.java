/**
 * $RCSfile: ActivityDefImpl.java,v $
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
package net.ooder.config;


import net.ooder.annotation.IconEnumstype;

public enum ActivityDefImpl implements IconEnumstype {

    No( "人工活动","spafont spa-icon-login"),

    Tool("自动任务","spafont spa-icon-options"),

    Device( "设备节点","spafont spa-icon-phonegap"),

    Service("服务节点","spafont spa-icon-conf"),

    Block( "场景服务","spafont spa-icon-c-video"),

    Event("设备事件","spafont spa-icon-event"),

    SubFlow( "子流程","spafont spa-icon-editpath"),

    OutFlow("外部流程","spafont spa-icon-newprj"),

    Process("默认流程","spafont spa-icon-c-databinder");


    private final String name;
    private String type;
    private String imageClass;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    ActivityDefImpl(String name, String imageClass) {
        this.type = name();
        this.name = name;
        this.imageClass=imageClass;


    }

    @Override
    public String toString() {
        return name;
    }

    public static ActivityDefImpl fromType(String typeName) {
        for (ActivityDefImpl type : ActivityDefImpl.values()) {
            if (type.getType().equals(typeName)) {
                return type;
            }
        }
        return Process;
    }

    @Override
    public String getImageClass() {
        return imageClass;
    }



    @Override
    public String getName() {
        return name;
    }

}
