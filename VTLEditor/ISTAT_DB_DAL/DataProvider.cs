using Oracle.ManagedDataAccess.Client;
using System;
using System.Data;
using System.Data.OleDb;
using System.Data.SqlClient;


namespace ISTAT_DB_DAL
{
    /// <remarks>/// Manage calls to the appropriate .NET data provider
    /// based upon the configuration.
    /// </remarks>
    public class DataProvider
    {
        /// <summary>
        /// Types of databases that can be accessed through the
        /// data provider
        /// </summary>

        // Internal DatabaseType value
        private ProviderType _DatabaseType;

        /// <value>Get or set the database type</value>
        public ProviderType DatabaseType
        {
            get { return this._DatabaseType; }
            set { this._DatabaseType = value; }
        }

        // Internal ConnectionString value
        private string _ConnectionString = "";

        /// <value>Get or set the database connection string</value>
        public string ConnectionString
        {
            get { return this._ConnectionString; }
            set { this._ConnectionString = value; }
        }

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="ConnectString">DB connection string</param>
        /// <param name="Database">Database type to access</param>
        public DataProvider(string ConnectString, ProviderType Database)
        {
            this.ConnectionString = ConnectString;
            this.DatabaseType = Database;
        }


        /// <summary>
        /// Executes commands such as SQL INSERT, DELETE, AND UPDATE
        /// statements and returns the number of rows affected.
        /// </summary>
        /// <param name="v_CmdText">Command text</param>
        /// <returns>Number of rows affected</returns>
        public int ExecuteNonQuery(string CmdText)
        {
            IDbCommand command = null;          // Database command
            IDbConnection connection = null;    // Database connection

            try
            {
                connection = this.GetConnection();
                command = this.GetCommand(CmdText, connection);
                connection.Open();
                return command.ExecuteNonQuery();
            }
            catch (Exception exception)
            {
                System.Diagnostics.Debug.WriteLine(exception.Message);
                return 0;
            }
            finally
            {
                // Clean up the objects
                if (command != null) command.Dispose();
                if (connection != null) connection.Dispose();
            }
        }

        /// <summary>
        /// Executes commands such as SQL INSERT, DELETE, AND UPDATE
        /// statements and returns the number of rows affected.
        /// </summary>
        /// <param name="v_CmdText">Command text</param>
        /// <returns>Number of rows affected</returns>
        public void ExecuteNonQueryMultiple(string[] CmdsText)
        {
            IDbCommand command = null;          // Database command
            IDbConnection connection = null;    // Database connection
            IDbTransaction tran = null;
            try
            {
                connection = this.GetConnection();
               
                connection.Open();                
                tran=connection.BeginTransaction();
                try
                {
                    foreach (string cmd in CmdsText)
                    {
                        command = this.GetCommand(cmd, connection);
                        command.Transaction = tran;
                        command.ExecuteNonQuery();
                    }
                    tran.Commit();                    
                }
                catch (Exception singleException)
                {
                    tran.Rollback();
                    System.Diagnostics.Debug.WriteLine(singleException.Message);
                    throw new Exception(singleException.Message);
                }
                
            }
            catch (Exception exception)
            {
                System.Diagnostics.Debug.WriteLine(exception.Message);
                throw new Exception(exception.Message);
            }
            finally
            {
                // Clean up the objects                
                if (tran != null) tran.Dispose();
                if (command != null) command.Dispose();
                if (connection != null) connection.Dispose();                
            }
        }

        public IDbConnection OpenConnection() 
        {
            IDbConnection connection = null;
             try
            {                    
                connection = this.GetConnection();
                connection.Open();
                return connection;
            }
             catch (Exception exception)
             {
                 if (connection!=null)
                    if (connection.State == ConnectionState.Open) connection.Close();
                 System.Diagnostics.Debug.WriteLine(exception.Message);
                 throw new Exception(exception.Message);
             }

        }

        public IDbTransaction OpenTransaction(IDbConnection connection) 
        {              
            IDbTransaction tran = null;
            try
            {
                if (connection.State==ConnectionState.Open)
                    tran = connection.BeginTransaction();

                return tran;
            }
            catch (Exception exception)
            {                
                System.Diagnostics.Debug.WriteLine(exception.Message);
                throw new Exception(exception.Message);
            }
        }

        public void RollBackTransaction(IDbTransaction tran) 
        {
            try
            {
                tran.Rollback();                
             }
            catch (Exception exception)
            {
                System.Diagnostics.Debug.WriteLine(exception.Message);
                throw new Exception(exception.Message);
            }
        }

        public void CloseConnection(IDbConnection connection) 
        { 
            try
            {
                if (connection!=null)
                    if (connection.State == ConnectionState.Open) connection.Close();
            }
             catch (Exception exception)
            {
                System.Diagnostics.Debug.WriteLine(exception.Message);
                throw new Exception(exception.Message);
            }        
        }

        public void ExecuteNonQueryMultiple(string CmdsText)
        {
            IDbCommand command = null;          // Database command
            IDbConnection connection = null;    // Database connection
            IDbTransaction tran = null;
            try
            {
                connection = this.GetConnection();

                connection.Open();
                tran = connection.BeginTransaction();
                try
                {
                    
                    command = this.GetCommand(CmdsText, connection);
                    command.Transaction = tran;
                    command.ExecuteNonQuery();
                    
                    tran.Commit();
                }
                catch (Exception singleException)
                {
                    tran.Rollback();
                    connection.Close();
                    System.Diagnostics.Debug.WriteLine(singleException.Message);
                    throw new Exception(singleException.Message);
                }

            }
            catch (Exception exception)
            {
                System.Diagnostics.Debug.WriteLine(exception.Message);
                throw new Exception(exception.Message);
            }
            finally
            {
                // Clean up the objects                
                if (tran != null) tran.Dispose();
                if (command != null) command.Dispose();
                if (connection != null) connection.Dispose();
            }
        }
        /// <summary>
        /// Executes SQL commands that return rows.
        /// </summary>
        /// <param name="CmdText">Command text</param>
        /// <returns>DataReader containing data</returns>
        public IDataReader ExecuteReader(string CmdText)
        {
            IDbCommand command = null;          // Database command
            IDbConnection connection = null;    // Database connection

            try
            {
                connection = this.GetConnection();
                command = this.GetCommand(CmdText, connection);
                connection.Open();

                // Use the command behavior to automatically close
                // the connection when the reader is closed. We need
                // to leave the connection open until the data is
                // retrieved from the data source.
                IDataReader r = command.ExecuteReader(
                      System.Data.CommandBehavior.CloseConnection);
                return r;
            }

            catch (Exception exception)
            {
                System.Diagnostics.Debug.WriteLine(exception.Message);
                return null;
            }
        }

        public IDataReader ExecuteReaderWithParam(string CmdText, string[] paramName, string[] paramVal)
        {
            IDbCommand command = null;          // Database command
            IDbConnection connection = null;    // Database connection

            try
            {
                connection = this.GetConnection();
                command = this.GetCommand(CmdText, connection);
                connection.Open();
                IDataParameter dbParam = null;

                for (int i = 0; i < paramName.Length; i++)
                {
                dbParam = command.CreateParameter();
                dbParam.ParameterName = paramName[i];
                //dbParam.DbType = DbType.String;
                dbParam.Value = paramVal[i];
                command.Parameters.Add(dbParam);
                }

                // Use the command behavior to automatically close
                // the connection when the reader is closed. We need
                // to leave the connection open until the data is
                // retrieved from the data source.
                IDataReader r = command.ExecuteReader(
                      System.Data.CommandBehavior.CloseConnection);
                return r;
            }

            catch (Exception exception)
            {
                System.Diagnostics.Debug.WriteLine(exception.Message);
                return null;
            }
        }
        
        public IDataReader ExecuteReaderWithParam(string CmdText, string paramName, string paramVal, DbType type)
        {
            IDbCommand command = null;          // Database command
            IDbConnection connection = null;    // Database connection

            try
            {
                connection = this.GetConnection();
                command = this.GetCommand(CmdText, connection);
                connection.Open();
                IDataParameter dbParam = null;

                dbParam = command.CreateParameter();
                dbParam.ParameterName = paramName;
                dbParam.DbType = type;
                dbParam.Value = paramVal;
                command.Parameters.Add(dbParam);

                IDataReader r = command.ExecuteReader(
                      System.Data.CommandBehavior.CloseConnection);
                return r;
            }

            catch (Exception exception)
            {
                System.Diagnostics.Debug.WriteLine(exception.Message);
                return null;
            }
        }

        /// <summary>
        /// Retrieves a single value (for example, an aggregate
        /// value) from a database. It retrieves the first
        /// value in the first row from the resultset.
        /// </summary>
        /// <param name="CmdText">Command text</param>
        /// <returns>Single value as object</returns>
        public Object ExecuteScalar(string CmdText)
        {
            IDbCommand command = null;          // Database command
            IDbConnection connection = null;    // Database connection

            try
            {
                connection = this.GetConnection();
                command = this.GetCommand(CmdText, connection);
                connection.Open();
                return command.ExecuteScalar();
            }
            catch (Exception exception)
            {
                System.Diagnostics.Debug.WriteLine(exception.Message);
                return null;
            }
            finally
            {
                if (command != null) command.Dispose();
                if (connection != null) connection.Dispose();
            }
        }

        /// <summary>
        /// Executes SQL commands that return rows and fill a DataSet
        /// with the results.
        /// </summary>
        /// <param name="CmdText">Command text</param>
        /// <returns>DataSet containing results</returns>
        public DataSet FillDataSet(string CmdText)
        {
            DataSet dataSet = null;             // DataSet to return
            IDataAdapter adapter = null;        // Data adapter
            IDbCommand command = null;          // Database command
            IDbConnection connection = null;    // Database connection

            try
            {
                connection = this.GetConnection();
                command = this.GetCommand(CmdText, connection);
                adapter = this.GetDataAdapter(command);

                // The data adapter will open and close the connection
                dataSet = new DataSet();
                adapter.Fill(dataSet);
                return dataSet;
            }
            catch (Exception exception)
            {
                System.Diagnostics.Debug.WriteLine(exception.Message);
                return null;
            }
            finally
            {
                if (command != null) command.Dispose();
                if (connection != null) connection.Dispose();
            }
        }

        /*
         * Get a data provider specific database connection.
         */
        public IDbConnection GetConnection()
        {
            IDbConnection connection = null;    // Database connection

            switch (this.DatabaseType)
            {
                case ProviderType.SqlServer:
                    connection = new SqlConnection(this.ConnectionString);
                    break;
                case ProviderType.OleDb:
                    connection = new OleDbConnection(this.ConnectionString);
                    break;
                case ProviderType.Oracle:
                    connection = new OracleConnection(this.ConnectionString);
                    break;
                default:
                    connection = new SqlConnection(this.ConnectionString);
                    break;
            }
            return connection;
        }

        /*
         * Get a data provider specific database command object.
         */
        private IDbCommand GetCommand(string CmdText, IDbConnection Connection)
        {
            IDbCommand command = null;
            switch (this.DatabaseType)
            {
                case ProviderType.SqlServer:
                    command = new SqlCommand(CmdText,
                              (SqlConnection)Connection);
                    break;
                case ProviderType.OleDb:
                    command = new OleDbCommand(CmdText,
                               (OleDbConnection)Connection);
                    break;
                case ProviderType.Oracle:
                    command = new OracleCommand(CmdText, (OracleConnection)Connection);
                    break;
                default:
                    command = new SqlCommand(CmdText,
                                     (SqlConnection)Connection);
                    break;
            }
            return command;
        }

        /*
         * Get a data provider specific data adapter.
         */
        private IDataAdapter GetDataAdapter(IDbCommand command)
        {
            IDataAdapter adapter = null;
            switch (this.DatabaseType)
            {
                case ProviderType.SqlServer:
                    adapter = new SqlDataAdapter((SqlCommand)command);
                    break;
                case ProviderType.OleDb:
                    adapter = new OleDbDataAdapter((OleDbCommand)command);
                    break;
                case ProviderType.Oracle:
                    adapter = new OracleDataAdapter((OracleCommand)command);
                    break;
                default:
                    adapter = new SqlDataAdapter((SqlCommand)command);
                    break;
            }
            return adapter;
        }

        public static bool TestDatabaseConnection(string connectionString, ProviderType dbType)
        {
            IDbConnection conn = null;            
            IDbCommand comm = null;
            DataProvider dp = null;

            try
            {
                 dp = new DataProvider(connectionString, dbType);
                conn = dp.OpenConnection();
                IDataReader rdr = null;

                string SQLStatement = null;
                SQLStatement = "select count(*) from all_objects where object_type in ('TABLE')";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);                
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr == null) return false;
                rdr.Read();
                if (rdr.GetInt32(0) == 0) return false;

                rdr.Close();
                dp.CloseConnection(conn);
                return true;
            }
            catch (Exception ex)
            {
                dp.CloseConnection(conn);                
                return false;
            }
        }
    }
}
/*{
    
   public enum DatabaseType
   {
      Access,
      SQLServer,
      Oracle
      // any other data source type
   }

   public enum ParameterType
   {
      Integer,
      Char,
      VarChar
      // define a common parameter type set
   }

   public class DataFactory
   {
      private DataFactory(){}

      public static IDbConnection CreateConnection(string ConnectionString, DatabaseType dbtype)
      {
         IDbConnection cnn;

         switch(dbtype)
         {
            case DatabaseType.Access:
               cnn = new OleDbConnection
                  (ConnectionString); 
               break;
            case DatabaseType.SQLServer:
               cnn = new SqlConnection
                  (ConnectionString); 
               break;
            case DatabaseType.Oracle:
               cnn = new OracleConnection
                  (ConnectionString);
               break;
            default:
               cnn = new SqlConnection
                  (ConnectionString); 
               break;               
         }

         return cnn;
      }


      public static IDbCommand CreateCommand(string CommandText, DatabaseType dbtype, IDbConnection cnn)
      {
         IDbCommand cmd;
         switch(dbtype)
         {
            case DatabaseType.Access:
               cmd = new OleDbCommand
                  (CommandText,
                  (OleDbConnection)cnn);
               break;

            case DatabaseType.SQLServer:
               cmd = new SqlCommand
                  (CommandText,
                  (SqlConnection)cnn); 
               break;

            case DatabaseType.Oracle:
               cmd = new OracleCommand
                  (CommandText,
                  (OracleConnection)cnn);
               break;
            default:
               cmd = new SqlCommand
                  (CommandText,
                  (SqlConnection)cnn); 
               break;
         }

         return cmd;
      }


      public static DbDataAdapter CreateAdapter(IDbCommand cmd, DatabaseType dbtype)
      {
         DbDataAdapter da;
         switch(dbtype)
         {
            case DatabaseType.Access:
               da = new OleDbDataAdapter
                  ((OleDbCommand)cmd); 
               break;

            case DatabaseType.SQLServer:
               da = new SqlDataAdapter
                  ((SqlCommand)cmd); 
               break;

            case DatabaseType.Oracle:
               da = new OracleDataAdapter
                  ((OracleCommand)cmd); 
               break;

            default:
               da = new SqlDataAdapter
                  ((SqlCommand)cmd); 
               break;
         }

         return da;
      }
   }
}*/