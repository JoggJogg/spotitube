package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.DomainObject;
import nl.han.oose.dea.spotitube.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCredentialsDataMapper extends AbstractMapper <User> {

    private static final String FIND_QUERY = "SELECT password from spotitube.User WHERE username = ?";

    @Override
    protected String findStatement() {
        return FIND_QUERY;
    }

    @Override
    protected User doLoad(int id, ResultSet rs) throws SQLException {
        String username = rs.getString(1);
        String password = rs.getString(2);
        return new User(username, password);
    }

    public boolean correctLogin(User inputUser) throws SQLException {
        String username = inputUser.getUser();

        ResultSet rs = null;
        User databaseUser = doLoad(1, rs);

       return inputUser.passWordsMatch(databaseUser);
    }
}
