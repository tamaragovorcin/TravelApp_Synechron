import React, { Component, useState, useContext } from "react";
import { dataService } from "../../services/DataService.jsx";
import { DataContext } from "../../contexts/DataContext.jsx";
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import { modalConstants } from "../../constants/ModalConstants";


const TransferDetails = () => {
    
    const { dataState, dispatch } = useContext(DataContext);
    let transferData = dataState.updateData.data.travelDetails;
	const [departFrom, setDepartFrom] = useState(transferData.departFrom);
    const [arriveTo, setArriveTo] = useState(transferData.arriveTo);
    const [departureDate, setDepartureDate] = useState(transferData.departureDate);
    const [travelReqID, setTravelReqID] = useState(transferData.travelReqID);
    const [transferDate, setTransferDate] = useState(transferData.transferDate);
    const [transferTime, setTransferTime] = useState(transferData.transferTime);
    const [transferCity, setTransferCity] = useState(transferData.transferCity);
    const [transferAddress, setTransferAddress] = useState(transferData.transferAddress);
    const [employee, setEmployee] = useState(transferData.employee);
    const [arrivalTime, setArrivalTime] = useState(transferData.arrivalTime);
    const [arrivalDate, setArrivalDate] = useState(transferData.arrivalDate);
    const [departureTime, setDepartureTime] = useState(transferData.departureTime);
    const [departingCountry, setDepartingCountry] = useState(transferData.departingCountry);
    const [arrivalCountry, setArrivalCountry] = useState(transferData.arrivalCountry);
    const [additionalComments, setAdditionalComments] = useState(transferData.additionalComments);
    const [flightId, setFlightId] = useState(transferData.flightId);
    const [reservationDate, setReservationDate] = useState(transferData.reservationDate);
    const [reservationTime, setReservationTime] = useState(transferData.reservationTime);
    const [open, setOpen] = React.useState(false);
    function Alert(props) {
        return <MuiAlert elevation={6} variant="filled" {...props} />;
    }
    const [hiddenEditInfo, setHiddenEditInfo] = useState(true);
    
    const handleChange = (e) => {
        e.preventDefault();
        setHiddenEditInfo(false);
        let currentDate = new Date();
        let updateDate = dataService.getValidDate(currentDate);
        let updateTime = dataService.getTime(currentDate);
        let updateRequest = {
            "id" : transferData.id,
            "departFrom" : departFrom,
            "arriveTo" : arriveTo,
            "departureDate" : departureDate,
            "travelReqID" : travelReqID,
            "employee" : employee,
            "arrivalTime" : arrivalTime,
            "arrivalDate" : arrivalDate,
            "departureTime" : departureTime, 
            "departingCountry" : departingCountry,
            "arrivalCountry" : arrivalCountry,
            "additionalComments" : additionalComments,
            "flightId" : flightId, 
            "reservationDate" : reservationDate,
            "reservationTime" : reservationTime,
            "updateDate" : updateDate,
            "updateTime" : updateTime,
        };
        
        let datesRequest = {
            startDate : "",
            endDate : "",
          };
        dataService.updateTransferInfo(datesRequest, updateRequest, dispatch);
        setOpen(true);
    };
    const handleClose = (event, reason) => {
        if (reason   === 'clickaway') {
          return;
        }
        dispatch({ type: modalConstants.HIDE_EDIT_DATA_MODAL});
        setOpen(false);
      };

    return (
        <React.Fragment>

            <div className="container" >


                <div className="row mt-5">
                    <div className="col shadow p-3 bg-white rounded">

                        <form id="contactForm" name="sentMessage">

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
                                    style={{ background: "#1977cc", marginTop: "15px", marginLeft: "750px" }}
                                    onClick={(e) => handleChange(e)}
                                    className="btn btn-primary btn-xl"
                                    id="sendMessageButton"
                                    type="button"
                                >
                                    Change
                                </button>
                            </div>
                            <table  style={{marginLeft :"4rem", marginBottom: "4rem"}}>
                                <td width="600rem"  >
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                            <label><b>Depart from</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo  === false ? "form-control-plaintext" : "form-control"}
                                                        placeholder="Depart from"
                                                        aria-describedby="basic-addon1"
                                                        id="name"
                                                        type="text"
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                    
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
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
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
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Departure date"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setDepartureDate(e.target.value)}
                                                        value={departureDate}
                                                    />
                                                </div>
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
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
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
                                            <label><b>Employee ID</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
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
                                            <label><b>Additional comments</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
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
                                            <label><b>Flight id</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Flight id"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setFlightId(e.target.value)}
                                                        value={flightId}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Transfer Date</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Transfer Date"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setTransferDate(e.target.value)}
                                                        value={transferDate}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Transfer City</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Transfer City"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setTransferCity(e.target.value)}
                                                        value={transferCity}
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
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Arrival time"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setArrivalTime(e.target.value)}
                                                        value={arrivalTime}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Arrival date</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Arrival date"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setArrivalDate(e.target.value)}
                                                        value={arrivalDate}
                                                    />
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Departure time</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Departure time"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setDepartureTime(e.target.value)}
                                                        value={departureTime}
                                                    />
                                                </div>

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
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
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
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
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
                                            <label><b>Reservation date</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Reservation date"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setReservationDate(e.target.value)}
                                                        value={reservationDate}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Reservation time</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Reservation time"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setReservationTime(e.target.value)}
                                                        value={reservationTime}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Transfer time</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Transfer time"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setTransferTime(e.target.value)}
                                                        value={transferTime}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Transfer Address</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={hiddenEditInfo}
                                                        className={!hiddenEditInfo   === false ? "form-control-plaintext" : "form-control"}
                                                        style={ {backgroundColor: !hiddenEditInfo  === false ? '#DCDCDC' : 'white', outline: 'none' }}
                                                        placeholder="Transfer Address"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setTransferAddress(e.target.value)}
                                                        value={transferAddress}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
            <Snackbar open={open} autoHideDuration={2000} onClose={handleClose}>
                <Alert  severity="success">
                    Transfer is updated!
                </Alert>
                </Snackbar>
        </React.Fragment>
    );

}

export default TransferDetails;