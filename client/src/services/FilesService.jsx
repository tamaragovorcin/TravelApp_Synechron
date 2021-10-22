import Axios from "axios";
import { fileConstants } from "../constants/FileConstants";


export const filesService = {
	fileSubmit,
	getAllFiles,
	removeFile,
	getFile
};

async function fileSubmit(files,travelReqID,dispatch) {

	dispatch(request());

	const formData = new FormData();
        for(let i = 0; i< files.length; i++) {
            formData.append('files', files[i].file)
        }
	 
	Axios.post(`http://localhost:8085/api/files/${travelReqID}`, formData,{
		headers: {
		  "Content-Type": "multipart/form-data;boundary=10",
		}})
		  .then((res) => {
			if (res.status === 200) {
			
				dispatch(success(res.data));
			}else {
				dispatch(failure("Error while fetching data"));
				alert("Maximum permitted size is exceeded!")
			}
		  })
		  .catch((err) => {
			dispatch(failure("Error"));
			console.log(err);
		});

		function request() {
			return { type: fileConstants.FILE_SUBMIT_REQUEST };
		}
		function success(data) {

			return { type: fileConstants.FILE_SUBMIT_SUCCESS, files: data };
		}
		function failure(message) {

			return { type: fileConstants.FILE_SUBMIT_FAILURE,errorMessage : message };
		}
};

async function getAllFiles(travelReqID,dispatch) {

	dispatch(request());

	await Axios.get(`http://localhost:8085/api/files/all/${travelReqID}`, { validateStatus: () => true })
		.then((res) => {
			if (res.status === 200) {
				dispatch(success(res.data));
			}else {
				dispatch(failure("Error while fetching data"));
			}
		})
		.catch((err) => {
			dispatch(failure("Error"));
			console.log(err);
		});

		function request() {
			return { type: fileConstants.SET_FILES_REQUEST };
		}
		function success(data) {

			return { type: fileConstants.SET_FILES_SUCCESS, files: data };
		}
		function failure(message) {

			return { type: fileConstants.SET_FILES_FAILURE, errorMessage : message };
		}
};

async function removeFile(fileName,dispatch) {

	dispatch(request());

	await Axios.delete(`http://localhost:8085/api/files/${fileName}`, { validateStatus: () => true })
		.then((res) => {
			if (res.status === 200) {
				dispatch(success(res.data));
			}else {
				dispatch(failure("Error while fetching data"));
			}
		})
		.catch((err) => {
			dispatch(failure("Error"));
			console.log(err);
		});

		function request() {

			return { type: fileConstants.SET_FILE_REMOVE_REQUEST };
		}
		function success(data) {
			return { type: fileConstants.SET_FILE_REMOVE_SUCCESS, files: data };

		}
		function failure(message) {
		
			return { type: fileConstants.SET_FILE_REMOVE_FAILURE, errorMessage : message };
		}
};

async function getFile(fileName, dispatch) {
	const FileDownload = require("js-file-download");

	await Axios.get(`http://localhost:8085/api/files/${fileName}`, { validateStatus: () => true, responseType: "blob" })
		.then((res) => {
			if (res.status === 200) {
				FileDownload(res.data, fileName);
			}
		})
		.catch((err) => {
			console.log(err);

		});

}
