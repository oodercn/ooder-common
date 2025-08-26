/**
 * $RCSfile: FileVersion.java,v $
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

import com.alibaba.fastjson.annotation.JSONField;
import net.ooder.annotation.ESDEntity;
import net.ooder.annotation.MethodChinaName;
import net.ooder.common.md5.MD5InputStream;
import net.ooder.common.md5.MD5OutputStream;

import java.util.List;
import java.util.Set;

/**
 * 文件版本
 */
@ESDEntity
public interface FileVersion extends java.io.Serializable {

    @MethodChinaName(cname = "获取版本文件Id")
    public String getVersionID();

    @MethodChinaName(cname = "源版本ID")
    public String getSourceId();

    @MethodChinaName(cname = "获取源文件Id")
    public String getFileId();

    @MethodChinaName(cname = "取得版本名称")
    public String getVersionName();

    @MethodChinaName(cname = "获取文件名称")
    public String getFileName();

    @MethodChinaName(cname = "获取版本索引位置")
    public Integer getIndex();

    @MethodChinaName(cname = "获取文件长度")
    public Long getLength();

    public String getFileObjectId();

    public void setFileObjectId(String objectId);

    public String getPersonId();

    @MethodChinaName(cname = "获取源文件")

    @JSONField(serialize = false)
    public FileObject getFileObject();


    @MethodChinaName(cname = "获取源文件路径")
    public String getPath();

    public Integer writeLine(String str);

    // public List<String> readLine(List<Integer> lineNums);

    // public void addFileView(String viewId);

    // public void writeTo(final OutputStream outstream) ;

    @JSONField(serialize = false)
    public FileView createView(String objectId,Integer fileIndex);


    @JSONField(serialize = false)
    @MethodChinaName(cname = "取得当前版本文件MD5流")
    public MD5InputStream getInputStream();


    @JSONField(serialize = false)
    @MethodChinaName(cname = "取得当前版本文件MD5输出流")
    public MD5OutputStream getOutputStream();


    @JSONField(serialize = false)
    @MethodChinaName(cname = "获取视图")
    public List<FileView> getViews();

    @MethodChinaName(cname = "获取版本创建时间")
    public Long getCreateTime();

    public Set<String> getViewIds();

}
