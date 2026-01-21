package LE_10_01.db.util;

import LE_10_01.db.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class DbWrite {

    @FunctionalInterface
    public interface ParamsSetter {
        void set(PreparedStatement ps) throws SQLException;
    }

    @FunctionalInterface
    public interface TxWork<T> {
        T run(Connection conn) throws Exception;
    }

    public static <T> T inTransaction(TxWork<T> work) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                T result = work.run(conn);
                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int executeUpdate(Connection conn, String sql, ParamsSetter setter) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (setter != null) setter.set(ps);
            return ps.executeUpdate();
        }
    }

    public static int insertAndGetId(Connection conn, String sql, ParamsSetter setter) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (setter != null) setter.set(ps);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
            throw new SQLException("No generated key");
        }
    }

    public static void setNullableString(PreparedStatement ps, int idx, String value) throws SQLException {
        if (value == null) ps.setNull(idx, Types.VARCHAR);
        else ps.setString(idx, value);
    }

    public static void setNullableDate(PreparedStatement ps, int idx, LocalDate value) throws SQLException {
        if (value == null) ps.setNull(idx, Types.DATE);
        else ps.setDate(idx, Date.valueOf(value));
    }
}
