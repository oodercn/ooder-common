package  net.ooder.command;

import  net.ooder.common.ContextType;
import  net.ooder.enums.CommandEnums;
import  net.ooder.annotation.EsbBeanAnnotation;

@EsbBeanAnnotation(id = "SyncTime",dataType=ContextType.Command, name = "同步时间", expressionArr = "SyncTimeCommand()", desc = "同步时间")

public class SyncTimeCommand extends SycnCommand {

    public SyncTimeCommand() {
        super(CommandEnums.SyncTime);
        this.setValue(Long.valueOf(System.currentTimeMillis()).toString());
    }

}
