package util;

public class Session {


	private static int userId;
	private static String username;
	private static String userRole;
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Session.username = username;
	}

	

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
