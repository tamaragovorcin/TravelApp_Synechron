import React, { useState, useContext } from "react";

import { DataContext } from "../../contexts/DataContext.jsx";

const AccommodationDetails = () => {
 


	const { dataState } = useContext(DataContext);

    const [employeeId, setEmployeeId] = useState(dataState.updateData.data.employeeDetails.employeeId);
    const [email, setEmail] = useState(dataState.updateData.data.employeeDetails.email);
    const [firstName, setFirstName] = useState(dataState.updateData.data.employeeDetails.firstName);
    const [lastName, setLastName] = useState(dataState.updateData.data.employeeDetails.lastName);
    const [designationName, setDesignationName] = useState(dataState.updateData.data.employeeDetails.designationName);



    return (
        <React.Fragment>

            <div className="container" >


                <div className="row mt-5">
                    <div className="col shadow p-3 bg-white rounded">

                        <form id="contactForm" name="sentMessage">

                      
                            <table  style={{marginLeft :"18rem", marginBottom: "4rem"}}>
                                <tbody>
                                    <tr>
                                <td width="600rem"  >
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                            <label><b>Employee ID</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={true}
                                                        className= "form-control-plaintext" 
                                                        placeholder="Employee ID"
                                                        aria-describedby="basic-addon1"
                                                        id="name"
                                                        type="text"
                                                        style={ {backgroundColor: '#DCDCDC' , outline: 'none' }}
                                                    
                                                        onChange={(e) => setEmployeeId(e.target.value)}
                                                        value={employeeId}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Email</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={true}
                                                        className="form-control-plaintext" 
                                                        style={ {backgroundColor: '#DCDCDC', outline: 'none' }}
                                                        placeholder="Email"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setEmail(e.target.value)}
                                                        value={email}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>First name</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={true}
                                                        className= "form-control-plaintext"
                                                        style={ {backgroundColor: '#DCDCDC', outline: 'none' }}
                                                        placeholder="First name"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setFirstName(e.target.value)}
                                                        value={firstName}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Last name</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={true}
                                                        className= "form-control-plaintext" 
                                                        style={ {backgroundColor: '#DCDCDC', outline: 'none' }}
                                                        placeholder="Last name"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setLastName(e.target.value)}
                                                        value={lastName}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Designation name</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        readOnly={true}
                                                        className= "form-control-plaintext"
                                                        style={ {backgroundColor: '#DCDCDC', outline: 'none' }}
                                                        placeholder="Designation name"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setDesignationName(e.target.value)}
                                                        value={designationName}
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
