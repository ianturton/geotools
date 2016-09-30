package org.geotools.geopkg;

import org.geotools.jdbc.JDBCLobOnlineTest;
import org.geotools.jdbc.JDBCLobTestSetup;

/**
 * 
 *
 * @source $URL$
 */
public class GeoPkgLobOnlineTest extends JDBCLobOnlineTest {

    @Override
    protected JDBCLobTestSetup createTestSetup() {
        return new GeoPkgLobTestSetup(new GeoPkgTestSetup());
    }

}
