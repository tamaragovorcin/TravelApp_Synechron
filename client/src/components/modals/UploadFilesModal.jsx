import { useContext } from "react";
import {  Modal, Button } from "react-bootstrap";
import { modalConstants } from "../../constants/ModalConstants";
import { DataContext } from "../../contexts/DataContext";
import FilesContextProvider from "../../contexts/FilesContext";
import FailureAlert from "../FailureAlert";
import SelectFilesForm from "../TravelInfo/SelectFilesForm";
import ModalDialogInfo from "./ModalDialogInfo";

const UploadFilesModal = () => {

    const { dataState, dispatch } = useContext(DataContext);

	const handleModalClose = () => {
		dispatch({ type: modalConstants.HIDE_UPLOAD_FILES_MODAL });
	};

	return (
		<Modal show={dataState.uploadFiles.showModal} aria-labelledby="contained-modal-title-vcenter" class= ".modal-lg" centered onHide={handleModalClose}>
			<Modal.Header closeButton>
				<Modal.Title id="contained-modal-title-vcenter">
					<big>Upload files - {dataState.uploadFiles.travelReqID}</big>
				</Modal.Title>
			</Modal.Header>
			<Modal.Body>
				<FailureAlert
					hidden={!dataState.uploadFiles.showErrorMessage}
					header="Error"
					message={dataState.uploadFiles.errorMessage}
					handleCloseAlert={() => dispatch({ type: modalConstants.FILE_UPLOAD_MODAL_HIDE_ERROR })}
				/>
				<FilesContextProvider>
					<SelectFilesForm 
						travelReqID = {dataState.uploadFiles.travelReqID}
					/>
					<ModalDialogInfo 
					/>
				</FilesContextProvider>
			</Modal.Body>
			<Modal.Footer>
				<Button onClick={handleModalClose}>Close</Button>
			</Modal.Footer>
		</Modal>
	);
};

export default UploadFilesModal;