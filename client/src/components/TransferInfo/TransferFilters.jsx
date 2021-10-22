import React, {forwardRef, useState,useImperativeHandle } from "react";
import { dataService } from "../../services/DataService";

const TransferFilters = (props) => {
    const [departingFrom, setDeparteFrom] = useState("");
    const [arriveTo, setArriveTo] = useState("");
    const [employeeName, setEmployeeName] = useState("");
    const [employeeLastName, setEmployeeLastName] = useState("");
    const [departingCountry, setDepartingCountry] = useState("");
    const [arrivalCountry, setArrivalCountry] = useState("");
    const [reservationType, setReservationType] = useState("All");

    const searchTransfers = ()=> {
        const searchRequest = {
            departureDateString : dataService.getValidDate(props.startDate),
            arrivalDateString : dataService.getValidDate(props.endDate),
            employeeName,
            employeeLastName,
            departingFrom,
            arriveTo, 
            departingCountry,
            arrivalCountry, 
            reservationType
        }
        
        dataService.searchTransfer(searchRequest,props.dispatch);
    }

	return (
        <div>
            <div className="row mt-5">
                    <div className="col shadow p-3 bg-white rounded">

                        <form id="contactForm" name="sentMessage">

                            <table>
                            <tbody>
                                <tr>
                                <td width="600rem">
                                <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                            <label><b>Employee name</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                       
                                                        className={ "form-control"}
                                                        placeholder="Employee name"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setEmployeeName(e.target.value)}
                                                        value={employeeName}
                                                    />
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1}}>
                                            <label><b>Employee surname</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        className={"form-control" }
                                                        placeholder="Employee surname"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setEmployeeLastName(e.target.value)}
                                                        value={employeeLastName}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                            <label><b>Departing country</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        className={"form-control-plaintext" }
                                                        placeholder="Departing country"
                                                        aria-describedby="basic-addon1"
                                                        id="name"
                                                        className="form-control"
                                                        type="text"
                                                        onChange={(e) => setDepartingCountry(e.target.value)}
                                                        value={departingCountry}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                            <label><b>Arriving to</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                       
                                                        className={ "form-control"}
                                                        placeholder="Arriving to"
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
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                            <label><b>Departing city</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        className={"form-control-plaintext" }
                                                        placeholder="Departing city"
                                                        aria-describedby="basic-addon1"
                                                        id="name"
                                                        className="form-control"
                                                        type="text"
                                                        onChange={(e) => setDeparteFrom(e.target.value)}
                                                        value={departingFrom}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                            <label><b>Arrival city</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                       
                                                        className={ "form-control"}
                                                        placeholder="Arrival city"
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
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                            <label><b>Reservation type</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                <select className={ "form-control"}
                                                 aria-describedby="basic-addon1"
                                                 onChange = {(e) => setReservationType(e.target.value)}>
                                                    <option>All</option>
                                                    <option>Reserved</option>
                                                    <option>Nonreserved</option>
                                                </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div className="form-group text-center">
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <button
                                                        style={{ background: "#1977cc"}}
                                                        onClick={() => searchTransfers()}
                                                        className="btn btn-primary btn-xl"
                                                        id="sendMessageButton"
                                                        type="button"
                                                    >
                                                        Search
                                                    </button>
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
	);
};

export default TransferFilters;