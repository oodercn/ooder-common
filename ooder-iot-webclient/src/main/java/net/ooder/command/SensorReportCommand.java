package  net.ooder.command;

import  net.ooder.annotation.EsbBeanAnnotation;
import  net.ooder.common.ContextType;
import  net.ooder.enums.CommandEnums;

@EsbBeanAnnotation(id = "SensorReport",dataType=ContextType.Command, name = "上报传感器", expressionArr = "SensorReportCommand()", desc = "上报传感器")

public class SensorReportCommand extends Command {

	public SensorReportCommand() {
		super(CommandEnums.SensorReport);
	}

}
