package util;

public class Session {


	private static int userId;
	private static String userRole;

	public static int getUserId() {
		return userId;
	}

	public static void setUserId(int userId) {
		Session.userId = userId;
	}

	public static String getUserRole() {
		return userRole;
	}

	public static void setUserRole(String userRole) {
		Session.userRole = userRole;
	}


	

}
