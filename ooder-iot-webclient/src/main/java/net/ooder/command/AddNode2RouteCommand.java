package  net.ooder.command;

import  net.ooder.common.ContextType;
import  net.ooder.enums.CommandEnums;
import  net.ooder.annotation.EsbBeanAnnotation;


@EsbBeanAnnotation(id = "AddNode2Route",dataType=ContextType.Command, name = "在指定节点添加入网", expressionArr = "AddNode2RouteCommand()", desc = "在指定节点添加入网")
public class AddNode2RouteCommand extends BindCommand implements NetCommand{

	public AddNode2RouteCommand(){
		super(CommandEnums.AddNode2Route);
	}
}
