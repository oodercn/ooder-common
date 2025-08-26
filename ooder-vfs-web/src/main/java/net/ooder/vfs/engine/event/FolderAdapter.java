package  net.ooder.vfs.engine.event;

import  net.ooder.vfs.VFSException;

/**
 * <p>
 * Title:VFS文件管理
 * </p>
 * <p>
 * Description: 核心文件事件监听器适配器
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: raddev.cn
 * </p>
 * 
 * @author wenzhang li
 * @version 1.0
 */
public class FolderAdapter implements FolderListener {

	@Override
	public void create(FolderEvent event) throws VFSException {

	}

	@Override
	public void lock(FolderEvent event) throws VFSException {

	}

	@Override
	public void beforReName(FolderEvent event) throws VFSException {

	}

	@Override
	public void reNameEnd(FolderEvent event) throws VFSException {

	}

	@Override
	public void beforDelete(FolderEvent event) throws VFSException {

	}

	@Override
	public void deleteing(FolderEvent event) throws VFSException {

	}

	@Override
	public void deleteEnd(FolderEvent event) throws VFSException {

	}

	@Override
	public void save(FolderEvent event) throws VFSException {

	}

	@Override
	public void beforCopy(FolderEvent event) throws VFSException {

	}

	@Override
	public void copying(FolderEvent event) throws VFSException {

	}

	@Override
	public void copyEnd(FolderEvent event) throws VFSException {

	}

	@Override
	public void beforMove(FolderEvent event) throws VFSException {

	}

	@Override
	public void moving(FolderEvent event) throws VFSException {

	}

	@Override
	public void moveEnd(FolderEvent event) throws VFSException {

	}

	@Override
	public void beforClean(FolderEvent event) throws VFSException {

	}

	@Override
	public void cleanEnd(FolderEvent event) throws VFSException {

	}

	@Override
	public void restore(FolderEvent event) throws VFSException {

	}

	/**
	 * getSystemCode
	 */
	public String getSystemCode() {
		return null;
	}

}