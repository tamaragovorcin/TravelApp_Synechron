
import { useContext, React } from "react";
import { DataContext } from "../contexts/DataContext";
import { modalConstants } from "../constants/ModalConstants";
const HomePage = () => {
	const { dispatch } = useContext(DataContext);
	const handleShowModal = () => {
		dispatch({ type: modalConstants.SHOW_EDIT_DATA_MODAL });
	};


	return (
		<div>
		
				
				<button onClick={handleShowModal}> Show </button>
				
				
		</div>
	);
};

export default HomePage;
