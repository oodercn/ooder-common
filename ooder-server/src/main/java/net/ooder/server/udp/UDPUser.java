package net.ooder.server.udp;

import net.ooder.engine.JDSSessionHandle;

import java.util.ArrayList;
import java.util.List;

public class UDPUser implements java.io.Serializable {
    String account;

    List<JDSSessionHandle> handles = new ArrayList<JDSSessionHandle>();

    public UDPUser(String account) {
	this.account = account;
    }

    public String getAccount() {
	return account;
    }

    public List<JDSSessionHandle> getHandles() {
	return handles;
    }

    public void setHandles(List<JDSSessionHandle> handles) {
	this.handles = handles;
    }

    public void setAccount(String account) {
	this.account = account;
    }

}
