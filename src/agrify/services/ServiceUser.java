/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;
import agrify.entities.User;
import agrify.utils.DataSource;

/**
 *
 * @author tbagh
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ServiceUser implements IServiceUser<User> {
    private Connection connect;
    private DataSource dataSource; // Add a dataSource field


    public ServiceUser(Connection connection) {
        this.connect = connection;
    }
    
    public ServiceUser(DataSource dataSource) {
    this.dataSource = dataSource;
}


   @Override
public void ajouter(User user) {
        try {
           System.out.println(connect);
PreparedStatement statement = connect.prepareStatement ("INSERT INTO user(user_nom, user_prenom, user_email, user_telephone, user_role, user_genre, user_nbrabscence, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        statement.setString(1, user.getUser_nom());
        statement.setString(2, user.getUser_prenom());
        statement.setString(3, user.getUser_email());
        statement.setString(4, user.getUser_telephone());
        statement.setString(5, user.getUser_role());
        statement.setString(6, user.getUser_genre());
        statement.setInt(7, user.getUser_nbrabscence());
        statement.setString(8, user.getUsername());
        statement.setString(9, user.getPassword());

        statement.executeUpdate();
        statement.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    

    @Override
    public void modifier(User user) {
        try {
            String updateQuery = "UPDATE `user` SET `user_nom`=?, `user_prenom`=?, `user_email`=?, `user_telephone`=?, `user_role`=?, `user_genre`=?, `user_nbrabscence`=?, `username`=?, `password`=? WHERE `user_id`=?";

            PreparedStatement preparedStatement = connect.prepareStatement(updateQuery);
            preparedStatement.setString(1, user.getUser_nom());
            preparedStatement.setString(2, user.getUser_prenom());
            preparedStatement.setString(3, user.getUser_email());
            preparedStatement.setString(4, user.getUser_telephone());
            preparedStatement.setString(5, user.getUser_role());
            preparedStatement.setString(6, user.getUser_genre());
            preparedStatement.setInt(7, user.getUser_nbrabscence());
            preparedStatement.setString(8, user.getUsername());
            preparedStatement.setString(9, user.getPassword());
            preparedStatement.setLong(10, user.getUser_id());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int userId) {
        try {
            String deleteQuery = "DELETE FROM `personne` WHERE `user_id`=?";

            PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  public List<User> searchUsersByName(String name) throws SQLException {
        List<User> users = new ArrayList<>();
        String selectQuery = "SELECT * FROM `user` WHERE `user_nom` LIKE ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, "%" + name + "%"); // Search for names that contain the provided name

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setUser_id(rs.getInt("user_id"));
                    user.setUser_nom(rs.getString("user_nom"));
                    user.setUser_prenom(rs.getString("user_prenom"));
                    user.setUser_email(rs.getString("user_email"));
                    user.setUser_telephone(rs.getString("user_telephone"));
                    user.setUser_role(rs.getString("user_role"));
                    user.setUser_genre(rs.getString("user_genre"));
                    user.setUser_nbrabscence(rs.getInt("user_nbrabscence"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred while searching for users by name.");
            ex.printStackTrace();
            throw ex;
        }

        return users;
    }
  

    @Override
    public User getOne(int userId) {
        try {
            String selectQuery = "SELECT * FROM `user` WHERE `user_id`=?";
            PreparedStatement preparedStatement = connect.prepareStatement(selectQuery);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUser_nom(rs.getString("user_nom"));
                user.setUser_prenom(rs.getString("user_prenom"));
                user.setUser_email(rs.getString("user_email"));
                user.setUser_telephone(rs.getString("user_telephone"));
                user.setUser_role(rs.getString("user_role"));
                user.setUser_genre(rs.getString("user_genre"));
                user.setUser_nbrabscence(rs.getInt("user_nbrabscence"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM `user`";
            PreparedStatement preparedStatement = connect.prepareStatement(selectQuery);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUser_nom(rs.getString("user_nom"));
                user.setUser_prenom(rs.getString("user_prenom"));
                user.setUser_email(rs.getString("user_email"));
                user.setUser_telephone(rs.getString("user_telephone"));
                user.setUser_role(rs.getString("user_role"));
                user.setUser_genre(rs.getString("user_genre"));
                user.setUser_nbrabscence(rs.getInt("user_nbrabscence"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("error happened here ");
            System.out.println(ex.getMessage());
        }
        return users;
    }
    
    public void supprimerByName(String name) {
    try {
        String deleteQuery = "DELETE FROM `user` WHERE `user_nom` = ?";

        PreparedStatement preparedStatement = connect.prepareStatement(deleteQuery);
        preparedStatement.setString(1, name);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
      public void updateUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String updateQuery = "UPDATE user SET user_nbrabscence = ? WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setInt(1, user.getUser_nbrabscence());
            preparedStatement.setInt(2, user.getUser_id());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
    }

    public void updateUserr(User user) {
    PreparedStatement preparedStatement = null;

    try {
        String updateQuery = "UPDATE user SET user_nbrabscence = ? WHERE user_id = ?";
        preparedStatement = connect.prepareStatement(updateQuery);

        preparedStatement.setInt(1, user.getUser_nbrabscence());
        preparedStatement.setInt(2, user.getUser_id());

        preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

    
public User getUserBest(int year) throws SQLException {
    // Query to find the user with the smallest number of absences for the specified year
    String query = "SELECT u.* " +
                   "FROM user u " +
                   "INNER JOIN presence p ON u.user_id = p.user_id " +
                   "WHERE YEAR(p.date) = ? " + // Filter by year
                   "ORDER BY u.user_nbrabscence ASC " + // Order by smallest number of absences
                   "LIMIT 1";

    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
        preparedStatement.setInt(1, year);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setUser_id(resultSet.getInt("user_id"));
            user.setUser_nom(resultSet.getString("user_nom"));
            user.setUser_prenom(resultSet.getString("user_prenom"));
            user.setUser_email(resultSet.getString("user_email"));
            user.setUser_telephone(resultSet.getString("user_telephone"));
            user.setUser_role(resultSet.getString("user_role"));
            user.setUser_genre(resultSet.getString("user_genre"));
            user.setUser_nbrabscence(resultSet.getInt("user_nbrabscence"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
    }

    return null; // Return null if no user is found
}


 


    
    
}







