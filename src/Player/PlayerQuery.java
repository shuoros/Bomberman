package Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DataBase.Connect;

public class PlayerQuery {
	public void addPlayer(Player player) {
		Connection con = Connect.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"INSERT INTO `players`(`username`, `password`, `email`, `pic`, `highscore`, `level`,  `xp`) VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, player.getUserName());
			ps.setString(2, player.getPassWord());
			ps.setString(3, player.getEmail());
			ps.setBytes(4, player.getPic());
			ps.setString(5, Double.toString(player.getHighScore()));
			ps.setInt(6, player.getLevel());
			ps.setString(7, Double.toString(player.getXp()));
			if (ps.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "New Account Createrd");
			} else {
				JOptionPane.showMessageDialog(null, "Something Wrong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Somthing Wrong With DataBase");
		}
	}

	/**
	 * This function takes a player and updates it in the database.
	 * 
	 * @param player
	 *            Player received.
	 * @param message
	 *            Whether to display a message or not.
	 */
	public void updatePlayer(Player player, boolean withImage, boolean withPass, boolean message) {
		Connection con = Connect.getConnection();
		PreparedStatement ps;
		String updateQuery = "";
		if (withImage == true) {
			if (withPass == true) {
				updateQuery = "UPDATE `players` SET `username`=?,`password`=?,`email`=?, `pic`=? WHERE `id`=?";
				try {
					ps = con.prepareStatement(updateQuery);
					ps.setString(1, player.getUserName());
					ps.setString(2, player.getPassWord());
					ps.setString(3, player.getEmail());
					ps.setBytes(4, player.getPic());
					ps.setInt(5, player.getId());
					if (ps.executeUpdate() != 0 && message == true) {
						JOptionPane.showMessageDialog(null, "Player Info Edited");
					} else if (!(ps.executeUpdate() != 0) && message == true) {
						JOptionPane.showMessageDialog(null, "Something Wrong");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				updateQuery = "UPDATE `players` SET `username`=?, `email`=?, `pic`=? WHERE `id`=?";
				try {
					ps = con.prepareStatement(updateQuery);
					ps.setString(1, player.getUserName());
					ps.setString(2, player.getEmail());
					ps.setBytes(3, player.getPic());
					ps.setInt(4, player.getId());
					if (ps.executeUpdate() != 0 && message == true) {
						JOptionPane.showMessageDialog(null, "Player Info Edited");
					} else if (!(ps.executeUpdate() != 0) && message == true) {
						JOptionPane.showMessageDialog(null, "Something Wrong");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			if (withPass == true) {
				updateQuery = "UPDATE `players` SET `username`=?,`password`=?,`email`=? WHERE `id`=?";
				try {
					ps = con.prepareStatement(updateQuery);
					ps.setString(1, player.getUserName());
					ps.setString(2, player.getPassWord());
					ps.setString(3, player.getEmail());
					ps.setInt(4, player.getId());
					if (ps.executeUpdate() != 0 && message == true) {
						JOptionPane.showMessageDialog(null, "Player Info Edited");
					} else if (!(ps.executeUpdate() != 0) && message == true) {
						JOptionPane.showMessageDialog(null, "Something Wrong");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				updateQuery = "UPDATE `players` SET `username`=?, `email`=? WHERE `id`=?";
				try {
					ps = con.prepareStatement(updateQuery);
					ps.setString(1, player.getUserName());
					ps.setString(2, player.getEmail());
					ps.setInt(3, player.getId());
					if (ps.executeUpdate() != 0 && message == true) {
						JOptionPane.showMessageDialog(null, "Player Info Edited");
					} else if (!(ps.executeUpdate() != 0) && message == true) {
						JOptionPane.showMessageDialog(null, "Something Wrong");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public ArrayList<Player> players() {
		ArrayList<Player> plist = new ArrayList<Player>();
		Connection con = Connect.getConnection();
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT `id`, `username`, `password`, `email`, `pic`, `highscore`, `level`,`xp` FROM `players`");
			Player player;
			while (rs.next()) {
				player = new Player(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("email"), rs.getBytes("pic"), Double.parseDouble(rs.getString("highscore")),
						rs.getInt("level"), Double.parseDouble(rs.getString("xp")));
				plist.add(player);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Somthing Wrong With DataBase");
		}
		return plist;
	}
}
