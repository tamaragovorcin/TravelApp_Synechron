import React, { createContext, useReducer } from "react";
import { dataReducer } from "../reducers/DataReducer";

export const DataContext = createContext();

const DataContextProvider = (props) => {

	const [dataState, dispatch] = useReducer(dataReducer, {

		listTravelInfo: {
			showError: false,
			errorMessage: "",
			travelInfo: [],
		},

		listTransferInfo: {
			showError: false,
			errorMessage: "",
			transferInfo: [],
		},


		updateData: {
			showModal: false,
			showErrorMessage: false,
			errorMessage: "",
			data: {
				accommodationDetails: {
					accountName: "",
					checkInDate: null,
					checkOutDate: null,
					accommodationRequestNo: "",
					bookingPurpose: "",
					billingDetails: "",
					employeeId: "",
					travelDetails: [],
				},
				travelDetails: {
					departFrom: "",
					arriveTo: "",
					departureDate: null,
					travelReqID: "",
					accountName: "",
					approvedBy: "",
					employee: "",
					visaDetail: "",
					tripPaidBy: "",
					arrivalTime: null,
					arrivalDate: null,
					departureTime: null,
					departingCountry: "",
					arrivalCountry: "",
					additionalComments: "",
					bookingPurpose: "",
					partOfTheDayFrom: null,
					partOfTheDayTo: null,
					accommodationDetails: null,
				},
				employeeDetails: {
					employeeId: "",
					email: "",
					firstName: "",
					lastName: "",
					designationName: ""
				}

			},
		},
		uploadFiles: {
			showModal: false,
			travelReqID: "",
			showErrorMessage: false,
			errorMessage: "",
		},

		reserveTransfer: {
			showModal: false,
			id : "",
			reservationDate : null,
            reservationTime : null,
            transferDate : null,
            transferTime : null,
            transferCity : "",
            transferAddress : "",
            additionalComments : "",
		}

	});

	return <DataContext.Provider value={{ dataState, dispatch }}>{props.children}</DataContext.Provider>;
};

export default DataContextProvider;
