/**
 * $RCSfile: JDocumentBean.java,v $
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
package net.ooder.index.config.bean;

public class JDocumentBean implements JDocument {
    String id;
    String name;
    Class clazz;
    boolean vfsValid;
    boolean indexValid;

    VFSJsonBean vfsJson;
        
    JFSDirectory fsDirectory;
    
    JIndexWriter indexWriter;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
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

    public void setClazz(Class clazz) {
	this.clazz = clazz;
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
    

    public void setVfsValid(boolean vfsValid) {
        this.vfsValid = vfsValid;
    }

    public void setIndexValid(boolean indexValid) {
        this.indexValid = indexValid;
    }
  

    public boolean isVfsValid() {
        return vfsValid;
    }

    public boolean isIndexValid() {
        return indexValid;
    }


    @Override
    public VFSJsonBean getVfsJson() {
	return vfsJson;
    }

    public void setVfsJson(VFSJsonBean _vfsJson) {
	this.vfsJson = _vfsJson;
    }

}
