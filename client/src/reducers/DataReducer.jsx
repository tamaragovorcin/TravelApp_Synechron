import { modalConstants } from "../constants/ModalConstants";
import { dataConstants } from "../constants/DataConstants";
import { travelInfoConstants } from "../constants/TravelInfoConstants";
import { transferInfoConstants } from "../constants/TransferInfoConstants";

var prodCpy = {};

export const dataReducer = (state, action) => {

	switch (action.type) {

		case modalConstants.SHOW_EDIT_DATA_MODAL:
			prodCpy = { ...state };
			if (action.data.employee != null) {
				prodCpy.updateData.data.employeeDetails = action.data.employee;
			}

			if (action.data.accommodationDetails != null) {
				prodCpy.updateData.data.accommodationDetails = action.data.accommodationDetails;
			}
			else{
				prodCpy.updateData.data.accommodationDetails = {};
				prodCpy.updateData.data.accommodationDetails.employeeId = action.data.employee.employeeId;
				prodCpy.updateData.data.accommodationDetails.travelDetails = []
				prodCpy.updateData.data.accommodationDetails.travelDetails.push(action.data);
			}
			if (action.data != null) {
				console.log(action.data)
				prodCpy.updateData.data.travelDetails = action.data;
			}

			prodCpy.updateData.showModal = true;
			return prodCpy;

		case modalConstants.HIDE_EDIT_DATA_MODAL:
			prodCpy = { ...state };
			prodCpy.updateData.showModal = false;
			return prodCpy;

		case dataConstants.FIND_BY_ID_DATA_REQUEST:
			return state;

		case dataConstants.FIND_BY_ID_DATA_SUCCESS:
			prodCpy = { ...state };

			prodCpy.updateData.data = action.data;
			prodCpy.updateData.imageSelected = true;
			prodCpy.updateData.showedImage = action.data.imageUrl;
			return prodCpy;

		case dataConstants.FIND_BY_ID_DATA_FAILURE:
			return state;


		case dataConstants.EDIT_DATA_SUCCESS:
			prodCpy = { ...state };
			console.log(action.data)
			prodCpy.listTravelInfo.travelInfo= action.data;
			
			prodCpy.updateData.showModal = false;
			return prodCpy;

		case dataConstants.EDIT_DATA_FAILURE:
			prodCpy = { ...state };
			prodCpy.updateData.showErrorMessage = true;
			prodCpy.updateData.errorMessage = action.errorMessage;
			return prodCpy;

		case travelInfoConstants.SET_TRAVEL_INFO_REQUEST:
			return {
				...state,
				listTravelInfo: {
					showError: false,
					errorMessage: "",
					travelInfo: [],
				},
			};
		case travelInfoConstants.SET_TRAVEL_INFO_SUCCESS:
			return {
			
				...state,
				listTravelInfo: {
					showError: false,
					errorMessage: "",
					travelInfo: action.travelInfo,
				},
			};
		case travelInfoConstants.SET_TRAVEL_INFO_FAILURE:
			return {
				...state,
				listTravelInfo: {
					showError: true,
					errorMessage: action.errorMessage,
					travelInfo: [],
				},
			};
		
			case transferInfoConstants.SET_TRANSFER_INFO_REQUEST:
				return {
					...state,
					listTransferInfo: {
						showError: false,
						errorMessage: "",
						transferInfo: [],
					},
				};
			case transferInfoConstants.SET_TRANSFER_INFO_SUCCESS:
				return {
					...state,
					listTransferInfo: {
						showError: false,
						errorMessage: "",
						transferInfo: action.transferInfo,
					},
				};
			case transferInfoConstants.SET_TRANSFER_INFO_FAILURE:
				return {
					...state,
					listTransferInfo: {
						showError: true,
						errorMessage: action.errorMessage,
						transferInfo: [],
					},
				};

		case modalConstants.SHOW_UPLOAD_FILES_MODAL:
			prodCpy = { ...state };
			prodCpy.uploadFiles.showModal = true;		
			prodCpy.uploadFiles.travelReqID = action.travelReqID;
			return prodCpy;
			
		case modalConstants.HIDE_UPLOAD_FILES_MODAL:
			prodCpy = { ...state };
			prodCpy.uploadFiles.showModal = false;
			prodCpy.uploadFiles.travelReqID = "";
			prodCpy.uploadFiles.showErrorMessage = "";
			prodCpy.uploadFiles.showErrorMessage = "";
			return prodCpy;	
			
		case modalConstants.SHOW_RESERVE_TRANSFER_MODAL:
			prodCpy = { ...state };
			prodCpy.reserveTransfer.showModal = true;	
			prodCpy.reserveTransfer = action.data;
			return prodCpy;
			
		case modalConstants.HIDE_RESERVE_TRANSFER_MODAL:
			prodCpy = { ...state };
			prodCpy.reserveTransfer.showModal = false;
			prodCpy.reserveTransfer.transferCity = "";
			prodCpy.reserveTransfer.transferAddress = "";
			prodCpy.reserveTransfer.additionalComments = "";
			prodCpy.reserveTransfer.reservationDate = null;
            prodCpy.reserveTransfer.reservationTime = null;
            prodCpy.reserveTransfer.transferDate = null;
            prodCpy.reserveTransfer.transferTime = null;
			return prodCpy;	

	
		default:
			return state;
	}
};
