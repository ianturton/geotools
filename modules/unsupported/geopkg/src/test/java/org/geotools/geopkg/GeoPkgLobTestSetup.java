package org.geotools.geopkg;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.geotools.jdbc.JDBCLobTestSetup;
import org.geotools.jdbc.JDBCTestSetup;

/**
 * 
 *
 * @source $URL$
 */
public class GeoPkgLobTestSetup extends JDBCLobTestSetup {

    public GeoPkgLobTestSetup(JDBCTestSetup delegate) {
        super(delegate);
    }

    @Override
    protected void createLobTable() throws Exception {
        
        Connection con = getDataSource().getConnection();
        con.prepareStatement("create table \"testlob\" (\"fid\" INTEGER PRIMARY KEY, " +
        		"\"blob_field\" blob, \"clob_field\" TEXT, \"raw_field\" blob)").execute();
        String sql = "INSERT INTO gpkg_contents (table_name, data_type, identifier, srs_id) VALUES " +
                "('testlob', 'features', 'testlob', 4326)";
        run(sql);
        
        PreparedStatement ps =con.prepareStatement( "INSERT INTO \"testlob\" (\"blob_field\",\"clob_field\",\"raw_field\")  VALUES (?,?,?)");
        ps.setBytes(1, new byte[] {1,2,3,4,5});
        ps.setString(2, "small clob");
        ps.setBytes(3, new byte[] {6,7,8,9,10});
        ps.execute();
        ps.close();
        con.close();               
    }

    @Override
    protected void dropLobTable() throws Exception {
     
        ((GeoPkgTestSetup)delegate).removeTable("testlob");
    }

}
