package  net.ooder.vfs.service;

import  net.ooder.annotation.MethodChinaName;
import  net.ooder.common.FolderState;
import  net.ooder.common.FolderType;
import  net.ooder.config.ResultModel;
import  net.ooder.vfs.FileInfo;
import  net.ooder.vfs.FileVersion;
import  net.ooder.vfs.Folder;

public interface VFSDiskService {

    @MethodChinaName(cname = "COPY文件夹")
    public ResultModel<Boolean> copyFolder(String spath, String tpaht);

    @MethodChinaName(cname = "克隆文件夹")
    public ResultModel<Boolean> cloneFolder(String spath, String tpaht);

    @MethodChinaName(cname = "根据逻辑地址获取文件信息")
    public ResultModel<FileInfo> getFileInfoByPath(String path);

    @MethodChinaName(cname = "根据文件夹path获取文件")
    public ResultModel<Folder> getFolderByPath(String path);

    @MethodChinaName(cname = "创建文件")
    public ResultModel<FileInfo> createFile(String path, String name);

    @MethodChinaName(cname = "创建文件夹")
    public ResultModel<Folder> mkDir(String path);

    @MethodChinaName(cname = "删除文件")
    public ResultModel<Boolean> delete(String path);

    @MethodChinaName(cname = "COPY文件信息")
    public ResultModel<Boolean> copyFile(String path, String path2);

    @MethodChinaName(cname = "COPY文件信息并重命名")
    public ResultModel<FileInfo> createFile2(String path, String name, String descrition);

    @MethodChinaName(cname = "创建文件夹")
    ResultModel<Folder> mkDir2(String path, String descrition, FolderType type);

    @MethodChinaName(cname = "创建指定HASH版本")
    ResultModel<FileVersion> createFileVersion(String path, String filehash);

    @MethodChinaName(cname = "获取版本")
    public ResultModel<FileVersion> getVersionByPath(String path);


    @MethodChinaName(cname = "更新文件信息")
    public ResultModel<Boolean> updateFileInfo(String path, String name, String descrition);

    @MethodChinaName(cname = "更新文件夹信息")
    public ResultModel<Boolean> updateFolderInfo(String path, String name, String descrition, FolderType type);

    @MethodChinaName(cname = "更新文件夹状态")
    public ResultModel<Boolean> updateFolderState(String path, FolderState state);

}
