import { useContext } from "react";
import {  Modal } from "react-bootstrap";
import { modalConstants } from "../../constants/ModalConstants";
import { DataContext } from "../../contexts/DataContext";
import EditDataForm from "../EditDataForm";

const DataModal = () => {

	const { dataState, dispatch } = useContext(DataContext);

	const handleModalClose = () => {
		dispatch({ type: modalConstants.HIDE_EDIT_DATA_MODAL });
	};



	return (
		<Modal show={dataState.updateData.showModal} aria-labelledby="contained-modal-title-vcenter" class="modal-dialog modal-lg" centered onHide={handleModalClose}   size="lg">
			
			<Modal.Header closeButton>
				<Modal.Title id="contained-modal-title-vcenter">
					<big>Edit data</big>
				</Modal.Title>
			</Modal.Header>
			<Modal.Body>
				
				<EditDataForm />
			</Modal.Body>
			<Modal.Footer>
			</Modal.Footer>

		</Modal>
	);
};

export default DataModal;
