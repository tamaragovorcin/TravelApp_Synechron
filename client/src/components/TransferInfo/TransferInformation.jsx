import DatesSelector from "../TravelInfo/DatesSelector";
import TransferBasicInfo from "./TransferBasicInfo";
import { useContext, useState } from "react";
import { modalConstants } from "../../constants/ModalConstants";
import { DataContext } from "../../contexts/DataContext";
import TransferFilters from "./TransferFilters";

const TransferInformation = () => {
    const [startDate, setStartDate] = useState(new Date());
	const [endDate, setEndDate] = useState(new Date());
    const onChangeDates = () => {
        
    }
    const { transferInfoState,dispatch } = useContext(DataContext);
    const handleShowModal = (data) => {
      
        console.log(data)

		dispatch({ type: modalConstants.SHOW_EDIT_DATA_MODAL, data });
	};

    const handleReserveModal = (data) => {
      
        console.log(data)

		dispatch({ type: modalConstants.SHOW_RESERVE_TRANSFER_MODAL, data });
	};

	return (
        <div class="row">
            <div class="col-lg-3 p-4">
                <TransferFilters 
                    startDate={startDate}
                    endDate={endDate}
                    travelInfoState = {transferInfoState}
                    dispatch = {dispatch}
                />
            </div>
            <div class="col p-4">
                <DatesSelector
                    startDate={startDate}
                    setStartDate={setStartDate}
                    endDate={endDate}
                    setEndDate={setEndDate}
                    onChangeDates = {onChangeDates}
                />  
                <TransferBasicInfo 
                    handleShowModal = {handleShowModal}
                    handleReserveModal = {handleReserveModal}
                    startDate={startDate}
                    endDate={endDate}
                />
            </div>
         </div>  
	);
};

export default TransferInformation;