package  net.ooder.command;

import  net.ooder.enums.CommandEnums;

public class SycnCommand extends Command {

    String value="SycnCommand";

    public SycnCommand(CommandEnums command) {
	super(command);
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

}
