package co.edu.uco.grades.data.factory.azuresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.crosscutting.util.sql.UtilConnection;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.crosscutting.exception.enumeration.ExceptionLocation;
import co.edu.uco.grades.data.dao.AttendanceDAO;
import co.edu.uco.grades.data.dao.CourseDAO;
import co.edu.uco.grades.data.dao.IdTypeDAO;
import co.edu.uco.grades.data.dao.ProfessorDAO;
import co.edu.uco.grades.data.dao.SessionDAO;
import co.edu.uco.grades.data.dao.StudentCourseDAO;
import co.edu.uco.grades.data.dao.StudentCourseStateDAO;
import co.edu.uco.grades.data.dao.StudentDAO;
import co.edu.uco.grades.data.dao.SubjectDAO;
import co.edu.uco.grades.data.dao.azuresql.AttendanceAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.CourseAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.IdTypeAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.ProfessorAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.SessionAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.StudentAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.StudentCourseAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.StudentCourseStateAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.SubjectAzureSqlDAO;
import co.edu.uco.grades.data.factory.DAOFactory;

public class AzureSqlDAOFactory extends DAOFactory {

	private Connection connection;

	private AzureSqlDAOFactory() {
		openConnection();
	}
	
	public static DAOFactory create() {
		return new AzureSqlDAOFactory();
	}

	@Override
	protected void openConnection() {

		String stringConnection = "jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser;password=4c4d3m1cDmlUs3r;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		try {
			connection = DriverManager.getConnection(stringConnection);

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalException(
					"There was a problem trying to get the connection with SQL server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
					exception, ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalException(
					"An unexpected problem has ocurred while trying to get the connection with SQL server", exception,
					ExceptionLocation.DATA);

		}

	}

	@Override
	protected Connection getConnection() {
		return connection;

	}

	@Override
	public void closeConnection() {
		if (UtilConnection.isClosed(getConnection())) {
			throw GradesException
					.buildTechnicalException("It's not possible to close a connection because the conection is closed");
		}

		try {
			getConnection().close();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalException(
					"There was a problem trying to close the connection with the SQL Server", exception,
					ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalException(
					"An unexpected problem has ocurred while trying to close the connection with the SQL Server",
					exception, ExceptionLocation.DATA);

		}

	}

	@Override
	public void rollbackTransaction() {
		if (UtilConnection.isClosed(connection)) {
			throw GradesException.buildTechnicalException(
					"It's not possible to rollback a connection because the conection is closed");
		}

		try {
			if (getConnection().getAutoCommit()) {
				throw GradesException.buildTechnicalException(
						"It's not possible to rollback a connection because the SQL server is managing the transaction");
			}

			getConnection().rollback();

		} catch (GradesException exception) {
			throw exception;

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalException("There was a problem trying to rollback the transaction",
					exception, ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalException(
					"An unexpected problem has ocurred while trying to rollback the transaction", exception,
					ExceptionLocation.DATA);

		}

	}

	@Override
	public void initTransaction() {
		if (UtilConnection.isClosed(connection)) {
			throw GradesException.buildTechnicalException(
					"It's not possible to initialize a connection because the conection is already closed");
		}

		try {
			if (!getConnection().getAutoCommit()) {
				throw GradesException.buildTechnicalException(
						"It's not possible to initialize a connection because it is already initialized");
			}
			getConnection().setAutoCommit(false);

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalException("There was a problem trying to initialize the transaction",
					exception, ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalException(
					"An unexpected problem has ocurred while trying to initialize the transaction", exception,
					ExceptionLocation.DATA);

		}

	}

	@Override
	public void commitTransaction() {
		if (UtilConnection.isClosed(connection)) {
			throw GradesException.buildTechnicalException(
					"It's not possible to commit a connection because the conection is already closed");
		}

		try {

			if (getConnection().getAutoCommit()) {
				throw GradesException.buildTechnicalException(
						"It's not possible to commit a connection because the SQL server is managing the transaction");
			}

			getConnection().commit();

		} catch (GradesException exception) {
			throw exception;

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalException("There was a problem trying to commit the transaction",
					exception, ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalException(
					"An unexpected problem has ocurred while trying to commit the transaction", exception,
					ExceptionLocation.DATA);

		}

	}

	@Override
	public StudentDAO getStudentDAO() {
		return StudentAzureSqlDAO.build(getConnection());
	}

	@Override
	public AttendanceDAO getAttendanceDAO() {
		return AttendanceAzureSqlDAO.build(getConnection());
	}

	@Override
	public CourseDAO getCourseDAO() {
		return CourseAzureSqlDAO.build(getConnection());
	}

	@Override
	public IdTypeDAO getIdtypeDAO() {
		return IdTypeAzureSqlDAO.build(getConnection());
	}

	@Override
	public ProfessorDAO getProfessorDAO() {
		return ProfessorAzureSqlDAO.build(getConnection());
	}

	@Override
	public SessionDAO getSessionDAO() {
		return SessionAzureSqlDAO.build(getConnection());
	}

	@Override
	public StudentCourseDAO getStudentCourseDAO() {
		return StudentCourseAzureSqlDAO.build(getConnection());
	}

	@Override
	public StudentCourseStateDAO getStudentCourseStateDAO() {
		return StudentCourseStateAzureSqlDAO.build(getConnection());
	}

	@Override
	public SubjectDAO getSubjectDAO() {
		return SubjectAzureSqlDAO.build(getConnection());
	}

}
