package  net.ooder.command;

import  net.ooder.common.ContextType;
import  net.ooder.enums.CommandEnums;
import  net.ooder.annotation.EsbBeanAnnotation;

@EsbBeanAnnotation(id = "SetTemperature",dataType=ContextType.Command, name = "设置温度", expressionArr = "SetTemperature()", desc = "设置温度")

public class SetTemperatureCommand extends SensorCommand {

    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SetTemperatureCommand() {
	super(CommandEnums.SetTemperature);
    }

}
