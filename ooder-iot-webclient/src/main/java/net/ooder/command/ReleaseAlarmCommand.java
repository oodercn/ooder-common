package  net.ooder.command;

import  net.ooder.common.ContextType;
import  net.ooder.enums.CommandEnums;
import  net.ooder.annotation.EsbBeanAnnotation;


@EsbBeanAnnotation(id = "ReleaseAlarm",dataType=ContextType.Command, name = "解除报警", expressionArr = "ReleaseAlarmCommand()", desc = "解除报警")
public class ReleaseAlarmCommand extends SensorCommand{
	public ReleaseAlarmCommand() {
		super(CommandEnums.ReleaseAlarm);
	}

}
