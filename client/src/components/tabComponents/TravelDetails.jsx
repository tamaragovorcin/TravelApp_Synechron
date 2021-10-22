import React, { useState, useContext } from "react";
import { DataContext } from "../../contexts/DataContext.jsx";
import Datetime from 'react-datetime';
import { dataService } from "../../services/DataService";
const TravelDetails = () => {

    const { dataState, dispatch } = useContext(DataContext);
    const [departFrom, setDepartFrom] = useState(dataState.updateData.data.travelDetails.departFrom);
    const [arriveTo, setArriveTo] = useState(dataState.updateData.data.travelDetails.arriveTo);
    const [departureDateHelp, setDepartureDate] = useState(dataState.updateData.data.travelDetails.departureDate);
    const [travelReqID, setTravelReqID] = useState(dataState.updateData.data.travelDetails.travelReqID);
    const [accountName, setAccountName] = useState(dataState.updateData.data.travelDetails.accountName);
    const [approvedBy, setApprovedBy] = useState(dataState.updateData.data.travelDetails.approvedBy);
    const [employee, setEmployee] = useState(dataState.updateData.data.travelDetails.employee);
    const [visaDetail, setVisaDetail] = useState(dataState.updateData.data.travelDetails.visaDetail);
    const [tripPaidBy, setTripPaidBy] = useState(dataState.updateData.data.travelDetails.tripPaidBy);
    const [arrivalTimeHelp, setArrivalTime] = useState(dataState.updateData.data.travelDetails.arrivalTime);
    const [arrivalDateHelp, setArrivalDate] = useState(dataState.updateData.data.travelDetails.arrivalDate);
    const [departureTimeHelp, setDepartureTime] = useState(dataState.updateData.data.travelDetails.departureTime);
    const [departingCountry, setDepartingCountry] = useState(dataState.updateData.data.travelDetails.departingCountry);
    const [arrivalCountry, setArrivalCountry] = useState(dataState.updateData.data.travelDetails.arrivalCountry);
    const [additionalComments, setAdditionalComments] = useState(dataState.updateData.data.travelDetails.additionalComments);
    const [bookingPurpose, setBookingPurpose] = useState(dataState.updateData.data.travelDetails.bookingPurpose);
    const [partOfTheDayFromHelp, setPartOfTheDayFrom] = useState(dataState.updateData.data.travelDetails.partOfTheDayFrom);
    const [partOfTheDayToHelp, setPartOfTheDayTo] = useState(dataState.updateData.data.travelDetails.partOfTheDayTo);
    const [id] = useState(dataState.updateData.data.travelDetails.id);

    const [departureDateError, setDepartureDateError] = useState("none");
    const [arrivalDateError, setArrivalDateError] = useState("none");
    const [arrivalTimeError, setArrivalTimeError] = useState("none");
    const [departureTimeError, setDepartureTimeError] = useState("none");
    const [partOfTheDayFromError, setPartOfTheDayFromError] = useState("none");
    const [partOfTheDayToError, setPartOfTheDayToError] = useState("none");
    const [hiddenEditInfo, setHiddenEditInfo] = useState(true);

    const regexDate = /^([01][0-9])\/([0-3][0-9])\/([2][0][0-9][0-9])$/;
    const regexTime = /^([0-2][0-9]):([0-6][0-9])$/;
    let date = "";
    let dateArrival = "";

    if (departureDateHelp != null) {
        if (departureDateHelp[1] === "/" || departureDateHelp[2] === '/') {
            date = departureDateHelp

        }
        else if (regexDate.test(departureDateHelp.target.value) === true) {
            date = departureDateHelp.target.value
        }
    }


    var valid = function (current) {
        if (date != "") {
            return current.isAfter(Date.parse(date));
        } else {
            return true;
        }
    };

    if (arrivalDateHelp != null) {
        if (arrivalDateHelp[1] === "/" || arrivalDateHelp[2] === '/') {
            dateArrival = arrivalDateHelp

        }
        else if (regexDate.test(arrivalDateHelp.target.value) === true) {
            dateArrival = arrivalDateHelp.target.value
        }
    }

    var validDeparture = function (current) {
        if (dateArrival != "") {
            return current.isBefore(Date.parse(dateArrival));
        } else {
            return true;
        }
    };


    const handleChange = (e) => {

        setDepartureDateError("none")
        setArrivalDateError("none")
        setArrivalTimeError("none")
        setDepartureTimeError("none")
        setPartOfTheDayFromError("none")
        setPartOfTheDayToError("none")

        let departureDate = null;
        let arrivalDate = null;

        if (departureDateHelp != null) {
            if (departureDateHelp[1] === "/" || departureDateHelp[2] === '/') {
                departureDate = departureDateHelp

            }

            else if (regexDate.test(departureDateHelp.target.value) === true || departureDateHelp.target.value === "") {
                departureDate = departureDateHelp.target.value
            }
            else {
                setDepartureDateError("initial")
                return;
            }
        }
        if (arrivalDateHelp != null) {
            if (arrivalDateHelp[1] === '/' || arrivalDateHelp[2] === '/') {
                arrivalDate = arrivalDateHelp

            }
            else if (regexDate.test(arrivalDateHelp.target.value) === true || arrivalDateHelp.target.value === "") {
                arrivalDate = arrivalDateHelp.target.value
            }
            else {
                setArrivalDateError("initial")
                return;
            }

        }

        let arrivalTime = null;
        let departureTime = null;
        let partOfTheDayFrom = null;
        let partOfTheDayTo = null;


        if (arrivalTimeHelp != null) {
            if (arrivalTimeHelp[2] === ":") {
                arrivalTime = arrivalTimeHelp

            }
            else if (regexTime.test(arrivalTimeHelp.target.value) === true || arrivalTimeHelp.target.value === "") {
                arrivalTime = arrivalTimeHelp.target.value
            }
            else {
                setArrivalTimeError("initial")
                return;
            }
        }


        if (departureTimeHelp != null) {
            if (departureTimeHelp[2] === ":") {
                departureTime = departureTimeHelp

            }
            else if (regexTime.test(departureTimeHelp.target.value) === true || departureTimeHelp.target.value === "") {
                departureTime = departureTimeHelp.target.value
            }
            else {
                setDepartureTimeError("initial")
                return;
            }
        }

        if (partOfTheDayFromHelp != null) {
            if (partOfTheDayFromHelp[2] === ":") {
                partOfTheDayFrom = partOfTheDayFromHelp

            }
            else if (regexTime.test(partOfTheDayFromHelp.target.value) === true || partOfTheDayFromHelp.target.value === "") {
                partOfTheDayFrom = partOfTheDayFromHelp.target.value
            }
            else {
                setPartOfTheDayFromError("initial")
                return;
            }
        }

        if (partOfTheDayToHelp != null) {
            if (partOfTheDayToHelp[2] === ":") {
                partOfTheDayTo = partOfTheDayToHelp

            }
            else if (regexTime.test(partOfTheDayToHelp.target.value) === true || partOfTheDayToHelp.target.value === "") {
                partOfTheDayTo = partOfTheDayToHelp.target.value
            }
            else {
                setPartOfTheDayToError("initial")
                return;
            }
        }

        e.preventDefault();
        setHiddenEditInfo(false)
        let updateRequest = {
            id,
            departFrom,
            arriveTo,
            departureDate,
            travelReqID,
            accountName,
            approvedBy,
            employee,
            visaDetail,
            tripPaidBy,
            arrivalTime,
            arrivalDate,
            departureTime,
            departingCountry,
            arrivalCountry,
            additionalComments,
            bookingPurpose,
            partOfTheDayFrom,
            partOfTheDayTo
            
        };

        dataService.updateTravelRequestInfo(updateRequest, dispatch);
    };

    return (
        <React.Fragment>

            <div className="container" >


                <div className="row mt-5">
                    <div className="col shadow p-3 bg-white rounded">

                        <form id="contactForm" >

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
                            <table style={{ marginLeft: "4rem", marginBottom: "4rem" }}>
                                <td width="600rem"  >
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                            <label><b>Depart from</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        placeholder="Depart from"
                                                        aria-describedby="basic-addon1"
                                                        id="name"
                                                        type="text"
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}

                                                        onChange={(e) => setDepartFrom(e.target.value)}
                                                        value={departFrom}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Arrive to</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Arrive to"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setArriveTo(e.target.value)}
                                                        value={arriveTo}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Departure date</b></label>
                                            <div class="row" >

                                                <div class="form-group col-lg-10">
                                                    <Datetime timeFormat={false} isValidDate={validDeparture}
                                                        inputProps={{
                                                            disabled: hiddenEditInfo,
                                                            style: { backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' },
                                                            placeholder: departureDateHelp == null ? "Departure date" : departureDateHelp,
                                                            onClick: setDepartureDate
                                                        }}

                                                    />


                                                </div>
                                            </div>
                                            <div className="text-danger" style={{ display: departureDateError }}>
                                                Departure date must be entered in format MM/dd/YYYY
                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Travel request ID</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Travel request ID"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setTravelReqID(e.target.value)}
                                                        value={travelReqID}
                                                    />
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Account name</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Account name"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setAccountName(e.target.value)}
                                                        value={accountName}
                                                    />
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Approved by</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Approved by"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setApprovedBy(e.target.value)}
                                                        value={approvedBy}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Employee ID</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Employee ID"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setEmployee(e.target.value)}
                                                        value={employee.employeeId}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Visa details</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Visa details"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setVisaDetail(e.target.value)}
                                                        value={visaDetail}
                                                    />
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Trip paid by</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Trip paid by"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setTripPaidBy(e.target.value)}
                                                        value={tripPaidBy}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td width="600rem">
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Arrival time</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <Datetime dateFormat={false} timeFormat="HH:mm"
                                                        inputProps={{
                                                            disabled: hiddenEditInfo,
                                                            style: { backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' },
                                                            placeholder: arrivalTimeHelp == null ? "Arrival time" : arrivalTimeHelp,
                                                            onClick: setArrivalTime
                                                        }
                                                        }
                                                    />

                                                </div>

                                            </div>
                                            <div className="text-danger" style={{ display: arrivalTimeError }}>
                                                Arrival time must be entered in format HH:mm
                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Arrival date</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <Datetime timeFormat={false} isValidDate={valid}
                                                        inputProps={{
                                                            disabled: hiddenEditInfo,
                                                            style: { backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' },
                                                            placeholder: arrivalDateHelp == null ? "Arrival date" : arrivalDateHelp,
                                                            onClick: setArrivalDate
                                                        }}

                                                    />

                                                </div>
                                            </div>
                                            <div className="text-danger" style={{ display: arrivalDateError }}>
                                                Arrival date must be entered in format MM/dd/YYYY
                                            </div>
                                        </div>

                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Departure time</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <Datetime dateFormat={false} timeFormat="HH:mm"
                                                        inputProps={{
                                                            disabled: hiddenEditInfo,
                                                            style: { backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' },
                                                            placeholder: departureTimeHelp == null ? "Departure time" : departureTimeHelp,
                                                            onClick: setDepartureTime
                                                        }
                                                        }
                                                    />

                                                </div>

                                            </div>
                                            <div className="text-danger" style={{ display: departureTimeError }}>
                                                Departure time must be entered in format HH:mm
                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Departing country</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Departing country"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setDepartingCountry(e.target.value)}
                                                        value={departingCountry}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Arrival country</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Arrival country"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setArrivalCountry(e.target.value)}
                                                        value={arrivalCountry}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Additional comments</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo === false ? "form-control-plaintext" : "form-control"}
                                                        style={{ backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Additional comments"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setAdditionalComments(e.target.value)}
                                                        value={additionalComments}
                                                    />
                                                </div>

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
                                            <label><b>Part of the day from</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <Datetime dateFormat={false} timeFormat="HH:mm"
                                                        inputProps={{
                                                            disabled: hiddenEditInfo,
                                                            style: { backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' },
                                                            placeholder: partOfTheDayFromHelp == null ? "Part of the day from" : partOfTheDayFromHelp,
                                                            onClick: setPartOfTheDayFrom
                                                        }
                                                        }
                                                    />

                                                </div>

                                            </div>
                                            <div className="text-danger" style={{ display: partOfTheDayFromError }}>
                                                Part of the day from must be entered in format HH:mm
                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Part of the day to</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <Datetime dateFormat={false} timeFormat="HH:mm"
                                                        inputProps={{
                                                            disabled: hiddenEditInfo,
                                                            style: { backgroundColor: !hiddenEditInfo === false ? '#DCDCDC' : 'white', outline: 'none' },
                                                            placeholder: partOfTheDayToHelp == null ? "Part of the day to" : partOfTheDayToHelp,
                                                            onClick: setPartOfTheDayTo
                                                        }
                                                        }
                                                    />


                                                </div>

                                            </div>
                                            <div className="text-danger" style={{ display: partOfTheDayToError }}>
                                                Part of the day to must be entered in format HH:mm
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </table>



                        </form>
                    </div>

                </div>
            </div>


        </React.Fragment>
    );

}

export default TravelDetails;
