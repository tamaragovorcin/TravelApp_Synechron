import { useContext, useState, useRef,React } from "react";
import DatesSelector from "./DatesSelector";
import TravelFilters from "./TravelFilters";
import TravelBasicInfo from "./TravelBasicInfo";
import { DataContext } from "../../contexts/DataContext";
import { modalConstants } from "../../constants/ModalConstants";

const TravelInformation = () => {
    const [startDate, setStartDate] = useState(new Date());
	const [endDate, setEndDate] = useState(new Date());

    const { travelInfoState,dispatch } = useContext(DataContext);
    
	const handleShowModal = (data) => {
      		dispatch({ type: modalConstants.SHOW_EDIT_DATA_MODAL, data });
	};

    const childRef = useRef();
    const onChangeDates = () => {
        childRef.current.getTravelInfoBetwenDates()
    }

    const StartDateChange = (e) => {
        setStartDate(e.target.value)
       
    }

    const EndDateChange = (e) => {
        setEndDate(e.target.value)
       
    }
	return (
        <div class="row">
            <div class="col-lg-3 p-4">
                <TravelFilters 
                    startDate={startDate}
                    endDate={endDate}
                    travelInfoState = {travelInfoState}
                    dispatch = {dispatch}
                />
            </div>
            <div class="col p-4" >
                <DatesSelector 
                    startDate={startDate}
                    StartDateChange={StartDateChange}
                    endDate={endDate}
                    EndDateChange={EndDateChange}
                    onChangeDates = {onChangeDates}
                />  
                <TravelBasicInfo 
                    handleShowModal = {handleShowModal}
                    ref={childRef}
                    startDate={startDate}
                    endDate={endDate}
                    travelInfoState = {travelInfoState}
                    dispatch = {dispatch}
                />
            </div>
         </div>  
	);
};

export default TravelInformation;
