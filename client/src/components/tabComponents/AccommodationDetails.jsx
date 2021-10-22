import React, { Component, useState, useContext } from "react";
import { DataContext } from "../../contexts/DataContext.jsx";
import { dataService } from "../../services/DataService.jsx";
import Datetime from 'react-datetime';
import "react-datetime/css/react-datetime.css";
const AccommodationDetails = () => {


    const { dataState, dispatch } = useContext(DataContext);
    const [accountName, setAccountName] = useState(dataState.updateData.data.accommodationDetails.accountName);
    const [checkInDateString, setCheckInDate] = useState(dataState.updateData.data.accommodationDetails.checkInDate);
    const [employeeId] = useState(dataState.updateData.data.accommodationDetails.employeeId);
    const [travelDetails] = useState(dataState.updateData.data.accommodationDetails.travelDetails);
    const [checkOutDateString, setCheckOutDate] = useState(dataState.updateData.data.accommodationDetails.checkOutDate);
    const [accommodationRequestNo] = useState(dataState.updateData.data.accommodationDetails.accommodationRequestNo);
    const [bookingPurpose, setBookingPurpose] = useState(dataState.updateData.data.accommodationDetails.bookingPurpose);
    const [billingDetails, setBillingDetails] = useState(dataState.updateData.data.accommodationDetails.billingDetails);
    const [previousId] = useState(dataState.updateData.data.accommodationDetails.previousId);
    const [updateDate] = useState(dataState.updateData.data.accommodationDetails.updateDate);
    const [updateTime] = useState(dataState.updateData.data.accommodationDetails.updateTime);
    const [id] = useState(dataState.updateData.data.accommodationDetails.id);

    const [checkInDateError, setCheckInDateError] = useState("none");
    const [checkOutDateError, setCheckOutDateError] = useState("none");
    const [hiddenEditInfo, setHiddenEditInfo] = useState(true);

    const regexDate = /^([01][0-9])\/([0-3][0-9])\/([2][0][0-9][0-9]) ([0-2][0-9]):([0-6][0-9])$/;
    let date = "";
    let dateCheckOut = "";

    if (checkInDateString != null) {
        if (checkInDateString[1] === "/" || checkInDateString[2] === '/') {
            date = checkInDateString

        }
        else if (regexDate.test(checkInDateString.target.value) === true) {
            date = checkInDateString.target.value
        }

    }

    var valid = function (current) {
        if (date != "") {
            return current.isAfter(Date.parse(date));
        } else {
            return true;
        }
    };

    if (checkOutDateString != null) {
        if (checkOutDateString[1] === "/" || checkOutDateString[2] === '/') {
            dateCheckOut = checkOutDateString

        }
        else if (regexDate.test(checkOutDateString.target.value) === true) {
            dateCheckOut = checkOutDateString.target.value
        }
    }

    var validCheckOut = function (current) {
        if (dateCheckOut != "") {
            return current.isBefore(Date.parse(dateCheckOut));
        } else {
            return true;
        }
    };

    const handleChange = (e) => {
        setCheckInDateError("none")
        setCheckOutDateError("none")
        let checkInDate = null;
        let checkOutDate = null;

        console.log(checkOutDateString)


        if (checkInDateString != null) {
            if (checkInDateString[1] === "/" || checkInDateString[2] === '/') {
                checkInDate = checkInDateString

            }
            else if (regexDate.test(checkInDateString.target.value) === true || checkInDateString.target.value === "") {
                checkInDate = checkInDateString.target.value
            }
            else {
                setCheckInDateError("initial")
                return;
            }
        }
        if (checkOutDateString != null) {
            if (checkOutDateString[1] === '/' || checkOutDateString[2] === '/') {
                checkOutDate = checkOutDateString

            }
            else if (regexDate.test(checkOutDateString.target.value) === true || checkOutDateString.target.value === "") {
                checkOutDate = checkOutDateString.target.value
            }
            else {
                setCheckOutDateError("initial")
                return;
            }

        }


        e.preventDefault();
        setHiddenEditInfo(false)
        let updateRequest = {
            id,
            accountName,
            checkInDate,
            checkOutDate,
            accommodationRequestNo,
            bookingPurpose,
            billingDetails,
            employeeId,
            travelDetails,
            previousId,
            updateDate,
            updateTime
        };

        dataService.updateAccommodationInfo(updateRequest, dispatch);
    };

    return (
        <React.Fragment>

            <div className="container" >


                <div className="row mt-5">
                    <div className="col shadow p-3 bg-white rounded">

                        <form id="accommodationDetails" >

                            <div className="form-group text-center" hidden={!hiddenEditInfo}>
                                <button
                                    style={{ background: "#1977cc", marginTop: "15px", marginLeft: "750px" }}
                                    onClick={(e) => setHiddenEditInfo(false)}
                                    className="btn btn-primary btn-xl"
                                    id="sendMessageButton"
                                    type="button"
                                >
                                    Change information
                                </button>
                            </div>
                            <div className="form-group text-center" hidden={hiddenEditInfo}>
                                <button
                                    style={{ background: "#1977cc", marginTop: "15px", marginLeft: "850px" }}
                                    onClick={(e) => handleChange(e)}
                                    className="btn btn-primary btn-xl"
                                    id="sendMessageButton"
                                    type="button"
                                >
                                    Change
                                </button>
                            </div>
                            <table style={{ marginLeft: "18rem", marginBottom: "4rem" }}>
                                <tbody>
                                    <tr>

                                        <td width="600rem"  >

                                            <div className="control-group">
                                                <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                                    <label><b>Account name</b></label>
                                                    <div class="row" >
                                                        <div class="form-group col-lg-10">
                                                            <input
                                                                readOnly={hiddenEditInfo}
                                                                className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                                placeholder="Account name"
                                                                aria-describedby="basic-addon1"
                                                                id="name"
                                                                type="text"
                                                                style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}

                                                                onChange={(e) => setAccountName(e.target.value)}
                                                                value={accountName}
                                                            />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="control-group">
                                                <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                                    <label><b>Check in date</b></label>
                                                    <div class="row" >
                                                        <div class="form-group col-lg-10">
                                                            <Datetime timeFormat="HH:mm" isValidDate={validCheckOut}
                                                                inputProps={{
                                                                    disabled: hiddenEditInfo,
                                                                    style: { backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' },
                                                                    placeholder: checkInDateString == null ? "Check in date" : checkInDateString,
                                                                    onClick: setCheckInDate
                                                                    
                                                                }}

                                                            />
                                                        </div>
                                                    </div>
                                                    <div className="text-danger" style={{ display: checkInDateError }}>
                                                        Check in date must be entered in format MM/dd/YYYY HH:mm
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="control-group">
                                                <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                                    <label><b>Check out date</b></label>
                                                    <div class="row" >
                                                        <div class="form-group col-lg-10">
                                                            <Datetime timeFormat="HH:mm" isValidDate={valid}
                                                                inputProps={{
                                                                    disabled: hiddenEditInfo,
                                                                    style: { backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' },
                                                                    placeholder: checkOutDateString == null ? "Check out date" : checkOutDateString,
                                                                    onClick: setCheckOutDate
                                                                }
                                                                }

                                                            />

                                                        </div>
                                                    </div>
                                                    <div className="text-danger" style={{ display: checkOutDateError }}>
                                                        Check out date must be entered in format MM/dd/YYYY HH:mm
                                                    </div>
                                                </div>
                                            </div>


                                            <div className="control-group">
                                                <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                                    <label><b>Booking purpose</b></label>
                                                    <div class="row" >
                                                        <div class="form-group col-lg-10">
                                                            <input
                                                                readOnly={hiddenEditInfo}
                                                                className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                                style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                                placeholder="Booking purpose"
                                                                type="text"
                                                                aria-describedby="basic-addon1"
                                                                onChange={(e) => setBookingPurpose(e.target.value)}
                                                                value={bookingPurpose}
                                                            />
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>

                                            <div className="control-group">
                                                <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                                    <label><b>Billing details</b></label>
                                                    <div class="row" >
                                                        <div class="form-group col-lg-10">
                                                            <input
                                                                readOnly={hiddenEditInfo}
                                                                className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                                style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                                placeholder="Billing details"
                                                                type="text"
                                                                aria-describedby="basic-addon1"
                                                                onChange={(e) => setBillingDetails(e.target.value)}
                                                                value={billingDetails}
                                                            />
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>


                                        </td>
                                    </tr>
                                </tbody>
                            </table>



                        </form>
                    </div>

                </div>
            </div>


        </React.Fragment>
    );

}

export default AccommodationDetails;
