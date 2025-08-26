package  net.ooder.command;

import  net.ooder.common.ContextType;
import  net.ooder.enums.CommandEnums;
import  net.ooder.annotation.EsbBeanAnnotation;

@EsbBeanAnnotation(id = "IRControl",dataType=ContextType.Command, name = "红外命令切换", expressionArr = "IRControlCommand()", desc = "红外命令切换")
public class IRControlCommand extends SensorCommand {

    String value="00B6EF";

    public IRControlCommand() {
	super(CommandEnums.IRControl);
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }
}
