import { fileConstants } from "../constants/FileConstants";

export const filesReducer = (state, action) => {
	switch (action.type) {

            case fileConstants.FILE_SUBMIT_REQUEST:
                return {
					...state,
					listFiles: {
						showError: false,
						errorMessage: "",
						files: [],
						showModal: false,
						header : "",
						text: ""
					},
                };
            case fileConstants.FILE_SUBMIT_FAILURE:
                return {
					...state,
					listFiles: {
						showError: true,
						errorMessage: action.errorMessage,
						files: action.files,
						showModal: true,
						header : "Error",
						text: "Error with file upload! Files exceed maximum permitted size!"
					},
                    
                };
            case fileConstants.FILE_SUBMIT_SUCCESS:
                return {
                    ...state,
					listFiles: {
						showError: false,
						errorMessage: "",
						files: action.files,
						showModal: true,
						header : "Success",
						text: "Success upload"
					},
                };
			case fileConstants.SET_FILES_REQUEST:
				return {
					...state,
					listFiles: {
						showError: false,
						errorMessage: "",
						files: [],
						showModal: false,
						header : "",
						text: ""
					},
				};
			case fileConstants.SET_FILES_SUCCESS:
				return {
					...state,
					listFiles: {
						showError: false,
						errorMessage: "",
						files: action.files,
						showModal: false,
						header : "",
						text: ""
					},
				};
			case fileConstants.SET_FILES_FAILURE:
				return {
					...state,
					listFiles: {
						showError: true,
						errorMessage: action.errorMessage,
						files: [],
						showModal: false,
						header : "",
						text: ""
					},
				};			
			case fileConstants.SET_FILE_REMOVE_REQUEST:
				return {
					...state,
					listFiles: {
						showError: false,
						errorMessage: "",
						files: [],
						showModal: false,
						header : "",
						text: ""
					},
				};
			case fileConstants.SET_FILE_REMOVE_SUCCESS:
				return {
					...state,
					listFiles: {
						showError: false,
						errorMessage: "",
						files: action.files,
						showModal: true,
						header : "Success",
						text: "Success delete"
					},
					
				};
			case fileConstants.SET_FILE_REMOVE_FAILURE:
				return {
					...state,
					listFiles: {
						showError: true,
						errorMessage: action.errorMessage,
						files: [],
						showModal: true,
						header : "Error",
						text: "Error with delete"
					},
				};	
			case fileConstants.HIDE_MODAL_INFO:
				return {
					...state,
					listFiles: {
						showError: false,
						errorMessage: "",
						files: action.files,
						showModal: false,
						header : "",
						text: ""
					},
				};							
				
		default:
			return state;
	}
};
