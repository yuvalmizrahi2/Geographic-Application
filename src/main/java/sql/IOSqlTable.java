package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import sample.Sample;
import sample.WayPoint;
import sample.Wifi;

public class IOSqlTable {

	public static Set<Sample> readtable(Table table ) throws ParseException
	{
		Set<Sample> data = new HashSet<>();
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(table.getUrl(), table.getUsername(), table.getPassword());
			PreparedStatement pst = con.prepareStatement("SELECT * FROM "+table.getTablename());
			rs = pst.executeQuery();

			while (rs.next()) {
				Sample s = new Sample();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = rs.getString(2);
				s.setDate(dateFormat.parse(date));
				s.setId(rs.getString(3));
				s.setWaypoint(new WayPoint(rs.getDouble(4), rs.getDouble(5), rs.getDouble(6)));
				ArrayList<Wifi> wifis = new ArrayList<>();
				for(int i = 0 ; i < rs.getInt(7) ; i++)
				{
					wifis.add(new Wifi(rs.getString(8 * i), rs.getDouble(9 * i)));

				}
				s.setArraywifi(wifis);
				data.add(s);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(IOSqlTable.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (con != null) { con.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(IOSqlTable.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
	return data;
}

}
