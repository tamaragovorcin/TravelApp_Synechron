import React, { createContext, useReducer } from "react";
import { filesReducer } from "../reducers/FilesReducer";

export const FilesContext = createContext();

const FilesContextProvider = (props) => {
	const [filesState, dispatch] = useReducer(filesReducer, {
		fileSubmitError: {
			showError: false,
			errorMessage: "",
		},
		
		listFiles: {
			showError: false,
			errorMessage: "",
			files: [],
			showModal: false,
			header : "",
			text: ""
		},
		
	});

	return <FilesContext.Provider value={{ filesState, dispatch }}>{props.children}</FilesContext.Provider>;
};

export default FilesContextProvider;