import { userConstants } from "../constants/UserConstants";

export const userReducer = (state, action) => {
	switch (action.type) {

		case userConstants.LOGIN_FAILURE:
			return {
				loginError: {
					showError: true,
					errorMessage: "Sorry, your email or password was incorrect. Please double-check your password.",
				},
			};
		case userConstants.LOGIN_SUCCESS:
			return {
				loginError: {
					showError: false,
					errorMessage: "",
				},
			};

		default:
			return state;
	}
};
