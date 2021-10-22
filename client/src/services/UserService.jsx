import Axios from "axios";
import { userConstants } from "../constants/UserConstants";
import { deleteLocalStorage, setAuthInLocalStorage } from "../helpers/auth-header";

export const userService = {
	login,
	logout,
	checkIfUserIdExist,
};

function login(loginRequest, dispatch) {
	dispatch(request());
	Axios.post(`http://localhost:8080/api/auth/login`, loginRequest, { validateStatus: () => true })
		.then((res) => {
			if (res.status === 200) {
				setAuthInLocalStorage(res.data);
				dispatch(success());
				window.location = "#/";
			} else if (res.status === 401) {
				dispatch(failure(res.data.message));
			} else if (res.status === 403) {
				window.location = "#/blocked-user/" + res.data.userId;
			} else {
				dispatch({ type: userConstants.LOGIN_FAILURE });
			}
		})
		.catch((err) => console.error(err));

	function request() {
		return { type: userConstants.LOGIN_SUCCESS };
	}
	function success() {
		return { type: userConstants.LOGIN_SUCCESS };
	}
	function failure(error) {
		return { type: userConstants.LOGIN_FAILURE, error };
	}
}

function logout() {
	deleteLocalStorage();
	window.location = "#/login";
}


function checkIfUserIdExist(userId, dispatch) {
	Axios.get(`/api/users/check-existence/` + userId, { validateStatus: () => true })
		.then((res) => {
			if (res.status === 200) {
				dispatch(success(res.data.emailAddress));
			} else if (res.status === 404) {
				window.location = "#/404";
			}
		})
		.catch((err) => {});

	function success(emailAddress) {
		return { type: userConstants.BLOCKED_USER_EMAIL_REQUEST, emailAddress };
	}
}
