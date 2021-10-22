import React, { useState } from "react";
import { dataService } from "../../services/DataService";

const TravelFilters = (props) => {
    const [departingCountry, setDepartingCountry] = useState("");
    const [arrivalCountry, setArrivalCountry] = useState("");
    const [employeeName, setEmployeeName] = useState("");
    const [employeeLastName, setEmployeeLastName] = useState("");

    
    const findTravelInfoByInputs = ()=> {
    
        const searchRequest = {
            departureDateString : props.startDate,
            arrivalDateString : props.endDate,
            employeeName,
            employeeLastName,
            departingCountry,
            arrivalCountry,
        }
        
        const getTravelBasicInfoHandler = async () => {
            await dataService.findAllTravelRequestsBySearch(searchRequest,props.dispatch);
          };
          getTravelBasicInfoHandler();
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
                                            <label><b>Departing from</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        className={"form-control" }
                                                        placeholder="Departing from"
                                                        aria-describedby="basic-addon1"
                                                        id="name"
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

                                    <div className="form-group text-center">
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <button
                                                        style={{ background: "#1977cc"}}
                                                        onClick={() => findTravelInfoByInputs()}
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

export default TravelFilters;