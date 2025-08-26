package  net.ooder.iot.json;

import java.util.List;

import  net.ooder.command.WifiInfo;
import  net.ooder.command.WlanInfo;

public class NetworkInfo {
	
   
	String gwieee;
	
	WifiInfo wifi;

	WlanInfo wlan;

	List<DHCPInfo> dhcp;

	public List<DHCPInfo> getDhcp() {
		return dhcp;
	}

	public void setDhcp(List<DHCPInfo> dhcp) {
		this.dhcp = dhcp;
	}

	public WifiInfo getWifi() {
		return wifi;
	}

	public void setWifi(WifiInfo wifi) {
		this.wifi = wifi;
	}

	public WlanInfo getWlan() {
		return wlan;
	}

	public void setWlan(WlanInfo wlan) {
		this.wlan = wlan;
	}

	public String getGwieee() {
		return gwieee;
	}

	public void setGwieee(String gwieee) {
		this.gwieee = gwieee;
	}

	
}
