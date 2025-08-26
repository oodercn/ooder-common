package  net.ooder.command;

import  net.ooder.common.ContextType;
import  net.ooder.enums.CommandEnums;
import  net.ooder.annotation.EsbBeanAnnotation;

@EsbBeanAnnotation(id = "AddPassword",dataType=ContextType.Command, name = "添加密码", expressionArr = "AddPasswordCommand()", desc = "添加密码")
public class AddPasswordCommand extends PasswordCommand {
    public AddPasswordCommand() {
	super(CommandEnums.AddPassword);
    }
}
