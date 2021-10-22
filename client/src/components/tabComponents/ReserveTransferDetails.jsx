import React, { Component, useState, useContext } from "react";
import { dataService } from "../../services/DataService";
import { DataContext } from "../../contexts/DataContext.jsx";
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import { modalConstants } from "../../constants/ModalConstants";

const ReserveTransferDetails = () => {
    
    
    const { dataState, dispatch } = useContext(DataContext);
    let transferData = dataState.reserveTransfer;
    const [transferDate, setTransferDate] = useState(transferData.transferDate);
    const [transferTime, setTransferTime] = useState(transferData.transferTime);
    const [transferCity, setTransferCity] = useState(transferData.transferCity);
    const [transferAddress, setTransferAddress] = useState(transferData.transferAddress);
    const [additionalComments, setAdditionalComments] = useState(transferData.additionalComments);
    const [open, setOpen] = React.useState(false);

    
    const handleChange = (data) => {
        
        let dto = [];
        let currentDate = new Date();
        let reservationDate = dataService.getValidDate(currentDate);
        let reservationTime = dataService.getTime(currentDate);
        for(let i = 0; i < transferData.length; i++){
            let reserveTransferRequest = {
                "id" : transferData[i].id,
                "currentDate" : reservationDate,
                "currentTime" : reservationTime,
                "transferDate" : transferDate,
                "transferTime" : transferTime,
                "transferCity" : transferCity,
                "transferAddress" : transferAddress,
                "additionalComments" : additionalComments,
            };
            dto.push(reserveTransferRequest);
        }
        
        let datesRequest = {
            startDate : "",
            endDate : "",
          };
        let retVal = dataService.reserveTransfer(datesRequest, dto, dispatch);
        if(retVal != null){
            setOpen(true);
          }
    }

    function Alert(props) {
        return <MuiAlert elevation={6} variant="filled" {...props} />;
    }
    
    const handleClose = (event, reason) => {
        if (reason   === 'clickaway') {
          return;
        }
        dispatch({ type: modalConstants.HIDE_RESERVE_TRANSFER_MODAL});
        setOpen(false);
      };

    

    return (
        <React.Fragment>
            <div className="container" >


                <div className="row mt-5">
                    <div className="col shadow p-3 bg-white rounded">

                        <form>

                            <table  style={{marginLeft :"4rem", marginBottom: "4rem"}}>
                                <td width="600rem"  >
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1 }}>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                <input
                                                    name = "date"
                                                    className = "form-control"
                                                    aria-describedby="basic-addon1"
                                                    style={ {backgroundColor: 'white', outline: 'none' }}
                                                    id="date"
                                                    label="Transfer Date"
                                                    type="date"
                                                    onChange={(e) => setTransferDate(e.target.value)}
                                                    value={transferDate}
                                                    sx={{ width: 320 }}
                                                    InputLabelProps={{
                                                    shrink: true,
                                                    }}
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
                                                        name = "city"
                                                        className = "form-control"
                                                        style={ {backgroundColor: 'white', outline: 'none' }}
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
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <label><b>Additional Comments</b></label>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                    <input
                                                        className="form-control"
                                                        style={ {backgroundColor: 'white', outline: 'none' }}
                                                        placeholder="Additional Comments"
                                                        type="text"
                                                        aria-describedby="basic-addon1"
                                                        onChange={(e) => setAdditionalComments(e.target.value)}
                                                        value={additionalComments}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="form-group text-center">
                                        <button
                                        style={{ background: "#1977cc", marginTop: "15px", marginLeft: "50px" }}
                                        onClick={(e) => handleChange(e)}
                                        className="btn btn-primary btn-xl"
                                        id="sendMessageButton"
                                        type="button"
                                    >
                                        Reserve
                                    </button>
                                    </div>
                                </td>
                                <td width="600rem">
                                    <div className="control-group">
                                        <div className="form-group controls mb-0 pb-2" style={{ color: "#6c757d", opacity: 1, marginTop: "2rem" }}>
                                            <div class="row" >
                                                <div class="form-group col-lg-10">
                                                <input
                                                    className="form-control"
                                                    aria-describedby="basic-addon1"
                                                    style={ {backgroundColor: 'white', outline: 'none' }}
                                                    id="time"
                                                    label="Transfer Time"
                                                    type="time"
                                                    onChange={(e) => setTransferTime(e.target.value)}
                                                    value={transferTime}
                                                    InputLabelProps={{
                                                    shrink: true,
                                                    }}
                                                    inputProps={{
                                                    step: 300,
                                                    }}
                                                    sx={{ width: 320 }}
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
                                                        name = "address"
                                                        className="form-control"
                                                        style={ {backgroundColor: 'white', outline: 'none' }}
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
                    Transfer is reserved!
                </Alert>
            </Snackbar>
            
        </React.Fragment>
    );

}

export default ReserveTransferDetails;