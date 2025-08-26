/**
 * $RCSfile: JLucene.java,v $
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
package net.ooder.index.config;

import net.ooder.index.config.bean.JFSDirectory;
import net.ooder.index.config.bean.JField;
import net.ooder.index.config.bean.JIndexWriter;
import net.ooder.index.config.bean.VFSJson;

import java.util.List;

public interface JLucene {


    public String getUuid();

    public void setUuid(String uuid);

    public String getJson();

    public void setJson(String json);

    public String getUserId();

    public void setUserId(String userId);

    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);

    public JFSDirectory getFsDirectory();

    public void setFsDirectory(JFSDirectory jFsDirectory);

    public JIndexWriter getIndexWriter();

    public void setIndexWriter(JIndexWriter jIndexWriter);

    public List<JField> getFields();

    public void setFields(List<JField> fields);


    public List<String> getVfsFilePaths();

    public void setVfsFilePaths(List<String> filePaths);

    public VFSJson getVfsJson();
    
    public void setVfsJson(VFSJson vfsJson);

    public void setVfsValid(boolean vfsValid);

    public void setIndexValid(boolean indexValid);

    public boolean isVfsValid();

    public boolean isIndexValid();

    public List<String> getHighFields();

    public void setHighFields(List<String> hightFields);

}
