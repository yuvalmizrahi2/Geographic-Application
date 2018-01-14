package sql;
/**
 * A class that contain all the details of sql table
 * @author יובל מזרחי
 *
 */
public class Table {
	
	private String ip;
	private String port;
	private String namedatabase;
	private String username;
	private String password;
	private String url;
	private String tablename;
	/**
	 * A constructor for the class
	 * @param ip
	 * @param port
	 * @param namedatabase
	 * @param username
	 * @param password
	 * @param tablename
	 */
	public Table(String ip, String port, String namedatabase, String username, String password,String tablename) {
		this.ip = ip;
		this.port = port;
		this.namedatabase = namedatabase;
		this.username = username;
		this.password = password;
		this.url = "jdbc:mysql://"+ip+":"+port+"/"+namedatabase;
		this.tablename = tablename;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getNamedatabase() {
		return namedatabase;
	}
	public void setNamedatabase(String namedatabase) {
		this.namedatabase = namedatabase;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	
	

}
