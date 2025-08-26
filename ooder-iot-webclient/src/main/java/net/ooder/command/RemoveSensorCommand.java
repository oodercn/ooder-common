package  net.ooder.command;

import  net.ooder.common.ContextType;
import  net.ooder.enums.CommandEnums;
import  net.ooder.annotation.EsbBeanAnnotation;

@EsbBeanAnnotation(id = "RemoveSensor", dataType=ContextType.Command,name = "移除传感器", expressionArr = "RemoveSensorCommand()", desc = "移除传感器")
public class RemoveSensorCommand extends SensorCommand implements NetCommand{

	public RemoveSensorCommand() {
		super(CommandEnums.RemoveSensor);
	}

}
