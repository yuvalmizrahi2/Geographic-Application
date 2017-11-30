package classproject;

/**
 * A class containing all wifi data collected 
 * @author יובל מזרחי
 * 
 */
public class Wifi implements Comparable<Wifi>
{
	private String ssid;
	private String mac;
	private String frequncy;
	private double singal;
	/**
	 * Default constructor
	 */
	public Wifi() {
		super();
	}
	/**
	 * A constructor that receive only the parameter mac
	 * @param mac
	 */
	public Wifi(String mac)
	{
		this.mac = mac;
	}
	/**
	 * A constructor that receives the parameters ssid,mac,frequncy,singal
	 * @param ssid
	 * @param mac
	 * @param frequncy
	 * @param singal
	 */
	public Wifi(String ssid, String mac, String frequncy, int singal) {
		super();
		this.ssid = ssid;
		this.mac = mac;
		this.frequncy = frequncy;
		this.singal = singal;
	}
	/**
	 * function that return the value of the ssid
	 * @return ssid
	 */
	public String getSsid() {
		return ssid;
	}
	/**
	 * function that get ssid and change the ssid value
	 * @param ssid
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	/**
	 * function that return the value of the mac
	 * @return mac
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * function that get mac and change the mac value
	 * @param mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**
	 * function that return the value of the frequncy
	 * @return frequncy
	 */
	public String getFrequncy() {
		return frequncy;
	}
	/**
	 * function that get frequncy and change the frequncy value
	 * @param frequncy
	 */
	public void setFrequncy(String frequncy) {
		this.frequncy = frequncy;
	}
	/**
	 * function that return the value of the singal
	 * @return singal
	 */
	public double getSingal() {
		return singal;
	}
	/**
	 * function that get singal and change the singal value
	 * @param singal
	 */
	public void setSingal(double singal) {
		this.singal = singal;
	}
	/**
	 * A function that receives a number of channel and returns its frequency
	 * taken from stackoverflow
	 * @param Channel
	 * @return the frequency of the channel
	 */
	public String convertChannelToFrequency(int Channel)
	{ 

		int i;
		if (Channel >= 1 && Channel <= 14) {
			i = (Channel - 1) * 5 + 2412 ;
			return i + "";
		} else if (Channel >= 36 && Channel <= 165) {
			i = (Channel - 34) * 5 + 5170;
			return i + "";
		} else {
			return "";
		}
	}
	/**
	 * A function that compares two wifis according to their single volume
	 */
	@Override
	public int compareTo(Wifi o) {
		return (int) (Math.abs(this.singal) - Math.abs(o.singal));
	}
	/**
	 * A function that compares two wifis according to their mac
	 */
	@Override
	public boolean equals(Object obj) {
		Wifi wifi = (Wifi)obj;
		return this.getMac().equals(wifi.getMac());
	}
	/**
	 * A function that return string with the details of the wifi in csv format
	 */
	@Override
	public String toString()
	{
		return ","+this.ssid+","+this.mac+","+this.frequncy+","+this.singal;
	}
}
