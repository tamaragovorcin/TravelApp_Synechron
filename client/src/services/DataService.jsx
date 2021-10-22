import Axios from "axios";
import { dataConstants } from "../constants/DataConstants";


import { travelInfoConstants } from "../constants/TravelInfoConstants";
import { transferInfoConstants } from "../constants/TransferInfoConstants";
export const dataService = {
	findAllTravelRequests,
	findById,
	updateAccommodationInfo,
	updateTravelRequestInfo,
	findAllTravelRequestsBySearch,
	findAllTransferRequests,
	updateTransferInfo,
	reserveTransfer,
	getDate,
	getTime,
	searchTransfer,
	getValidDate,
	getDecisionAsExel,
	getCalculationAsExel,
};


async function findAllTravelRequestsBySearch(searchRequest, dispatch) {
	dispatch(request());

	if (searchRequest.departureDateString !== "" && searchRequest.arrivalDateString !== "") {
		searchRequest.departureDateString = getDate(searchRequest.departureDateString);
		searchRequest.arrivalDateString = getDate(searchRequest.arrivalDateString);
	}

	await Axios.post(`http://localhost:8085/api/travel-info/search`, searchRequest, { validateStatus: () => true })
		.then((res) => {
			if (res.status === 200) {
				dispatch(success(res.data));
			} else {
				dispatch(failure("Error while fetching data"));
			}
		})
		.catch((err) => {
			console.log(err);
			dispatch(failure("Error"));
		});

	function request() {
		return { type: travelInfoConstants.SET_TRAVEL_INFO_REQUEST };
	}
	function success(data) {
		return { type: travelInfoConstants.SET_TRAVEL_INFO_SUCCESS, travelInfo: data };
	}
	function failure(message) {
		return { type: travelInfoConstants.SET_TRAVEL_INFO_FAILURE, errorMessage: message };
	}
}

async function findAllTravelRequests(datesRequest, dispatch) {
	
	dispatch(request());
	var url = `http://localhost:8085/api/travel-info/`;
	if (datesRequest.startDate !== "" && datesRequest.endDate !== "") {
		url += getDate(datesRequest.startDate) + "/" + getDate(datesRequest.endDate);
	}
	await Axios.get(url, { validateStatus: () => true })
		.then((res) => {
			if (res.status === 200) {
				console.log(res.data);
				dispatch(success(res.data));
			} else {
				dispatch(failure("Error while fetching data"));
			}
		})
		.catch((err) => {
			console.log(err);
			dispatch(failure("Error"));
		});

	function request() {
		return { type: travelInfoConstants.SET_TRAVEL_INFO_REQUEST };
	}
	function success(data) {
		return { type: travelInfoConstants.SET_TRAVEL_INFO_SUCCESS, travelInfo: data };
	}
	function failure(message) {
		return { type: travelInfoConstants.SET_TRAVEL_INFO_FAILURE, errorMessage: message };
	}
}

async function findAllTransferRequests(datesRequest, dispatch) {
	dispatch(request());
	var url =  `http://localhost:8085/api/transfer-info/`;
	if(datesRequest.startDate!=="" && datesRequest.endDate!=="") {
		url+=getDate(datesRequest.startDate)+"/"+getDate(datesRequest.endDate);
	}
	await Axios.get(url, { validateStatus: () => true })
		.then((res) => {
			if (res.status === 200) {

				dispatch(success(res.data));
			} else {
				dispatch(failure("Error while fetching data"));
			}
		})
		.catch((err) => {
			console.log(err);
			dispatch(failure("Error"));
		});

	function request() {
		return { type: transferInfoConstants.SET_TRANSFER_INFO_REQUEST };
	}
	function success(data) {
		return { type: transferInfoConstants.SET_TRANSFER_INFO_SUCCESS, transferInfo: data };
	}
	function failure(message) {
		return { type: transferInfoConstants.SET_TRANSFER_INFO_FAILURE, errorMessage: message };
	}
}


function updateTransferInfo(datesRequest, updateRequest, dispatch) {
	console.log(updateRequest)
	dispatch(request());
	Axios.post(`http://localhost:8085/api/transfer-details/update`, updateRequest, { validateStatus: () => true })
		.then((res) => {
			console.log(res);
			if (res.status === 200) {
				console.log("Uspeo je")
				dispatch(success("Success"));
				findAllTransferRequests(datesRequest,dispatch);
			} else {
				dispatch(failure("Error while editing data"));
			}
		})
		.catch((err) => {
			console.log(err);
			dispatch(failure("Error"));
		});

		function request() {
			return { type: transferInfoConstants.SET_TRANSFER_INFO_REQUEST };
		}
		function success(data) {
			return { type: transferInfoConstants.SET_TRANSFER_INFO_SUCCESS, transferInfo: data };
		}
		function failure(message) {
			return { type: transferInfoConstants.SET_TRANSFER_INFO_FAILURE, errorMessage: message };
		}
}


async function reserveTransfer(datesRequest, updateRequest, dispatch) {
	
	dispatch(request());
	await Axios.post(`http://localhost:8085/api/transfer-info/reserve`, updateRequest, { validateStatus: () => true })
		.then((res) => {
			console.log(res);
			if (res.status === 200) {
				dispatch(success("Success"));
				findAllTransferRequests(datesRequest,dispatch);
			} else {
				dispatch(failure("Error while editing data"));
			}
		})
		.catch((err) => {
			console.log(err);
			dispatch(failure("Error"));
		});

		function request() {
			return { type: transferInfoConstants.SET_TRANSFER_INFO_REQUEST };
		}
		function success(data) {
			return { type: transferInfoConstants.SET_TRANSFER_INFO_SUCCESS, transferInfo: data};
		}
		function failure(message) {
			return { type: transferInfoConstants.SET_TRANSFER_INFO_FAILURE, errorMessage: message };
		}
}

function searchTransfer(searchRequest, dispatch) {
	dispatch(request());
	Axios.post(`http://localhost:8085/api/transfer-info/search`, searchRequest, { validateStatus: () => true })
		.then((res) => {
			console.log(res);
			if (res.status === 200) {
				dispatch(success(res.data));
			} else {
				dispatch(failure("Error while finding data"));
			}
		})
		.catch((err) => {
			console.log(err);
			dispatch(failure("Error"));
		});

		function request() {
			return { type: transferInfoConstants.SET_TRANSFER_INFO_REQUEST };
		}
		function success(data) {
			return { type: transferInfoConstants.SET_TRANSFER_INFO_SUCCESS, transferInfo: data };
		}
		function failure(message) {
			return { type: transferInfoConstants.SET_TRANSFER_INFO_FAILURE, errorMessage: message };
		}
}

function getValidDate(selectedDate){
	var date = selectedDate.toLocaleDateString('en-US')
	var pieces = date.split("/");
	var month = pieces[0]
	if(parseInt(month)<10){
		month = "0"+month;
	}
	var day = pieces[1];
	if(parseInt(day)<10){
		day = "0"+day;
	}
	var year = pieces[2]
	return year + "-"+month+"-"+day
}

function getDate(selectedDate) {
	if(selectedDate[4] != "-" || selectedDate == null){
		return null;
	}
	
	var date = selectedDate
	var pieces = date.split("-");
	var year = pieces[0]
	var month = pieces[1]
	var day = pieces[2]
	return year + "-" + month + "-" + day
}

function getTime(selectedDate) {
	var pieces = selectedDate.toString().split(" ");
	var time = pieces[4].split(":");
	var hours = time[0]
	var minutes = time[1]
	var seconds = time[2]
	return hours + ":"+minutes+":"+seconds
}



function updateAccommodationInfo(updateRequest, dispatch) {
console.log(updateRequest)
	dispatch(request());
	Axios.post(`http://localhost:8085/api/accomodation/update`, updateRequest, { validateStatus: () => true })
		.then((res) => {
			console.log(res);
			if (res.status === 200) {
				dispatch(success(res.data));
			} else {
				dispatch(failure("Error while editing data"));
			}
		})
		.catch((err) => {
			console.log(err);
			dispatch(failure("Error"));
		});

	function request() {
		return { type: dataConstants.EDIT_DATA_REQUEST };
	}
	function success(data) {
		return { type: dataConstants.EDIT_DATA_SUCCESS, data: data };
	}
	function failure(message) {
		return { type: dataConstants.EDIT_DATA_FAILURE, errorMessage: message };
	}
}

function updateTravelRequestInfo(updateRequest, dispatch) {
	console.log(updateRequest)
	dispatch(request());
	Axios.post(`http://localhost:8085/api/travel-details/update`, updateRequest, { validateStatus: () => true })
		.then((res) => {
			console.log(res);
			if (res.status === 200) {
				dispatch(success(res.data));
			} else {
				dispatch(failure("Error while editing data"));
			}
		})
		.catch((err) => {
			console.log(err);
			dispatch(failure("Error"));
		});

	function request() {
		return { type: dataConstants.EDIT_DATA_REQUEST };
	}
	function success(data) {
		return { type: dataConstants.EDIT_DATA_SUCCESS, data: data };
	}
	function failure(message) {
		return { type: dataConstants.EDIT_DATA_FAILURE, errorMessage: message };
	}
}

async function findById(id, dispatch) {
	dispatch(request());

	await Axios.get(`/api/data/${id}`, { validateStatus: () => true })
		.then((res) => {
			console.log(res);
			if (res.status === 200) {
				dispatch(success(res.data));
			} else {
				dispatch(failure("Error while fetching data"));
			}
		})
		.catch((err) => {
			console.log(err);
			dispatch(failure("Error"));
		});

	function request() {
		return { type: dataConstants.FIND_BY_ID_DATA_REQUEST };
	}
	function success(data) {
		return { type: dataConstants.FIND_BY_ID_DATA_SUCCESS, DATA: data };
	}
	function failure(message) {
		return { type: dataConstants.FIND_BY_ID_DATA_FAILURE, errorMessage: message };
	}
}
async function getDecisionAsExel(decisionId, dispatch) {
	const FileDownload = require("js-file-download");

	await Axios.get(`http://localhost:8085/api/decision/file/${decisionId}`, { validateStatus: () => true, responseType: "blob" })
		.then((res) => {
			if (res.status === 200) {
				FileDownload(res.data, "decision_" + decisionId + ".xls");
			}
		})
		.catch((err) => {
			console.log(err);
		});

}

async function getCalculationAsExel(travelReqId, dispatch) {
	const FileDownload = require("js-file-download");

	await Axios.get(`http://localhost:8085/api/decision/calculation/${travelReqId}`, { validateStatus: () => true, responseType: "blob" })
		.then((res) => {
			if (res.status === 200) {
				FileDownload(res.data, "calculation_" + travelReqId + ".xls");
			}
		})
		.catch((err) => {
			console.log(err);
		});

}