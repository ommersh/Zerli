package simulators;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBBoundry;
import database.DBController;

/**
 * simulate the import of the users data from the users management system
 * database to our database
 *
 */
public class UserDataImportFromDB {

	private String dbName = "UserManagmentSystem";

	/**
	 * get all the users from the "UserManagmentSystem" database into our users
	 * table in the database, print result to the simulation log
	 */
	public void importUserDataTable() {
		ArrayList<ImportedUserData> tableData = getTableFromDB(dbName);
		DBController dbController = DBController.getInstance();
		for (ImportedUserData user : tableData) {
			if (dbController.createNewUser(user)) {
				ServerSimulatorsManager.getInstance().SimulationsLog.add("Added user: " + user.getUserName());
			} else {
				ServerSimulatorsManager.getInstance().SimulationsLog.add("Failed to add user: " + user.getUserName());
			}
		}
	}

	/**
	 * get all the "users" table from the given db into arraylist
	 * 
	 * @param dbName
	 * @return
	 */
	private ArrayList<ImportedUserData> getTableFromDB(String dbName) {
		DBBoundry dbBoundry = DBController.getInstance().getDbBoundry();
		String s = "SELECT * FROM " + dbName + ".users";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		ArrayList<ImportedUserData> arr = new ArrayList<ImportedUserData>();
		ImportedUserData temp;
		try {
			while (res.next()) {
				temp = new ImportedUserData();
				temp.setUserName(res.getString("username"));
				temp.setPassword(res.getString("password"));
				temp.setEmail(res.getString("email"));
				temp.setType(res.getString("userType"));
				temp.setPhone(res.getString("phone"));
				temp.setFirstname(res.getString("firstname"));
				temp.setLastname(res.getString("lastname"));
				temp.setBranch(res.getString("branch"));
				temp.setId(res.getString("personID"));
				arr.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
}
