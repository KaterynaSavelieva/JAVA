package LE_10_01.db.util;

import LE_10_01.db.DBConnection;

import java.sql.*;
import java.time.LocalDate;

/**Helper class for INSERT / UPDATE / DELETE operations.
 This class also handles database transactions.*/
public class DbWrite {

    //Functional interface to set parameters for a PreparedStatement.
    //Used to fill the ? placeholders in SQL.
    @FunctionalInterface
    public interface ParamsSetter {
        void set(PreparedStatement ps) throws SQLException;
    }

    /* Functional interface for code that should run inside a transaction.
     The connection is provided by DbWrite.
     TxWork - TransactionWork means code that is executed within a transaction*/
    @FunctionalInterface
    public interface TxWork<T> {
        T run(Connection conn) throws Exception;
    }


    /* Executes code inside a database transaction.
     - commit if everything is OK
     - rollback if an error occurs

     <T> is a generic type parameter.
     It means: "this method can work with any type T".*/
    public static <T> T inTransaction(TxWork<T> work) {
        try (Connection conn = DBConnection.getConnection()) {

            // Disable auto-commit -> start transaction
            conn.setAutoCommit(false);

            try {
                // Run user code inside the transaction
                T result = work.run(conn);

                // Commit changes if successful
                conn.commit();
                return result;

            } catch (Exception e) {
                // Rollback if something goes wrong
                conn.rollback();
                throw e;
            }

        } catch (Exception e) {
            // Wrap checked exception into runtime exception
            throw new RuntimeException(e);
        }
    }

    /* Executes INSERT / UPDATE / DELETE SQL statements.
      Does NOT return a generated ID.*/
    public static int executeUpdate(Connection conn,
                                    String sql,
                                    ParamsSetter setter) throws SQLException {

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set SQL parameters if provided
            if (setter != null) {
                setter.set(ps);
            }

            // Execute SQL and return affected rows
            return ps.executeUpdate();
        }
    }

    /* Executes an INSERT statement and returns the generated ID.
      Used for tables with AUTO_INCREMENT primary keys.  */
    public static int insertAndGetId(Connection conn,
                                     String sql,
                                     ParamsSetter setter) throws SQLException {

        try (PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set SQL parameters
            if (setter != null) {
                setter.set(ps);
            }

            // Execute INSERT
            ps.executeUpdate();

            // Read generated key (ID)
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }

            // No ID was generated -> error
            throw new SQLException("No generated key");
        }
    }

    /* Sets a nullable String parameter.
      If value is null, SQL NULL is used.     */
    public static void setNullableString(PreparedStatement ps,
                                         int index,
                                         String value) throws SQLException {

        if (value == null) {
            ps.setNull(index, Types.VARCHAR);
        } else {
            ps.setString(index, value);
        }
    }

    /* Sets a nullable LocalDate parameter.
     * If value is null, SQL NULL is used.     */
    public static void setNullableDate(PreparedStatement ps,
                                       int index,
                                       LocalDate value) throws SQLException {

        if (value == null) {
            ps.setNull(index, Types.DATE);
        } else {
            ps.setDate(index, Date.valueOf(value));
        }
    }
}
