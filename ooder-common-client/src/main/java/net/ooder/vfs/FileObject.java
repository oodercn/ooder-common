/**
 * $RCSfile: FileObject.java,v $
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
package net.ooder.vfs;

import net.ooder.annotation.ESDEntity;
import net.ooder.annotation.MethodChinaName;
import net.ooder.annotation.Pid;
import net.ooder.common.JDSException;
import net.ooder.common.md5.MD5InputStream;

import java.util.List;

/**
 * 文件实体
 */
@ESDEntity
public interface FileObject extends java.io.Serializable {
    @MethodChinaName(cname = "取得文件标识")
    @Pid
    public String getID();

    @MethodChinaName(cname = "文件标识")
    public void setID(String id);

    @MethodChinaName(cname = "取得文件名称")
    public String getName();

    @MethodChinaName(cname = "文件名称")
    public void setName(String name);

    @MethodChinaName(cname = "跟目录地址")
    public String getRootPath();

    @MethodChinaName(cname = "跟目录地址")
    public void setRootPath(String path);

    @MethodChinaName(cname = "文件读取适配器")
    public String getAdapter();

    @MethodChinaName(cname = "文件读取适配器")
    public void setAdapter(String adapter);

    @MethodChinaName(cname = "取得文件大小")
    public Long getLength();

    @MethodChinaName(cname = "文件大小")
    public void setLength(Long length);


    @MethodChinaName(cname = "取得文件hash")
    public String getHash();

    @MethodChinaName(cname = "文件hash")
    public void setHash(String hash);

    @MethodChinaName(cname = "取得文件物理路径")
    public String getPath();

    @MethodChinaName(cname = "文件物理路径")
    public void setPath(String path);

    @MethodChinaName(cname = "取得创建时间")
    public Long getCreateTime();


    @MethodChinaName(cname = "取得创建时间")
    public void setCreateTime(Long createTime);

    @MethodChinaName(cname = "下载片段")
    public MD5InputStream downLoad() throws JDSException;

    @MethodChinaName(cname = "追加片段")
    public Integer writeLine(String str) throws JDSException;

    @MethodChinaName(cname = "获取指定行数")
    public List<String> readLine(List<Integer> lineNums) throws JDSException;

}
