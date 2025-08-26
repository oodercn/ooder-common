package  net.ooder.command;

import com.alibaba.fastjson.JSONObject;
import  net.ooder.common.ContextType;
import  net.ooder.enums.CommandEnums;
import  net.ooder.annotation.EsbBeanAnnotation;

@EsbBeanAnnotation(id = "GoRun",dataType=ContextType.Command, name = "返回运行期", expressionArr = "GoRunCommand()", desc = "返回运行期")
public class GoRunCommand extends Command implements ADCommand{

    public GoRunCommand() {
	super(CommandEnums.GoRun);
    }

    public static void main(String[] args) {

	GoRunCommand runCommand = new GoRunCommand();
	// runCommand.setCommand(runCommand);

	System.out.println(JSONObject.toJSONString(runCommand));
    }

    @Override
    public String getFactory() {
        return "jds";
    }
}
