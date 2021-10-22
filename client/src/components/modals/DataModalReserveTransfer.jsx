import { useContext } from "react";
import {  Modal } from "react-bootstrap";
import { modalConstants } from "../../constants/ModalConstants";
import { DataContext } from "../../contexts/DataContext";
import ReserveTransferForm from "../ReserveTransferForm";

const DataModalReserveTransfer = () => {

	const { dataState, dispatch } = useContext(DataContext);
	const handleModalClose = () => {
		dispatch({ type: modalConstants.HIDE_RESERVE_TRANSFER_MODAL });
	};

	return (
		<Modal show={dataState.reserveTransfer.showModal} aria-labelledby="contained-modal-title-vcenter" class="modal-dialog modal-lg" centered onHide={handleModalClose}   size="lg">
			
			<Modal.Header closeButton>
				<Modal.Title id="contained-modal-title-vcenter">
					<big>Reserve Transfer</big>
				</Modal.Title>
			</Modal.Header>
			<Modal.Body>
				
				<ReserveTransferForm />
			</Modal.Body>
			<Modal.Footer>
			</Modal.Footer>

		</Modal>
	);
};

export default DataModalReserveTransfer;