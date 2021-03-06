package pl.meleride.api.storage.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import pl.meleride.api.storage.Storage;
import pl.meleride.api.storage.StorageException;

public interface SqlStorage extends Storage {

  String TEST_QUERY = "DO 1";

  void update(String query) throws StorageException;

  void update(String query, SQLStorageConsumer consumer) throws StorageException;

  ResultSet query(Connection connection, String query) throws StorageException;

  ResultSet query(Connection connection, String query, SQLStorageConsumer consumer) throws StorageException;

}
