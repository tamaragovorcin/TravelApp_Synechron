import React from "react";
import {  Modal, Button } from "react-bootstrap";
import { useContext } from "react";
import { fileConstants } from "../../constants/FileConstants";
import { FilesContext } from "../../contexts/FilesContext";


const ModalDialogInfo = () => {
    const { filesState, dispatch } = useContext(FilesContext);
    const handleModalClose = () => {
		dispatch({ type: fileConstants.HIDE_MODAL_INFO, files : filesState.listFiles.files});
	};


    return (
        <Modal
            show={filesState.listFiles.showModal}
            aria-labelledby="contained-modal-title-vcenter"
            centered
            class= ".modal-lg" 
            onHide={handleModalClose}
            >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    {filesState.listFiles.header}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
            <p>{filesState.listFiles.text}</p>
            </Modal.Body>
            <Modal.Footer>
				<Button onClick={handleModalClose}>Close</Button>
			</Modal.Footer>
        </Modal>
        );
        

}

export default ModalDialogInfo;
