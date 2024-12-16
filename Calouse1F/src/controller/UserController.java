package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import model.User;
import util.Connect;
import util.Session;


public class UserController {

	

	public static boolean Login(
			String name,
			String password,
			Label nameErrorMessage,
			Label passwordErrorMessage) {
		return isLoginInputValid(
				name,
				password,
				nameErrorMessage,
				passwordErrorMessage);
	}
	
	private static boolean isLoginInputValid(
			String name,
			String password,
			Label nameErrorMessage,
			Label passwordErrorMessage
	) {
		
		boolean result = true;
		
		if(name.isEmpty()) {
			nameErrorMessage.setManaged(true);
			nameErrorMessage.setText("Must be filled");
			result = false;
		} else {
			nameErrorMessage.setManaged(false);
			nameErrorMessage.setText("");
		}
		
		if(password.isEmpty()) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Must be filled");
			result = false;
		} else {
			passwordErrorMessage.setManaged(false);
			passwordErrorMessage.setText("");
		}
		
		// can`t be empty
		if(result == false) return false;
		
		// check for role admin
		if(name.equals("admin") && password.equals("admin")) {
			Session.setUserRole("admin");
			return true;
		}
		
		User userData = User.getUserByUsername(name);
		if(userData == null) {
			nameErrorMessage.setManaged(true);
			nameErrorMessage.setText("Username not found");
			return false;
		}
		
		if(!userData.getPassword().equals(password)) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Wrong password");
			return false;
		}
		
		Session.setUserId(userData.getId());
		Session.setUserRole(userData.getRole());
		
		return true;
		
	}
	
	public static boolean Register(
			String name,
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
				name,
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
			return User.Register(name, password, phoneNumber, address, role);
		}
		
		return false;
	}
	
	
	private static boolean CheckAccountValidation(
			String name,
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
		
		if(name.isEmpty()) {
			nameErrorMessage.setManaged(true);
			nameErrorMessage.setText("Must be filled");
			result = false;
		} 
		else if(name.length() < 3) {
				nameErrorMessage.setManaged(true);
				nameErrorMessage.setText("Minimum length: 3");
				result = false;
			} else {			
				User userData = User.getUserByUsername(name);
				if(userData != null && userData.getUsername().equals(name)) {
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
			passwordErrorMessage.setText("Must be filled");
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
			phoneNumberErrorMessage.setText("Must be filled");
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
					phoneNumberErrorMessage.setText("Must only contain number");
					result = false;
				}
				phoneNumberErrorMessage.setManaged(false);
				phoneNumberErrorMessage.setText("");
			}
			
		
		
		if(address.isEmpty()) {
			addressErrorMessage.setManaged(true);
			addressErrorMessage.setText("Must be filled");
			result = false;
		} else {
			addressErrorMessage.setManaged(false);
			addressErrorMessage.setText("");
		}
		
		if(role.isEmpty()) {
			roleErrorMessage.setManaged(true);
			roleErrorMessage.setText("Must be filled");
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
