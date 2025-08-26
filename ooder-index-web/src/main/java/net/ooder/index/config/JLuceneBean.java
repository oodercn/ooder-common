/**
 * $RCSfile: JLuceneBean.java,v $
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

import java.util.ArrayList;
import java.util.List;

public class JLuceneBean implements JLucene {

    String name;
    String json;
    String id;
    String uuid;


    String userId;
    Class clazz;
    boolean vfsValid;
    boolean indexValid;

    JFSDirectory fsDirectory;
    JIndexWriter indexWriter;

    VFSJson vfsJson;

    List<String> vfsFilePaths=new ArrayList<>();

    List<JField> fields;

    List<String> highFields=new ArrayList<String>();

    public VFSJson getVfsJson() {
        return vfsJson;
    }

    public void setVfsJson(VFSJson vfsJson) {
        this.vfsJson = vfsJson;
    }

    public void setVfsValid(boolean vfsValid) {
        this.vfsValid = vfsValid;
    }


    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setIndexValid(boolean indexValid) {
        this.indexValid = indexValid;
    }
    @Override
    public List<String> getVfsFilePaths() {
        return vfsFilePaths;
    }

    @Override
    public void setVfsFilePaths(List<String> vfsFilePaths) {
        this.vfsFilePaths = vfsFilePaths;
    }

    public boolean isVfsValid() {
        return vfsValid;
    }

    public boolean isIndexValid() {
        return indexValid;
    }

    @Override
    public List<String> getHighFields() {
        return highFields;
    }

    @Override
    public void setHighFields(List<String> hightFields) {
        this.highFields = highFields;
    }

    @Override
    public String getJson() {
        return json;
    }

    @Override
    public void setJson(String json) {
         this.json = json;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {

        this.userId = userId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public JFSDirectory getFsDirectory() {
        return fsDirectory;
    }

    public void setFsDirectory(JFSDirectory fsDirectory) {
        this.fsDirectory = fsDirectory;
    }

    public JIndexWriter getIndexWriter() {
        return indexWriter;
    }

    public void setIndexWriter(JIndexWriter indexWriter) {
        this.indexWriter = indexWriter;
    }

    public List<JField> getFields() {
        return fields;
    }

    public void setFields(List<JField> fields) {
        this.fields = fields;
    }

}
