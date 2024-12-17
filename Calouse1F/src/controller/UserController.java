package controller;

import javafx.scene.control.Label;
import model.User;

import util.Session;


public class UserController {

	

	public static boolean Login(
			String username,
			String password,
			Label nameErrorMessage,
			Label passwordErrorMessage) {
		return isLoginInputValid(
				username,
				password,
				nameErrorMessage,
				passwordErrorMessage);
	}
	
	private static boolean isLoginInputValid(
			String username,
			String password,
			Label nameErrorMessage,
			Label passwordErrorMessage
	) {
		
		boolean result = true;
		
		if(username.isEmpty()) {
			nameErrorMessage.setManaged(true);
			nameErrorMessage.setText("Field can't be empty!");
			result = false;
		} else {
			nameErrorMessage.setManaged(false);
			nameErrorMessage.setText("");
		}
		
		if(password.isEmpty()) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Field can't be empty!");
			result = false;
		} else {
			passwordErrorMessage.setManaged(false);
			passwordErrorMessage.setText("");
		}
		
		// can`t be empty
		if(result == false) return false;
		
		// check for role admin
		if(username.equals("admin") && password.equals("admin")) {
			Session.setUserRole("admin");
			return true;
		} else if(username.equals("admin") && !password.equals("admin")) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Wrong password");
			return false;
		}
		
		User userData = User.getUserByUsername(username);
		if(userData == null) {
			nameErrorMessage.setManaged(true);
			nameErrorMessage.setText("Username not found");
			return false;
			} 
			else  if(!userData.getPassword().equals(password)) {
				passwordErrorMessage.setManaged(true);
				passwordErrorMessage.setText("Wrong password");
				return false;
		}
		
		Session.setUserId(userData.getId());
		Session.setUserRole(userData.getRole());
		Session.setUsername(userData.getUsername());
		
		return true;
		
	}
	
	public static boolean Register(
			String username,
			String password,
			String phoneNumber,
			String address,
			String role,
			Label nameErrorMessage,
			Label passwordErrorMessage,
			Label phoneNumberErrorMessage,
			Label addressErrorMessage,
			Label roleErrorMessage) {
		if(CheckAccountValidation(
				username,
				password,
				phoneNumber,
				address,
				role,
				nameErrorMessage,
				passwordErrorMessage,
				phoneNumberErrorMessage,
				addressErrorMessage,
				roleErrorMessage)
		) {			
			return User.Register(username, password, phoneNumber, address, role);
		}
		
		return false;
	}
	
	
	private static boolean CheckAccountValidation(
			String username,
			String password,
			String phoneNumber,
			String address,
			String role,
			Label nameErrorMessage,
			Label passwordErrorMessage,
			Label phoneNumberErrorMessage,
			Label addressErrorMessage,
			Label roleErrorMessage
	) {
		
		boolean result = true;
		
		if(username.isEmpty()) {
			nameErrorMessage.setManaged(true);
			nameErrorMessage.setText("Field can't be empty!");
			result = false;
		} 
		else if(username.length() < 3) {
				nameErrorMessage.setManaged(true);
				nameErrorMessage.setText("Minimum length: 3");
				result = false;
			} else if(username.toLowerCase().equals("admin")) {
				nameErrorMessage.setManaged(true);
				nameErrorMessage.setText("Username 'admin' is reserved!");
				result = false;
			}
			else{			
				User userData = User.getUserByUsername(username);
				if(userData != null && userData.getUsername().equals(username)) {
					nameErrorMessage.setManaged(true);
					nameErrorMessage.setText("Username already exists");
					result = false;
				} else {
				nameErrorMessage.setManaged(false);
				nameErrorMessage.setText("");
				}
			}
			
		
		
		if(password.isEmpty()) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Field can't be empty!");
			result = false;
		}else if(password.length() < 8) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Minimum length: 8");
			result = false;
		} else if(!(password.indexOf("!") != -1 ||
				password.indexOf("@") != -1 ||
				password.indexOf("#") != -1 ||
				password.indexOf("$") != -1 ||
				password.indexOf("%") != -1 ||
				password.indexOf("^") != -1 ||
				password.indexOf("&") != -1 ||
				password.indexOf("*") != -1)
		) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Must contain special character");
			result = false;
		} 
		else {
			
			passwordErrorMessage.setManaged(false);
			passwordErrorMessage.setText("");
		}
		
		if(phoneNumber.isEmpty()) {
			phoneNumberErrorMessage.setManaged(true);
			phoneNumberErrorMessage.setText("Field can't be empty!");
			result = false;
		} else if(!phoneNumber.startsWith("+62")) {
				phoneNumberErrorMessage.setManaged(true);
				phoneNumberErrorMessage.setText("Must start with +62");
				result = false;
			} else if(phoneNumber.length() != 12) {
				if(phoneNumber.length() > 12) {
					phoneNumberErrorMessage.setManaged(true);
					phoneNumberErrorMessage.setText("Phone Number can't be more than 10 digits");
					result = false;
				} else if(phoneNumber.length() < 12 ) {
					phoneNumberErrorMessage.setManaged(true);
					phoneNumberErrorMessage.setText("Phone Number can't be less than 10 digits");
				}
			} else {			
				String remainingPhoneNumber = phoneNumber.substring(3);
				try {
					Long.parseLong(remainingPhoneNumber);
				} catch (Exception e) {
					phoneNumberErrorMessage.setManaged(true);
					phoneNumberErrorMessage.setText("Must only contain numbers");
					result = false;
				}
				phoneNumberErrorMessage.setManaged(false);
				phoneNumberErrorMessage.setText("");
			}
			
		
		
		if(address.isEmpty()) {
			addressErrorMessage.setManaged(true);
			addressErrorMessage.setText("Field can't be empty!");
			result = false;
		} else {
			addressErrorMessage.setManaged(false);
			addressErrorMessage.setText("");
		}
		
		if(role.isEmpty()) {
			roleErrorMessage.setManaged(true);
			roleErrorMessage.setText("You have to choose a role!");
			result = false;
		} else {
			roleErrorMessage.setManaged(false);
			roleErrorMessage.setText("");
		}
		
		
		if(result == false) {
			return false;
		}
		
		
		
		return result;
		
	}
	

	
	
}
