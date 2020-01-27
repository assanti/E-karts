package com.ekarts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ekarts.dto.Client;
import com.ekarts.enumClass.KartEnumClass;
import com.ekarts.dto.Kart;

public class KartDao {
	
	public List<Kart> listAllItems() {
		String SQL_SELECT_ALL_ITEMS = "SELECT kart_id, kart_name, kart_type, kart_price_minute" + " FROM kart";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Client client = null;
		List<Kart> kartList = new ArrayList<>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL_ITEMS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("kart_id");
				String name = rs.getString("kart_name");
				String stringType = rs.getString("kart_type");
				KartEnumClass enumType = KartEnumClass.valueOf(stringType);
				Double priceMinute= rs.getDouble("kart_price_minute");
				String cover = rs.getString("kart_cover");
				
				Kart kart = new Kart(id, name, enumType, priceMinute, cover);
				kartList.add(kart);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return kartList;
	}

	/*
	 * Recupera un client a la base de dades segons el seu ID
	 * 
	 */
	
	public Kart findById(Kart kart) {
		String SQL_SELECT_BY_ID = "SELECT kart_id, kart_name, kart_type, kart_price_minute"
				+ " FROM kart WHERE kart_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, kart.getId());
			rs = stmt.executeQuery();
			rs.absolute(1);// nos posicionamos en el primer registro devuelto

			int id = rs.getInt("kart_id");
			String name = rs.getString("kart_name");
			String strType = rs.getString("kart_type");
			KartEnumClass enumTypeById = KartEnumClass.valueOf(strType);
			Double priceMinute= rs.getDouble("kart_price_minute");
			
			//Construimos el objeto kart
			
			kart.setName(name);
			kart.setEnumType(enumTypeById);
			kart.setPriceMinute(priceMinute);
			

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return kart;
	}

	/*
	 * Crea un client a la base de dades
	 * 
	 */
		
	public int create(Kart kart) {
		String SQL_INSERT = "INSERT INTO client(kart_id, kart_name, kart_type, kart_price_minute, kart_cover) "
				+ " VALUES(?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setInt(1, kart.getId());
			stmt.setString(2, kart.getName());
			stmt.setString(3, String.valueOf(kart.getEnumType()));
			stmt.setDouble(4, kart.getPriceMinute());
			stmt.setString(5, kart.getCover());
			System.out.println(kart.toString());
			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}

	/*
	 * Modifica un client de la base de dades
	 * 
	 */
	
	
	public int update(Kart kart) {
		String SQL_UPDATE = "UPDATE client "
				+ " SET kart_id=?, kart_name=?, =?, kart_price_minute=?, kart_cover=? WHERE kart_id=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			int i = 1;
			stmt.setInt(i++, kart.getId());
			stmt.setString(i++, kart.getName());
			stmt.setDouble(i++, kart.getPriceMinute());
			stmt.setString(i++, String.valueOf(kart.getEnumType()));
			stmt.setString(i++,  kart.getCover());
		
			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}

	/*
	 * Esborra un client de la base de dades
	 * 
	 */	
	
	public int delete(Kart kart) {
		String SQL_DELETE = "DELETE FROM kart WHERE kart_id= ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, kart.getId());
			
			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}

}
